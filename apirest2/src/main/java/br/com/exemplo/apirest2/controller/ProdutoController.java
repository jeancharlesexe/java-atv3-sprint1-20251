package br.com.exemplo.apirest2.controller;

import br.com.exemplo.apirest2.model.Produto;
import br.com.exemplo.apirest2.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);

        if(!produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produto.get());
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.salvar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Optional<Produto> produtoAtual = produtoService.buscarPorId(id);

        if(!produtoAtual.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoService.atualizar(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Optional<Produto> produtoAtual = produtoService.buscarPorId(id);

        if(!produtoAtual.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
