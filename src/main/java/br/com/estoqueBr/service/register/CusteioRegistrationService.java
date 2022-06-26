package br.com.estoqueBr.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.form.CusteioForm;
import br.com.estoqueBr.repository.CusteioRepository;

@Service
public class CusteioRegistrationService {
	
	@Autowired
	private CusteioRepository custeioRepository;
	
	public void createCusteio(CusteioForm custeioForm) {
		Custeio custeio = new Custeio(custeioForm.getNome());
		
		custeioRepository.save(custeio);
	}

}
