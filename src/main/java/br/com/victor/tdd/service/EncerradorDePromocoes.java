package br.com.victor.tdd.service;

import java.util.Date;
import java.util.List;

import br.com.victor.tdd.dao.PromocaoDao;
import br.com.victor.tdd.enums.Status;
import br.com.victor.tdd.model.Promocao;

public class EncerradorDePromocoes {

	public int encerra() {
		
		PromocaoDao dao = new PromocaoDao();
		
		int total = 0;
		List<Promocao> promocoes = dao.abertas();
		
		for (Promocao promocao : promocoes) {
			if (promocao.foraDaVigencia(new Date())) {
				promocao.setStatus(Status.ENCERRADA);
				dao.atualiza(promocao);
				total++;
			}
		}
		
		return total;
	}
}
