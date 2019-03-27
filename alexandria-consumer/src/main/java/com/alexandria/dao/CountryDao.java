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

    public List<CountryEntity> doCountriesList() {

        logger.info("DB_DO_LIST_COUNTRIES BEGIN");

        EntityManager session = beginTransaction();

        List<CountryEntity> countries = session.createNamedQuery("CountryEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_COUNTRIES END");

        return countries;
    }
}
