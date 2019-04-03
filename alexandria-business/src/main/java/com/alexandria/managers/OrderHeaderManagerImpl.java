package com.alexandria.managers;

import com.alexandria.entities.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderHeaderManagerImpl implements OrderHeaderManager {

    private OrderHeaderEntity order;

    public OrderHeaderManagerImpl() {

        // Initialize order
        order = new OrderHeaderEntity();
        order.setDatePlaced(new Date(System.currentTimeMillis()));
        order.setOrderLinesByIdOrderHeader(new ArrayList<>());
    }

    @Override
    public void setDateShipped(Date date) {
        order.setDateShipped(date);
    }

    @Override
    public void setDateDelivered(Date date) {
        order.setDateDelivered(date);
    }

    @Override
    public void setComment(String comment) {
        order.setComment(comment);
    }

    @Override
    public void setClient(ClientEntity client) {
        order.setClientByClientId(client);
    }

    @Override
    public void setShippingMethod(ShippingMethodEntity shippingMethod) {
        order.setShippingMethodByShippingMethodId(shippingMethod);
    }

    @Override
    public void addLineItem(ProductEntity product) {

        OrderLineEntity orderLine = new OrderLineEntity();

        // Initialize order line
        orderLine.setOrderHeaderId(order.getIdOrderHeader());
        orderLine.setProductId(product.getIdProduct());
        orderLine.setQuantity(1);
        orderLine.setOrderHeaderByOrderHeaderId(order);
        orderLine.setProductByProductId(product);

        // Add order line to order
        order.getOrderLinesByIdOrderHeader().add(orderLine);
    }

    @Override
    public void updateLineItem(ProductEntity product, int quantity) {

        int iProduct = order.getOrderLinesByIdOrderHeader().indexOf(product);

        order.getOrderLinesByIdOrderHeader().get(iProduct).setQuantity(quantity);
    }

    @Override
    public void removeLineItem(ProductEntity product) {

        int iProduct = order.getOrderLinesByIdOrderHeader().indexOf(product);

        order.getOrderLinesByIdOrderHeader().remove(iProduct);
    }

    @Override
    public List<OrderLineEntity> getOrderLines() {
        return order.getOrderLinesByIdOrderHeader();
    }

    @Override
    public int getNbOrderLines() {
        return order.getOrderLinesByIdOrderHeader().size();
    }

    @Override
    public BigDecimal getShippingTax() {
        return order.getShippingMethodByShippingMethodId().getCharges();
    }

    @Override
    public BigDecimal getTotalCostExVat() {
        BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 4);

        if(order.getOrderLinesByIdOrderHeader() == null || order.getOrderLinesByIdOrderHeader().size() == 0 )
            return totalCost;

        for(OrderLineEntity orderLine : order.getOrderLinesByIdOrderHeader()) {
            BigDecimal itemPrice = orderLine.getProductByProductId().getPriceExVat();
            BigDecimal itemCost  = itemPrice.multiply( new BigDecimal(orderLine.getQuantity()) );
            totalCost = totalCost.add(itemCost);
        }

        return totalCost;
    }

    @Override
    public BigDecimal getTotalCostInVat() {
        // TODO
        return new BigDecimal(BigInteger.ZERO, 4);
    }
}
