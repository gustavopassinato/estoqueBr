package br.com.estoqueBr.service.inputOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Saida;
import br.com.estoqueBr.model.form.SaidaForm;
import br.com.estoqueBr.repository.SaidaRepository;

@Service
public class SaidaRegisterService {
	
	@Autowired
	private SaidaRepository saidaRepository;

	@Autowired
	private LocalSeracherService localSeracherService;

	public void create(SaidaForm saidaForm) {

		Saida saida = new Saida(
				localSeracherService.searchForDestino(saidaForm.getNomeDestino()),
				localSeracherService.searchForMaterial(saidaForm.getCodigoMaterial()), 
				localSeracherService.searchForOrdemServico(saidaForm.getNumeroOs()) , 
				saidaForm.getQunatidade(),
				localSeracherService.searchForNotaFiscal(saidaForm.getNumNotaFiscal()));
		
		saidaRepository.save(saida);
		
	}

}
