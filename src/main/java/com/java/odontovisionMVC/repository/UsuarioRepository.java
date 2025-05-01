package com.java.odontovisionMVC.repository;

import com.java.odontovisionMVC.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query(
            value = """
            SELECT COUNT(*) 
              FROM usuario u 
             WHERE EXTRACT(MONTH FROM u.data_nascimento) = :mes
        """,
            nativeQuery = true
    )
    long countByMesNascimento(@Param("mes") int mes);

    @Query("""
        SELECT eu.estado, COUNT(u.id)
          FROM Usuario u
          JOIN EnderecoUsuario eu ON eu.usuario.id = u.id
      GROUP BY eu.estado
    """)
    Map<String, Long> countPacientesPorEstado();

    @Query("""
        SELECT u
          FROM Usuario u
          JOIN EnderecoUsuario eu ON eu.usuario.id = u.id
         WHERE FUNCTION('MONTH', u.dataNascimento) = :mes
    """)
    List<Usuario> findByMesNascimento(@Param("mes") int mes);
}
