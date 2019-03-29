package com.alexandria.dao;

import com.alexandria.entities.GenreEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenreDaoImpl extends AbstractDaoImpl<GenreEntity> implements GenreDao {

    private static final Logger logger = LogManager.getLogger(GenreDaoImpl.class);

    GenreDaoImpl() {
        super(GenreEntity.class);
    }
}
