package com.alexandria.dao;

import com.alexandria.entities.GenreEntity;

import java.util.List;

public interface GenreDao extends AbstractDao<GenreEntity> {

    void create(GenreEntity entity);

    void update(GenreEntity entity);

    void remove(GenreEntity entity);

    GenreEntity find(Object id);

    List<GenreEntity> findAll();

    List<GenreEntity> findRange(int iMin, int nb);

    int count();
}