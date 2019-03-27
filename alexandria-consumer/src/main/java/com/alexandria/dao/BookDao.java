package com.alexandria.dao;

import com.alexandria.entities.BookEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class BookDao extends AbstractDao<BookEntity> {

    private static final Logger logger = LogManager.getLogger(BookDao.class);

    public BookDao() {
        super(BookEntity.class);
    }
}
