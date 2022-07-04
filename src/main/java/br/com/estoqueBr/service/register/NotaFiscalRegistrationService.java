package br.com.estoqueBr.service.register;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.dto.NotaFiscalDto;
import br.com.estoqueBr.model.dto.NotaFiscalFiltroDto;
import br.com.estoqueBr.repository.NotaFiscalRepository;

@Service
public class NotaFiscalRegistrationService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	@Autowired
	private FornecedorRegistrationService fornecedorRegistrationService;

	@Autowired
	private DestinoRegistrationService destinoRegistrationService;

	@Autowired
	private CusteioRegistrationService custeioRegistrationService;

	public NotaFiscal create(NotaFiscalDto notaFiscalDto) {
		Destino destino = destinoRegistrationService.procuraNome(notaFiscalDto.getNomeDestino());

		Fornecedor fornecedor = fornecedorRegistrationService
				.procuraFornecedorPeloNome(notaFiscalDto.getNomeFornecedor());

		Custeio custeio = custeioRegistrationService.procuraPorNome(notaFiscalDto.getNomeCusteio());

		NotaFiscal notaFiscal = new NotaFiscal(notaFiscalDto.getNumero(), notaFiscalDto.getSerie(), destino, fornecedor,
				custeio);
		
		System.out.println("flag");

		return notaFiscalRepository.save(notaFiscal);
	}

	public Optional<NotaFiscal> valida(NotaFiscalDto notaFiscalDto) {

		Optional<NotaFiscal> notaFiscalOpt = notaFiscalRepository.findById(notaFiscalDto.getId());

		return notaFiscalOpt;

	}

	public List<NotaFiscalDto> procuraNotas() {
		List<NotaFiscalDto> notasDto = new ArrayList<NotaFiscalDto>();

		List<NotaFiscal> notas = notaFiscalRepository.findAll();

		notas.forEach(nota -> {
			notasDto.add(NotaFiscalDto.geraNotaDto(nota));
		});

		return notasDto;
	}

	public List<NotaFiscalDto> procuraNotas(String nomeFornecedor) {

		Fornecedor fornecedor = fornecedorRegistrationService.procuraFornecedorPeloNome(nomeFornecedor);
		List<NotaFiscalDto> notasDto = new ArrayList<NotaFiscalDto>();

		List<NotaFiscal> notas = notaFiscalRepository.findByFornecedor(fornecedor);

		notas.forEach(nota -> {
			notasDto.add(NotaFiscalDto.geraNotaDto(nota));
		});

		return notasDto;
	}

	public List<NotaFiscalFiltroDto> geraFiltroNotaFiscal() {
		List<Fornecedor> todosFornecedores = fornecedorRegistrationService.procuraTodosFornecedores();

		List<NotaFiscalFiltroDto> filtroList = new ArrayList<>();

		todosFornecedores.forEach(fornecedor -> {
			filtroList.add(NotaFiscalFiltroDto.geraFiltro(fornecedor));
		});

		return filtroList;

	}

}
