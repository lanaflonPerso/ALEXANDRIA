package com.alexandria.dao;

import com.alexandria.entities.PublisherEntity;

import java.util.List;

public interface PublisherDao extends AbstractDao<PublisherEntity> {

    void create(PublisherEntity entity);

    void update(PublisherEntity entity);

    void remove(PublisherEntity entity);

    PublisherEntity find(Object id);

    List<PublisherEntity> findAll();

    List<PublisherEntity> findRange(int iMin, int nb);

    int count();
}