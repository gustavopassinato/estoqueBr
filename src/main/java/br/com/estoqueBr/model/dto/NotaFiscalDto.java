package br.com.estoqueBr.model.dto;

import br.com.estoqueBr.model.NotaFiscal;

public class NotaFiscalDto {

	private Long id;
	private String numero;
	private Integer serie;
	private String nomeDestino;
	private String nomeFornecedor;
	private String nomeCusteio;

	public static NotaFiscalDto geraNotaDto(NotaFiscal notaFiscal) {
		NotaFiscalDto notaDto = new NotaFiscalDto();

		notaDto.setId(notaFiscal.getId());
		notaDto.setNumero(notaFiscal.getNumero());

		return notaDto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDestino() {
		return nomeDestino;
	}

	public void setNomeDestino(String nomeDestino) {
		this.nomeDestino = nomeDestino;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNomeCusteio() {
		return nomeCusteio;
	}

	public void setNomeCusteio(String nomeCusteio) {
		this.nomeCusteio = nomeCusteio;
	}

}
