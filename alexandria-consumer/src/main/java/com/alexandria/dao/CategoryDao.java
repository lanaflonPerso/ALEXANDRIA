package com.alexandria.dao;

import com.alexandria.entities.CategoryEntity;

import java.util.List;

public interface CategoryDao extends AbstractDao<CategoryEntity> {

    void create(CategoryEntity entity);

    void update(CategoryEntity entity);

    void remove(CategoryEntity entity);

    CategoryEntity find(Object id);

    List<CategoryEntity> findAll();

    List<CategoryEntity> findRange(int iMin, int nb);

    int count();

    List<CategoryEntity> findFromParent(int parent);
}