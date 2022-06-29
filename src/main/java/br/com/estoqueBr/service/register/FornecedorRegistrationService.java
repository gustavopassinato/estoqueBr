package br.com.estoqueBr.service.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.form.FornecedorDto;
import br.com.estoqueBr.repository.FornecedorRepository;
import br.com.estoqueBr.service.exceptions.FornecedorException;

@Service
public class FornecedorRegistrationService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor create(FornecedorDto fornecedorDto) {
		
		validaUnicidadeCnpj(fornecedorDto);
		
		Fornecedor fornecedor = new Fornecedor(fornecedorDto.getNome(), fornecedorDto.getCnpj());
		
		return fornecedorRepository.save(fornecedor);
	}
	
	public void validaUnicidadeCnpj(FornecedorDto fornecedorDto) {
		
		Optional<Fornecedor> fornecedor = fornecedorRepository.findByNome(fornecedorDto.getNome());
		
		if (fornecedor.isPresent()) {
			throw new FornecedorException("Fornecedor já encontrado com este mesmo CNPJ");
		}
	}
}
