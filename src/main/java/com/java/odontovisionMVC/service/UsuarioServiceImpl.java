package com.java.odontovisionMVC.service;

import com.java.odontovisionMVC.dto.UsuarioDto;
import com.java.odontovisionMVC.mapper.UsuarioMapper;
import com.java.odontovisionMVC.model.EnderecoUsuario;
import com.java.odontovisionMVC.model.Usuario;
import com.java.odontovisionMVC.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementação da interface UsuarioService.
 * Aplica regras de negócio relacionadas à entidade Usuario.
 */
@Service
class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Construtor com injeção de dependência recomendada (sem @Autowired).
     * Facilita testes e segue boas práticas.
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retorna todos os usuários cadastrados no banco, convertidos em DTOs.
     */
    @Override
    public List<UsuarioDto> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDto) // Conversão Entity → DTO
                .toList();
    }

    /**
     * Busca um usuário por ID. Se não encontrado, lança exceção com mensagem personalizada.
     */
    @Override
    public UsuarioDto buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
    }

    /**
     * Salva (ou atualiza) um usuário.
     * Se for novo, gera uma senha aleatória e criptografa com BCrypt.
     * Em edição, mantém a senha atual.
     */
    @Override
    public UsuarioDto salvar(UsuarioDto dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto); // DTO → Entity

        // Se o usuário for novo (sem ID), gerar e codificar senha aleatória
        if (usuario.getId() == null) {
            String senhaGerada = UUID.randomUUID().toString().substring(0, 8); // Gera senha com 8 caracteres
            usuario.setSenha(passwordEncoder.encode(senhaGerada));

            // Apenas para testes/demonstração. Ideal: enviar essa senha por e-mail.
            System.out.println("Senha gerada para o novo usuário: " + senhaGerada);
        } else {
            // Em edição, manter a senha atual (não sobrescrever com null ou string vazia)
            Usuario existente = usuarioRepository.findById(usuario.getId()).orElseThrow();
            usuario.setSenha(existente.getSenha());
        }

        // Garante que o endereço conheça o usuário (relacionamento bidirecional)
        EnderecoUsuario endereco = usuario.getEndereco();
        if (endereco != null) {
            endereco.setUsuario(usuario);
        }

        // Persiste no banco e retorna como DTO
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(salvo);
    }

    /**
     * Remove um usuário do banco de dados após verificar se ele existe.
     */
    @Override
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Não existe usuário com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
