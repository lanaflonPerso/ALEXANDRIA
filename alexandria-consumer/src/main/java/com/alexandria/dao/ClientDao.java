package com.alexandria.dao;

import com.alexandria.entities.ClientEntity;
import com.alexandria.persistence.PersistenceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class ClientDao extends AbstractDao<ClientEntity> {

    private static final Logger logger = LogManager.getLogger(ClientDao.class);

    public ClientDao() {
        super(ClientEntity.class);
    }

    public void dbCreateClient(ClientEntity client) {

        logger.info("DB_CREATE BEGIN ");

        EntityManager session = beginTransaction();

        session.persist(client);

        commitTransaction();

        logger.info("DB_CREATE END ");
    }

    public void dbUpdateClient(ClientEntity client) {
        logger.info("DB_UPDATE BEGIN " + "idClient: " + client.getIdClient());

        EntityManager session = beginTransaction();

        ClientEntity client_ = session.merge(client);
        session.persist(client_);

        commitTransaction();

        logger.info("DB_UPDATE END " + "idClient: " + client.getIdClient());
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

    public ClientEntity dbFindClient(Integer idClient) {

        logger.info("DB_FIND_CLIENT BEGIN " + "idClient: " + idClient);

        EntityManager session = beginTransaction();

        ClientEntity client = session.find(ClientEntity.class, idClient);

        commitTransaction();

        logger.info("DB_FIND_CLIENT END " + "idClient: " + idClient);

        return client;
    }

    public List<ClientEntity> searchClients(String str) {

        logger.info("DB_SEARCH_CLIENTS BEGIN");

        EntityManager session = beginTransaction();

        TypedQuery<ClientEntity> query = session.createNamedQuery("ClientEntity.findFromFirstNameLastName", ClientEntity.class);
        query.setParameter("name", str);
        List<ClientEntity> searchClientsList = query.getResultList();

        commitTransaction();

        logger.info("DB_SEARCH_CLIENTS END");

        return searchClientsList;
    }
}
