package com.alexandria.dao;

import com.alexandria.entities.AuthorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorDao extends AbstractDao<AuthorEntity> {

    private static final Logger logger = LogManager.getLogger(AuthorDao.class);

    public AuthorDao() {
        super(AuthorEntity.class);
    }
}
