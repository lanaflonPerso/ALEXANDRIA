package com.alexandria.dao;

import com.alexandria.entities.PublisherEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PublisherDaoImpl extends AbstractDaoImpl<PublisherEntity> implements PublisherDao {

    private static final Logger logger = LogManager.getLogger(PublisherDaoImpl.class);

    PublisherDaoImpl() {
        super(PublisherEntity.class);
    }
}
