package com.alexandria.dao;

import com.alexandria.entities.TitleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class TitleDao extends AbstractDao<TitleEntity> {

    private static final Logger logger = LogManager.getLogger(TitleDao.class);

    public TitleDao() {
        super(TitleEntity.class);
    }
}
