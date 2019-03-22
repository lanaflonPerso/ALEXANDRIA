package com.alexandria.entities;

import com.alexandria.windows.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client", schema = "dbo", catalog = "DB_ALEXANDRIA")
@NamedQueries({
        @NamedQuery(
                name = "ClientEntity.findAll",
                query = "from ClientEntity"),
        @NamedQuery(
                name = "ClientEntity.findAllDisplayed",
                query =
                "select c from ClientEntity as c " +
                "join fetch c.titleByTitleId as t"
                ),
        @NamedQuery(
                name = "ClientEntity.findFromFirstNameLastName",
                query = "from ClientEntity as c where (" +
                        "upper(c.firstName) like upper(concat('%', :name, '%')) " +
                        "or " +
                        "upper(c.lastName) like upper(concat('%', :name, '%')) " +
                        ")"
        )
})
public class ClientEntity extends AbstractModelObject {
    private Integer idClient;
    private TitleEntity titleByTitleId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private AddressEntity addressByInvoiceAddressId;
    private AddressEntity addressByDeliveryAddressId;
    private PaymentMethodEntity paymentMethodByPaymentMethodId;
    private Set<OrderHeaderEntity> orderHeadersByIdClient;

    public ClientEntity() {
    }

    public ClientEntity(ClientEntity client) {
        this.idClient = client.idClient;
        this.titleByTitleId = client.titleByTitleId;
        this.firstName = client.firstName;
        this.lastName = client.lastName;
        this.email = client.email;
        this.phone = client.phone;
        this.password = client.password;
        this.addressByInvoiceAddressId = client.addressByInvoiceAddressId;
        this.addressByDeliveryAddressId = client.addressByDeliveryAddressId;
        this.paymentMethodByPaymentMethodId = client.paymentMethodByPaymentMethodId;
        this.orderHeadersByIdClient = client.orderHeadersByIdClient;
    }

    // Init for new record
    public ClientEntity(TitleEntity titleByTitleId, String firstName, String lastName, String email, String phone, String password, AddressEntity addressByInvoiceAddressId, AddressEntity addressByDeliveryAddressId, PaymentMethodEntity paymentMethodByPaymentMethodId) {
        this.titleByTitleId = titleByTitleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.addressByInvoiceAddressId = addressByInvoiceAddressId;
        this.addressByDeliveryAddressId = addressByDeliveryAddressId;
        this.paymentMethodByPaymentMethodId = paymentMethodByPaymentMethodId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", nullable = false)
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {

        Integer oldIdClient = this.idClient;
        this.idClient = idClient;
        changeSupport.firePropertyChange("idClient", oldIdClient, idClient);
    }

    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "id_title", nullable = false)
    public TitleEntity getTitleByTitleId() {
        return titleByTitleId;
    }

    public void setTitleByTitleId(TitleEntity titleByTitleId) {

        TitleEntity oldTitleByTitleId = this.titleByTitleId;
        this.titleByTitleId = titleByTitleId;
        changeSupport.firePropertyChange("titleByTitleId", oldTitleByTitleId, titleByTitleId);
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        String oldFirstName = this.firstName;
        this.firstName = firstName;
        changeSupport.firePropertyChange("name", oldFirstName, firstName);
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        String oldLastName = this.lastName;
        this.lastName = lastName;
        changeSupport.firePropertyChange("surname", oldLastName, lastName);
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {

        String oldPhone = this.phone;
        this.phone = phone;
        changeSupport.firePropertyChange("phone", oldPhone, phone);
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(idClient, that.idClient) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, firstName, lastName, email, phone, password);
    }

    @OneToOne(cascade = CascadeType.ALL) // TODO : TBC
    /**
     * EAGER par défaut pour ManytoOne
     * OneToMany: LAZY
     * ManyToOne: EAGER
     * ManyToMany: LAZY
     * OneToOne: EAGER
     */
    @JoinColumn(name = "invoice_address_id", referencedColumnName = "id_address", nullable = false)
    public AddressEntity getAddressByInvoiceAddressId() { return addressByInvoiceAddressId; }

    public void setAddressByInvoiceAddressId(AddressEntity addressByInvoiceAddressId) {

        AddressEntity oldAddressByInvoiceAddressId = this.addressByInvoiceAddressId;
        this.addressByInvoiceAddressId = addressByInvoiceAddressId;
        changeSupport.firePropertyChange("addressByInvoiceAddressId", oldAddressByInvoiceAddressId, addressByInvoiceAddressId);
    }

    @OneToOne(cascade = CascadeType.ALL) // TODO : TBC
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "id_address", nullable = false)
    public AddressEntity getAddressByDeliveryAddressId() { return addressByDeliveryAddressId; }

    public void setAddressByDeliveryAddressId(AddressEntity addressByDeliveryAddressId) {

        AddressEntity oldAddressByDeliveryAddressId = this.addressByDeliveryAddressId;
        this.addressByDeliveryAddressId = addressByDeliveryAddressId;
        changeSupport.firePropertyChange("addressByDeliveryAddressId", oldAddressByDeliveryAddressId, addressByDeliveryAddressId);
    }

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id_payment_method", nullable = false)
    public PaymentMethodEntity getPaymentMethodByPaymentMethodId() {
        return paymentMethodByPaymentMethodId;
    }

    public void setPaymentMethodByPaymentMethodId(PaymentMethodEntity paymentMethodByPaymentMethodId) {

        PaymentMethodEntity oldPaymentMethodByPaymentMethodId = this.paymentMethodByPaymentMethodId;
        this.paymentMethodByPaymentMethodId = paymentMethodByPaymentMethodId;
        changeSupport.firePropertyChange("paymentMethodByPaymentMethodId", oldPaymentMethodByPaymentMethodId, paymentMethodByPaymentMethodId);
    }

    @OneToMany(mappedBy = "clientByClientId", cascade = CascadeType.ALL) // TODO : TBC
    public Set<OrderHeaderEntity> getOrderHeadersByIdClient() {
        return orderHeadersByIdClient;
    }

    public void setOrderHeadersByIdClient(Set<OrderHeaderEntity> orderHeadersByIdClient) {

        Set<OrderHeaderEntity> oldOrderHeadersByIdClient = this.orderHeadersByIdClient;
        this.orderHeadersByIdClient = orderHeadersByIdClient;
        changeSupport.firePropertyChange("orderHeadersByIdClient", oldOrderHeadersByIdClient, orderHeadersByIdClient);
    }
}
