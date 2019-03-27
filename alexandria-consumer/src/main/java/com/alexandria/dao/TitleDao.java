package com.alexandria.dao;

import com.alexandria.entities.TitleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class TitleDao {

    private static final Logger logger = LogManager.getLogger(TitleDao.class);

    public List<TitleEntity> doTitlesList() {

        logger.info("DB_DO_LIST_TITLES BEGIN");

        EntityManager session = beginTransaction();

        List<TitleEntity> titles = session.createNamedQuery("TitleEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_TITLES END");

        return titles;
    }
}
