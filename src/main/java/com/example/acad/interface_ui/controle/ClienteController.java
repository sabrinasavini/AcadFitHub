package com.example.acad.interface_ui.controle;

import com.example.acad.domain.entity.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final List<Cliente> clientes = new ArrayList<>();




    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }


    @GetMapping
    public List<Cliente> listarClientes() {
        return clientes;
    }


    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clientes.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }


    @PutMapping("/{id}/ficha")
    public Cliente atualizarFicha(@PathVariable Long id, @RequestBody Map<String, String> novaFicha) {
        Cliente cliente = buscarPorId(id);
        cliente.setFichaTreino(cliente.getFichaTreino());
        return cliente;
    }

}
