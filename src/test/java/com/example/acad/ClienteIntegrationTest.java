package com.example.acad;

import com.example.acad.application.dto.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarClienteValido() throws Exception {
        var dto = new ClienteDTO(
                null,
                "Tainá Silva",
                "taina.silva@email.com",
                "11999999999",
                "Rua Alo Alo, 123",
                "Mensal",
                "Treino A - Cardio + Pernas",
                120.0,
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Tainá Silva")) // Correção aqui!
                .andExpect(jsonPath("$.email").value("taina.silva@email.com"));
    }

    @Test
    void deveRetornarErroSePlanoForInvalido() throws Exception {
        var dto = new ClienteDTO(
                null,
                "João Souza",
                "joaozinho@email.com",
                "(11) 1234-5678",
                "Rua skjskas",
                "Anual",
                "Treino B",
                95.0,
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("O tipo de plano é obrigatório."));
    }

    @Test
    void deveAtualizarFichaDeTreino() throws Exception {
        var dto = new ClienteDTO(
                null,
                "Carlos",
                "carlitos@gmail.com",
                "11987654321",
                "Rua Jojo Toddynho n78",
                "Mensal",
                "Treino C",
                150.0,
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );


        String responseContent = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ClienteDTO clienteSalvo = objectMapper.readValue(responseContent, ClienteDTO.class);


        var novaFichaDTO = new ClienteDTO(
                null,
                "Carlos",
                "carlitos@gmail.com",
                "11987654321",
                "Rua Jojo Toddynho n78",
                "Mensal",
                "Treino D - Peito + Tríceps",
                150.0,
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );

        mockMvc.perform(put("/clientes/" + clienteSalvo.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novaFichaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fichaTreino").value("Treino D - Peito + Tríceps"));
    }

    @Test
    void deveDeletarCliente() throws Exception {
        var dto = new ClienteDTO(
                null,
                "Ana",
                "ana@email.com",
                "11912345678",
                "Rua AEIOU n50",
                "Mensal",
                "Treino A",
                130.0,
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );

        String responseContent = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ClienteDTO cliente = objectMapper.readValue(responseContent, ClienteDTO.class);

        mockMvc.perform(delete("/clientes/" + cliente.id()))
                .andExpect(status().isNoContent());
    }
}