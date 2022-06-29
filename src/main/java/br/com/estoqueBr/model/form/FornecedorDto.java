package br.com.estoqueBr.model.form;

import javax.validation.constraints.NotEmpty;

public class FornecedorDto {
	
	@NotEmpty(message = "O nome não pode ser vazio!")
	private String nome;
	
	@NotEmpty(message = "O CNPJ não pode ser vazio!")
	private String cnpj;

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
