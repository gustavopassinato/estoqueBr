package br.com.estoqueBr.model.dto;

import javax.validation.constraints.NotBlank;

import br.com.estoqueBr.model.NotaFiscal;

public class NotaFiscalDto {

	private Long id;
	
	private String numero;

	private Integer serie;
	
	@NotBlank(message = "O código do destino é obrigatório!")
	private Long idDestino;
	private String nomeDestino;

	@NotBlank(message = "O código do fornecedor é obrigatório!")
	private Long idFornecedor;
	private String nomeFornecedor;

	@NotBlank(message = "O código do centro de custeio é obrigatório!")
	private Long idCusteio;
	private String nomeCusteio;
	
	public static NotaFiscalDto geraNotaDto(NotaFiscal notaFiscal) {
		NotaFiscalDto notaDto = new NotaFiscalDto();
		
		notaDto.setId(notaFiscal.getId());
		notaDto.setIdCusteio(notaFiscal.getCusteio().getId());
		notaDto.setIdDestino(notaFiscal.getDestino().getId());
		notaDto.setIdFornecedor(notaFiscal.getFornecedor().getId());
		
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

	public Long getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}

	public String getNomeDestino() {
		return nomeDestino;
	}

	public void setNomeDestino(String nomeDestino) {
		this.nomeDestino = nomeDestino;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public Long getIdCusteio() {
		return idCusteio;
	}

	public void setIdCusteio(Long idCusteio) {
		this.idCusteio = idCusteio;
	}

	public String getNomeCusteio() {
		return nomeCusteio;
	}

	public void setNomeCusteio(String nomeCusteio) {
		this.nomeCusteio = nomeCusteio;
	}

}
