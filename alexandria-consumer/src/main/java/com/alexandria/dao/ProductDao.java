package com.alexandria.dao;

import com.alexandria.entities.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.*;

public class ProductDao extends AbstractDao<ProductEntity> {

    private static final Logger logger = LogManager.getLogger(ProductDao.class);

    ProductDao() {
        super(ProductEntity.class);
    }

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
