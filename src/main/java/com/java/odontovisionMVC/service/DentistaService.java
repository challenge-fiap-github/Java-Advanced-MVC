package com.java.odontovisionMVC.service;

import com.java.odontovisionMVC.model.Dentista;
import com.java.odontovisionMVC.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    private DentistaRepository dentistaRepository;

    public List<Dentista> listarTodos() {
        return dentistaRepository.findAll();
    }

    public Dentista salvar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Long id) {
        return dentistaRepository.findById(id);
    }

    public void excluir(Long id) {
        dentistaRepository.deleteById(id);
    }
}
