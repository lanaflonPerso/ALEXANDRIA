package com.alexandria.dao;

import com.alexandria.entities.CategoryEntity;
import com.alexandria.entities.ProductEntity;

import java.util.List;

public interface ProductDao extends AbstractDao<ProductEntity> {

    void create(ProductEntity entity);

    void update(ProductEntity entity);

    void remove(ProductEntity entity);

    ProductEntity find(Object id);

    List<ProductEntity> findAll();

    List<ProductEntity> findRange(int iMin, int nb);

    List<ProductEntity> findFromName(String name);

    List<ProductEntity> findAllFromCategoryId(List<CategoryEntity> categories);

    int count();
}