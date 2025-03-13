package com.java.odontovisionMVC.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentista")
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String cro;  // Registro no Conselho Regional de Odontologia

    private String especialidade;

    @Column(length = 15)
    private String telefone;

    @Column(unique = true, length = 100)
    private String email;

    @OneToOne(mappedBy = "dentista", cascade = CascadeType.ALL)
    private EnderecoClinica endereco;

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

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoClinica getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoClinica endereco) {
        this.endereco = endereco;
    }
}
