package com.alexandria.managers;

import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.OrderLineEntity;
import com.alexandria.entities.ProductEntity;
import com.alexandria.entities.ShippingMethodEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface OrderHeaderManager {

    void setDateShipped(Date date);

    void setDateDelivered(Date date);

    void setComment(String comment);

    void setClient(ClientEntity client);

    void setShippingMethod(ShippingMethodEntity shippingMethod);

    void addLineItem(ProductEntity product);

    void updateLineItem(ProductEntity product, int quantity);

    void removeLineItem(ProductEntity product);

    List<OrderLineEntity> getOrderLines();

    int getNbOrderLines();

    BigDecimal getShippingTax();

    BigDecimal getTotalCostExVat();

    BigDecimal getTotalCostInVat();
}