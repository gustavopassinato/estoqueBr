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
public class Saida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer quantidade;
	private String descricao;

	@Column(name = "data_registro")
	private LocalDateTime dataRegistro;

	@ManyToOne
	@JoinColumn(name = "id_ordem_servico")
	private OrdemServico ordemServico;

	@ManyToOne
	@JoinColumn(name = "id_material")
	private Material material;

	@ManyToOne
	@JoinColumn(name = "id_destino_real")
	private Destino destinoReal;

	@ManyToOne
	@JoinColumn(name = "id_destino_previsto")
	private Destino destinoPrevisto;

	public Saida(LocalDateTime dataRegistro, Destino destinoPrevisto, Destino destinoReal, Material material,
			OrdemServico ordemServico, Integer quantidade) {
		this.dataRegistro = dataRegistro;
		this.destinoPrevisto = destinoPrevisto;
		this.destinoReal = destinoReal;
		this.material = material;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Destino getDestinoReal() {
		return destinoReal;
	}

	public void setDestinoReal(Destino destinoReal) {
		this.destinoReal = destinoReal;
	}

	public Destino getDestinoPrevisto() {
		return destinoPrevisto;
	}

	public void setDestinoPrevisto(Destino destinoPrevisto) {
		this.destinoPrevisto = destinoPrevisto;
	}

}
