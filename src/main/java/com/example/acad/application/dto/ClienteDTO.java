package com.example.acad.application.dto;

import com.example.acad.domain.entity.exception.repository.entity.Cliente;

import java.time.LocalDate;

public record ClienteDTO(
        int id,
        String nome,
        String email,
        String telefone,
        String endereco,
        String tipoDePlano,
        String fichaTreino,
        int valorPlano,
        LocalDate dataInicio,
        LocalDate dataVencimento
) {

    }

