package br.com.estoqueBr.controler.cadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.dto.EntradaDto;
import br.com.estoqueBr.model.dto.MaterialQuantidadeDto;
import br.com.estoqueBr.model.dto.NotaFiscalDto;
import br.com.estoqueBr.model.dto.NotaFiscalFiltroDto;
import br.com.estoqueBr.model.dto.RegistroQuantidadeMaterialDto;
import br.com.estoqueBr.service.register.NotaFiscalRegistrationService;

@Controller
public class EntradaController {
	
	@Autowired
	private NotaFiscalRegistrationService notaFiscalRegistrationService;

	
	@GetMapping("/cadastro/entrada/ini")
	public String iniciaEntrada(Model model) {
		NotaFiscalDto notaFiscalDto = new NotaFiscalDto();
		NotaFiscalFiltroDto notaFiscalFiltroDto = new NotaFiscalFiltroDto();
		
		List<NotaFiscalDto> procuraNotas = notaFiscalRegistrationService.procuraNotas();
		List<NotaFiscalFiltroDto> filtro = notaFiscalRegistrationService.geraFiltroNotaFiscal();
		
		model.addAttribute("filtros", filtro);
		model.addAttribute("notaFiscalDtoList", procuraNotas);
		model.addAttribute("notaFiscalDto", notaFiscalDto);
		model.addAttribute("notaFiscalFiltroDto", notaFiscalFiltroDto);
		
		return "cadastro/entrada/entrada_init";
	}
	
	@PostMapping("/cadastro/entrada/ini/filtro")
	public String iniciaEntradaFiltro(NotaFiscalDto notaFiscalDto, Model model, NotaFiscalFiltroDto notaFiscalFiltroDto) {
		System.out.println(notaFiscalFiltroDto.getNomeFornecedor());
		
		List<NotaFiscalDto> procuraNotas = notaFiscalRegistrationService.procuraNotas(notaFiscalFiltroDto.getNomeFornecedor());
		
		List<NotaFiscalFiltroDto> filtro = notaFiscalRegistrationService.geraFiltroNotaFiscal();
		
		model.addAttribute("filtros", filtro);
		model.addAttribute("notaFiscalDtoList", procuraNotas);
		
		return "cadastro/entrada/entrada_init";
	}
	
	@PostMapping("/cadastro/entrada/ini")
	public String validaNota(NotaFiscalDto notaFiscalDto, HttpSession session) {
		
		Optional<NotaFiscal> validaNota = notaFiscalRegistrationService.valida(notaFiscalDto);
		
		if(validaNota.isPresent()) {
			NotaFiscalDto notaDtoValidada = NotaFiscalDto.geraNotaDto(validaNota.get());
			session.setAttribute("notaFiscalEntrada", notaDtoValidada);
			
			return "redirect:/cadastro/entrada/material";
		}
		
		return "cadastro/nota_fiscal";
	}

	
	
	
	
	
	
	@GetMapping("/cadastro/entrada/material")
	public String preparaListaEntrada(RegistroQuantidadeMaterialDto registroQuantidade) {

		return "cadastro/entrada_init";

	}

	@PostMapping("/cadastro/entrada/quantidade")
	public String criaListaEntrada(RegistroQuantidadeMaterialDto registroQuantidade, HttpSession session) {
		List<MaterialQuantidadeDto> materiaisQuantidades = new ArrayList<>();
		for (int i = 1; i <= registroQuantidade.getQuantidade(); i++) {
			materiaisQuantidades.add(new MaterialQuantidadeDto(1234l));
		}

		EntradaDto entradaDto = new EntradaDto();
		entradaDto.setMateriaisQuantidades(materiaisQuantidades);

		session.setAttribute("entradaDto", entradaDto);
		

		return "redirect:/cadastro/entrada";

	}

	@GetMapping("/cadastro/entrada")
	public String baseEntrada(EntradaDto entradaDto, HttpSession session, Model model) {
		EntradaDto entradaDtoSession = (EntradaDto) session.getAttribute("entradaDto");
		
		System.out.println(entradaDtoSession.getMateriaisQuantidades().get(0));
		System.out.println(entradaDtoSession.getCodigoNota());

		model.addAttribute("entradaDto", entradaDtoSession);

		return "cadastro/entrada";
	}

	@PostMapping("/cadastro/entrada/novo")
	public String finalEntrada(EntradaDto entradaDto) {

		return "index";
	}
}
