package br.com.estoqueBr.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Entrada;
import br.com.estoqueBr.model.Fabricante;
import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.UnidadeMedida;
import br.com.estoqueBr.repository.EntradaRepository;

@Service
public class TesteService {

	@Autowired
	private EntradaRepository entradaRepository;

	public void teste() {

		UnidadeMedida unidMed2 = new UnidadeMedida("unidade", "un");

		Custeio custeio1 = new Custeio("Funape");

		Destino destino1 = new Destino("Quadra 62");

		Fabricante fabricante1 = new Fabricante("ControlId");

		Fornecedor fornecedor1 = new Fornecedor("Qualiti", "16745974821746");

		Material material1 = new Material("Placa controladora de acesso", "IdFlex", fabricante1, unidMed2);

		Entrada entrada1 = new Entrada(10, LocalDateTime.now(), custeio1, material1, destino1, fornecedor1);

		entradaRepository.save(entrada1);

	}
}
