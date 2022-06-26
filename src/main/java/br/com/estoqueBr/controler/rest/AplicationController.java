package br.com.estoqueBr.controler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoqueBr.model.form.MaterialForm;
import br.com.estoqueBr.service.TesteService;
import br.com.estoqueBr.service.popula.Populador;
import br.com.estoqueBr.service.register.CusteioRegistrationService;

@RestController
public class AplicationController {
	
	@Autowired
	private TesteService testeService;
	
	@GetMapping("/custeio/new")
	public void teste(@RequestParam(name = "custeio") String custeio) {
//		generalRegistrationService.createCusteio(custeio);
	}
	
	@GetMapping("/popula")
	public void popula(@RequestBody Populador populador) {
		testeService.teste(populador);
	}
	
	@GetMapping("/material")
	public void registraMaterial(@RequestBody MaterialForm materialForm) {
		
		testeService.cadastroMaterial(materialForm);
	}

}
