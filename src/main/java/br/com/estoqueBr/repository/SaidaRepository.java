package br.com.estoqueBr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoqueBr.model.Saida;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Integer>{

}
