package br.com.estoqueBr.service.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.UnidadeMedida;
import br.com.estoqueBr.repository.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaRegistrationService {

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public void create (String nome, String abreviacao) {
		UnidadeMedida unidadeMedida = new UnidadeMedida(nome, abreviacao);
		
		unidadeMedidaRepository.save(unidadeMedida);
	}

	public List<UnidadeMedida> procuraTodos() {
		return unidadeMedidaRepository.findAll();
	}
}
