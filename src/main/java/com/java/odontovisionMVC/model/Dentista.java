package com.java.odontovisionMVC.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentista") // Define que esta entidade será mapeada para a tabela "dentista"
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Long id;

    @Column(nullable = false, unique = true) // CRO não pode ser nulo e deve ser único
    private String cro;

    @Column(nullable = false) // Nome é obrigatório
    private String nome;

    @Column(nullable = false, unique = true) // Email é obrigatório e único
    private String email;

    // Telefone e especialidade são campos opcionais
    private String telefone;
    private String especialidade;

    @OneToOne(mappedBy = "dentista", cascade = CascadeType.ALL, orphanRemoval = true)
    private EnderecoClinica endereco;
    // Mapeamento bidirecional com EnderecoClinica
    // Cascade.ALL garante que ao salvar/deletar o dentista, o endereço também será salvo/deletado
    // orphanRemoval = true garante que ao remover o vínculo do endereço, ele será removido do banco

    // Construtor com todos os atributos (exceto o endereço, que é associado separadamente)
    public Dentista(Long id, String cro, String nome, String email, String telefone, String especialidade) {
        this.id = id;
        this.cro = cro;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public Dentista() {
        // Construtor padrão necessário para o JPA instanciar a entidade automaticamente
    }

    public void setEndereco(EnderecoClinica endereco) {
        this.endereco = endereco;
        if (endereco != null) {
            endereco.setDentista(this);
        }
    }
    // Esse método garante a consistência do relacionamento bidirecional:
    // Quando um endereço é setado, ele também recebe a referência do dentista atual

    // Getters e setters comuns para acesso e modificação dos dados da entidade

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCro() { return cro; }
    public void setCro(String cro) { this.cro = cro; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public EnderecoClinica getEndereco() { return endereco; }
}
