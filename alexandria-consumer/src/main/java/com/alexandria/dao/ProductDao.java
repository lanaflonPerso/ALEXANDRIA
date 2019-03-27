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

    public ProductDao() {
        super(ProductEntity.class);
    }

    public List<ProductEntity> searchProducts(String param) {

        logger.info("DB_SEARCH_PRODUCTS BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.findFromName", ProductEntity.class);
        query.setParameter("name", param);
        List<ProductEntity> searchProductsList = query.getResultList();

        closeEntityManager();

        logger.info("DB_SEARCH_PRODUCTS END");
        
        return searchProductsList;
    }

    public void dbRemoveProduct(Integer idProduct)
    {
        logger.info("DB_REMOVE BEGIN " + "idProduct: " + idProduct);

        EntityManager session = beginTransaction();

        ProductEntity product = session.find(ProductEntity.class, idProduct);

        session.remove(product);

        commitTransaction();

        logger.info("DB_REMOVE END " + "idProduct: " + idProduct);
    }
}
