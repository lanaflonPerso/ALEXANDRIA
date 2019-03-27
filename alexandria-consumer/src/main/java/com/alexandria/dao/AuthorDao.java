package com.alexandria.dao;

import com.alexandria.entities.AuthorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class AuthorDao extends AbstractDao<AuthorEntity> {

    private static final Logger logger = LogManager.getLogger(AuthorDao.class);

    public AuthorDao() {
        super(AuthorEntity.class);
    }
}
