package br.com.estoqueBr.service.register;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.dto.CusteioDto;
import br.com.estoqueBr.repository.CusteioRepository;
import br.com.estoqueBr.service.exceptions.CusteioException;

@Service
public class CusteioRegistrationService {

	@Autowired
	private CusteioRepository custeioRepository;

	public void create(CusteioDto custeioForm) {
		Custeio custeio = new Custeio(custeioForm.getNome());

		custeioRepository.save(custeio);
	}

	public Custeio createCusteio(String nome) {
		Custeio custeio = new Custeio(nome);

		return custeioRepository.save(custeio);
	}

	public List<Custeio> procuraTodosCusteio() {
		return custeioRepository.findAll();
	}

	public Custeio procuraPorNome(String nomeCusteio) {
		Optional<Custeio> custeioOpt = custeioRepository.findByNome(nomeCusteio);
		if (custeioOpt.isEmpty()) {
			throw new CusteioException("Centro de custeio não encontrado");
		}

		return custeioOpt.get();
	}

}
