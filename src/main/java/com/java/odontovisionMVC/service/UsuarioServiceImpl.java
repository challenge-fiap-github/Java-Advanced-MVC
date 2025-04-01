package com.java.odontovisionMVC.service;

import com.java.odontovisionMVC.dto.UsuarioDto;
import com.java.odontovisionMVC.mapper.UsuarioMapper;
import com.java.odontovisionMVC.model.Usuario;
import com.java.odontovisionMVC.model.EnderecoUsuario;
import com.java.odontovisionMVC.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação da interface UsuarioService.
 * Responsável por aplicar as regras de negócio relacionadas a usuários,
 * além de intermediar a comunicação entre controller e repositório.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Injeção de dependência via construtor, favorecendo testabilidade e clareza.
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Retorna uma lista de usuários convertidos para DTO.
     * Utiliza stream para mapear cada entidade para seu DTO correspondente.
     */
    @Override
    public List<UsuarioDto> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDto)
                .toList();
    }

    /**
     * Busca um usuário pelo ID, lançando exceção caso não seja encontrado.
     * Utiliza Optional para evitar NullPointerException.
     */
    @Override
    public UsuarioDto buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
    }

    /**
     * Salva (ou atualiza) um usuário.
     * Realiza mapeamento DTO → Entidade e garante o relacionamento bidirecional com EnderecoUsuario.
     */
    @Override
    public UsuarioDto salvar(UsuarioDto dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);

        // Relacionamento bidirecional: o endereço aponta para o usuário e vice-versa
        EnderecoUsuario endereco = usuario.getEndereco();
        if (endereco != null) {
            endereco.setUsuario(usuario);
        }

        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(salvo);
    }

    /**
     * Exclui um usuário pelo ID.
     * Antes de excluir, valida se o usuário existe para evitar erro silencioso.
     */
    @Override
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Não existe usuário com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
