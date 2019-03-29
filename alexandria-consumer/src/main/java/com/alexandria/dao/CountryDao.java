package com.alexandria.dao;

import com.alexandria.entities.CountryEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class CountryDao extends AbstractDao<CountryEntity> {

    private static final Logger logger = LogManager.getLogger(CountryDao.class);

    CountryDao() {
        super(CountryEntity.class);
    }
}
