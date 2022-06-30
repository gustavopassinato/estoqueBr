package br.com.estoqueBr.controler.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.dto.EntradaDto;
import br.com.estoqueBr.model.dto.MaterialQuantidadeDto;
import br.com.estoqueBr.model.dto.RegistroQuantidadeMaterialDto;

@Controller
public class EntradaController {
	
	
	@GetMapping("/cadastro/entrada/quantidade")
	public String criaListaEntrada(RegistroQuantidadeMaterialDto registroQuantidade, Model model) {
		
		List<MaterialQuantidadeDto> materiaisQuantidades = new ArrayList<>();
		for (int i = 1; i <= registroQuantidade.getQuantidade(); i++) {
			materiaisQuantidades.add(new MaterialQuantidadeDto());
		}
		
		EntradaDto entradaDto = new EntradaDto();
		entradaDto.setMateriaisQuantidades(materiaisQuantidades);
		
		model.addAttribute("entradaDto",entradaDto);
		
		return "cadastro/entrada";
		
	}
	
	@GetMapping("/cadastro/entrada")
	public String baseEntrada(EntradaDto entradaDto) {
		
		return "cadastro/entrada";
	}
	
	@PostMapping("/cadastro/entrada/novo")
	public String finalEntrada(EntradaDto entradaDto) {
		
		return "index";
	}
}
