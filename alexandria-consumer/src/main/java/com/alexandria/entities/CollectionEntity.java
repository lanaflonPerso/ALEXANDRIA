package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "collection", schema = "dbo", catalog = "DB_ALEXANDRIA")
public class CollectionEntity extends AbstractModelObject {
    private Integer idCollection;
    private String description;
    private Set<BookEntity> booksByIdCollection;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collection", nullable = false)
    public Integer getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(Integer idCollection) {
        this.idCollection = idCollection;
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
        CollectionEntity that = (CollectionEntity) o;
        return Objects.equals(idCollection, that.idCollection) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCollection, description);
    }

    @OneToMany(mappedBy = "collectionByCollectionId")
    public Set<BookEntity> getBooksByIdCollection() {
        return booksByIdCollection;
    }

    public void setBooksByIdCollection(Set<BookEntity> booksByIdCollection) {
        this.booksByIdCollection = booksByIdCollection;
    }
}
