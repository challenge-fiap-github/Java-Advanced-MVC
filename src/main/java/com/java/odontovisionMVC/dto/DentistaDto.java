package com.java.odontovisionMVC.dto;

import java.io.Serializable;

/**
 * DTO (Data Transfer Object) para transferir dados da entidade Dentista.
 * Esta classe é usada para comunicação entre camadas sem expor diretamente a entidade JPA.
 * Segue boas práticas como encapsulamento, construtor vazio, e métodos getters/setters.
 */
public class DentistaDto implements Serializable {

    private Long id;
    private String cro;
    private String nome;
    private String email;
    private String telefone;
    private String especialidade;
    private EnderecoClinicaDto endereco;

    /**
     * Construtor vazio necessário para frameworks como Spring MVC e Jackson (serialização).
     */
    public DentistaDto() {
        this.endereco = new EnderecoClinicaDto(); // Inicializa o endereço para evitar NullPointerException
    }

    /**
     * Construtor completo usado quando todos os dados estão disponíveis.
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

    /**
     * Método de fábrica para criação de DTO vazio (conveniente para formulários).
     */
    public static DentistaDto vazio() {
        return new DentistaDto();
    }

    // Getters e Setters (seguindo convenção JavaBeans)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public EnderecoClinicaDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoClinicaDto endereco) {
        this.endereco = endereco;
    }
}