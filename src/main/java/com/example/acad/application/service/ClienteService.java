package com.example.acad.application.service;

import com.example.acad.application.dto.ClienteDTO;
import com.example.acad.domain.entity.Cliente;
import com.example.acad.domain.exception.ClienteNaoEncontradoException;
import com.example.acad.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }
    public ClienteDTO salvar(ClienteDTO dto){
        Cliente cliente = dto.toEntity();
        return ClienteDTO.fromEntity(repository.save(cliente));
    }

    public ClienteDTO buscarPorId(Long id) {
        return ClienteDTO.fromEntity(
                repository.findById(id)
                        .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado."))
        );
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dtoAtualizado) {
        Cliente existente = repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado."));

        Cliente atualizado = dtoAtualizado.toEntity();
        atualizado.setId(existente.getId());


        return ClienteDTO.fromEntity(repository.save(atualizado));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
    public List<ClienteDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(ClienteDTO::fromEntity)
                .toList();
    }

    public List<ClienteDTO> buscarPorTipoDePlano(String tipoDePlano) {
        return repository.findByTipoDePlano(tipoDePlano)
                .stream()
                .map(ClienteDTO::fromEntity)
                .toList();
    }
    public List<ClienteDTO>listar(){
        return repository.findAll()
                .stream()
                .map(ClienteDTO::fromEntity)
                .toList();
    }
}
