package com.java.odontovisionMVC.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.java.odontovisionMVC.model.Dentista}
 *
 * Esta classe representa o DTO (Data Transfer Object) de Dentista.
 * É utilizada para transportar dados entre as camadas da aplicação
 * (Controller ⇄ Service ⇄ View), desacoplando a entidade JPA da API ou UI.
 */
public class DentistaDto implements Serializable {

    // Campos imutáveis que representam os dados do dentista
    private final Long id;
    private final String cro;
    private final String nome;
    private final String email;
    private final String telefone;
    private final String especialidade;
    private final EnderecoClinicaDto endereco;

    /**
     * Construtor completo (todos os campos obrigatórios para criação do DTO)
     * Seguindo o princípio de imutabilidade.
     */
    public DentistaDto(Long id, String cro, String nome, String email, String telefone, String especialidade, EnderecoClinicaDto endereco) {
        this.id = id;
        this.cro = cro;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    // Getters (não há setters para manter o DTO imutável)
    public Long getId() {
        return id;
    }

    public String getCro() {
        return cro;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public EnderecoClinicaDto getEndereco() {
        return endereco;
    }

    /**
     * Fábrica estática para criar um DTO "vazio"
     * Esse método é usado para preencher formulários no Controller com valores nulos,
     * evitando erros de binding no Thymeleaf.
     */
    public static DentistaDto vazio() {
        return new DentistaDto(null, null, null, null, null, null,
                new EnderecoClinicaDto(null, null, null, null, null, null, null));
    }

    // equals e hashCode para comparação entre instâncias
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DentistaDto)) return false;
        DentistaDto that = (DentistaDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cro, that.cro) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telefone, that.telefone) &&
                Objects.equals(especialidade, that.especialidade) &&
                Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cro, nome, email, telefone, especialidade, endereco);
    }

    // toString com todos os campos para fácil debug e logging
    @Override
    public String toString() {
        return "DentistaDto(" +
                "id=" + id +
                ", cro='" + cro + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", endereco=" + endereco +
                ')';
    }
}
