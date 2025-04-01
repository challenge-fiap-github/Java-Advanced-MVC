package com.java.odontovisionMVC.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) para a entidade EnderecoClinica.
 * Essa classe é usada para transferir dados entre as camadas sem expor diretamente a entidade JPA.
 */
public class EnderecoClinicaDto implements Serializable {

    // Campos imutáveis para garantir integridade dos dados no DTO
    private final Long id;
    private final String logradouro;
    private final String numero;
    private final String cidade;
    private final String estado;
    private final String cep;
    private final String complemento;

    /**
     * Construtor completo, utilizado para criar instâncias de EnderecoClinicaDto com todos os dados.
     */
    public EnderecoClinicaDto(Long id, String logradouro, String numero, String cidade, String estado, String cep, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    /**
     * Método de fábrica para retornar uma instância "vazia" do DTO.
     * Útil para inicialização de formulários no front-end.
     */
    public static EnderecoClinicaDto vazio() {
        return new EnderecoClinicaDto(null, "", "", "", "", "", "");
    }

    // Getters para todos os atributos, conforme convenção de DTOs imutáveis
    public Long getId() { return id; }
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public String getComplemento() { return complemento; }

    /**
     * Sobrescreve equals e hashCode para comparação segura de objetos e uso em coleções.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnderecoClinicaDto)) return false;
        EnderecoClinicaDto that = (EnderecoClinicaDto) o;
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
     * Representação em string do objeto, útil para depuração.
     */
    @Override
    public String toString() {
        return "EnderecoClinicaDto{" +
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
