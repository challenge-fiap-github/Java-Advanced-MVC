package com.java.odontovisionMVC.model;

import jakarta.persistence.*;

/**
 * Entidade JPA que representa o endereço de uma clínica odontológica.
 * Está associada diretamente a um dentista (relacionamento 1:1).
 */
@Entity
@Table(name = "endereco_clinica")
public class EnderecoClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relacionamento 1:1 com a entidade Dentista.
     * A coluna 'dentista_id' será usada como chave estrangeira.
     * A anotação 'nullable = false' garante integridade referencial no banco de dados.
     */
    @OneToOne
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

    // Atributos básicos do endereço da clínica
    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cep;

    private String complemento;

    /**
     * Construtor padrão necessário para JPA.
     */
    public EnderecoClinica() {}

    /**
     * Construtor completo com ID.
     * Esse construtor é utilizado em mapeamentos como o Mapper para facilitar a conversão de DTOs para entidades.
     */
    public EnderecoClinica(Long id, String logradouro, String numero, String cidade, String estado, String cep, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    // Getters
    public Long getId() { return id; }
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public String getComplemento() { return complemento; }
    public Dentista getDentista() { return dentista; }

    /**
     * Define o dentista vinculado a este endereço.
     * Utilizado para manter a integridade do relacionamento bidirecional.
     */
    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
}
