package com.example.acad.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import com.example.acad.domain.entity.Cliente;
import jakarta.validation.constraints.*;

public record ClienteDTO(

        @Schema(description = "ID do cliente", example = "1")
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Nome completo do cliente")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        @Schema(description = "Email do cliente")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        @Schema(description = "Telefone do cliente", example = "(11) 91234-5678")
        String telefone,

        @NotBlank(message = "Endereço é obrigatório")
        @Schema(description = "Endereço do cliente")
        String endereco,

        @NotBlank(message = "Tipo de plano é obrigatório")
        @Schema(description = "Tipo de plano contratado", example = "Mensal/Anual")
        String tipoDePlano,

        @Schema(description = "Ficha de treino do cliente")
        String fichaTreino,

        @DecimalMin(value = "105.0", message = "Você deve inserir um valor válido")
        @Schema(description = "Valor do plano contratado")
        Double valorPlano,

        @NotNull(message = "Data de início é obrigatória")
        @Schema(description = "Data de início do plano")
        LocalDate dataInicio,

        @NotNull(message = "Data de vencimento é obrigatória")
        @Schema(description = "Data de vencimento do plano", example = "2025-02-01")
        LocalDate dataVencimento

) {
    public static ClienteDTO fromEntity(Cliente c) {
        return new ClienteDTO(
                c.getId(),
                c.getNome(),
                c.getEmail(),
                c.getTelefone(),
                c.getEndereco(),
                c.getTipoDePlano(),
                c.getFichaTreino(),
                c.getValorPlano(),
                c.getDataInicio(),
                c.getDataVencimento()
        );
    }

    public Cliente toEntity() {
        return Cliente.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .endereco(endereco)
                .tipoDePlano(tipoDePlano)
                .fichaTreino(fichaTreino)
                .valorPlano(valorPlano)
                .dataInicio(dataInicio)
                .dataVencimento(dataVencimento)
                .build();
    }
}
