package br.com.estoqueBr.controler.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.dto.CusteioDto;
import br.com.estoqueBr.service.register.CusteioRegistrationService;

@Controller
public class CusteioController {
	
	@Autowired
	private CusteioRegistrationService custeioRegistrationService;
	
	@GetMapping("/cadastro/custeio")
	public String custeioCadastroHome(CusteioDto custeioDto) {
		return "cadastro/custeio";
	}
	
	@PostMapping("/cadastro/custeio/novo")
	public String custeioCadastroNovo(@Valid CusteioDto custeioDto, BindingResult result) {
		
		if (result.hasErrors()) {
			return "cadastro/custeio";
		}
		
		custeioRegistrationService.createCusteio(custeioDto);
		
		return "index";
	}
}
