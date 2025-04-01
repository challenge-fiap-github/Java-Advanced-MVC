package com.java.odontovisionMVC.service;

import com.java.odontovisionMVC.dto.UsuarioDto;
import java.util.List;

/**
 * Interface que define o contrato para operações de serviço relacionadas à entidade Usuario.
 * Abstrai a lógica de negócio da aplicação, garantindo uma separação clara entre controller e repositório.
 */

public interface UsuarioService {
    List<UsuarioDto> listarTodos();
    UsuarioDto buscarPorId(Long id);
    UsuarioDto salvar(UsuarioDto usuarioDto);
    void excluir(Long id);
}
