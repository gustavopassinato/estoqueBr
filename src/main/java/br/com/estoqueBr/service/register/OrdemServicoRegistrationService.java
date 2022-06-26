package br.com.estoqueBr.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.OrdemServico;
import br.com.estoqueBr.repository.OrdemServicoRepository;

@Service
public class OrdemServicoRegistrationService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	public void create(String ordemServicoNumero) {
		OrdemServico ordemServico = new OrdemServico(ordemServicoNumero);
		
		ordemServicoRepository.save(ordemServico);
	}
}
