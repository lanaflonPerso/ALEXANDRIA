package com.alexandria.dao;

import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.OrderLineEntity;
import com.alexandria.entities.OrderLineEntityPK;
import com.alexandria.entities.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class OrderLineDaoImpl extends AbstractDaoImpl<OrderLineEntity> implements OrderLineDao {

    private static final Logger logger = LogManager.getLogger(OrderLineDaoImpl.class);

    OrderLineDaoImpl() {
        super(OrderLineEntity.class);
    }

    @Override
    public void remove(OrderLineEntity orderLine) {

        logger.info( " DB_REMOVE_OVERRIDE BEGIN");

        // NB : order_line is an associative table
        OrderLineEntityPK orderLineEntityPK = new OrderLineEntityPK();
        orderLineEntityPK.setProductId(orderLine.getProductId());
        orderLineEntityPK.setOrderHeaderId(orderLine.getOrderHeaderId());

        super.remove__(orderLineEntityPK);

        logger.info( " DB_REMOVE_OVERRIDE END");
    }

    @Override
    public List<OrderLineEntity> findFromOrderHeader(OrderHeaderEntity orderHeader) {
        logger.info("DB FIND_FROM_ORDERHEADER BEGIN");
        EntityManager em = getEntityManager();

        TypedQuery<OrderLineEntity> query = em.createNamedQuery("OrderLineEntity.findFromOrderHeader", OrderLineEntity.class);
        query.setParameter("orderHeader", orderHeader);
        List<OrderLineEntity> searchOrderLinesList = query.getResultList();

        closeEntityManager();
        logger.info("DB FIND_FROM_ORDERHEADER END");
        return searchOrderLinesList;
    }

    @Override
    public void updateQuantity(OrderLineEntity entity) {
        logger.info("DB_UPDATE_QUANTITY BEGIN");

        EntityManager em = beginTransaction();

        Query query = em.createNamedQuery("OrderLineEntity.updateQuantity");
        query.setParameter("productId", entity.getProductId());
        query.setParameter("orderHeaderId", entity.getOrderHeaderId());
        query.setParameter("quantity", entity.getQuantity());
        query.executeUpdate();

        commitTransaction();

        logger.info("DB_UPDATE_QUANTITY END");
    }

}
