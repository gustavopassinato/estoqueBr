package br.com.estoqueBr.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.repository.FornecedorRepository;

@Service
public class FornecedorRegistrationService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor create(String fornecedorNome, String fornecedorCnpj) {
		Fornecedor fornecedor = new Fornecedor(fornecedorNome, fornecedorCnpj);
		
		return fornecedorRepository.save(fornecedor);
	}
}
