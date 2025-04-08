package com.java.odontovisionMVC.dto;

import java.io.Serializable;

/**
 * DTO (Data Transfer Object) para encapsular dados de endereço do usuário.
 * Classe tradicional utilizada para maior flexibilidade com frameworks como Spring MVC.
 */
public class EnderecoUsuarioDto implements Serializable {

    private Long id;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    /**
     * Construtor padrão (necessário para frameworks como Spring e ferramentas de serialização).
     */
    public EnderecoUsuarioDto() {
    }

    /**
     * Construtor completo.
     */
    public EnderecoUsuarioDto(Long id, String logradouro, String numero, String cidade,
                              String estado, String cep, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    /**
     * Método de fábrica para instanciar um DTO vazio.
     */
    public static EnderecoUsuarioDto vazio() {
        return new EnderecoUsuarioDto(null, "", "", "", "", "", "");
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}