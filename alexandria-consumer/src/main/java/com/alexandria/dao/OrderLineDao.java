package com.alexandria.dao;

import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.OrderLineEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class OrderLineDao {

    private static final Logger logger = LogManager.getLogger(OrderLineDao.class);

    // FIXME : crash if used (Entity not managed)
    public void dbRefreshOrderLine(OrderLineEntity orderLine) {

        logger.info("DB_REFRESH_ORDER_LINE BEGIN " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());

        EntityManager session = beginTransaction();

        session.refresh(orderLine);

        commitTransaction();

        logger.info("DB_REFRESH_ORDER_LINE END " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());
    }

    public void dbCreateOrderLine(OrderLineEntity orderLine) {

        logger.info("DB_CREATE_ORDER_LINE BEGIN ");

        EntityManager session = beginTransaction();

        session.persist(orderLine);

        commitTransaction();

        logger.info("DB_CREATE_ORDER_LINE END ");
    }

    public void dbUpdateOrderLine(OrderLineEntity orderLine) {

        logger.info("DB_UPDATE_ORDER_LINE BEGIN " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());

        EntityManager session = beginTransaction();

        session.merge(orderLine);

        commitTransaction();

        logger.info("DB_UPDATE_ORDER_LINE END " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());
    }

    public void dbRemoveOrderLine(OrderLineEntity orderLine)
    {
        logger.info("DB_REMOVE_ORDER_LINE BEGIN " + "iOrderLine: ");

        EntityManager session = beginTransaction();

        OrderHeaderEntity order = session.getReference(OrderHeaderEntity.class, orderLine.getOrderHeaderByOrderHeaderId().getIdOrderHeader());
        order.getOrderLinesByIdOrderHeader().remove(0);

        commitTransaction();

        logger.info("DB_REMOVE_ORDER_LINE END " + "iOrderLine: ");
    }
}