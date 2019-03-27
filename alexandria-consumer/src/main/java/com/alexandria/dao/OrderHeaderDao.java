package com.alexandria.dao;

import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.persistence.PersistenceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class OrderHeaderDao extends AbstractDao<OrderHeaderEntity> {

    private static final Logger logger = LogManager.getLogger(OrderHeaderDao.class);

    public OrderHeaderDao() {
        super(OrderHeaderEntity.class);
    }

    public List<OrderHeaderEntity> doOrdersList() {

        logger.info("DB_DO_LIST_ORDERS BEGIN");

        EntityManager session = beginTransaction();

        List<OrderHeaderEntity> ordersList = session.createNamedQuery("OrderHeaderEntity.findAllDisplayed").getResultList();

        PersistenceUtils.commitTransaction();

        logger.info("DB_DO_LIST_ORDERS END");

        return ordersList;
    }

    public void dbCreateOrder(OrderHeaderEntity order) {

        logger.info("DB_CREATE_ORDER BEGIN ");

        EntityManager session = beginTransaction();

        session.persist(order);

        commitTransaction();

        logger.info("DB_CREATE_ORDER END ");
    }

    public OrderHeaderEntity dbFindOrder(Integer idOrderHeader) {

        logger.info("DB_FIND_ORDER BEGIN " + "idOrderHeader: " + idOrderHeader);

        EntityManager session = beginTransaction();

        OrderHeaderEntity order = session.find(OrderHeaderEntity.class, idOrderHeader);

        // Retrieve the order lines as the association is @OneToMany(fetch = FetchType.LAZY) (default) via the trigger ".size()"
        order.getOrderLinesByIdOrderHeader().size();

        commitTransaction();

        logger.info("DB_FIND_ORDER END " + "idOrderHeader: " + idOrderHeader);

        return order;
    }

    public void dbUpdateOrder(OrderHeaderEntity order) {

        logger.info("DB_UPDATE_ORDER BEGIN " + "idOrderHeader: " + order.getIdOrderHeader());

        EntityManager session = beginTransaction();

        session.merge(order);

        commitTransaction();

        logger.info("DB_UPDATE_ORDER END " + "idOrderHeader: " + order.getIdOrderHeader());
    }

    public void dbRemoveOrder(Integer idOrderHeader)
    {
        logger.info("DB_REMOVE_ORDER BEGIN " + "idOrderHeader: " + idOrderHeader);

        EntityManager session = beginTransaction();

        OrderHeaderEntity order = session.find(OrderHeaderEntity.class, idOrderHeader);

        session.remove(order);

        commitTransaction();

        logger.info("DB_REMOVE_ORDER END " + "idOrderHeader: " + idOrderHeader);
    }
}
