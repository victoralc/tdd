package br.com.victor.tdd.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.victor.tdd.enums.Local;
import br.com.victor.tdd.enums.Status;
import br.com.victor.tdd.util.DateUtils;

@Entity
public class Promocao {

	@Id 
	@GeneratedValue
	private Integer id;
	
	private String nome;
	private BigDecimal valorMaximo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private Local entrega;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTA;
	
	private boolean receberEmDinheiro = false;
	
	@JoinColumn(name="promocao_id")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lance> lances = new ArrayList<>();
	
	//Registra um novo lance
	public void registra(Lance lance) {
		this.lances.add(lance);
	}
	
	/**
	 * Verifica se promoção pode ser encerrada
	 */
	public boolean foraDaVigencia(Date dataBase) {
		int dias = DateUtils.diferencaEmDias(this.data, dataBase);
		return dias >= 30;
	}
	
	//gettters e setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Local getEntrega() {
		return entrega;
	}

	public void setEntrega(Local entrega) {
		this.entrega = entrega;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isReceberEmDinheiro() {
		return receberEmDinheiro;
	}

	public void setReceberEmDinheiro(boolean receberEmDinheiro) {
		this.receberEmDinheiro = receberEmDinheiro;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}
	
}
