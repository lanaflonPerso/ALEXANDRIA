package com.alexandria.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;

public abstract class AbstractDao<T> {

	private static final Logger logger = LogManager.getLogger(AbstractDao.class);

	private Class<T> entityClass;

	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void create(T entity) {
		logger.info(entityClass + " DB_CREATE BEGIN");

		EntityManager em = beginTransaction();
		em.persist(entity);
		commitTransaction();

		logger.info(entityClass + " DB_CREATE END");
	}

	public void update(T entity) {
		logger.info(entityClass + " DB_UPDATE BEGIN");

		EntityManager em = beginTransaction();
		em.merge(entity);
		commitTransaction();

		logger.info(entityClass + " DB_UPDATE END");
	}

	public void remove_(T entity) {
		logger.info(entityClass + " DB_REMOVE BEGIN");

		EntityManager em = beginTransaction();
		em.remove(em.merge(entity));
		commitTransaction();

		logger.info(entityClass + " DB_REMOVE END");
	}

	// TODO : unify remove methods
	public void remove(Object id) {
		logger.info(entityClass + " DB_REMOVE2 BEGIN " + "id: " + id);

		EntityManager em = beginTransaction();
		em.remove(em.find(entityClass, id));
		commitTransaction();

		logger.info(entityClass + " DB_REMOVE2 END " + "id: " + id);
	}

	public T find(Object id) {
		logger.info(entityClass + " DB_FIND BEGIN " + "id: " + id);

		T obj = getEntityManager().find(entityClass, id);
		closeEntityManager();

		logger.info(entityClass + " DB_FIND END " + "id: " + id);

		return obj;
	}

	public List<T> findAll() {
		logger.info(entityClass + " DB_FIND_ALL BEGIN");

		EntityManager em = getEntityManager();

		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		List<T> list = (List<T>) em.createQuery(cq).getResultList();

		closeEntityManager();

		logger.info(entityClass + " DB_FIND_ALL END");

		return list;
	}

	public List<T> findRange(int[] range) {
		logger.info(entityClass + " DB_FIND_RANGE BEGIN");

		EntityManager em = getEntityManager();

		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		TypedQuery<Object> q = em.createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		List<T> list = (List<T>) q.getResultList();

		closeEntityManager();

		logger.info(entityClass + " DB_FIND_RANGE END");

		return list;
	}

	public int count() {
		logger.info(entityClass + " DB_COUNT BEGIN");

		EntityManager em = getEntityManager();

		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(em.getCriteriaBuilder().count(rt));
		javax.persistence.Query q = em.createQuery(cq);
		int count = ((Long) q.getSingleResult()).intValue();

		closeEntityManager();

		logger.info(entityClass + " DB_COUNT END");

		return count;
	}
}
