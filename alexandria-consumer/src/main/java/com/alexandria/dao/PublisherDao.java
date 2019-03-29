package com.alexandria.dao;

import com.alexandria.entities.PublisherEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class PublisherDao extends AbstractDao<PublisherEntity> {

    private static final Logger logger = LogManager.getLogger(PublisherDao.class);

    PublisherDao() {
        super(PublisherEntity.class);
    }
}
