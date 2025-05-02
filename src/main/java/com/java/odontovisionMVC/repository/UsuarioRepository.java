package com.java.odontovisionMVC.repository;

import com.java.odontovisionMVC.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    // Query nativa para contar usuários por mês de nascimento
    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM usuario " +
                    "WHERE TO_CHAR(data_nascimento, 'MM') = LPAD(:mes, 2, '0')",
            nativeQuery = true
    )
    long countByMesNascimento(@Param("mes") int mes);

    // JPQL para agrupar usuários por estado
    @Query("SELECT eu.estado, COUNT(u.id) " +
            "FROM Usuario u " +
            "JOIN EnderecoUsuario eu ON eu.usuario.id = u.id " +
            "GROUP BY eu.estado")
    List<Object[]> countPacientesPorEstadoRaw();

    // Query nativa para retornar usuários com base no mês de nascimento
    @Query(
            value = "SELECT u.* " +
                    "FROM usuario u " +
                    "JOIN endereco_usuario eu ON eu.usuario_id = u.id " +
                    "WHERE TO_CHAR(u.data_nascimento, 'MM') = LPAD(:mes, 2, '0')",
            nativeQuery = true
    )
    List<Usuario> findByMesNascimento(@Param("mes") int mes);
}
