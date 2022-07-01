package br.com.estoqueBr.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.estoqueBr.model.dto.CusteioDto;

@Controller
public class TesteController {
	
	@GetMapping("/teste")
	public String teste(HttpSession session) {
		CusteioDto custeio = new CusteioDto();
		custeio.setNome("Setando nome no contexto");
		
		
		
		session.setAttribute("custeio", custeio);
		return "redirect:/cadastro/custeio";
	}
}
