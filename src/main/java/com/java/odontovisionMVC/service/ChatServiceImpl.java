package com.java.odontovisionMVC.service;

import com.java.odontovisionMVC.model.Usuario;
import com.java.odontovisionMVC.repository.DentistaRepository;
import com.java.odontovisionMVC.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final WebClient webClient;
    private final UsuarioRepository usuarioRepo;
    private final DentistaRepository dentistaRepo;
    private final String deploymentId;

    private volatile String contextoSistema = "Você é um assistente administrativo do OdontoVision.";

    public ChatServiceImpl(
            WebClient.Builder webClientBuilder,
            @Value("${spring.ai.azure.openai.endpoint}") String endpoint,
            @Value("${spring.ai.azure.openai.api-key}") String apiKey,
            @Value("${spring.ai.azure.openai.chat.options.deployment-name}") String deploymentId,
            UsuarioRepository usuarioRepo,
            DentistaRepository dentistaRepo
    ) {
        this.webClient = webClientBuilder
                .baseUrl(endpoint)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("api-key", apiKey)
                .build();
        this.deploymentId = deploymentId;
        this.usuarioRepo = usuarioRepo;
        this.dentistaRepo = dentistaRepo;
    }

    @Override
    public void atualizarContexto(String novoContexto) {
        this.contextoSistema = novoContexto;
    }

    private String getResumoPorEstado() {
        Map<String, Long> pacientesPorEstado = usuarioRepo.countPacientesPorEstadoRaw().stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> (Long) r[1]
                ));

        Map<String, Long> dentistasPorEstado = dentistaRepo.countDentistasPorEstadoRaw().stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> (Long) r[1]
                ));

        StringBuilder sb = new StringBuilder("Distribuição por estado:\n");
        pacientesPorEstado.forEach((estado, total) -> sb.append(String.format("• %s: %d pacientes\n", estado, total)));
        dentistasPorEstado.forEach((estado, total) -> sb.append(String.format("• %s: %d dentistas\n", estado, total)));

        return sb.toString();
    }

    private String getAniversariantesDoMes() {
        List<Usuario> aniversariantes = usuarioRepo.findByMesNascimento(LocalDate.now().getMonthValue());
        if (aniversariantes.isEmpty()) return "Nenhum aniversariante neste mês.\n";

        return aniversariantes.stream()
                .map(a -> String.format("• %s - %s", a.getNome(), a.getDataNascimento()))
                .collect(Collectors.joining("\n", "Aniversariantes do mês:\n", "\n"));
    }

    @Override
    public String perguntar(String mensagem) {
        long totalPacientes = usuarioRepo.count();
        long totalDentistas = dentistaRepo.count();
        long aniversariantes = usuarioRepo.countByMesNascimento(LocalDate.now().getMonthValue());

        String systemPrompt = String.format(
                "%s\n\nResumo do sistema:\n• %d pacientes\n• %d dentistas\n• %d aniversariantes este mês\n\n%s\n%s",
                contextoSistema,
                totalPacientes, totalDentistas, aniversariantes,
                getAniversariantesDoMes(),
                getResumoPorEstado()
        );

        Map<String, Object> payload = Map.of(
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", mensagem)
                ),
                "temperature", 0.7
        );

        try {
            Map<String, Object> response = webClient.post()
                    .uri(uri -> uri
                            .path("/openai/deployments/{deployment}/chat/completions")
                            .queryParam("api-version", "2023-07-01-preview")
                            .build(deploymentId))
                    .body(Mono.just(payload), new ParameterizedTypeReference<Object>() {})
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                    .block();

            if (response == null || !response.containsKey("choices")) return "Resposta inesperada do serviço.";

            List<?> choices = (List<?>) response.get("choices");
            if (choices.isEmpty()) return "Nenhuma resposta gerada.";

            Object messageObj = ((Map<?, ?>) choices.get(0)).get("message");
            if (!(messageObj instanceof Map)) return "Erro ao interpretar resposta.";

            Map<?, ?> msgMap = (Map<?, ?>) messageObj;
            Object conteudo = msgMap.get("content");
            return conteudo != null ? conteudo.toString() : "Sem conteúdo gerado.";

        } catch (Exception ex) {
            logger.error("Erro ao chamar Azure OpenAI", ex);
            return "Erro ao gerar resposta: " + ex.getMessage();
        }
    }
}
