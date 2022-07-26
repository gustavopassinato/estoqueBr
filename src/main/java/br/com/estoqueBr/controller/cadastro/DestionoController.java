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
import br.com.estoqueBr.model.dto.DestinoDto;
import br.com.estoqueBr.service.register.DestinoRegistrationService;

@Controller
public class DestionoController {

	@Autowired
	private DestinoRegistrationService destinoRegistrationService;
	
	@GetMapping("/cadastro/destino")
	public String destinoCadastroHome(DestinoDto destinoDto, HttpSession session, Model model) {
		String onCancelUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL_ON_CANCELL.toString());
		session.removeAttribute(SessionsConstants.CALL_BACK_URL_ON_CANCELL.toString());
		
		if (onCancelUrl == null) {
			 onCancelUrl = "/cadastro";
		}

		model.addAttribute("onCancelUrl", onCancelUrl);
		
		return "cadastro/destino";
	}
	
	@PostMapping("/cadastro/destino/novo")
	public String destinoCadastroNovo(@Valid DestinoDto detinoDto, HttpSession session, BindingResult result) {
		
		if(result.hasErrors()) {
			return "cadastro/destino";
		}
		
		destinoRegistrationService.create(detinoDto);
		
		String callBackUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL_ON_SUBMIT.toString());

		if (callBackUrl == null) {

			return "redirect:/cadastro";
		}

		session.setAttribute(SessionsConstants.DESTINO_DTO.toString(), detinoDto);

		return String.format("redirect:%s", callBackUrl);
	}
}
