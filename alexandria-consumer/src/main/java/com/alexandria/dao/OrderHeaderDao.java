package com.alexandria.dao;

import com.alexandria.entities.OrderHeaderEntity;

import java.util.List;

public interface OrderHeaderDao extends AbstractDao<OrderHeaderEntity> {

    void create(OrderHeaderEntity entity);

    void update(OrderHeaderEntity entity);

    void remove_(OrderHeaderEntity entity);

    OrderHeaderEntity find(Object id);

    List<OrderHeaderEntity> findAll();

    List<OrderHeaderEntity> findRange(int[] range);

    int count();
}