package br.com.estoqueBr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoqueBr.model.Destino;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long>{

}
