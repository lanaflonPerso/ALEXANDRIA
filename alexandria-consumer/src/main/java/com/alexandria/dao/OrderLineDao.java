package com.alexandria.dao;

import com.alexandria.entities.OrderLineEntity;

import java.util.List;

public interface OrderLineDao extends AbstractDao<OrderLineEntity> {

    void create(OrderLineEntity entity);

    void update(OrderLineEntity entity);

    void remove_(OrderLineEntity entity);

    OrderLineEntity find(Object id);

    List<OrderLineEntity> findAll();

    List<OrderLineEntity> findRange(int[] range);

    int count();
}