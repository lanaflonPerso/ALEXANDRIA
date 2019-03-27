package com.alexandria.dao;

import com.alexandria.entities.PublisherEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class PublisherDao extends AbstractDao<PublisherEntity> {

    private static final Logger logger = LogManager.getLogger(PublisherDao.class);

    public PublisherDao() {
        super(PublisherEntity.class);
    }

    public List<PublisherEntity> doPublishersList() {

        logger.info("DB_DO_LIST_PUBLISHERS BEGIN");

        EntityManager session = beginTransaction();

        List<PublisherEntity> publishers = session.createNamedQuery("PublisherEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_PUBLISHERS END");

        return publishers;
    }
}
