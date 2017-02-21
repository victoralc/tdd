package br.com.victor.tdd.testes;

import java.math.BigDecimal;

import br.com.victor.tdd.model.Cliente;
import br.com.victor.tdd.model.Lance;
import br.com.victor.tdd.model.Promocao;
import br.com.victor.tdd.service.Sorteio;

public class TestaSorteio {

	public static void main(String[] args) {
		//Cenário
		Cliente victor = new Cliente("Victor Alcantara");
		Cliente rafael = new Cliente("Rafael");
		Cliente maria = new Cliente("Maria");
		Cliente joao = new Cliente("Joao");
		Cliente jose = new Cliente("Jose");
		Cliente roberto = new Cliente("Roberto");
		Cliente karine = new Cliente("Karine");
		
		Promocao promocao = new Promocao("Xbox");
		promocao.registra(new Lance(victor, new BigDecimal("800")));
		promocao.registra(new Lance(maria, new BigDecimal("500")));
		promocao.registra(new Lance(rafael, new BigDecimal("900")));
		promocao.registra(new Lance(joao, new BigDecimal("200")));
		promocao.registra(new Lance(jose, new BigDecimal("100")));
		promocao.registra(new Lance(roberto, new BigDecimal("700")));
		promocao.registra(new Lance(karine, new BigDecimal("600")));
		
		//Ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
	}
}
