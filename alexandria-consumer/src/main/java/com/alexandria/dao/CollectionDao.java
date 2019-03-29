package com.alexandria.dao;

import com.alexandria.entities.CollectionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CollectionDao extends AbstractDao<CollectionEntity> {

    private static final Logger logger = LogManager.getLogger(CollectionDao.class);

    CollectionDao() {
        super(CollectionEntity.class);
    }
}
