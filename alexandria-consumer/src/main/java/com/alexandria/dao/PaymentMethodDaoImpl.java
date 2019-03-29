package com.alexandria.dao;

import com.alexandria.entities.PaymentMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentMethodDaoImpl extends AbstractDaoImpl<PaymentMethodEntity> implements PaymentMethodDao {

    private static final Logger logger = LogManager.getLogger(PaymentMethodDaoImpl.class);

    PaymentMethodDaoImpl() {
        super(PaymentMethodEntity.class);
    }
}
