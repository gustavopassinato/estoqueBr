package br.com.estoqueBr.controler.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.estoqueBr.model.form.FabricanteDto;
import br.com.estoqueBr.service.register.FabricanteRegistrationService;

@Controller
public class FabricanteController {
	
	@Autowired
	private FabricanteRegistrationService fabricanteRegistrationService;
	

	@GetMapping("/cadastro/fabricante")
	public String fabricanteCadastroHome(FabricanteDto fabricanteDto) {

		return "cadastro/fabricante";

	}
	
	public String fabricanteCadastroNovo(@Valid FabricanteDto fabricanteDto, BindingResult result) {
		
		if (result.hasErrors()) {
			return "cadastro/fabricante";
		}
		
		fabricanteRegistrationService.create(fabricanteDto.getNome());
		
		return "redirect:/cadastro";
	}	
}
