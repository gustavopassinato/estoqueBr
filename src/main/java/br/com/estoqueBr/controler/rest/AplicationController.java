package br.com.estoqueBr.controler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoqueBr.service.register.CusteioRegistrationService;

@RestController
public class AplicationController {
	
	@Autowired
	private CusteioRegistrationService generalRegistrationService;
	
	@GetMapping("/custeio/new")
	public void teste(@RequestParam(name = "custeio") String custeio) {
//		generalRegistrationService.createCusteio(custeio);
	}

}
