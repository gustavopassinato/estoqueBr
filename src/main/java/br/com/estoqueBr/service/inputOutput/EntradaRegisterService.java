package br.com.estoqueBr.service.inputOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Entrada;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.form.EntradaForm;
import br.com.estoqueBr.repository.EntradaRepository;

@Service
public class EntradaRegisterService {

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private LocalSeracherService localSeracherService;

	public void create(EntradaForm entradaForm) {
		Material material = localSeracherService.searchForMaterial(entradaForm.getMaterialCodigo());

		NotaFiscal notaFiscal = localSeracherService.searchForNotaFiscal(entradaForm.getNumeroNota());

		Entrada entrada = new Entrada(entradaForm.getQuantidade(), material, notaFiscal);

		entradaRepository.save(entrada);
	}



}
