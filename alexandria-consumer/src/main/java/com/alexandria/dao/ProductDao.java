package com.alexandria.dao;

import com.alexandria.entities.ProductEntity;

import java.util.List;

public interface ProductDao extends AbstractDao<ProductEntity> {

    void create(ProductEntity entity);

    void update(ProductEntity entity);

    void remove_(ProductEntity entity);

    ProductEntity find(Object id);

    List<ProductEntity> findAll();

    List<ProductEntity> findRange(int[] range);

    List<ProductEntity> findFromName(String name);

    int count();
}