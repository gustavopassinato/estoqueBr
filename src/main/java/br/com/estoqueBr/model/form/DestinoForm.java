package br.com.estoqueBr.model.form;

import javax.validation.constraints.NotBlank;

public class DestinoForm {
	
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
