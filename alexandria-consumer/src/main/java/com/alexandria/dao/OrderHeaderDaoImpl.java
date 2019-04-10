package com.alexandria.dao;

import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.OrderHeaderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

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
    public List<OrderHeaderEntity> findFromClient(ClientEntity client) {

        logger.info("DB_FIND_FROM_CLIENT BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<OrderHeaderEntity> query = em.createNamedQuery("OrderHeaderEntity.findFromClient", OrderHeaderEntity.class);
        query.setParameter("client", client);
        List<OrderHeaderEntity> searchOrdersList = query.getResultList();

        // Retrieve the order lines as the association is @OneToMany(fetch = FetchType.LAZY) (default) via the trigger ".size()"
        for(OrderHeaderEntity order : searchOrdersList)
            order.getOrderLinesByIdOrderHeader().size();

        closeEntityManager();

        logger.info("DB_FIND_FROM_CLIENT END");

        return searchOrdersList;
    }

    @Override
    public void remove(OrderHeaderEntity orderHeader) {

        logger.info("DB_REMOVE_OVERRIDE BEGIN");

        // FIXME : as the abstract Dao method "remove(merge(obj))" doesn't work (i.e only orderHeaderLine's are removed but not orderHeader)
        //  we wrap the remove method to call the abstract Dao method "remove(find(...))"
        //  so that in business code only one homogeneous abstract method "remove" is used.
        super.remove__(orderHeader.getIdOrderHeader());

        logger.info("DB_REMOVE_OVERRIDE END");
    }
}
