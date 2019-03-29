package com.alexandria.dao;

import com.alexandria.entities.GenreEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class GenreDao extends AbstractDao<GenreEntity> {

    private static final Logger logger = LogManager.getLogger(GenreDao.class);

    GenreDao() {
        super(GenreEntity.class);
    }
}
