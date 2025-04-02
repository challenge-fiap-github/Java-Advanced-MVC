package com.java.odontovisionMVC.dto;

import java.io.Serializable;
/**
 * DTO (Data Transfer Object) para a entidade Dentista usando `record`.
 * Utilizado para transferir dados entre as camadas (Controller ⇄ Service ⇄ View)
 * desacoplando a entidade JPA da lógica de apresentação.
 */
public record DentistaDto(
        Long id,
        String cro,
        String nome,
        String email,
        String telefone,
        String especialidade,
        EnderecoClinicaDto endereco
) implements Serializable {

    /**
     * Método de fábrica para criar um DTO vazio.
     * Útil para inicializar formulários no front-end.
     */
    public static DentistaDto vazio() {
        return new DentistaDto(null, null, null, null, null, null,
                new EnderecoClinicaDto(null, null, null, null, null, null, null));
    }
}