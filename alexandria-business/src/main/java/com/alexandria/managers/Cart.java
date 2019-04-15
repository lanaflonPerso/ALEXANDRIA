package com.alexandria.managers;

import com.alexandria.entities.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface Cart {

    ClientEntity getClient();

    void setDatePlaced(Date date); // TODO : should be set automatically since the payment is done

    void setDateShipped(Date date);

    void setDateDelivered(Date date);

    void setComment(String comment);

    void setShippingMethod(ShippingMethodEntity shippingMethod);

    void addLineItem(ProductEntity product);

    void updateLineItem(ProductEntity product, int quantity);

    void removeLineItem(ProductEntity product);

    void clearOrderLines();

    OrderHeaderEntity getOrder();

    List<OrderLineEntity> getOrderLines();

    int getNbOrderLines();

    BigDecimal getShippingTax();

    BigDecimal getTotalCostExVat();

    BigDecimal getTotalCostInVat();
}
