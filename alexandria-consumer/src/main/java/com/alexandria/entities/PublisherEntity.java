package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "publisher", schema = "dbo", catalog = "DB_ALEXANDRIA")
public class PublisherEntity extends AbstractModelObject {
    private Integer idPublisher;
    private String name;
    private Set<BookEntity> booksByIdPublisher;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publisher", nullable = false)
    public Integer getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Integer idPublisher) {
        this.idPublisher = idPublisher;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherEntity that = (PublisherEntity) o;
        return Objects.equals(idPublisher, that.idPublisher) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublisher, name);
    }

    @OneToMany(mappedBy = "publisherByPublisherId")
    public Set<BookEntity> getBooksByIdPublisher() {
        return booksByIdPublisher;
    }

    public void setBooksByIdPublisher(Set<BookEntity> booksByIdPublisher) {
        this.booksByIdPublisher = booksByIdPublisher;
    }
}
