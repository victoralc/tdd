package br.com.victor.tdd.builders;

import java.math.BigDecimal;

import br.com.victor.tdd.model.Cliente;
import br.com.victor.tdd.model.Lance;
import br.com.victor.tdd.model.Promocao;

public class CriadorDePromocao {

	private Promocao promocao;
	
	public CriadorDePromocao para(String nome){
		promocao = new Promocao(nome);
		return this;
	}
	
	public CriadorDePromocao comLance(Cliente cliente, BigDecimal valor){
		promocao.registra(new Lance(cliente, valor));
		return this;
	}
	
	public Promocao cria(){
		return promocao;
	}
}
