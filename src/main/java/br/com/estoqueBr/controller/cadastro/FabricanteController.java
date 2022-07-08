package br.com.estoqueBr.controller.cadastro;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.controller.SessionsConstants;
import br.com.estoqueBr.model.dto.FabricanteDto;
import br.com.estoqueBr.service.register.FabricanteRegistrationService;

@Controller
public class FabricanteController {
	
	@Autowired
	private FabricanteRegistrationService fabricanteRegistrationService;
	
	@GetMapping("/cadastro/fabricante")
	public String fabricanteCadastroHome(FabricanteDto fabricanteDto, HttpSession session, Model model) {
		
		String onCancelUrl = (String) session.getAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		session.removeAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		
		if (onCancelUrl == null) {
			 onCancelUrl = "/cadastro";
		}

		model.addAttribute("onCancelUrl", onCancelUrl);

		return "cadastro/fabricante";
	}
	
	@PostMapping("/cadastro/fabricante/novo")
	public String fabricanteCadastroNovo(@Valid FabricanteDto fabricanteDto, HttpSession session, BindingResult result) {
		
		if (result.hasErrors()) {
			return "cadastro/fabricante";
		}
		
		fabricanteRegistrationService.create(fabricanteDto.getNome());
		
		String callBackUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL.toString());

		if (callBackUrl == null) {

			return "redirect:/cadastro";
		}

		session.setAttribute(SessionsConstants.FABRICANTE_DTO.toString(), fabricanteDto);

		return String.format("redirect:%s", callBackUrl);
	}	
}
