package com.java.odontovisionMVC.service;

import com.java.odontovisionMVC.dto.DentistaDto;
import com.java.odontovisionMVC.mapper.DentistaMapper;
import com.java.odontovisionMVC.model.Dentista;
import com.java.odontovisionMVC.model.EnderecoClinica;
import com.java.odontovisionMVC.repository.DentistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação da interface DentistaService.
 * Essa classe realiza a lógica de negócio relacionada à entidade Dentista,
 * interagindo com o repositório e utilizando o mapper para conversão entre DTOs e entidades.
 */
@Service
class DentistaServiceImpl implements DentistaService {

    private final DentistaRepository dentistaRepository;

    // Injeção de dependência via construtor (boa prática para facilitar testes e imutabilidade)
    public DentistaServiceImpl(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    /**
     * Retorna uma lista de todos os dentistas, convertendo de entidade para DTO.
     */
    @Override
    public List<DentistaDto> listarTodos() {
        return dentistaRepository.findAll()
                .stream()
                .map(DentistaMapper::toDto)
                .toList(); // converte a lista de entidades em lista de DTOs
    }

    /**
     * Busca um dentista por ID e converte para DTO.
     * Lança exceção se o dentista não for encontrado.
     */
    @Override
    public DentistaDto buscarPorId(Long id) {
        return dentistaRepository.findById(id)
                .map(DentistaMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Dentista não encontrado com ID: " + id));
    }

    /**
     * Converte o DTO em entidade, faz o vínculo com o endereço (caso exista),
     * salva no banco e retorna a versão salva como DTO.
     */
    @Override
    public DentistaDto salvar(DentistaDto dto) {
        Dentista dentista = DentistaMapper.toEntity(dto);

        // Garante o relacionamento bidirecional entre dentista e seu endereço
        EnderecoClinica endereco = dentista.getEndereco();
        if (endereco != null) {
            endereco.setDentista(dentista);
        }

        Dentista salvo = dentistaRepository.save(dentista);
        return DentistaMapper.toDto(salvo);
    }

    /**
     * Exclui um dentista pelo ID, após verificar se ele existe.
     */
    @Override
    public void excluir(Long id) {
        if (!dentistaRepository.existsById(id)) {
            throw new IllegalArgumentException("Não existe dentista com o ID: " + id);
        }
        dentistaRepository.deleteById(id);
    }
}
