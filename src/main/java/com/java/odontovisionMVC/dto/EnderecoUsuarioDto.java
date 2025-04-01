package com.java.odontovisionMVC.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO (Data Transfer Object) para encapsular dados de endereço do usuário.
 * Utilizado para transportar os dados da camada de apresentação até a lógica de negócio
 * sem expor a entidade JPA diretamente.
 */
public class EnderecoUsuarioDto implements Serializable {

    // Atributos imutáveis, tornando a classe mais segura e thread-safe
    private final Long id;
    private final String logradouro;
    private final String numero;
    private final String cidade;
    private final String estado;
    private final String cep;
    private final String complemento;

    /**
     * Construtor completo para inicializar todos os campos.
     * Usado pelo Mapper para converter Entity em DTO e vice-versa.
     */
    public EnderecoUsuarioDto(Long id, String logradouro, String numero, String cidade, String estado, String cep, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    // Getters públicos para acesso controlado aos campos
    public Long getId() { return id; }
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public String getComplemento() { return complemento; }

    /**
     * Implementação de equals baseada nos atributos.
     * Utilizado em comparações e testes de igualdade de objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnderecoUsuarioDto)) return false;
        EnderecoUsuarioDto that = (EnderecoUsuarioDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(logradouro, that.logradouro) &&
                Objects.equals(numero, that.numero) &&
                Objects.equals(cidade, that.cidade) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(cep, that.cep) &&
                Objects.equals(complemento, that.complemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logradouro, numero, cidade, estado, cep, complemento);
    }

    /**
     * Método auxiliar para facilitar logging e debugging.
     */
    @Override
    public String toString() {
        return "EnderecoUsuarioDto{" +
                "id=" + id +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
