package br.edu.projetovenda.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	private static EntityManagerFactory entityManager;

	static {
		entityManager = Persistence.createEntityManagerFactory("Projeto-vendaPU");
	}

	public static EntityManager getEntityManager() {
		return entityManager.createEntityManager();
	}
}
