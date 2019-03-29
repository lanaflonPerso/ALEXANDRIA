package com.alexandria.dao;

import com.alexandria.entities.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;

public class ProductDaoImpl extends AbstractDaoImpl<ProductEntity> implements ProductDao {

    private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);

    ProductDaoImpl() {
        super(ProductEntity.class);
    }

    @Override
    public List<ProductEntity> findFromName(String name) {

        logger.info("DB_FIND_FROM_NAME BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.findFromName", ProductEntity.class);
        query.setParameter("name", name);
        List<ProductEntity> searchProductsList = query.getResultList();

        closeEntityManager();

        logger.info("DB_FIND_FROM_NAME END");
        
        return searchProductsList;
    }
}
