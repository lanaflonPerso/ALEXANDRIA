package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_header", schema = "dbo", catalog = "DB_ALEXANDRIA")
@NamedQueries({
        @NamedQuery(
                name = "OrderHeaderEntity.findFromClient",
                query = "from OrderHeaderEntity as o where o.clientByClientId = :client"
        ),
        @NamedQuery(
                name = "OrderHeaderEntity.updateShippingMethod",
                query = "update OrderHeaderEntity as o set o.shippingMethodByShippingMethodId = :shippingMethod where o.idOrderHeader = :idOrderHeader"
        ),
        @NamedQuery(
                name = "OrderHeaderEntity.updateDatePlaced",
                query = "update OrderHeaderEntity as o set o.datePlaced = :datePlaced where o.idOrderHeader = :idOrderHeader"
        )
})
public class OrderHeaderEntity extends AbstractModelObject {
    private Integer idOrderHeader;
    private Date datePlaced;
    private Date dateShipped;
    private Date dateDelivered;
    private String comment;
    private ClientEntity clientByClientId;
    private ShippingMethodEntity shippingMethodByShippingMethodId;
    private List<OrderLineEntity> orderLinesByIdOrderHeader; // TODO : TBC : Set has changed to List for JTable databinding

    public OrderHeaderEntity() {
    }

    public OrderHeaderEntity(OrderHeaderEntity orderHeader) {
        this.idOrderHeader = orderHeader.idOrderHeader;
        this.datePlaced = orderHeader.datePlaced;
        this.dateShipped = orderHeader.dateShipped;
        this.dateDelivered = orderHeader.dateDelivered;
        this.comment = orderHeader.comment;
        this.clientByClientId = orderHeader.clientByClientId;
        this.shippingMethodByShippingMethodId = orderHeader.shippingMethodByShippingMethodId;
        this.orderLinesByIdOrderHeader = orderHeader.orderLinesByIdOrderHeader;
    }

    // Init for new record
    public OrderHeaderEntity(Date datePlaced, Date dateShipped, Date dateDelivered, String comment, ShippingMethodEntity shippingMethodByShippingMethodId) {
        this.datePlaced = datePlaced;
        this.dateShipped = dateShipped;
        this.dateDelivered = dateDelivered;
        this.comment = comment;
        this.shippingMethodByShippingMethodId = shippingMethodByShippingMethodId;
        this.orderLinesByIdOrderHeader = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_header", nullable = false)
    public Integer getIdOrderHeader() {
        return idOrderHeader;
    }

    public void setIdOrderHeader(Integer idOrderHeader) {
        this.idOrderHeader = idOrderHeader;
    }

    @Basic
    @Column(name = "date_placed", nullable = false)
    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    @Basic
    @Column(name = "date_shipped", nullable = true)
    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    @Basic
    @Column(name = "date_delivered", nullable = true)
    public Date getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHeaderEntity that = (OrderHeaderEntity) o;
        return Objects.equals(idOrderHeader, that.idOrderHeader) &&
                Objects.equals(datePlaced, that.datePlaced) &&
                Objects.equals(dateShipped, that.dateShipped) &&
                Objects.equals(dateDelivered, that.dateDelivered) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderHeader, datePlaced, dateShipped, dateDelivered, comment);
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_client", nullable = false)
    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {

        ClientEntity oldClientByClientId = this.clientByClientId;
        this.clientByClientId = clientByClientId;
        changeSupport.firePropertyChange("clientByClientId", oldClientByClientId, clientByClientId);
    }

    @ManyToOne
    @JoinColumn(name = "shipping_method_id", referencedColumnName = "id_shipping_method", nullable = false)
    public ShippingMethodEntity getShippingMethodByShippingMethodId() {
        return shippingMethodByShippingMethodId;
    }

    public void setShippingMethodByShippingMethodId(ShippingMethodEntity shippingMethodByShippingMethodId) {
        this.shippingMethodByShippingMethodId = shippingMethodByShippingMethodId;
    }

    @OneToMany(mappedBy = "orderHeaderByOrderHeaderId", orphanRemoval=true) // TODO : TBC : alternative -> cascade = CascadeType.ALL
    public List<OrderLineEntity> getOrderLinesByIdOrderHeader() {
        return orderLinesByIdOrderHeader;
    }

    public void setOrderLinesByIdOrderHeader(List<OrderLineEntity> orderLinesByIdOrderHeader) {
        this.orderLinesByIdOrderHeader = orderLinesByIdOrderHeader;
    }

    @Transient
    public BigDecimal getTotalCostExVat() {

        BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 4);

        if(orderLinesByIdOrderHeader == null || orderLinesByIdOrderHeader.size() == 0 )
            return totalCost;

        for(OrderLineEntity orderLine : orderLinesByIdOrderHeader) {
            BigDecimal itemPrice = orderLine.getProductByProductId().getPriceExVat();
            BigDecimal itemCost  = itemPrice.multiply( new BigDecimal(orderLine.getQuantity()) );
            totalCost = totalCost.add(itemCost);
        }

        return totalCost;
    }

    @Transient
    public Integer getNbOrderLines() {
        return orderLinesByIdOrderHeader.size();
    }
}
