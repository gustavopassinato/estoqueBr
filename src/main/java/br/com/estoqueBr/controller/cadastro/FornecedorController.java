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
import br.com.estoqueBr.model.dto.ErroDto;
import br.com.estoqueBr.model.dto.FornecedorDto;
import br.com.estoqueBr.service.exceptions.FornecedorException;
import br.com.estoqueBr.service.register.FornecedorRegistrationService;

@Controller
public class FornecedorController {

	@Autowired
	private FornecedorRegistrationService fornecedorRegistrationService;

	@GetMapping("/cadastro/fornecedor")
	public String fornecedorCadastroHome(FornecedorDto fornecedorDto, HttpSession session, Model model) {
		
		String onCancelUrl = (String) session.getAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		session.removeAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		
		if (onCancelUrl == null) {
			 onCancelUrl = "/cadastro";
		}

		model.addAttribute("onCancelUrl", onCancelUrl);

		return "cadastro/fornecedor";
	}

	@PostMapping("/cadastro/fornecedor/novo")
	public String fornecedorCadastroNovo(@Valid FornecedorDto fornecedorDto, HttpSession session, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return "cadastro/fornecedor";
		}

		try {
			fornecedorRegistrationService.create(fornecedorDto);

			String callBackUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL.toString());
			session.removeAttribute(SessionsConstants.CALL_BACK_URL.toString());
			
			if (callBackUrl == null) {

				return "redirect:/cadastro";
			}

			session.setAttribute(SessionsConstants.FORNECEDOR_DTO.toString(), fornecedorDto);

			return String.format("redirect:%s", callBackUrl);

		} catch (FornecedorException e) {
			String fieldErrorName = "Fornecedor";

			ErroDto erro = new ErroDto(e.getMessage(), fornecedorDto.getCnpj(), fieldErrorName);

			model.addAttribute("erroDto", erro);

			return "erro";
		}
	}

}
