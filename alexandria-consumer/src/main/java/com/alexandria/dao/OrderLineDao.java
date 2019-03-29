package com.alexandria.dao;

import com.alexandria.entities.OrderLineEntity;
import com.alexandria.entities.OrderLineEntityPK;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderLineDao extends AbstractDao<OrderLineEntity> {

    private static final Logger logger = LogManager.getLogger(OrderLineDao.class);

    OrderLineDao() {
        super(OrderLineEntity.class);
    }

    @Override
    public void remove_(OrderLineEntity orderLine) {

        logger.info( " DB_REMOVE_OVERRIDE BEGIN");

        // NB : order_line is an associative table
        OrderLineEntityPK orderLineEntityPK = new OrderLineEntityPK();
        orderLineEntityPK.setProductId(orderLine.getProductId());
        orderLineEntityPK.setOrderHeaderId(orderLine.getOrderHeaderId());

        super.remove(orderLineEntityPK);

        logger.info( " DB_REMOVE_OVERRIDE END");
    }
}
