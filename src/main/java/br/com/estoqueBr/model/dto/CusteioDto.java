package br.com.estoqueBr.model.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
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
