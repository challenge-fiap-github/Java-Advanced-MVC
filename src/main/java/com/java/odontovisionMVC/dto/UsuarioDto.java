package com.java.odontovisionMVC.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO (Data Transfer Object) que representa a entidade Usuario de forma isolada para transferência de dados
 * entre as camadas da aplicação. Ele evita o acoplamento direto com a entidade JPA.
 */
public class UsuarioDto implements Serializable {

    // Atributos imutáveis: tornam a classe segura para múltiplos usos
    private final Long id;
    private final String nome;
    private final String email;
    private final String senha;
    private final String telefone;
    private final String cpf;
    private final LocalDate dataNascimento;
    private final EnderecoUsuarioDto endereco;

    /**
     * Construtor completo que inicializa todos os atributos.
     * Utilizado principalmente no mapeamento de Entity para DTO.
     */
    public UsuarioDto(Long id, String nome, String email, String senha, String telefone, String cpf, LocalDate dataNascimento, EnderecoUsuarioDto endereco) {
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
     * Método de fábrica estático que cria uma instância vazia do DTO.
     * Facilita a inicialização nos formulários Thymeleaf.
     */
    public static UsuarioDto vazio() {
        return new UsuarioDto(null, "", "", "", "", "", null,
                new EnderecoUsuarioDto(null, "", "", "", "", "", ""));
    }

    // Getters públicos para acesso imutável aos dados (não há setters)
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public EnderecoUsuarioDto getEndereco() { return endereco; }

    /**
     * Implementações de equals e hashCode garantem a correta comparação entre objetos,
     * principalmente quando usados em coleções.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioDto)) return false;
        UsuarioDto that = (UsuarioDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(email, that.email) &&
                Objects.equals(senha, that.senha) &&
                Objects.equals(telefone, that.telefone) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(dataNascimento, that.dataNascimento) &&
                Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, telefone, cpf, dataNascimento, endereco);
    }

    /**
     * toString simplificado para ajudar no debug e logging.
     */
    @Override
    public String toString() {
        return "UsuarioDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", endereco=" + endereco +
                '}';
    }
}
