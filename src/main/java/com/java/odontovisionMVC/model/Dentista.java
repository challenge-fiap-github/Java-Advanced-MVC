package com.java.odontovisionMVC.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentista")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cro;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefone;
    private String especialidade;

    @OneToOne(mappedBy = "dentista", cascade = CascadeType.ALL, orphanRemoval = true)
    private EnderecoClinica endereco;

    // Getters e Setters
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

    public EnderecoClinica getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoClinica endereco) {
        this.endereco = endereco;
        if (endereco != null) {
            endereco.setDentista(this);
        }
    }
}
