package com.alexandria.dao;

import com.alexandria.entities.CategoryEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryDao extends AbstractDao<CategoryEntity> {

    private static final Logger logger = LogManager.getLogger(CategoryDao.class);

    public CategoryDao() {
        super(CategoryEntity.class);
    }
}
