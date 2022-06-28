package br.com.estoqueBr.controler.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.form.CusteioForm;
import br.com.estoqueBr.service.register.CusteioRegistrationService;

@Controller
public class CusteioController {
	
	@Autowired
	private CusteioRegistrationService custeioRegistrationService;
	
	@GetMapping("/cadastro/custeio")
	public String custeioCadastroHome(CusteioForm custeioForm) {
		return "cadastro/custeio";
	}
	
	@PostMapping("/custeio/new")
	public String custeioCadastroNovo(CusteioForm custeioForm) {
		
		custeioRegistrationService.createCusteio(custeioForm);
		
		return "index";
	}
}
