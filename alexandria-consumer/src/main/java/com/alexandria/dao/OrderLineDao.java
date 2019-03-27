package com.alexandria.dao;

import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.OrderLineEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class OrderLineDao extends AbstractDao<OrderLineEntity> {

    private static final Logger logger = LogManager.getLogger(OrderLineDao.class);

    public OrderLineDao() {
        super(OrderLineEntity.class);
    }

    // FIXME : crash if used (Entity not managed)
    public void dbRefreshOrderLine(OrderLineEntity orderLine) {

        logger.info("DB_REFRESH_ORDER_LINE BEGIN " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());

        EntityManager session = beginTransaction();

        session.refresh(orderLine);

        commitTransaction();

        logger.info("DB_REFRESH_ORDER_LINE END " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());
    }
}
