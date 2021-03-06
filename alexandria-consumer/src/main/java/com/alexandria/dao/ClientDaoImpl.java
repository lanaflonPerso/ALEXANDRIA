package com.alexandria.dao;

import com.alexandria.entities.ClientEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;

public class ClientDaoImpl extends AbstractDaoImpl<ClientEntity> implements ClientDao {

    private static final Logger logger = LogManager.getLogger(ClientDaoImpl.class);

    ClientDaoImpl() {
        super(ClientEntity.class);
    }

    @Override
    public List<ClientEntity> findFromFirstNameLastName(String name) {

        logger.info("DB_FIND_FROM_FIRST_NAME_LAST_NAME BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<ClientEntity> query = em.createNamedQuery("ClientEntity.findFromFirstNameLastName", ClientEntity.class);
        query.setParameter("name", name);
        List<ClientEntity> searchClientsList = query.getResultList();

        closeEntityManager();

        logger.info("DB_FIND_FROM_FIRST_NAME_LAST_NAME END");

        return searchClientsList;
    }

    @Override
    public List<ClientEntity> findFromEmailPassword(String email, String password) {

        logger.info("DB_FIND_FROM_EMAIL_PASSWORD BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<ClientEntity> query = em.createNamedQuery("ClientEntity.findFromEmailPassword", ClientEntity.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        List<ClientEntity> searchClientsList = query.getResultList();

        closeEntityManager();

        logger.info("DB_FIND_FROM_EMAIL_PASSWORD END");

        return searchClientsList;
    }

    @Override
    public List<ClientEntity> findFromEmail(String email) {

        logger.info("DB_FIND_FROM_EMAIL BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<ClientEntity> query = em.createNamedQuery("ClientEntity.findFromEmail", ClientEntity.class);
        query.setParameter("email", email);
        List<ClientEntity> searchClientsList = query.getResultList();

        closeEntityManager();

        logger.info("DB_FIND_FROM_EMAIL END");

        return searchClientsList;
    }
}
