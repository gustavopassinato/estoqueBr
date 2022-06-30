package br.com.estoqueBr.controler.cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.dto.EntradaDto;

@Controller
public class EntradaController {
	
	@GetMapping("/cadastro/entrada")
	public String baseEntrada(EntradaDto entradaDto) {
		
		return "cadastro/entrada";
	}
	
	@PostMapping("/cadastro/entrada/novo")
	public String finalEntrada(EntradaDto entradaDto) {
		
		return "index";
	}
}
