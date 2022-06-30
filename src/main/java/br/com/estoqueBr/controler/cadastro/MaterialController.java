package br.com.estoqueBr.controler.cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaterialController {

	@GetMapping("/cadastro/material")
	public String materialCadastroHome() {
		
		return "cadastro/material";
	}
	

}
