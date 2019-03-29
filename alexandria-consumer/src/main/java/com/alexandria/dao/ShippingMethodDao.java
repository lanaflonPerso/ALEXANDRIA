package com.alexandria.dao;

import com.alexandria.entities.ShippingMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ShippingMethodDao extends AbstractDao<ShippingMethodEntity> {

    private static final Logger logger = LogManager.getLogger(ShippingMethodDao.class);

    ShippingMethodDao() {
        super(ShippingMethodEntity.class);
    }
}
