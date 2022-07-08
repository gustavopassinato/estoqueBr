package br.com.estoqueBr.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EntradaDto {

	private Integer quantidade;
	private Long idMaterial;
	private Long codigoNota;

	public Long getCodigoNota() {
		return codigoNota;
	}

	public void setCodigoNota(Long codigoNota) {
		this.codigoNota = codigoNota;
	}

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
