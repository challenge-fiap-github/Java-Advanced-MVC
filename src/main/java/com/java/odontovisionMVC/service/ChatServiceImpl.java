package com.java.odontovisionMVC.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.java.odontovisionMVC.repository.UsuarioRepository;
import com.java.odontovisionMVC.repository.DentistaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    private final WebClient webClient;
    private final UsuarioRepository usuarioRepo;
    private final DentistaRepository dentistaRepo;
    private final String deploymentId;

    public ChatServiceImpl(
            WebClient.Builder webClientBuilder,
            @Value("${spring.ai.azure.openai.endpoint}") String endpoint,
            @Value("${spring.ai.azure.openai.api-key}") String apiKey,
            @Value("${azure.openai.deployment}") String deploymentId,
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
    public String perguntar(String mensagem) {
        // 1) Coleta estatísticas
        long totalPacientes = usuarioRepo.count();
        long totalDentistas = dentistaRepo.count();
        int mesAtual = LocalDate.now().getMonthValue();
        // chama o método correto do repositório
        long aniversariantes = usuarioRepo.countByMesNascimento(mesAtual);

        // 2) Monta o contexto de sistema
        String sistema = String.format(
                "Você é um assistente administrativo do OdontoVision.%n" +
                        "Atualmente temos:%n" +
                        "  • %d pacientes cadastrados%n" +
                        "  • %d dentistas cadastrados%n" +
                        "  • %d pacientes fazem aniversário este mês%n",
                totalPacientes, totalDentistas, aniversariantes
        );

        // 3) Prepara payload para o Azure OpenAI
        Map<String, Object> payload = Map.of(
                "messages", List.of(
                        Map.of("role", "system", "content", sistema),
                        Map.of("role", "user", "content", mensagem)
                ),
                "temperature", 0.7
        );

        // 4) Chama o endpoint de chat
        Map<String, Object> response;
        try {
            response = webClient.post()
                    .uri(uri -> uri
                            .path("/openai/deployments/{deployment}/chat/completions")
                            .queryParam("api-version", "2023-07-01-preview")
                            .build(deploymentId))
                    .body(Mono.just(payload), new ParameterizedTypeReference<>() {
                    })
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .block();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Erro ao gerar resposta: " + ex.getMessage();
        }


        // 5) Extrai a resposta com segurança
        if (response == null || !response.containsKey("choices")) {
            return "Resposta inesperada do serviço.";
        }
        var choices = (List<?>) response.get("choices");
        if (choices.isEmpty()) {
            return "Nenhuma resposta gerada.";
        }
        var first = choices.get(0);
        if (!(first instanceof Map<?, ?>)) {
            return first.toString();
        }
        var messageObj = ((Map<?, ?>) first).get("message");
        if (!(messageObj instanceof Map<?, ?>)) {
            return messageObj != null ? messageObj.toString() : "";
        }
        var content = ((Map<?, ?>) messageObj).get("content");
        return content != null ? content.toString() : "";
    }
}
