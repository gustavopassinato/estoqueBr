package br.com.estoqueBr.service.inputOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Entrada;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.dto.EntradaDto;
import br.com.estoqueBr.repository.EntradaRepository;

@Service
public class EntradaRegisterService {

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private LocalSeracherService localSeracherService;

	public void create(EntradaDto entradaDto) {
		NotaFiscal notaFiscal = localSeracherService.searchForNotaFiscal(entradaDto.getCodigoNota());

		Material material = localSeracherService.searchForMaterial(entradaDto.getIdMaterial());

		Entrada entrada = new Entrada(entradaDto.getQuantidade(), material, notaFiscal);

		entradaRepository.save(entrada);

	}

}
