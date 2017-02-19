package br.com.victor.tdd.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Lance {

	@Id
	@GeneratedValue
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	private BigDecimal valor;

	@ManyToOne
	private Cliente cliente;

	@Embedded
	private Cupom cupom;

	public Lance() {
	}

	public Lance(Cliente cliente, BigDecimal valor) {
		this(cliente, valor, null);
	}

	public Lance(Cliente cliente, BigDecimal valor, Cupom cupom) {
		this.cliente = cliente;
		this.valor = valor;
		this.cupom = cupom;
		this.data = new Date();
	}

	// getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

}
