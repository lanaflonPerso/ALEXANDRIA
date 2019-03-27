package com.alexandria.dao;

import com.alexandria.entities.CategoryEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class CategoryDao {

    private static final Logger logger = LogManager.getLogger(CategoryEntity.class);

    public List<CategoryEntity> doCategoriesList() {

        logger.info("DB_DO_LIST_CATEGORIES BEGIN");

        EntityManager session = beginTransaction();

        List<CategoryEntity> categories = session.createNamedQuery("CategoryEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_CATEGORIES END");

        return categories;
    }
}
