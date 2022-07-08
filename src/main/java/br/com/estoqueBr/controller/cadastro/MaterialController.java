package br.com.estoqueBr.controller.cadastro;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.controller.SessionsConstants;
import br.com.estoqueBr.model.Fabricante;
import br.com.estoqueBr.model.UnidadeMedida;
import br.com.estoqueBr.model.dto.ErroDto;
import br.com.estoqueBr.model.dto.MaterialDto;
import br.com.estoqueBr.service.exceptions.FornecedorException;
import br.com.estoqueBr.service.register.FabricanteRegistrationService;
import br.com.estoqueBr.service.register.MaterialRegisterService;
import br.com.estoqueBr.service.register.UnidadeMedidaRegistrationService;

@Controller
public class MaterialController {

	@Autowired
	private FabricanteRegistrationService fabricanteRegistrationService;

	@Autowired
	private UnidadeMedidaRegistrationService unidadeMedidaRegistrationService;

	@Autowired
	private MaterialRegisterService materialRegisterService;

	@GetMapping("/cadastro/material")
	public String materialCadastroHome(MaterialDto materialDto, HttpSession session, Model model) {
		String onCancelUrl = (String) session.getAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		session.removeAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		
		if (onCancelUrl == null) {
			 onCancelUrl = "/cadastro";
		}

		model.addAttribute("onCancelUrl", onCancelUrl);
		
		session.setAttribute(SessionsConstants.CALL_BACK_URL.toString(), "/cadastro/material");

		List<Fabricante> fabricantes = fabricanteRegistrationService.procuraTodos();

		List<UnidadeMedida> unidadesMedida = unidadeMedidaRegistrationService.procuraTodos();

		model.addAttribute("mateialDto", materialDto);
		model.addAttribute("fabricantes", fabricantes);
		model.addAttribute("unidadesMedida", unidadesMedida);

		return "cadastro/material";
	}

	@PostMapping("/cadastro/material/novo")
	public String materialCadastroNovo(@Valid MaterialDto materialDto, HttpSession session, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return "cadastro/material";
		}

		try {
			materialRegisterService.create(materialDto);

			String callBackUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL.toString());
			session.removeAttribute(SessionsConstants.CALL_BACK_URL.toString());

			if (callBackUrl == null) {

				return "redirect:/cadastro";
			}

			session.setAttribute(SessionsConstants.MATERIAL_DTO.toString(), materialDto);

			return String.format("redirect:%s", callBackUrl);

		} catch (FornecedorException e) {
			String fieldErrorName = "Material";

			ErroDto erro = new ErroDto(e.getMessage(), materialDto.getNome(), fieldErrorName);

			model.addAttribute("erroDto", erro);

			return "erro";
		}

	}
	
	@GetMapping("/cadastro/material/redirect/fabricante")
	public String redirectFabricante(HttpSession session) {
		session.setAttribute(SessionsConstants.ON_CANCEL_URL.toString(), "/cadastro/material");
		
		return "redirect:/cadastro/fabricante";
	}

}
