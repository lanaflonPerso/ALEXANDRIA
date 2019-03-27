package com.alexandria.dao;

import com.alexandria.entities.ShippingMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

public class ShippingMethodDao extends AbstractDao<ShippingMethodEntity> {

    private static final Logger logger = LogManager.getLogger(ShippingMethodDao.class);

    public ShippingMethodDao() {
        super(ShippingMethodEntity.class);
    }
}
