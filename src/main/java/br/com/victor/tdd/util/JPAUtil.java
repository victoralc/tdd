package br.com.victor.tdd.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("lance_unico");
		}
		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager manager) {
		manager.close();
	}

}
