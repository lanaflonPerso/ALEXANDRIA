package com.alexandria.dao;

import com.alexandria.entities.ShippingMethodEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShippingMethodDaoImpl extends AbstractDaoImpl<ShippingMethodEntity> implements ShippingMethodDao {

    private static final Logger logger = LogManager.getLogger(ShippingMethodDaoImpl.class);

    ShippingMethodDaoImpl() {
        super(ShippingMethodEntity.class);
    }
}
