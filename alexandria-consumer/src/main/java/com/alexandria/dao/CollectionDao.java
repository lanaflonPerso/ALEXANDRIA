package com.alexandria.dao;

import com.alexandria.entities.CollectionEntity;

import java.util.List;

public interface CollectionDao extends AbstractDao<CollectionEntity> {

    void create(CollectionEntity entity);

    void update(CollectionEntity entity);

    void remove(CollectionEntity entity);

    CollectionEntity find(Object id);

    List<CollectionEntity> findAll();

    List<CollectionEntity> findRange(int iMin, int nb);

    int count();
}