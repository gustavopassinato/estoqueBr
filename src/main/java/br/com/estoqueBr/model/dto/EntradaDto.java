package br.com.estoqueBr.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EntradaDto {

	private List<MaterialQuantidadeDto> materiaisQuantidades = new ArrayList<MaterialQuantidadeDto>();
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
