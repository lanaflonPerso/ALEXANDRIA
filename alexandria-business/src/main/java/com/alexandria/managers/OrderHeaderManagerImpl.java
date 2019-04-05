package com.alexandria.managers;

import com.alexandria.dao.OrderHeaderDao;
import com.alexandria.dao.OrderLineDao;
import com.alexandria.dao.ProductDao;
import com.alexandria.dao.ShippingMethodDao;
import com.alexandria.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderHeaderManagerImpl implements OrderHeaderManager {

    private static final Logger logger = LogManager.getLogger(OrderHeaderManagerImpl.class);

    // TODO : Here we write in database at each change of the model
    //  -> it would be easier to write in database periodically via a dedicated thread
    // TODO : Use Spring TX (transactions) for atomically write order line and product stock.

    @Autowired
    public OrderHeaderDao orderHeaderDao;

    @Autowired
    public OrderLineDao orderLineDao;

    @Autowired
    public ProductDao productDao;

    @Autowired
    public ShippingMethodDao shippingMethodDao;

    private OrderHeaderEntity order;

    @Override
    public void initialize(ClientEntity client) {

        // Look for an active order
        order = getCurrentClientOrder(client);

        // If no active order create a new one
        if( order == null ) {
            order = new OrderHeaderEntity();
            order.setClientByClientId(client);
            order.setOrderLinesByIdOrderHeader(new ArrayList<>());

            // FIXME : Set dummy data for shipping method as this field doesn't accept null in database
            ShippingMethodEntity shippingMethod = shippingMethodDao.findRange(0, 1).get(0);
            order.setShippingMethodByShippingMethodId(shippingMethod);

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
        OrderLineEntity orderLine = findOrderLineInOrderFromProduct(product);

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

            // Update the stock in the model
            product.setStock(product.getStock()-orderLine.getQuantity());

            // Update the stock in database
            productDao.update(product);

        } else {
            updateLineItem(orderLine, product, 1); // If order line exists just increase quantity by 1
        }
    }

    @Override
    public void updateLineItem(ProductEntity product, int quantity) {

        OrderLineEntity orderLine = findOrderLineInOrderFromProduct(product);

        if(orderLine == null) {
            logger.warn("orderLine is null");
            return;
        }

        updateLineItem(orderLine, product, quantity);
    }

    private void updateLineItem(OrderLineEntity orderLine, ProductEntity product, int quantity) {

        // Calculate actual quantity to minor the stock (before/after update)
        Integer before = orderLine.getQuantity();
        orderLine.setQuantity(quantity);
        Integer after = orderLine.getQuantity();
        Integer delta = after - before;

        // Update order line in database
        orderLineDao.update(orderLine);

        // Update the stock in the model
        product.setStock(product.getStock() - delta);

        // Update the stock in database
        productDao.update(product);
    }

    @Nullable
    private OrderLineEntity findOrderLineInOrderFromProduct(ProductEntity product) {

        List<OrderLineEntity> orderLines = order.getOrderLinesByIdOrderHeader();

        for(OrderLineEntity orderLine : orderLines) {
            if( orderLine.getProductId() == product.getIdProduct() ) return orderLine;
        }

        return null;
    }

    @Override
    public void removeLineItem(ProductEntity product) {

        OrderLineEntity orderLine = findOrderLineInOrderFromProduct(product);

        if(orderLine == null) {
            logger.warn("orderLine is null");
            return;
        }

        // Update the stock in the model
        product.setStock(product.getStock() + orderLine.getQuantity());

        // Update the stock in database
        productDao.update(product);

        // Remove order line in database
        orderLineDao.remove(orderLine);

        // Remove order line in model
        order.getOrderLinesByIdOrderHeader().remove(orderLine); // TODO : overhead due to the '.equals(picture)' -> use index instead
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
