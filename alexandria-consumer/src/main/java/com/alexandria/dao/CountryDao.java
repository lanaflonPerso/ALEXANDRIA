package com.alexandria.dao;

import com.alexandria.entities.CountryEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class CountryDao extends AbstractDao<CountryEntity> {

    private static final Logger logger = LogManager.getLogger(CountryDao.class);

    public CountryDao() {
        super(CountryEntity.class);
    }
}
