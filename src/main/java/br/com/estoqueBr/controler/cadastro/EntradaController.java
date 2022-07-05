package br.com.estoqueBr.controler.cadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.dto.EntradaDto;
import br.com.estoqueBr.model.dto.MaterialQuantidadeDto;
import br.com.estoqueBr.model.dto.NotaFiscalDto;
import br.com.estoqueBr.model.dto.NotaFiscalFiltroDto;
import br.com.estoqueBr.model.dto.RegistroQuantidadeMaterialDto;
import br.com.estoqueBr.service.register.CusteioRegistrationService;
import br.com.estoqueBr.service.register.DestinoRegistrationService;
import br.com.estoqueBr.service.register.FornecedorRegistrationService;
import br.com.estoqueBr.service.register.NotaFiscalRegistrationService;

@Controller
public class EntradaController {

	@Autowired
	private NotaFiscalRegistrationService notaFiscalRegistrationService;

	@Autowired
	private FornecedorRegistrationService fornecedorRegistrationService;

	@Autowired
	private DestinoRegistrationService destinoRegistrationService;

	@Autowired
	private CusteioRegistrationService custeioRegistrationService;

	@GetMapping("/cadastro/entrada/ini")
	public String iniciaEntrada(Model model, HttpSession session) {
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
	public String iniciaEntradaFiltro(NotaFiscalDto notaFiscalDto, Model model,
			NotaFiscalFiltroDto notaFiscalFiltroDto) {

		List<NotaFiscalDto> procuraNotas = notaFiscalRegistrationService
				.procuraNotas(notaFiscalFiltroDto.getNomeFornecedor());

		List<NotaFiscalFiltroDto> filtro = notaFiscalRegistrationService.geraFiltroNotaFiscal();

		model.addAttribute("filtros", filtro);
		model.addAttribute("notaFiscalDtoList", procuraNotas);

		return "cadastro/entrada/entrada_init";
	}

	@GetMapping("/cadastro/entrada/nota_fiscal")
	public String criaNotaEntrada(Model model) {

		NotaFiscalDto notaFiscalDto = new NotaFiscalDto();

		List<Fornecedor> todosFornecedores = fornecedorRegistrationService.procuraTodosFornecedores();
		List<Destino> todosDestinos = destinoRegistrationService.procuraTodosDestinos();
		List<Custeio> todosCusteio = custeioRegistrationService.procuraTodosCusteio();

		model.addAttribute("todosFornecedores", todosFornecedores);
		model.addAttribute("todosDestinos", todosDestinos);
		model.addAttribute("todosCusteio", todosCusteio);
		model.addAttribute("notaFiscalDto", notaFiscalDto);

		return "cadastro/entrada/nota_fiscal";

	}

	@PostMapping("/cadastro/entrada/nota_fiscal/nova")
	public String validaNota(NotaFiscalDto notaFiscalDto, HttpSession session) {

		NotaFiscal notaFiscal = notaFiscalRegistrationService.create(notaFiscalDto);

		session.setAttribute("notaFiscalEntrada", notaFiscal);

		return "redirect:/cadastro/entrada/ini";
	}
	
	@PostMapping("/cadastro/entrada/nota_fiscal/select") 
	public String recebeNotaSelecionada(NotaFiscalDto notaFiscalDto,  HttpSession session) {
		
		NotaFiscalDto notaDto = (NotaFiscalDto)session.getAttribute("notaFiscalEntrada");
		
		if(notaDto == null) {
			if (notaFiscalDto == null) {
				throw new NullPointerException("Nota fiscal nula");
			}
			session.setAttribute("notaFiscalEntrada", notaFiscalDto); 
		}
		
		return "redirect:/cadastro/entrada/material";
	}
	
	@GetMapping("/cadastro/entrada/material")
	public String entradaDeMaterial() {
		return "";
		
	}

}
