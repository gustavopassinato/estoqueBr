package br.com.estoqueBr.controler.cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.estoqueBr.model.dto.NotaFiscalDto;

@Controller
public class NotaFiscalController {

	@GetMapping("/cadastro/nota_fiscal")
	public String criaNotaFiscal(NotaFiscalDto notaFiscalDto) {
		
		return "cadastro/nota_fiscal";
	}
}
