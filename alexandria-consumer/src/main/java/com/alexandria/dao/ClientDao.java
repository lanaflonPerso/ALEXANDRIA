package com.alexandria.dao;

import com.alexandria.entities.ClientEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;

public class ClientDao extends AbstractDao<ClientEntity> {

    private static final Logger logger = LogManager.getLogger(ClientDao.class);

    public ClientDao() {
        super(ClientEntity.class);
    }

    public void dbRemoveClient(Integer idClient)
    {
        logger.info("DB_REMOVE BEGIN " + "idClient: " + idClient);

        EntityManager session = beginTransaction();

        ClientEntity client = session.find(ClientEntity.class, idClient);

        session.remove(client);

        commitTransaction();

        logger.info("DB_REMOVE END " + "idClient: " + idClient);
    }

    public List<ClientEntity> searchClients(String param) {

        logger.info("DB_SEARCH_CLIENTS BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<ClientEntity> query = em.createNamedQuery("ClientEntity.findFromFirstNameLastName", ClientEntity.class);
        query.setParameter("name", param);
        List<ClientEntity> searchClientsList = query.getResultList();

        closeEntityManager();

        logger.info("DB_SEARCH_CLIENTS END");

        return searchClientsList;
    }
}
