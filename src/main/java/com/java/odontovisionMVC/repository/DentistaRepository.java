package com.java.odontovisionMVC.repository;

import com.java.odontovisionMVC.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso a dados da entidade Dentista.
 * Estende JpaRepository, que já traz os métodos CRUD básicos.
 * Aqui definimos apenas queries compatíveis com os campos atuais da entidade.
 */
@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

    /**
     * Busca um dentista pelo e-mail (para evitar duplicatas).
     */
    Optional<Dentista> findByEmail(String email);

    /**
     * Busca um dentista pelo CRO (Registro no Conselho Regional de Odontologia).
     */
    Optional<Dentista> findByCro(String cro);

    /**
     * Busca todos os dentistas cuja especialidade seja exatamente a passada.
     */
    List<Dentista> findByEspecialidade(String especialidade);

    /**
     * Busca dentistas cujo nome contenha a string passada (case-insensitive).
     */
    List<Dentista> findByNomeContainingIgnoreCase(String fragmentoNome);
}
