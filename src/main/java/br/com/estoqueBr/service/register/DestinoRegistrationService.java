package br.com.estoqueBr.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.form.DestinoForm;
import br.com.estoqueBr.repository.DestinoRepository;

@Service
public class DestinoRegistrationService {

	@Autowired
	private DestinoRepository destinoRepository;

	public Destino create(DestinoForm destinoForm) {
		Destino destino = new Destino(destinoForm.getNome());

		return destinoRepository.save(destino);
	}

}
