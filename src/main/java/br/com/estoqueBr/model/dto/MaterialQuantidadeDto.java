package br.com.estoqueBr.model.dto;

public class MaterialQuantidadeDto {

	private Integer quantidade;
	private Long codigoMaterial;
	
	public MaterialQuantidadeDto(Long codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	
	public MaterialQuantidadeDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(Long codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

}
