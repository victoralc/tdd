package br.com.victor.tdd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.victor.tdd.model.Lance;
import br.com.victor.tdd.model.Promocao;

public class Sorteio {
	
	private BigDecimal maiorDeTodos = new BigDecimal("-100000000.0");
	private BigDecimal menorDeTodos = new BigDecimal("100000000.0");
	
	private List<Lance> menoresLances;

	public void sorteia(Promocao promocao){
		encontraOMaiorEMenorLanceNa(promocao);
		encontraOs3MenoresLancesNa(promocao);
	}

	private void encontraOMaiorEMenorLanceNa(Promocao promocao) {
		for (Lance lance : promocao.getLances()) {
			if (lance.getValor().compareTo(maiorDeTodos) == 1) 
				maiorDeTodos = lance.getValor();
			
			if(lance.getValor().compareTo(menorDeTodos) == -1)
				menorDeTodos = lance.getValor();
		}
	}

	private void encontraOs3MenoresLancesNa(Promocao promocao) {
		menoresLances = new ArrayList<Lance>(promocao.getLances());
		menoresLances.sort((l1, l2) -> l1.getValor().compareTo(l2.getValor()));
		
		menoresLances = menoresLances.stream()
		.filter(l1 -> l1.getValor().compareTo(maiorDeTodos) == -1 
						|| l1.getValor().compareTo(maiorDeTodos) == 0)
		.limit(3)
		.collect(Collectors.toList());
	}
	
	public BigDecimal getMaiorLance() {
		return maiorDeTodos;
	}
	
	public BigDecimal getMenorLance() {
		return menorDeTodos;
	}
	
	public List<Lance> getTresMenoresLances() {
		return menoresLances;
	}
}
