package com.example.acad.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("cliente")
@Data
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String tipoDePlano;
    private String fichaTreino;
    private Double valorPlano;
    private LocalDate dataInicio;
    private LocalDate dataVencimento;
    }