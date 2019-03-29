package com.alexandria.dao;

import com.alexandria.entities.AuthorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorDaoImpl extends AbstractDaoImpl<AuthorEntity> implements AuthorDao {

    private static final Logger logger = LogManager.getLogger(AuthorDaoImpl.class);

    AuthorDaoImpl() {
        super(AuthorEntity.class);
    }
}
