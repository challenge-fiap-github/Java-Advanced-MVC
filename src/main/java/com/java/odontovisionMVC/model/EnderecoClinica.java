package com.java.odontovisionMVC.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco_clinica")
public class EnderecoClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

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

    public EnderecoClinica() {}

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
    public Dentista getDentista() { return dentista; }
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public String getComplemento() { return complemento; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDentista(Dentista dentista) { this.dentista = dentista; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCep(String cep) { this.cep = cep; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
}
