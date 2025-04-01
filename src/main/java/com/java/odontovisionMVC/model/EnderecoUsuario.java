package com.java.odontovisionMVC.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco_usuario")
public class EnderecoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associação bidirecional com Usuario
    // Um endereço pertence a exatamente um usuário
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Chave estrangeira obrigatória
    private Usuario usuario;

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

    private String complemento; // Campo opcional

    // Construtor padrão obrigatório para JPA
    public EnderecoUsuario() {}

    // Construtor com argumentos, utilizado pelo Mapper e testes
    public EnderecoUsuario(Long id, String logradouro, String numero, String cidade, String estado, String cep, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    // Getters (acessores)
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public String getComplemento() { return complemento; }

    // Setters (modificadores)
    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCep(String cep) { this.cep = cep; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
}
