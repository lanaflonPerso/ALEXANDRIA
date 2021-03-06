package com.alexandria.dao;

import com.alexandria.entities.TitleEntity;

import java.util.List;

public interface TitleDao extends AbstractDao<TitleEntity> {

    void create(TitleEntity entity);

    void update(TitleEntity entity);

    void remove(TitleEntity entity);

    TitleEntity find(Object id);

    List<TitleEntity> findAll();

    List<TitleEntity> findRange(int iMin, int nb);

    int count();
}