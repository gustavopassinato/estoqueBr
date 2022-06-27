package br.com.estoqueBr.model.form;

public class SaidaForm {
	private String nomeDestino;
	private Long codigoMaterial;
	private String numeroOs;
	private Integer qunatidade;
	private String numNotaFiscal;

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

	public Integer getQunatidade() {
		return qunatidade;
	}

	public void setQunatidade(Integer qunatidade) {
		this.qunatidade = qunatidade;
	}

	public String getNumNotaFiscal() {
		return numNotaFiscal;
	}

	public void setNumNotaFiscal(String numNotaFiscal) {
		this.numNotaFiscal = numNotaFiscal;
	}

}
