package com.java.odontovisionMVC.dto;

import java.io.Serializable;

/**
 * DTO (Data Transfer Object) para encapsular dados de endereço do usuário.
 * Com o uso de `record`, eliminamos boilerplate e garantimos imutabilidade.
 */
public record EnderecoUsuarioDto(
        Long id,
        String logradouro,
        String numero,
        String cidade,
        String estado,
        String cep,
        String complemento
) implements Serializable {

    /**
     * Método de fábrica estático para retornar uma instância vazia (útil para formulários).
     */
    public static EnderecoUsuarioDto vazio() {
        return new EnderecoUsuarioDto(null, "", "", "", "", "", "");
    }
}