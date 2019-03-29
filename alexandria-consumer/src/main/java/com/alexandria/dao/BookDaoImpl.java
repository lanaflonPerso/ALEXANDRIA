package com.alexandria.dao;

import com.alexandria.entities.BookEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDaoImpl extends AbstractDaoImpl<BookEntity> implements BookDao {

    private static final Logger logger = LogManager.getLogger(BookDaoImpl.class);

    BookDaoImpl() {
        super(BookEntity.class);
    }
}
