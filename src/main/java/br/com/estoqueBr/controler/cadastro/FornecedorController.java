package br.com.estoqueBr.controler.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.form.ErroDto;
import br.com.estoqueBr.model.form.FornecedorDto;
import br.com.estoqueBr.service.exceptions.FornecedorException;
import br.com.estoqueBr.service.register.FornecedorRegistrationService;

@Controller
public class FornecedorController {

	@Autowired
	private FornecedorRegistrationService fornecedorRegistrationService;
	
	@GetMapping("/cadastro/fornecedor")
	public String fornecedorCadastroHome(FornecedorDto fornecedorDto) {
		
		return "cadastro/fornecedor";
	}
	
	@PostMapping("/cadastro/fornecedor/novo")
	public String fornecedorCadastroNovo(@Valid FornecedorDto fornecedorDto, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "cadastro/fornecedor";
		}
		
		try {
			fornecedorRegistrationService.create(fornecedorDto);
			
			return "redirect:/cadastro";
		} catch (FornecedorException e) {
			ErroDto erro = new ErroDto(e.getMessage(), fornecedorDto.getCnpj());
			
			model.addAttribute("erroDto", erro);
			
			return "error";
		}
	}
	
}
