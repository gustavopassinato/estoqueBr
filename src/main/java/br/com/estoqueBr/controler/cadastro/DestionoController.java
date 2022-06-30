package br.com.estoqueBr.controler.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.dto.DestinoDto;
import br.com.estoqueBr.service.register.DestinoRegistrationService;

@Controller
public class DestionoController {

	@Autowired
	private DestinoRegistrationService destinoRegistrationService;
	
	@GetMapping("/cadastro/destino")
	public String destinoCadastroHome(DestinoDto destinoDto) {
		return "cadastro/destino";
	}
	
	@PostMapping("/cadastro/destino/novo")
	public String destinoCadastroNovo(@Valid DestinoDto detinoDto, BindingResult result) {
		
		if(result.hasErrors()) {
			return "cadastro/destino";
		}
		
		destinoRegistrationService.create(detinoDto);
		
		return "redirect:/cadastro";
	}
}
