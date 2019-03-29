package com.alexandria.dao;

import com.alexandria.entities.BookEntity;

import java.util.List;

public interface BookDao extends AbstractDao<BookEntity> {

    void create(BookEntity entity);

    void update(BookEntity entity);

    void remove(BookEntity entity);

    BookEntity find(Object id);

    List<BookEntity> findAll();

    List<BookEntity> findRange(int[] range);

    int count();
}