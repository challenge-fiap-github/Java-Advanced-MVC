package com.java.odontovisionMVC.service;

import com.java.odontovisionMVC.dto.DentistaDto;
import java.util.List;

/**
 * Interface que define o contrato da camada de serviço para operações relacionadas a Dentistas.
 * Segue o princípio de abstração, desacoplando a lógica de negócio da implementação específica.
 */
public interface DentistaService {

    List<DentistaDto> listarTodos();

    DentistaDto salvar(DentistaDto dentistaDto);

    DentistaDto buscarPorId(Long id);

    void excluir(Long id);
}
