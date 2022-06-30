package br.com.estoqueBr.model.dto;

public class SaidaDto {
	private String nomeDestino;
	private Long codigoMaterial;
	private String numeroOs;
	private Integer quantidade;
	private Long codigoNota;

	public Long getCodigoNota() {
		return codigoNota;
	}

	public void setCodigoNota(Long codigoNota) {
		this.codigoNota = codigoNota;
	}

	public String getNomeDestino() {
		return nomeDestino;
	}

	public void setNomeDestino(String nomeDestino) {
		this.nomeDestino = nomeDestino;
	}

	public Long getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(Long codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public String getNumeroOs() {
		return numeroOs;
	}

	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer qunatidade) {
		this.quantidade = qunatidade;
	}

}
