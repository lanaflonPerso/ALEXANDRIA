package com.alexandria.dao;

import com.alexandria.entities.CountryEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CountryDaoImpl extends AbstractDaoImpl<CountryEntity> implements CountryDao {

    private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);

    CountryDaoImpl() {
        super(CountryEntity.class);
    }
}
