package br.com.estoqueBr.service.register;

import java.awt.dnd.DragSourceMotionListener;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.dto.DestinoDto;
import br.com.estoqueBr.repository.DestinoRepository;
import br.com.estoqueBr.service.exceptions.DestinoException;

@Service
public class DestinoRegistrationService {

	@Autowired
	private DestinoRepository destinoRepository;

	public Destino create(DestinoDto destinoDto) {
		Destino destino = new Destino(destinoDto.getNome());

		return destinoRepository.save(destino);
	}
	
	public List<Destino> procuraTodosDestinos(){
		return destinoRepository.findAll();
	}

	public Destino procuraNome(String nomeDestino) {
		Optional<Destino> destinoOpt = destinoRepository.findByNome(nomeDestino);
		
		if (destinoOpt.isEmpty()) {
			throw new DestinoException("Destino não encontrado!");
		}
		
		return destinoOpt.get();
	}

}
