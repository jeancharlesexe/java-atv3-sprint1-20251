package br.com.exemplo.apirest2.repository;
import br.com.exemplo.apirest2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
