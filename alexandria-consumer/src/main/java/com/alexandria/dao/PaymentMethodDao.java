package com.alexandria.dao;

import com.alexandria.entities.PaymentMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class PaymentMethodDao extends AbstractDao<PaymentMethodEntity> {

    private static final Logger logger = LogManager.getLogger(PaymentMethodDao.class);

    public PaymentMethodDao() {
        super(PaymentMethodEntity.class);
    }
}
