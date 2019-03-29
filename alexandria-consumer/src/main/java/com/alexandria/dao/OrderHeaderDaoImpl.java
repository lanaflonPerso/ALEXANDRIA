package com.alexandria.dao;

import com.alexandria.entities.OrderHeaderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.alexandria.persistence.PersistenceUtils.*;

public class OrderHeaderDaoImpl extends AbstractDaoImpl<OrderHeaderEntity> implements OrderHeaderDao {

    private static final Logger logger = LogManager.getLogger(OrderHeaderDaoImpl.class);

    OrderHeaderDaoImpl() {
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

    @Override
    public void remove_(OrderHeaderEntity orderHeader) {

        logger.info("DB_REMOVE_OVERRIDE BEGIN");

        // FIXME : as the abstract Dao method "remove(merge(obj))" doesn't work (i.e only orderHeaderLine's are removed but not orderHeader)
        //  we wrap the remove method to call the abstract Dao method "remove(find(...))"
        //  so that in business code only one homogeneous abstract method "remove" is used.
        super.remove(orderHeader.getIdOrderHeader());

        logger.info("DB_REMOVE_OVERRIDE END");
    }
}
