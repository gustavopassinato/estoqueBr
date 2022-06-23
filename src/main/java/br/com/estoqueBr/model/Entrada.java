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
	private Integer id;

	private Integer quantidade;

	@Column(name = "data_registro")
	private LocalDateTime dataRegistro;

	@ManyToOne
	@JoinColumn(name="id_custeio")
	private Custeio idCusteio;

	@ManyToOne
	@JoinColumn(name="id_material")
	private Material idMaterial;

	@ManyToOne
	@JoinColumn(name="id_destino")
	private Destino idDestino;

	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private Fornecedor idFornecedor;
	
	public Entrada(Integer quantidade, LocalDateTime dataRegistro, Custeio custeio, Material material, Destino destino, Fornecedor fornecedor) {
		this.quantidade = quantidade;
		this.dataRegistro = dataRegistro;
		this.idCusteio = custeio;
		this.idMaterial = material;
		this.idDestino = destino;
		this.idFornecedor = fornecedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Custeio getIdCusteio() {
		return idCusteio;
	}

	public void setIdCusteio(Custeio idCusteio) {
		this.idCusteio = idCusteio;
	}

	public Material getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Material idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Destino getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Destino idDestino) {
		this.idDestino = idDestino;
	}

	public Fornecedor getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Fornecedor idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

}
