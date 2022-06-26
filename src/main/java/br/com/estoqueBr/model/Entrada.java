package br.com.estoqueBr.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quantidade;

	@Column(name = "data_registro")
	private LocalDateTime dataRegistro;

	@ManyToOne
	@JoinColumn(name = "id_nota")
	private NotaFiscal notaFiscal;

	@ManyToOne
	@JoinColumn(name = "id_material")
	private Material idMaterial;

	public Entrada() {
		// TODO Auto-generated constructor stub
	}

	public Entrada(Integer quantidade, Material material) {
		this.quantidade = quantidade;
		this.dataRegistro = LocalDateTime.now();
		this.idMaterial = material;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Material getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Material idMaterial) {
		this.idMaterial = idMaterial;
	}

}
