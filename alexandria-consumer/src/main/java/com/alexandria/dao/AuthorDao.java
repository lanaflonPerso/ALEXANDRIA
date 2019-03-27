package com.alexandria.dao;

import com.alexandria.entities.AuthorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class AuthorDao {

    private static final Logger logger = LogManager.getLogger(AuthorDao.class);

    public List<AuthorEntity> doAuthorsList() {

        logger.info("DB_DO_LIST_AUTHORS BEGIN");

        EntityManager session = beginTransaction();

        List<AuthorEntity> authors = session.createNamedQuery("AuthorEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_AUTHORS END");

        return authors;
    }
}
