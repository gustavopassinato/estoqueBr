package br.com.estoqueBr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoqueBr.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	Optional<Fornecedor> findByCnpj(String nome);

	Optional<Fornecedor> findByNome(String nomeFornecedor);

}
