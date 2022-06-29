package br.com.estoqueBr.controler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoqueBr.model.form.EntradaDto;
import br.com.estoqueBr.model.form.MaterialDto;
import br.com.estoqueBr.model.form.SaidaDto;
import br.com.estoqueBr.service.TesteService;
import br.com.estoqueBr.service.inputOutput.EntradaRegisterService;
import br.com.estoqueBr.service.inputOutput.SaidaRegisterService;
import br.com.estoqueBr.service.popula.Populador;

@RestController
public class AplicationController {

	@Autowired
	private TesteService testeService;

	@Autowired
	private EntradaRegisterService entradaRegisterService;

	@Autowired
	private SaidaRegisterService saidaRegisterService;

	@GetMapping("/custeio/new")
	public void teste(@RequestParam(name = "custeio") String custeio) {
//		generalRegistrationService.createCusteio(custeio);
	}

	@GetMapping("/popula")
	public void popula(@RequestBody Populador populador) {
		testeService.teste(populador);
	}

	@GetMapping("/material")
	public void registraMaterial(@RequestBody MaterialDto materialForm) {

		testeService.cadastroMaterial(materialForm);
	}

	@GetMapping("/entrada")
	public void registraEntrada(@RequestBody EntradaDto entradaForm) {
		entradaRegisterService.create(entradaForm);

	}

	@GetMapping("/saida")
	public void registraSaida(@RequestBody SaidaDto saidaForm) {
		saidaRegisterService.create(saidaForm);
	}

}
