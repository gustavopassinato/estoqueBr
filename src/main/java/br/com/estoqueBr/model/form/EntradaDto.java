package br.com.estoqueBr.model.form;

public class EntradaDto {

	private Integer quantidade;
	private Long materialCodigo;
	private String numeroNota;

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getMaterialCodigo() {
		return materialCodigo;
	}

	public void setMaterialCodigo(Long materialCodigo) {
		this.materialCodigo = materialCodigo;
	}

}
