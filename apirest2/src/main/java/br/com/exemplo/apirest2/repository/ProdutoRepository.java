package br.com.exemplo.apirest2.repository;
import br.com.exemplo.apirest2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
