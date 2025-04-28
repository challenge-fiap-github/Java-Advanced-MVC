package com.java.odontovisionMVC.repository;

import com.java.odontovisionMVC.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório de dados para a entidade Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    /**
     * Conta quantos usuários têm aniversário no mês informado (1–12),
     * usando EXTRACT(MONTH FROM …) em query nativa Oracle.
     */
    @Query(
            value = """
        SELECT COUNT(*) 
          FROM usuario u 
         WHERE EXTRACT(MONTH FROM u.data_nascimento) = :mes
      """,
            nativeQuery = true
    )
    long countByMesNascimento(@Param("mes") int mes);
}
