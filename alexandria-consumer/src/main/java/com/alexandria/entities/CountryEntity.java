package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "country", schema = "dbo", catalog = "DB_ALEXANDRIA")
@NamedQueries({
        @NamedQuery(name="CountryEntity.findAll", query ="from CountryEntity")
})
public class CountryEntity extends AbstractModelObject {
    private Integer idCountry;
    private String description;
    private Set<AddressEntity> addressesByIdCountry;

    public CountryEntity() {
    }

    // Init for new record
    public CountryEntity(Integer idCountry, String description) {
        this.idCountry = idCountry;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country", nullable = false)
    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(idCountry, that.idCountry) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCountry, description);
    }

    @OneToMany(mappedBy = "countryByCountryId")
    public Set<AddressEntity> getAddressesByIdCountry() {
        return addressesByIdCountry;
    }

    public void setAddressesByIdCountry(Set<AddressEntity> addressesByIdCountry) {
        this.addressesByIdCountry = addressesByIdCountry;
    }

    @Override
    public String toString() { return description ; }
}
