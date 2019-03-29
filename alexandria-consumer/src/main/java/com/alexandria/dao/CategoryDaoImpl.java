package com.alexandria.dao;

import com.alexandria.entities.CategoryEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryDaoImpl extends AbstractDaoImpl<CategoryEntity> implements CategoryDao {

    private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

    CategoryDaoImpl() {
        super(CategoryEntity.class);
    }
}
