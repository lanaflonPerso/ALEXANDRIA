package com.alexandria.dao;

import com.alexandria.entities.CategoryEntity;
import com.alexandria.entities.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    @Override
    public List<ProductEntity> findAllFromCategoryId(List<CategoryEntity> categories) {
        logger.info("DB_FIND_FROM_CATEGORY_ID BEGIN");

        EntityManager em = getEntityManager();

        TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.findAllFromCategoryId", ProductEntity.class);
        query.setParameter("categories", categories);
        List<ProductEntity> searchProductsList = query.getResultList();

        closeEntityManager();

        logger.info("DB_FIND_FROM_CATEGORY_ID END");

        return searchProductsList;
    }

    @Override
    public void updateStock(ProductEntity entity) {

        logger.info("DB_UPDATE_STOCK BEGIN");

        EntityManager em = beginTransaction();

        Query query = em.createNamedQuery("ProductEntity.updateStock");
        query.setParameter("idProduct", entity.getIdProduct());
        query.setParameter("stock", entity.getStock());
        query.executeUpdate();

        commitTransaction();

        logger.info("DB_UPDATE_STOCK END");
    }

}
