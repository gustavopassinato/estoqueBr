package br.com.estoqueBr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String nome;
	private String modelo;
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;

	@ManyToOne
	@JoinColumn(name = "id_unid_med")
	private UnidadeMedida unidMed;

	public Material() {
		// TODO Auto-generated constructor stub
	}

	public Material(String nome, String modelo, Fabricante fabricante, UnidadeMedida unidMedida) {
		this.nome = nome;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.unidMed = unidMedida;

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setIdFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public UnidadeMedida getUnidMed() {
		return this.unidMed;
	}

	public void setIdUnidMed(UnidadeMedida unidMed) {
		this.unidMed = unidMed;
	}

}
