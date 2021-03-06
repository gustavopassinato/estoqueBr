package br.com.estoqueBr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoqueBr.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long>{

}
