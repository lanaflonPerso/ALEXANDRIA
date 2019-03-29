package com.alexandria.dao;

import com.alexandria.entities.CategoryEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class CategoryDao extends AbstractDao<CategoryEntity> {

    private static final Logger logger = LogManager.getLogger(CategoryDao.class);

    CategoryDao() {
        super(CategoryEntity.class);
    }
}
