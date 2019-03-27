package com.alexandria.dao;

import com.alexandria.entities.ShippingMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShippingMethodDao extends AbstractDao<ShippingMethodEntity> {

    private static final Logger logger = LogManager.getLogger(ShippingMethodDao.class);

    public ShippingMethodDao() {
        super(ShippingMethodEntity.class);
    }
}
