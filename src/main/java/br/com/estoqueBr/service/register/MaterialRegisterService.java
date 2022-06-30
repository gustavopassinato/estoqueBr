package br.com.estoqueBr.service.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Fabricante;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.UnidadeMedida;
import br.com.estoqueBr.model.dto.MaterialDto;
import br.com.estoqueBr.repository.FabricanteRepository;
import br.com.estoqueBr.repository.MaterialRepository;
import br.com.estoqueBr.repository.UnidadeMedidaRepository;

@Service
public class MaterialRegisterService {

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	public void create(String nome, String modelo, Fabricante fabricante, UnidadeMedida unidadeMedida) {
		Material material = new Material(nome, modelo, fabricante, unidadeMedida);

		materialRepository.save(material);
	}

	public void create(MaterialDto materialForm) {
		Optional<Fabricante> fabricante = fabricanteRepository.findByNome(materialForm.getFabricanteNome());
		Optional<UnidadeMedida> unidMed = unidadeMedidaRepository.findByNome(materialForm.getUnidadeMedidaNome());
		
		System.out.println(fabricante.get().getNome());
		System.out.println(unidMed.get().getNome());

		if (fabricante.isEmpty() || unidMed.isEmpty()) {
			throw new RuntimeException();
		}

		Material material = new Material(materialForm.getNome(), materialForm.getModelo(), fabricante.get(),
				unidMed.get());

		materialRepository.save(material);
	}
}
