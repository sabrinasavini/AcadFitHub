package com.example.acad.interface_ui.controle;

import com.example.acad.application.dto.ClienteDTO;
import com.example.acad.application.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteDTO dto) {
        ClienteDTO salvo = service.salvar(dto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}/ficha")
    public ResponseEntity<ClienteDTO> atualizarFicha(@PathVariable Long id, @RequestBody Map<String, String> novaFicha) {
        ClienteDTO cliente = service.buscarPorId(id);
        ClienteDTO atualizado = new ClienteDTO(
                cliente.id(),
                cliente.nome(),
                cliente.email(),
                cliente.telefone(),
                cliente.endereco(),
                cliente.tipoDePlano(),
                novaFicha.get("fichaTreino"),
                cliente.valorPlano(),
                cliente.dataInicio(),
                cliente.dataVencimento()
        );
        return ResponseEntity.ok(service.atualizar(id, atualizado));
    }

    @Operation(
            summary = "Deletar os dados de um cliente",
            parameters = {
                    @Parameter (name = "nome", description = "Nome do cliente a ser deletado do sistema", example = "Fulano de Tal")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarpornome")
    public ResponseEntity<List<ClienteDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @GetMapping("/buscarporplano")
    public ResponseEntity<List<ClienteDTO>> buscarPorTipoDePlano(@RequestParam String tipoDePlano) {
        return ResponseEntity.ok(service.buscarPorTipoDePlano(tipoDePlano));
    }
}
