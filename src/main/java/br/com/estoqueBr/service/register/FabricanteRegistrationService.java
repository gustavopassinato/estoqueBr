package br.com.estoqueBr.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Fabricante;
import br.com.estoqueBr.repository.FabricanteRepository;

@Service
public class FabricanteRegistrationService {

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	public void create(String fabricanteNome) {
		Fabricante fabricante = new Fabricante(fabricanteNome);
		
		fabricanteRepository.save(fabricante);
	}
}
