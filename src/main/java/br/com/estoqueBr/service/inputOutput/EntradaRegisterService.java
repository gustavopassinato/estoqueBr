package br.com.estoqueBr.service.inputOutput;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Entrada;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.form.EntradaForm;
import br.com.estoqueBr.repository.EntradaRepository;
import br.com.estoqueBr.repository.MaterialRepository;

@Service
public class EntradaRegisterService {
	
	@Autowired
	private EntradaRepository entradaRepository;
	
	@Autowired
	private MaterialRepository materialRepository;
	
	public void create(EntradaForm entradaForm) {
		Material material = searchForMaterial(entradaForm.getMaterialCodigo());
		
		Entrada entrada = new Entrada(entradaForm.getQuantidade(), material);
		
		entradaRepository.save(entrada);
	}
	
	public Material searchForMaterial(Long codigoMateiral) {
		
		Optional<Material> material = materialRepository.findById(codigoMateiral);
		
		if (material.isEmpty()) {
			throw new NullPointerException();
		}
		return material.get();
	}
}
