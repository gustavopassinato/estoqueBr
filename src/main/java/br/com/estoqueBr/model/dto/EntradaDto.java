package br.com.estoqueBr.model.dto;

import java.util.List;

public class EntradaDto {

	private List<MaterialQuantidadeDto> materiaisQuantidades;
	private Long codigoNota;

	public Long getCodigoNota() {
		return codigoNota;
	}

	public void setCodigoNota(Long codigoNota) {
		this.codigoNota = codigoNota;
	}

	public List<MaterialQuantidadeDto> getMateriaisQuantidades() {
		return materiaisQuantidades;
	}

	public void setMateriaisQuantidades(List<MaterialQuantidadeDto> materiaisQuantidades) {
		this.materiaisQuantidades = materiaisQuantidades;
	}

}
