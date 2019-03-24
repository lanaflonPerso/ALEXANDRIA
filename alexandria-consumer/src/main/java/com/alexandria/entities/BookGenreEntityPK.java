package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BookGenreEntityPK extends AbstractModelObject implements Serializable {
    private Integer bookId;
    private Integer genreId;

    @Column(name = "book_id", nullable = false)
    @Id
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Column(name = "genre_id", nullable = false)
    @Id
    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenreEntityPK that = (BookGenreEntityPK) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, genreId);
    }
}
