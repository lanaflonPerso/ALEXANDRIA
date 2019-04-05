package com.alexandria.dao;

import com.alexandria.entities.AuthorEntity;

import java.util.List;

public interface AuthorDao extends AbstractDao<AuthorEntity> {

    void create(AuthorEntity entity);

    void update(AuthorEntity entity);

    void remove(AuthorEntity entity);

    AuthorEntity find(Object id);

    List<AuthorEntity> findAll();

    List<AuthorEntity> findRange(int iMin, int nb);

    int count();
}