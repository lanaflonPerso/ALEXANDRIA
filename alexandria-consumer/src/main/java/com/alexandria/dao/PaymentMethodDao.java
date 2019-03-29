package com.alexandria.dao;

import com.alexandria.entities.PaymentMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class PaymentMethodDao extends AbstractDao<PaymentMethodEntity> {

    private static final Logger logger = LogManager.getLogger(PaymentMethodDao.class);

    PaymentMethodDao() {
        super(PaymentMethodEntity.class);
    }
}
