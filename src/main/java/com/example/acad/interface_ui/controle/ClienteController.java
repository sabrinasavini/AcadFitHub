package com.example.acad.interface_ui.controle;

import com.example.acad.application.dto.ClienteDTO;
import com.example.acad.application.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteDTO dto) {
        ClienteDTO salvo = service.salvar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.id())
                .toUri();

        return ResponseEntity.created(location).body(salvo);
    }

    @Operation(summary = "Listar todos os clientes cadastrados")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar um cliente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Atualizar todos os dados de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarClienteCompleto(
            @PathVariable Long id,
            @RequestBody @Valid ClienteDTO dtoAtualizado
    ) {

        ClienteDTO atualizado = service.atualizar(id, dtoAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
            summary = "Deletar os dados de um cliente",
            parameters = {
                    @Parameter(name = "id", description = "ID do cliente a ser deletado do sistema", example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar clientes por parte do nome")
    @GetMapping("/buscarpornome")
    public ResponseEntity<List<ClienteDTO>> buscarPorNome(
            @Parameter(description = "Nome ou parte do nome a ser buscado", example = "Silva")
            @RequestParam String nome
    ) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @Operation(summary = "Buscar clientes por tipo de plano")
    @GetMapping("/buscarporplano")
    public ResponseEntity<List<ClienteDTO>> buscarPorTipoDePlano(
            @Parameter(description = "Nome exato do plano", example = "Mensal")
            @RequestParam String tipoDePlano
    ) {
        return ResponseEntity.ok(service.buscarPorTipoDePlano(tipoDePlano));
    }
}