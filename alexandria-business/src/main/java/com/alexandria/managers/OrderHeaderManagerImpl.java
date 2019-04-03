package com.alexandria.managers;

import com.alexandria.dao.OrderHeaderDao;
import com.alexandria.dao.OrderLineDao;
import com.alexandria.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderHeaderManagerImpl implements OrderHeaderManager {

    @Autowired
    public OrderHeaderDao orderHeaderDao;

    @Autowired
    public OrderLineDao orderLineDao;

    private OrderHeaderEntity order;

    @Override
    public void initialize(ClientEntity client) {

        // Look for an active oder
        order = getCurrentClientOrder(client);

        // If no order active create a new one
        if( order == null ) {
            order = new OrderHeaderEntity();
            order.setClientByClientId(client);
            order.setOrderLinesByIdOrderHeader(new ArrayList<>());

            // FIXME : Set dummy data for shipping method as this field is not null in database
            ShippingMethodEntity shippingMethod = new ShippingMethodEntity();
            shippingMethod.setIdShippingMethod(1);
            shippingMethod.setDescription("DHL");
            shippingMethod.setCharges(new BigDecimal(18.4065));

            order.setShippingMethodByShippingMethodId(new ShippingMethodEntity());

            // Create in database
            orderHeaderDao.create(order);
        }
    }

    @Override
    public void setDatePlaced(Date date) { // TODO : should be set automatically since the payment is done
        order.setDatePlaced(date);

        // Update in database
        orderHeaderDao.update(order);
    }
    @Override
    public void setDateShipped(Date date) {
        order.setDateShipped(date);

        // Update in database
        orderHeaderDao.update(order);
    }

    @Override
    public void setDateDelivered(Date date) {
        order.setDateDelivered(date);

        // Update in database
        orderHeaderDao.update(order);
    }

    @Override
    public void setComment(String comment) {
        order.setComment(comment);

        // Update in database
        orderHeaderDao.update(order);
    }

    @Override
    public void setShippingMethod(ShippingMethodEntity shippingMethod) {
        order.setShippingMethodByShippingMethodId(shippingMethod);

        // Update in database
        orderHeaderDao.update(order);
    }

    @Override
    public void addLineItem(ProductEntity product) {

        // Look for an existing order line
        OrderLineEntity orderLine = getOrderLineInOrderFromProduct(product);

        // If no order line create a new one
        if( orderLine == null ) {
            orderLine = new OrderLineEntity();

            // Initialize order line
            orderLine.setOrderHeaderId(order.getIdOrderHeader());
            orderLine.setProductId(product.getIdProduct());
            orderLine.setQuantity(1);
            orderLine.setOrderHeaderByOrderHeaderId(order);
            orderLine.setProductByProductId(product);

            // Add order line to order
            order.getOrderLinesByIdOrderHeader().add(orderLine);

            // Create in database
            orderLineDao.create(orderLine);

        } else {
            // If order line exist just increase quantity by 1
            orderLine.setQuantity(orderLine.getQuantity()+1);
        }
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

        List<OrderLineEntity> orderlines = order.getOrderLinesByIdOrderHeader();

        if(orderlines == null || orderlines.size() == 0 )
            return totalCost;

        for(OrderLineEntity orderLine : orderlines) {
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

    @Nullable
    private OrderLineEntity getOrderLineInOrderFromProduct(ProductEntity product)
    {
        List<OrderLineEntity> orderlines = order.getOrderLinesByIdOrderHeader();

        for(OrderLineEntity orderLine : orderlines) {
            if( orderLine.getProductId() == product.getIdProduct() ) return orderLine;
        }

        return null;
    }

    @Nullable
    private OrderHeaderEntity getCurrentClientOrder(ClientEntity client)
    {
        List<OrderHeaderEntity> orders = orderHeaderDao.findFromClient(client);

        // FIXME : Since there is no status for order we consider that an order is still active
        //  until the payment is done (date placed set)
        for(OrderHeaderEntity order : orders) {
            if(order.getDatePlaced() == null) return order;
        }

        return null;
    }
}
