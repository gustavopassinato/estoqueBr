package br.com.estoqueBr.model.dto;

import javax.validation.constraints.NotBlank;

public class CusteioDto {
	
	@NotBlank(message = "O nome do produto não pode ser vazio!")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
