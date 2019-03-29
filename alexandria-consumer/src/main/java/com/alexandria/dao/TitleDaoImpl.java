package com.alexandria.dao;

import com.alexandria.entities.TitleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TitleDaoImpl extends AbstractDaoImpl<TitleEntity> implements TitleDao {

    private static final Logger logger = LogManager.getLogger(TitleDaoImpl.class);

    TitleDaoImpl() {
        super(TitleEntity.class);
    }
}
