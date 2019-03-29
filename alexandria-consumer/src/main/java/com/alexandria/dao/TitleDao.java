package com.alexandria.dao;

import com.alexandria.entities.TitleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TitleDao extends AbstractDao<TitleEntity> {

    private static final Logger logger = LogManager.getLogger(TitleDao.class);

    TitleDao() {
        super(TitleEntity.class);
    }
}
