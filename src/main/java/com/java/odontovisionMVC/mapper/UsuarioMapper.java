package com.java.odontovisionMVC.mapper;

import com.java.odontovisionMVC.dto.EnderecoUsuarioDto;
import com.java.odontovisionMVC.dto.UsuarioDto;
import com.java.odontovisionMVC.model.EnderecoUsuario;
import com.java.odontovisionMVC.model.Usuario;

/**
 * Classe utilitária responsável por converter entre as entidades Usuario e EnderecoUsuario
 * e seus respectivos DTOs. Essa separação garante que as camadas de visualização e serviço
 * lidem com objetos específicos e protegidos contra exposição de entidades diretamente.
 */
public class UsuarioMapper {

    /**
     * Converte uma entidade Usuario para um DTO (UsuarioDto).
     * Ideal para enviar dados à camada de apresentação sem expor a entidade JPA.
     */
    public static UsuarioDto toDto(Usuario entity) {
        if (entity == null) return null;

        EnderecoUsuario endereco = entity.getEndereco();
        EnderecoUsuarioDto enderecoDto = null;

        // Mapeia o endereço se estiver presente
        if (endereco != null) {
            enderecoDto = new EnderecoUsuarioDto(
                    endereco.getId(),
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getCidade(),
                    endereco.getEstado(),
                    endereco.getCep(),
                    endereco.getComplemento()
            );
        }

        // Retorna o DTO completamente populado
        return new UsuarioDto(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getSenha(),
                entity.getTelefone(),
                entity.getCpf(),
                entity.getDataNascimento(),
                enderecoDto
        );
    }

    /**
     * Converte um DTO (UsuarioDto) para a entidade Usuario.
     * Ideal para persistir dados vindos de formulários ou APIs.
     */
    public static Usuario toEntity(UsuarioDto dto) {
        if (dto == null) return null;

        // Constrói a entidade principal (sem o endereço inicialmente)
        Usuario usuario = new Usuario(
                dto.getId(),
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getTelefone(),
                dto.getCpf(),
                dto.getDataNascimento()
        );

        // Se houver endereço, monta e vincula ao usuário
        EnderecoUsuarioDto enderecoDto = dto.getEndereco();
        if (enderecoDto != null) {
            EnderecoUsuario endereco = new EnderecoUsuario(
                    enderecoDto.getId(),
                    enderecoDto.getLogradouro(),
                    enderecoDto.getNumero(),
                    enderecoDto.getCidade(),
                    enderecoDto.getEstado(),
                    enderecoDto.getCep(),
                    enderecoDto.getComplemento()
            );
            endereco.setUsuario(usuario); // Relacionamento bidirecional
            usuario.setEndereco(endereco);
        }

        return usuario;
    }
}
