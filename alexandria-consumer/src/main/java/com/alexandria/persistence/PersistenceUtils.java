package com.alexandria.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

public class PersistenceUtils {

    private static final Logger logger = LogManager.getLogger(PersistenceUtils.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;

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

    public static String getJdbcUrl() {
        String jdbcUrl;
        if(emf != null) {
            Map<String, Object> emfProperties = emf.getProperties();
            jdbcUrl = (String) emfProperties.get("javax.persistence.jdbc.url");
        } else {
            jdbcUrl = "EntityManagerFactory not created";
        }
        return jdbcUrl;
    }

    public static EntityManager getEntityManager() throws HibernateException {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void closeEntityManager() throws HibernateException {
        if (em != null) em.close();
    }

    public static void shutdown() {
        logger.trace("SHUT_DOWN");
        // Close caches and connection pools
        if(emf != null) emf.close();
    }

    public static EntityManager beginTransaction() {
        logger.trace("BEGIN_TRANSACTION " + idTransaction);
        try {
            em = getEntityManager();
            em.getTransaction().begin();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
        return em;
    }

    public static void commitTransaction() {
        logger.trace("COMMIT_TRANSACTION " + idTransaction++);
        try {
            em.getTransaction().commit();
            closeEntityManager();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
    }

    private static void rollbackTransaction() {
        logger.trace("ROLLBACK_TRANSACTION " + idTransaction);
        try {
            em.getTransaction().rollback();
            closeEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
