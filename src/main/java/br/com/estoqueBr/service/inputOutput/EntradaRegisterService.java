package br.com.estoqueBr.service.inputOutput;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Entrada;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.dto.EntradaDto;
import br.com.estoqueBr.model.dto.MaterialQuantidadeDto;
import br.com.estoqueBr.repository.EntradaRepository;

@Service
public class EntradaRegisterService {

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private LocalSeracherService localSeracherService;

	public void create(EntradaDto entradaForm) {
		NotaFiscal notaFiscal = localSeracherService.searchForNotaFiscal(entradaForm.getCodigoNota());
		
		individualCreate(notaFiscal, entradaForm.getMateriaisQuantidades());
		
	}
	
	private void individualCreate(NotaFiscal notaFiscal, List<MaterialQuantidadeDto> materiaisQuantidades) {
		
		materiaisQuantidades.forEach(materialQuantidade -> {
			Material material = localSeracherService.searchForMaterial(materialQuantidade.getCodigoMaterial());

			Entrada entrada = new Entrada(materialQuantidade.getQuantidade(), material, notaFiscal);
			
			entradaRepository.save(entrada);
		});
	}



}
