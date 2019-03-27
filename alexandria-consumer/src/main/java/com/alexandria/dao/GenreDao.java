package com.alexandria.dao;

import com.alexandria.entities.GenreEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class GenreDao extends AbstractDao<GenreEntity> {

    private static final Logger logger = LogManager.getLogger(GenreDao.class);

    public GenreDao() {
        super(GenreEntity.class);
    }

    public List<GenreEntity> doGenresList() {

        logger.info("DB_DO_LIST_GENRE BEGIN");

        EntityManager session = beginTransaction();

        List<GenreEntity> genres = session.createNamedQuery("GenreEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_GENRE END");

        return genres;
    }
}
