package com.java.odontovisionMVC.repository;

import com.java.odontovisionMVC.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável pelo acesso a dados da entidade Dentista.
 * Estende JpaRepository, que fornece métodos prontos para operações de CRUD.
 */
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    // Nenhuma implementação adicional necessária por enquanto.
    // Métodos como save(), findById(), findAll(), deleteById() já estão disponíveis via JpaRepository.

    // Caso no futuro sejam necessárias queries personalizadas (ex: findByEmail), elas podem ser declaradas aqui.
}
