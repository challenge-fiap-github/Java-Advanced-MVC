package com.java.odontovisionMVC.repository;

import com.java.odontovisionMVC.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface que representa o repositório de dados para a entidade Usuario.
 * Extende JpaRepository, o que fornece implementações automáticas de operações CRUD básicas.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método de consulta derivada fornecido pelo Spring Data JPA.
     * Busca um usuário pelo seu e-mail.
     *
     * O Spring cria a implementação automaticamente com base na assinatura do método.
     * Retorna um Optional, o que evita NullPointerException e melhora a legibilidade ao tratar ausências.
     */
    Optional<Usuario> findByEmail(String email);
}
