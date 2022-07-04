package br.com.estoqueBr.model.dto;

import br.com.estoqueBr.model.Fornecedor;

public class NotaFiscalFiltroDto {

	private Long idFornecedor;
	private String nomeFornecedor;
	
	public static NotaFiscalFiltroDto geraFiltro(Fornecedor fornecedor) {
		NotaFiscalFiltroDto filtro = new NotaFiscalFiltroDto();
		filtro.setIdFornecedor(fornecedor.getId());
		filtro.setNomeFornecedor(fornecedor.getNome());
		
		return filtro;
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

}
