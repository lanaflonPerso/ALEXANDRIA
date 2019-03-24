package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_line", schema = "dbo", catalog = "DB_ALEXANDRIA")
@IdClass(OrderLineEntityPK.class)
public class OrderLineEntity extends AbstractModelObject {
    private Integer orderHeaderId;
    private Integer productId;
    private Integer quantity;
    private OrderHeaderEntity orderHeaderByOrderHeaderId;
    private ProductEntity productByProductId;

    public OrderLineEntity() {
    }

    public OrderLineEntity(OrderLineEntity orderLine) {
        this.orderHeaderId = orderLine.orderHeaderId;
        this.productId = orderLine.productId;
        this.quantity = orderLine.quantity;
        this.orderHeaderByOrderHeaderId = orderLine.orderHeaderByOrderHeaderId;
        this.productByProductId = orderLine.productByProductId;
    }

    // Init for new record
    public OrderLineEntity(Integer quantity, OrderHeaderEntity orderHeaderByOrderHeaderId) {
        this.quantity = quantity;
        this.orderHeaderByOrderHeaderId = orderHeaderByOrderHeaderId;
        this.orderHeaderId = orderHeaderByOrderHeaderId.getIdOrderHeader();
    }

    @Id
    @Column(name = "order_header_id", nullable = false)
    public Integer getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderHeaderId(Integer orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    @Id
    @Column(name = "product_id", nullable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {

        Integer oldProductId = this.productId;
        this.productId = productId;
        changeSupport.firePropertyChange("productId", oldProductId, productId);
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {

        Integer oldQuantity = this.quantity;
        this.quantity = quantity;
        changeSupport.firePropertyChange("quantity", oldQuantity, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineEntity that = (OrderLineEntity) o;
        return Objects.equals(orderHeaderId, that.orderHeaderId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderHeaderId, productId, quantity);
    }

    @ManyToOne
    @JoinColumn(name = "order_header_id", referencedColumnName = "id_order_header", insertable = false, updatable = false, nullable = false) // TODO : TBC
    public OrderHeaderEntity getOrderHeaderByOrderHeaderId() {
        return orderHeaderByOrderHeaderId;
    }

    public void setOrderHeaderByOrderHeaderId(OrderHeaderEntity orderHeaderByOrderHeaderId) {
        this.orderHeaderByOrderHeaderId = orderHeaderByOrderHeaderId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product", insertable = false, updatable = false, nullable = false) // TODO : TBC
    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {

        ProductEntity oldProductByProductId = this.productByProductId;
        this.productByProductId = productByProductId;
        changeSupport.firePropertyChange("productByProductId", oldProductByProductId, productByProductId);
    }
}
