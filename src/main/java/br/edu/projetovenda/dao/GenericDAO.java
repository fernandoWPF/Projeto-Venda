package br.edu.projetovenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class GenericDAO<T> {
	@PersistenceContext
	EntityManager entityManager;

	private Class<T> clazz;

	public GenericDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) getEntityManager().getReference(clazz.getClass(), id);
	}

	public T salvar(T object) {
		return getEntityManager().merge(object);
	}

	public void excluir(T object) {
		getEntityManager().remove(object);
	}

	public void excluir(Long id) {
		excluir(getById(id));
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		TypedQuery<T> q = (TypedQuery<T>) getEntityManager()
				.createQuery("SELECT o FROM " + clazz.getClass().getSimpleName() + " o ", clazz.getClass());

		List<T> list = q.getResultList();
		return list;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
