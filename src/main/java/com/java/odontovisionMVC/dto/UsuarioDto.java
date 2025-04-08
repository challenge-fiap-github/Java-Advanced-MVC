package com.java.odontovisionMVC.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para encapsular dados do usuário.
 * Classe tradicional que oferece melhor suporte para frameworks e formulários HTML.
 */
public class UsuarioDto implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;
    private EnderecoUsuarioDto endereco;

    /**
     * Construtor vazio para frameworks como Spring.
     */
    public UsuarioDto() {
    }

    /**
     * Construtor completo para uso programático.
     */
    public UsuarioDto(Long id, String nome, String email, String senha, String telefone,
                      String cpf, LocalDate dataNascimento, EnderecoUsuarioDto endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    /**
     * Método de fábrica para instanciar um DTO vazio.
     */
    public static UsuarioDto vazio() {
        return new UsuarioDto(null, "", "", "", "", "", null,
                new EnderecoUsuarioDto(null, "", "", "", "", "", ""));
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EnderecoUsuarioDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoUsuarioDto endereco) {
        this.endereco = endereco;
    }
}