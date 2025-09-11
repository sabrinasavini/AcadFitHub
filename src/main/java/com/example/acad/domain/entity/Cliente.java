package com.example.acad.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("cliente")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String tipoDePlano;
    private String fichaTreino;
    private int valorPlano;
    private LocalDate dataInicio;
    private LocalDate dataVencimento;
    }