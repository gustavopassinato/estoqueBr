package br.com.estoqueBr.service.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Fabricante;
import br.com.estoqueBr.repository.FabricanteRepository;

@Service
public class FabricanteRegistrationService {

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	public Fabricante create(String fabricanteNome) {
		Fabricante fabricante = new Fabricante(fabricanteNome);
		
		return fabricanteRepository.save(fabricante);
	}

	public List<Fabricante> procuraTodos() {
		return fabricanteRepository.findAll();
	}
}
