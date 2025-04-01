package com.java.odontovisionMVC.mapper;

import com.java.odontovisionMVC.dto.DentistaDto;
import com.java.odontovisionMVC.dto.EnderecoClinicaDto;
import com.java.odontovisionMVC.model.Dentista;
import com.java.odontovisionMVC.model.EnderecoClinica;

/**
 * Classe responsável por converter entre Dentista <-> DentistaDto.
 * Separa a camada de persistência (entidades JPA) da camada de apresentação (DTOs).
 */
public class DentistaMapper {

    /**
     * Converte uma entidade JPA Dentista para seu DTO equivalente.
     * Usado, por exemplo, para retornar dados do banco ao front-end ou API.
     */
    public static DentistaDto toDto(Dentista entity) {
        if (entity == null) return null;

        // Conversão manual do endereço
        EnderecoClinica endereco = entity.getEndereco();
        EnderecoClinicaDto enderecoDto = null;

        if (endereco != null) {
            enderecoDto = new EnderecoClinicaDto(
                    endereco.getId(),
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getCidade(),
                    endereco.getEstado(),
                    endereco.getCep(),
                    endereco.getComplemento()
            );
        }

        // Retorna um DTO completo de Dentista, com DTO de endereço embutido
        return new DentistaDto(
                entity.getId(),
                entity.getCro(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getEspecialidade(),
                enderecoDto
        );
    }

    /**
     * Converte um DentistaDto para a entidade Dentista.
     * Usado, por exemplo, ao salvar/atualizar dados vindos de formulários ou requisições.
     */
    public static Dentista toEntity(DentistaDto dto) {
        if (dto == null) return null;

        // Constrói a entidade principal
        Dentista dentista = new Dentista(
                dto.getId(),
                dto.getCro(),
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getEspecialidade()
        );

        // Conversão manual do endereço do DTO para entidade
        EnderecoClinicaDto enderecoDto = dto.getEndereco();
        if (enderecoDto != null) {
            EnderecoClinica endereco = new EnderecoClinica(
                    enderecoDto.getId(),
                    enderecoDto.getLogradouro(),
                    enderecoDto.getNumero(),
                    enderecoDto.getCidade(),
                    enderecoDto.getEstado(),
                    enderecoDto.getCep(),
                    enderecoDto.getComplemento()
            );

            // Estabelece o relacionamento bidirecional
            endereco.setDentista(dentista);
            dentista.setEndereco(endereco);
        }

        return dentista;
    }
}
