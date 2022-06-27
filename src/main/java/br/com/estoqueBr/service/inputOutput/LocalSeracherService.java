package br.com.estoqueBr.service.inputOutput;

import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.NotaFiscal;
import br.com.estoqueBr.model.OrdemServico;
import br.com.estoqueBr.repository.DestinoRepository;
import br.com.estoqueBr.repository.MaterialRepository;
import br.com.estoqueBr.repository.NotaFiscalRepository;
import br.com.estoqueBr.repository.OrdemServicoRepository;

@Service
public class LocalSeracherService {

	@Autowired
	private DestinoRepository destinoRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public Destino searchForDestino(String nomeDestino) {
		Optional<Destino> destino = destinoRepository.findByNome(nomeDestino);

		if (destino.isEmpty()) {
			throw new NullPointerException();
		}
		return destino.get();
	}

	public Material searchForMaterial(Long codigoMateiral) {

		Optional<Material> material = materialRepository.findById(codigoMateiral);

		if (material.isEmpty()) {
			throw new NullPointerException();
		}
		return material.get();
	}

	public NotaFiscal searchForNotaFiscal(String numeroNota) {
		System.out.println(numeroNota);
		Optional<NotaFiscal> notaFiscal = notaFiscalRepository.findByNumero(numeroNota);

		if (notaFiscal.isEmpty()) {
			throw new NullPointerException();
		}
		return notaFiscal.get();
	}

	public OrdemServico searchForOrdemServico(String numeroOs) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findByNumero(numeroOs);

		if (ordemServico.isEmpty()) {
			throw new NullPointerException();
		}
		return ordemServico.get();
	}
}
