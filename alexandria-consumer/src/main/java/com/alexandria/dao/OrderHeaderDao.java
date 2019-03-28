package com.alexandria.dao;

import com.alexandria.entities.OrderHeaderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.alexandria.persistence.PersistenceUtils.*;

public class OrderHeaderDao extends AbstractDao<OrderHeaderEntity> {

    private static final Logger logger = LogManager.getLogger(OrderHeaderDao.class);

    public OrderHeaderDao() {
        super(OrderHeaderEntity.class);
    }

    @Override
    public OrderHeaderEntity find(Object id) {

        logger.info("DB_FIND BEGIN " + "id: " + id);

        OrderHeaderEntity order = getEntityManager().find(OrderHeaderEntity.class, id);

        // Retrieve the order lines as the association is @OneToMany(fetch = FetchType.LAZY) (default) via the trigger ".size()"
        order.getOrderLinesByIdOrderHeader().size();

        closeEntityManager();

        logger.info("DB_FIND END " + "id: " + id);

        return order;
    }
}
