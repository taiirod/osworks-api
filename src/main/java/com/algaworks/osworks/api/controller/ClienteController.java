package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> listarPorNome(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente){
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clientId, @Valid @RequestBody Cliente cliente) {

        if(!clienteRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clientId);
        cliente = cadastroClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long clientId) {
        if(!clienteRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        cadastroClienteService.deletar(clientId);
        return ResponseEntity.noContent().build();
    }
}
