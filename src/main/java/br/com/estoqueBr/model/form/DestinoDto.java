package br.com.estoqueBr.model.form;

import javax.validation.constraints.NotBlank;

public class DestinoDto {
	
	@NotBlank(message = "O nome do destino não pode ser vazio!")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
