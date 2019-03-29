package com.alexandria.dao;

import com.alexandria.entities.CountryEntity;

import java.util.List;

public interface CountryDao extends AbstractDao<CountryEntity> {

    void create(CountryEntity entity);

    void update(CountryEntity entity);

    void remove_(CountryEntity entity);

    CountryEntity find(Object id);

    List<CountryEntity> findAll();

    List<CountryEntity> findRange(int[] range);

    int count();
}