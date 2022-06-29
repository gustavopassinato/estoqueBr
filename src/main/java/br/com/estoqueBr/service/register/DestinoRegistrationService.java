package br.com.estoqueBr.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.form.DestinoDto;
import br.com.estoqueBr.repository.DestinoRepository;

@Service
public class DestinoRegistrationService {

	@Autowired
	private DestinoRepository destinoRepository;

	public Destino create(DestinoDto destinoDto) {
		Destino destino = new Destino(destinoDto.getNome());

		return destinoRepository.save(destino);
	}

}
