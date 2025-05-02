package com.java.odontovisionMVC.repository;

import com.java.odontovisionMVC.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

    Optional<Dentista> findByEmail(String email);

    Optional<Dentista> findByCro(String cro);

    List<Dentista> findByEspecialidade(String especialidade);

    List<Dentista> findByNomeContainingIgnoreCase(String fragmentoNome);

    @Query("SELECT ec.estado, COUNT(d.id) " +
            "FROM Dentista d " +
            "JOIN EnderecoClinica ec ON ec.dentista.id = d.id " +
            "GROUP BY ec.estado")
    List<Object[]> countDentistasPorEstadoRaw();
}
