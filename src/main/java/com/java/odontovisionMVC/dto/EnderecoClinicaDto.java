package com.java.odontovisionMVC.dto;

import java.io.Serializable;

/**
 * DTO (Data Transfer Object) para a entidade EnderecoClinica, agora usando `record`.
 * Garante imutabilidade, reduz boilerplate e melhora a legibilidade.
 */
public record EnderecoClinicaDto(
        Long id,
        String logradouro,
        String numero,
        String cidade,
        String estado,
        String cep,
        String complemento
) implements Serializable {

    /**
     * Método de fábrica para criar uma instância "vazia", útil para inicialização em formulários.
     */
    public static EnderecoClinicaDto vazio() {
        return new EnderecoClinicaDto(null, "", "", "", "", "", "");
    }
}