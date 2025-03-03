package br.com.exemplo.apirest2.service;

import br.com.exemplo.apirest2.model.Cliente;
import br.com.exemplo.apirest2.model.Pedido;
import br.com.exemplo.apirest2.model.Produto;
import br.com.exemplo.apirest2.repository.ClienteRepository;
import br.com.exemplo.apirest2.repository.PedidoRepository;
import br.com.exemplo.apirest2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(Long clienteId, List<Long> produtosIds) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        List<Produto> produtos = produtoRepository.findAllById(produtosIds);
        if(produtos.size() != produtosIds.size()) {
            throw new RuntimeException("Algum produto não foi encontrado");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProdutos(produtos);
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Double CalcularValorTotal(Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();

        if(pedido == null) {
            throw new RuntimeException("Não encontrado nenhum pedido com esse id");
        }

        pedido.calcularValorTotal();
        return pedido.getValorTotal();
    }
}
