package com.alexandria.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;

public abstract class AbstractDao<T> {

	private Class<T> entityClass;

	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void create(T entity) {
		EntityManager em = beginTransaction();
		getEntityManager().persist(entity);
		commitTransaction();
	}

	public void edit(T entity) {
		EntityManager em = beginTransaction();
		getEntityManager().merge(entity);
		commitTransaction();
	}

	public void remove(T entity) {
		EntityManager em = beginTransaction();
		getEntityManager().remove(getEntityManager().merge(entity));
		commitTransaction();
	}

	public T find(Object id) {
		T obj = getEntityManager().find(entityClass, id);
		closeEntityManager();
		return obj;
	}

	public List<T> findAll() {
		EntityManager em = getEntityManager();

		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		List<T> list = (List<T>) em.createQuery(cq).getResultList();

		closeEntityManager();
		return list;
	}

	public List<T> findRange(int[] range) {
		EntityManager em = getEntityManager();

		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		TypedQuery<Object> q = em.createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		List<T> list = (List<T>) q.getResultList();

		closeEntityManager();
		return list;
	}

	public int count() {
		EntityManager em = getEntityManager();

		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		int count = ((Long) q.getSingleResult()).intValue();

		closeEntityManager();
		return count;
	}
}
