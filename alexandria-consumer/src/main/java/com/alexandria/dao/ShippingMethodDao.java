package com.alexandria.dao;

import com.alexandria.entities.ShippingMethodEntity;

import java.util.List;

public interface ShippingMethodDao extends AbstractDao<ShippingMethodEntity> {

    void create(ShippingMethodEntity entity);

    void update(ShippingMethodEntity entity);

    void remove_(ShippingMethodEntity entity);

    ShippingMethodEntity find(Object id);

    List<ShippingMethodEntity> findAll();

    List<ShippingMethodEntity> findRange(int[] range);

    int count();
}