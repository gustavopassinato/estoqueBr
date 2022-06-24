package br.com.estoqueBr.controler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoqueBr.service.TesteService;

@RestController
public class AplicationController {
	
	@Autowired
	private TesteService testeService;
	
	@GetMapping("/teste")
	public void teste() {
		testeService.teste();
	}

}
