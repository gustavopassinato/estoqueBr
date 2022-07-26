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
import br.com.estoqueBr.model.dto.CusteioDto;
import br.com.estoqueBr.service.register.CusteioRegistrationService;

@Controller
public class CusteioController {

	@Autowired
	private CusteioRegistrationService custeioRegistrationService;

	@GetMapping("/cadastro/custeio")
	public String custeioCadastroHome(CusteioDto custeioDto, HttpSession session, Model model) {
		String onCancelUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL_ON_CANCELL.toString());
		session.removeAttribute(SessionsConstants.CALL_BACK_URL_ON_CANCELL.toString());
		
		if (onCancelUrl == null) {
			 onCancelUrl = "/cadastro";
		}

		model.addAttribute("onCancelUrl", onCancelUrl);
		
		return "cadastro/custeio";
	}

	@PostMapping("/cadastro/custeio/novo")
	public String custeioCadastroNovo(@Valid CusteioDto custeioDto, HttpSession session, BindingResult result) {
		
		if (result.hasErrors()) {
			return "cadastro/custeio";
		}

		custeioRegistrationService.create(custeioDto);

		String callBackUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL_ON_SUBMIT.toString());

		if (callBackUrl == null) {

			return "redirect:/cadastro";
		}

		session.setAttribute(SessionsConstants.CUSTEIO_DTO.toString(), custeioDto);

		return String.format("redirect:%s", callBackUrl);
	}
}
