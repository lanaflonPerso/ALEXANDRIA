package com.alexandria.dao;

import com.alexandria.entities.BookEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class BookDao extends AbstractDao<BookEntity> {

    private static final Logger logger = LogManager.getLogger(BookDao.class);

    BookDao() {
        super(BookEntity.class);
    }
}
