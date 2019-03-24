package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address", schema = "dbo", catalog = "DB_ALEXANDRIA")
public class AddressEntity extends AbstractModelObject {
    private Integer idAddress;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private CountryEntity countryByCountryId;
    private ClientEntity clientByIdAddress;     // Invoice Address
    private ClientEntity clientByIdAddress_0;   // Delivery Address

    public AddressEntity() {
    }

    // Init for new record
    public AddressEntity(String addressLine1, String addressLine2, String city, String state, String postalCode, CountryEntity countryByCountryId) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.countryByCountryId = countryByCountryId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false)
    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        Integer oldIdAddress = this.idAddress;
        this.idAddress = idAddress;
        changeSupport.firePropertyChange("idAddress", oldIdAddress, idAddress);
    }

    @Basic
    @Column(name = "address_line_1", nullable = false, length = 255)
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        String oldAddressLine1 = this.addressLine1;
        this.addressLine1 = addressLine1;
        changeSupport.firePropertyChange("addressLine1", oldAddressLine1, addressLine1);
    }

    @Basic
    @Column(name = "address_line_2", nullable = true, length = 255)
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        String oldAddressLine2 = this.addressLine2;
        this.addressLine2 = addressLine2;
        changeSupport.firePropertyChange("addressLine2", oldAddressLine2, addressLine2);
    }

    @Basic
    @Column(name = "city", nullable = false, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        String oldCity = this.city;
        this.city = city;
        changeSupport.firePropertyChange("city", oldCity, city);
    }

    @Basic
    @Column(name = "state", nullable = true, length = 45)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        String oldState = this.state;
        this.state = state;
        changeSupport.firePropertyChange("state", oldState, state);
    }

    @Basic
    @Column(name = "postal_code", nullable = false, length = 10)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        String oldPostalCode = this.postalCode;
        this.postalCode = postalCode;
        changeSupport.firePropertyChange("postalCode", oldPostalCode, postalCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(idAddress, that.idAddress) &&
                Objects.equals(addressLine1, that.addressLine1) &&
                Objects.equals(addressLine2, that.addressLine2) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, addressLine1, addressLine2, city, state, postalCode);
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id_country", nullable = false)
    public CountryEntity getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(CountryEntity countryByCountryId) {
        CountryEntity oldCountryByCountryId = this.countryByCountryId;
        this.countryByCountryId = countryByCountryId;
        changeSupport.firePropertyChange("countryByCountryId", oldCountryByCountryId, countryByCountryId);
    }

    // Invoice Address
    @OneToOne(mappedBy = "addressByInvoiceAddressId")
    public ClientEntity getClientByIdAddress() {
        return clientByIdAddress;
    }

    public void setClientByIdAddress(ClientEntity clientByIdAddress) {
        ClientEntity oldClientByIdAddress = this.clientByIdAddress;
        this.clientByIdAddress = clientByIdAddress;
        changeSupport.firePropertyChange("clientByIdAddress", oldClientByIdAddress, clientByIdAddress);
    }

    // Delivery Address
    @OneToOne(mappedBy = "addressByDeliveryAddressId")
    public ClientEntity getClientByIdAddress_0() {
        return clientByIdAddress_0;
    }

    public void setClientByIdAddress_0(ClientEntity clientByIdAddress_0) {
        ClientEntity oldClientByIdAddress_0 = this.clientByIdAddress_0;
        this.clientByIdAddress_0 = clientByIdAddress_0;
        changeSupport.firePropertyChange("clientByIdAddress_0", oldClientByIdAddress_0, clientByIdAddress_0);
    }
}
