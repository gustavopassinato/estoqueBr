package br.com.estoqueBr.controler.cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.dto.NotaFiscalDto;

@Controller
public class NotaFiscalController {

	@GetMapping("/cadastro/entrada/nota_fiscal")
	public String preparaNotaFiscal(NotaFiscalDto notaFiscalDto) {
		
		return "cadastro/entrada/nota_fiscal";
	}
	
	@PostMapping("/cadastro/entrada/nota_fiscal")
	public String criaNotaFiscal(NotaFiscalDto notaFiscalDto) {
		
	}
}
