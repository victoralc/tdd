package br.com.victor.tdd.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.victor.tdd.controller.util.FacesUtils;
import br.com.victor.tdd.util.Transacional;

@Named
@RequestScoped
public class LimpaBancoDeDadosBean {

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private FacesUtils facesUtils;
	
	@Transacional
	public String limpa() {
		deletaTodosOsDadosDe("Lance");
		deletaTodosOsDadosDe("Cliente");
		deletaTodosOsDadosDe("Promocao");
		facesUtils.info("Banco de dados limpo com sucesso!");
		return "/index";
	}

	private void deletaTodosOsDadosDe(String entitade) {
		entityManager.createQuery("delete from " + entitade).executeUpdate();
	}
}
