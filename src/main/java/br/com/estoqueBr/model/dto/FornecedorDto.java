package br.com.estoqueBr.model.dto;

import javax.validation.constraints.NotEmpty;

import br.com.estoqueBr.model.Fornecedor;

public class FornecedorDto {
	
	@NotEmpty(message = "O nome não pode ser vazio!")
	private String nome;
	
	@NotEmpty(message = "O CNPJ não pode ser vazio!")
	private String cnpj;
	
	public static FornecedorDto geraFornecedorDto(Fornecedor fornecedor) {
		FornecedorDto fornecedorDto = new FornecedorDto();
		fornecedorDto.setNome(fornecedor.getNome());
		fornecedorDto.setCnpj(fornecedor.getCnpj());
		
		return fornecedorDto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
