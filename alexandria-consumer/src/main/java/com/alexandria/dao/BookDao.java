package com.alexandria.dao;

import com.alexandria.entities.BookEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class BookDao {

    private static final Logger logger = LogManager.getLogger(BookDao.class);

    public List<BookEntity> doBooksList() {

        logger.info("DB_DO_LIST_BOOKS BEGIN");

        EntityManager session = beginTransaction();

        List<BookEntity> books = session.createNamedQuery("BookEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_BOOKS END");

        return books;
    }
}