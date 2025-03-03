package br.com.exemplo.apirest2.controller;

import br.com.exemplo.apirest2.model.Cliente;
import br.com.exemplo.apirest2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        Optional<Cliente> clienteAtual = clienteService.buscarPorId(id);

        if(!clienteAtual.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAtual.get());
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.salvar(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteAtual = clienteService.buscarPorId(id);
        if(!clienteAtual.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Optional<Cliente> clienteAtual = clienteService.buscarPorId(id);
        if(!clienteAtual.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        clienteService.excluir(id);
        return ResponseEntity.ok().build();
    }


}
