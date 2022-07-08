package br.com.estoqueBr.model.dto;

public class MaterialDto {
	
	private String nome;
	private String modelo;
	private String fabricanteNome;
	private String unidadeMedidaNome;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricanteNome() {
		return fabricanteNome;
	}

	public void setFabricanteNome(String fabricanteNome) {
		this.fabricanteNome = fabricanteNome;
	}

	public String getUnidadeMedidaNome() {
		return unidadeMedidaNome;
	}

	public void setUnidadeMedidaNome(String unidadeMedidaNome) {
		this.unidadeMedidaNome = unidadeMedidaNome;
	}

}
