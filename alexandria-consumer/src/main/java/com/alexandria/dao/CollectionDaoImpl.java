package com.alexandria.dao;

import com.alexandria.entities.CollectionEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CollectionDaoImpl extends AbstractDaoImpl<CollectionEntity> implements CollectionDao {

    private static final Logger logger = LogManager.getLogger(CollectionDaoImpl.class);

    CollectionDaoImpl() {
        super(CollectionEntity.class);
    }
}
