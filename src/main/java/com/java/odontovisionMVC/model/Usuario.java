package com.java.odontovisionMVC.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de ID autoincremento
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true) // Garante que não haja e-mails duplicados
    private String email;

    @Column(nullable = false)
    private String senha;

    private String telefone;
    private String cpf;

    // Formatação da data no padrão ISO para facilitar manipulação em formulários
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    // Relacionamento um-para-um com EnderecoUsuario
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private EnderecoUsuario endereco;

    // Construtor padrão exigido pelo JPA
    public Usuario() {}

    // Construtor completo utilizado para mapeamento manual ou testes
    public Usuario(Long id, String nome, String email, String senha, String telefone, String cpf, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Garante o vínculo bidirecional entre Usuario e EnderecoUsuario
    public void setEndereco(EnderecoUsuario endereco) {
        this.endereco = endereco;
        if (endereco != null) {
            endereco.setUsuario(this);
        }
    }

    // Getters (acessores)
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public EnderecoUsuario getEndereco() { return endereco; }

    // Setters (modificadores)
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}
