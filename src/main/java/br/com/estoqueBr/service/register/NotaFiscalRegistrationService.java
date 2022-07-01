package br.com.estoqueBr.service.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.dto.NotaFiscalDto;
import br.com.estoqueBr.repository.NotaFiscalRepository;

@Service
public class NotaFiscalRegistrationService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	public void create(String numero, Destino destino, Fornecedor fornecedor, Custeio custeio) {
		NotaFiscal notaFiscal = new NotaFiscal(numero, destino, fornecedor, custeio);

		notaFiscalRepository.save(notaFiscal);
	}

	public Optional<NotaFiscal> valida(NotaFiscalDto notaFiscalDto) {

		Optional<NotaFiscal> notaFiscalOpt = notaFiscalRepository.findById(notaFiscalDto.getId());

		return notaFiscalOpt;

	}
}
