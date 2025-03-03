package br.com.exemplo.apirest2.repository;
import br.com.exemplo.apirest2.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
