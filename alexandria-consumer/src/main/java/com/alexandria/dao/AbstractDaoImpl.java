package com.alexandria.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

	private static final Logger logger = LogManager.getLogger(AbstractDaoImpl.class);

	private Class<T> entityClass;

	public AbstractDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public void create(T entity) {
		logger.info(entityClass + " DB_CREATE BEGIN");

		EntityManager em = beginTransaction();
		em.persist(entity);
		commitTransaction();

		logger.info(entityClass + " DB_CREATE END");
	}

	@Override
	public void update(T entity) {
		logger.info(entityClass + " DB_UPDATE BEGIN");

		EntityManager em = beginTransaction();
		em.merge(entity);
		commitTransaction();

		logger.info(entityClass + " DB_UPDATE END");
	}

	@Override
	public void remove(T entity) {
		logger.info(entityClass + " DB_REMOVE_ BEGIN");

		EntityManager em = beginTransaction();
		em.remove(em.merge(entity));
		commitTransaction();

		logger.info(entityClass + " DB_REMOVE_ END");
	}

	// TODO : unify remove methods -> cf. comments in OrderHeaderDao
	void remove__(Object id) {
		logger.info(entityClass + " DB_REMOVE__ BEGIN " + "id: " + id);

		EntityManager em = beginTransaction();
		em.remove(em.find(entityClass, id));
		commitTransaction();

		logger.info(entityClass + " DB_REMOVE__ END " + "id: " + id);
	}

	@Override
	public T find(Object id) {
		logger.info(entityClass + " DB_FIND BEGIN " + "id: " + id);

		T obj = getEntityManager().find(entityClass, id);
		closeEntityManager();

		logger.info(entityClass + " DB_FIND END " + "id: " + id);

		return obj;
	}

	@Override
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

	@Override
	public List<T> findRange(int iMin, int nb) {

		if(iMin < 0 || nb < 0) logger.error("iMin or nb < 0");

		logger.info(entityClass + " DB_FIND_RANGE BEGIN" + " iMin: " + iMin + " nb: " + nb);

		EntityManager em = getEntityManager();

		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		TypedQuery<Object> q = em.createQuery(cq);
		q.setMaxResults(nb);
		q.setFirstResult(iMin);
		List<T> list = (List<T>) q.getResultList();

		closeEntityManager();

		logger.info(entityClass + " DB_FIND_RANGE END" + " iMin: " + iMin + " nb: " + nb);

		return list;
	}

	@Override
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
