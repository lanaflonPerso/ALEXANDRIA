package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author", schema = "dbo", catalog = "DB_ALEXANDRIA")
public class AuthorEntity extends AbstractModelObject {
    private Integer idAuthor;
    private String firstName;
    private String lastName;
    private String bio;
    private Set<BookEntity> booksByIdAuthor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author", nullable = false)
    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Basic
    @Column(name = "bio", nullable = true, length = -1)
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(idAuthor, that.idAuthor) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(bio, that.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuthor, firstName, lastName, bio);
    }

    @OneToMany(mappedBy = "authorByAuthorId")
    public Set<BookEntity> getBooksByIdAuthor() {
        return booksByIdAuthor;
    }

    public void setBooksByIdAuthor(Set<BookEntity> booksByIdAuthor) {
        this.booksByIdAuthor = booksByIdAuthor;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
