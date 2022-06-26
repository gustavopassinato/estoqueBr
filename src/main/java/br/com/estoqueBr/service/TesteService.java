package br.com.estoqueBr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Fabricante;
import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.UnidadeMedida;
import br.com.estoqueBr.repository.EntradaRepository;
import br.com.estoqueBr.repository.FabricanteRepository;
import br.com.estoqueBr.repository.MaterialRepository;
import br.com.estoqueBr.repository.NotaFiscalRepository;
import br.com.estoqueBr.repository.SaidaRepository;
import br.com.estoqueBr.repository.UnidadeMedidaRepository;

@Service
public class TesteService {

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	@Autowired
	private SaidaRepository saidaRepository;

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;

	public void teste() {

//		UnidadeMedida unidMed2 = new UnidadeMedida("unidade", "uni");
//
//		Fabricante fabricante1 = new Fabricante("ControlId2");
//
//		System.out.println(fabricante1.getId());
//
//		Material material1 = new Material("Placa controladora de acesso", "IdBox", fabricante1, unidMed2);
//
//		materialRepository.save(material1);

//		
//		Custeio custeio1 = new Custeio("Funape");
//
//		Destino destino1 = new Destino("Quadra 62");
//		
//		Fornecedor fornecedor1 = new Fornecedor("Qualiti", "16745974821746");
		
		Optional<Material> findById = materialRepository.findById(1l);
		
		Material material = findById.get();
		
		materialRepository.delete(material);

	}
}
