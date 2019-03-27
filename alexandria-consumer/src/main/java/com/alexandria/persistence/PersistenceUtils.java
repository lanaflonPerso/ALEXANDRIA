package com.alexandria.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Map;

public class PersistenceUtils {

    private static final Logger logger = LogManager.getLogger(PersistenceUtils.class);

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    // For debug purpose
    private static int idTransaction;

    public static void init() {

        // Singleton
        if(emf != null) {
            return;
        }

        try {
            emf = Persistence.createEntityManagerFactory( "PersistenceUnit" );

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        logger.trace("CONFIG_OK");
    }

    public static String getJdbcUrl() throws HibernateException {
        String jdbcUrl;
        if(emf != null) {
            Map<String, Object> emfProperties = emf.getProperties();
            jdbcUrl = (String) emfProperties.get("javax.persistence.jdbc.url");
        } else {
            jdbcUrl = "EntityManagerFactory not created";
        }
        return jdbcUrl;
    }

    private static EntityManager getEntityManager() throws HibernateException {
        EntityManager manager = threadLocal.get();
        if (manager == null || !manager.isOpen()) {
            manager = emf.createEntityManager();
            threadLocal.set(manager);
        }
        return manager;
    }

    private static void closeEntityManager() throws HibernateException {
        EntityManager em = threadLocal.get();
        threadLocal.set(null);
        if (em != null) em.close();
    }

    public static void shutdown() throws HibernateException {
        logger.trace("SHUT_DOWN");
        // Close caches and connection pools
        if(emf != null) emf.close();
    }

    public static EntityManager beginTransaction() {
        logger.trace("BEGIN_TRANSACTION " + idTransaction);
        EntityManager em = getEntityManager();
        try {
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
        return em;
    }

    public static void commitTransaction() {
        logger.trace("COMMIT_TRANSACTION " + idTransaction++);
        try {
            getEntityManager().getTransaction().commit();
            closeEntityManager();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
    }

    private static void rollbackTransaction() {
        logger.trace("ROLLBACK_TRANSACTION " + idTransaction);
        try {
            getEntityManager().getTransaction().rollback();
            closeEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }
}
