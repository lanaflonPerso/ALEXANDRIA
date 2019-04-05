package com.alexandria.dao;

import com.alexandria.entities.PaymentMethodEntity;

import java.util.List;

public interface PaymentMethodDao extends AbstractDao<PaymentMethodEntity> {

    void create(PaymentMethodEntity entity);

    void update(PaymentMethodEntity entity);

    void remove(PaymentMethodEntity entity);

    PaymentMethodEntity find(Object id);

    List<PaymentMethodEntity> findAll();

    List<PaymentMethodEntity> findRange(int iMin, int nb);

    int count();
}