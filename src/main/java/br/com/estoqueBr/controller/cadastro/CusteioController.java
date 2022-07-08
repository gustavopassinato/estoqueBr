package br.com.estoqueBr.controller.cadastro;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.dto.CusteioDto;
import br.com.estoqueBr.service.register.CusteioRegistrationService;

@Controller
public class CusteioController {

	@Autowired
	private CusteioRegistrationService custeioRegistrationService;

	@GetMapping("/cadastro/custeio/teste")
	public String teste(HttpSession session) {
		CusteioDto custeio = new CusteioDto();
		custeio.setNome("Setando nome no contexto");

		session.setAttribute("custeio", custeio);
		return "redirect:/cadastro/custeio";
	}

	@GetMapping("/cadastro/custeio")
	public String custeioCadastroHome(CusteioDto custeioDto, HttpSession session, Model model) {
		CusteioDto custeioDto2 = (CusteioDto) session.getAttribute("custeio");
		model.addAttribute("custeioDto", custeioDto2);
		
		Enumeration<String> attributeNames = session.getAttributeNames();
		
		while (attributeNames.hasMoreElements()) {
			System.out.println(attributeNames.nextElement());
			
		}
		
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
