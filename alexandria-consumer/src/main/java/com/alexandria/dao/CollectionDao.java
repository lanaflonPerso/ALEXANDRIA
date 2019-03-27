package com.alexandria.dao;

import com.alexandria.entities.CollectionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class CollectionDao extends AbstractDao<CollectionEntity> {

    private static final Logger logger = LogManager.getLogger(CollectionDao.class);

    public CollectionDao() {
        super(CollectionEntity.class);
    }
}
