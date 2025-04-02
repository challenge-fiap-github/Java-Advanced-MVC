package com.java.odontovisionMVC.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para encapsular dados de endereço do usuário.
 * Com o uso de `record`, eliminamos boilerplate e garantimos imutabilidade.
 */
public record UsuarioDto(
        Long id,
        String nome,
        String email,
        String senha,
        String telefone,
        String cpf,
        LocalDate dataNascimento,
        EnderecoUsuarioDto endereco
) implements Serializable {
    /**
     * Método de fábrica estático para retornar uma instância vazia (útil para formulários).
     */
    public static UsuarioDto vazio() {
        return new UsuarioDto(null, "", "", "", "", "", null,
                new EnderecoUsuarioDto(null, "", "", "", "", "", ""));
    }
}