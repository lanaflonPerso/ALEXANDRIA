package com.alexandria.dao;

import com.alexandria.entities.PaymentMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class PaymentMethodDao {

    private static final Logger logger = LogManager.getLogger(PaymentMethodEntity.class);

    public List<PaymentMethodEntity> doPaymentMethodsList() {

        logger.info("DB_DO_LIST_PAYMENT_METHODS BEGIN");

        EntityManager session = beginTransaction();

        List<PaymentMethodEntity> paymentMethods = session.createNamedQuery("PaymentMethodEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_PAYMENT_METHODS END");

        return paymentMethods;
    }
}
