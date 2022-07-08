package br.com.estoqueBr.controller.cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadastroController {

	@GetMapping("/cadastro")
	public String homeCadastro() {
		return "cadastro/index";
	}
}
