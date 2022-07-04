package br.com.estoqueBr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoqueBr.model.Custeio;

@Repository
public interface CusteioRepository extends JpaRepository<Custeio, Long>{

	Optional<Custeio> findByNome(String nomeCusteio);

}
