package com.alexandria.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtils {

    private static final Logger logger = LogManager.getLogger(PersistenceUtils.class);

    private static EntityManagerFactory ourSessionFactory;
    private static EntityManager session;
    // For debug purpose
    private static int idTransaction;

//    log4j:
//    import org.apache.logging.log4j.LogManager;
//    import org.apache.logging.log4j.Logger;
//    private static final Logger logger = LogManager.getLogger(PersistenceUtils.class);
//    logger.info("Info level log");
//    logger.debug("Debug level log");
//    logger.warn("Warn level log");
//    logger.error("Error level log");
//    logger.trace("Trace level log");

    public static void init() {

        // Singleton
        if(ourSessionFactory != null) {
            return;
        }

        try {
            ourSessionFactory = Persistence.createEntityManagerFactory( "PersistenceUnit" );

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        logger.trace("CONFIG_OK");
    }

    private static EntityManagerFactory getSessionFactory() throws HibernateException {
        return ourSessionFactory;
    }

    private static EntityManager getSession() throws HibernateException {
        return ourSessionFactory.createEntityManager();
    }

    public static void shutdown() throws HibernateException {
        logger.trace("SHUT_DOWN");
        // Close caches and connection pools
        ourSessionFactory.close();
    }

    public static EntityManager beginTransaction() {
        logger.trace("BEGIN_TRANSACTION " + idTransaction);
        try {
            session = getSession();
            session.getTransaction().begin();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
        return session;
    }

    public static void commitTransaction() {
        logger.trace("COMMIT_TRANSACTION " + idTransaction++);
        try {
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
    }

    static void rollbackTransaction() {
        logger.trace("ROLLBACK_TRANSACTION " + idTransaction);
        try {
            session.getTransaction().rollback();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
