package br.com.exemplo.apirest2.controller;

import br.com.exemplo.apirest2.model.Pedido;
import br.com.exemplo.apirest2.model.Produto;
import br.com.exemplo.apirest2.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
         Optional<Pedido> pedido = pedidoService.buscarPorId(id);

         if(!pedido.isPresent()) {
             return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok(pedido.get());
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Pedido> salvar(@PathVariable Long clienteId, @RequestBody List<Long> produtosIds) {
        Pedido pedido =  pedidoService.salvar(clienteId, produtosIds);

        if(pedido == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);

        if(!pedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        pedidoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/valor")
    public ResponseEntity<Double> obterValor(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        if(!pedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pedidoService.CalcularValorTotal(id));
    }

}
