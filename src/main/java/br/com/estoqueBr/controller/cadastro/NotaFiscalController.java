package br.com.estoqueBr.controller.cadastro;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.controller.SessionsConstants;
import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.dto.NotaFiscalDto;
import br.com.estoqueBr.service.register.CusteioRegistrationService;
import br.com.estoqueBr.service.register.DestinoRegistrationService;
import br.com.estoqueBr.service.register.FornecedorRegistrationService;

@Controller
public class NotaFiscalController {
	
	@Autowired
	private FornecedorRegistrationService fornecedorRegistrationService;

	@Autowired
	private DestinoRegistrationService destinoRegistrationService;
	
	@Autowired
	private CusteioRegistrationService custeioRegistrationService;
	
	@GetMapping("/cadastro/nota_fiscal")
	public String preparaNotaFiscal(NotaFiscalDto notaFiscalDto, HttpSession session, Model model) {
		String onCancelUrl = (String) session.getAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		session.removeAttribute(SessionsConstants.ON_CANCEL_URL.toString());
		
		if (onCancelUrl == null) {
			 onCancelUrl = "/cadastro";
		}

		model.addAttribute("onCancelUrl", onCancelUrl);
		
		session.setAttribute(SessionsConstants.CALL_BACK_URL.toString(), "/cadastro/nota_fiscal");
		
		List<Fornecedor> fornecedores = fornecedorRegistrationService.procuraTodosFornecedores();
		List<Destino> destinos = destinoRegistrationService.procuraTodosDestinos();
		List<Custeio> custeios = custeioRegistrationService.procuraTodosCusteio();
		
		model.addAttribute("notaFiscalDto", notaFiscalDto);
		model.addAttribute("fornecedores", fornecedores);
		model.addAttribute("destinos", destinos);
		model.addAttribute("custeios", custeios);
		
		
		
		return "cadastro/nota_fiscal";
	}
	
	@PostMapping("/cadastro/nota_fiscal/nova")
	public String criaNotaFiscal(NotaFiscalDto notaFiscalDto, HttpSession session) {
		String callBackUrl = (String) session.getAttribute(SessionsConstants.CALL_BACK_URL.toString());
		session.removeAttribute(SessionsConstants.CALL_BACK_URL.toString());

		if (callBackUrl == null) {

			return "redirect:/cadastro";
		}

		session.setAttribute(SessionsConstants.NOTA_FISCAL_DTO.toString(), notaFiscalDto);

		return String.format("redirect:%s", callBackUrl);
	}
	
	@GetMapping("/cadastro/nota_fiscal/redirect/fornecedor")
	public String redirectFornecedor(HttpSession session) {
		session.setAttribute(SessionsConstants.ON_CANCEL_URL.toString(), "/cadastro/nota_fiscal");
		
		return "redirect:/cadastro/fornecedor";
	}
	
	@GetMapping("/cadastro/nota_fiscal/redirect/destino")
	public String redirectDestino(HttpSession session) {
		session.setAttribute(SessionsConstants.ON_CANCEL_URL.toString(), "/cadastro/nota_fiscal");
		
		return "redirect:/cadastro/destino";
	}
	
	@GetMapping("/cadastro/nota_fiscal/redirect/custeio")
	public String redirectCusteio(HttpSession session) {
		session.setAttribute(SessionsConstants.ON_CANCEL_URL.toString(), "/cadastro/nota_fiscal");
		
		return "redirect:/cadastro/custeio";
	}
}
