package br.com.estoqueBr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoqueBr.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{

}
