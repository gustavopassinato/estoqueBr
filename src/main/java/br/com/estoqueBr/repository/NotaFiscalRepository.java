package br.com.estoqueBr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long>{

	Optional<NotaFiscal> findByNumero(String numeroNota);
	
	List<NotaFiscal> findByFornecedor(Fornecedor fornecedor);

}
