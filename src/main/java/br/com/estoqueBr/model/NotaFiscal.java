package br.com.estoqueBr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;

	private Integer serie;

	@ManyToOne
	@JoinColumn(name = "id_destino")
	private Destino destino;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(name = "id_custeio")
	private Custeio custeio;

	public NotaFiscal() {
		// TODO Auto-generated constructor stub
	}

	public NotaFiscal(String numero, Integer serie, Destino destino, Fornecedor fornecedor, Custeio custeio) {
		this.serie = serie;
		this.numero = numero;
		this.destino = destino;
		this.fornecedor = fornecedor;
		this.custeio = custeio;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Custeio getCusteio() {
		return custeio;
	}

	public void setCusteio(Custeio custeio) {
		this.custeio = custeio;
	}

}
