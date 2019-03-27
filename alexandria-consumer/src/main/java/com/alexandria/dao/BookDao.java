package com.alexandria.dao;

import com.alexandria.entities.BookEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDao extends AbstractDao<BookEntity> {

    private static final Logger logger = LogManager.getLogger(BookDao.class);

    public BookDao() {
        super(BookEntity.class);
    }
}
