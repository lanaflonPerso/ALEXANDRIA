package com.alexandria.entities;

import com.alexandria.windows.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book_genre", schema = "dbo", catalog = "DB_ALEXANDRIA")
@IdClass(BookGenreEntityPK.class)
public class BookGenreEntity extends AbstractModelObject {
    private Integer bookId;
    private Integer genreId;
    private BookEntity bookByBookId;
    private GenreEntity genreByGenreId;

    @Id
    @Column(name = "book_id", nullable = false)
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Id
    @Column(name = "genre_id", nullable = false)
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
        BookGenreEntity that = (BookGenreEntity) o;
        return bookId.equals(that.bookId) &&
                genreId.equals(that.genreId) &&
                bookByBookId.equals(that.bookByBookId) &&
                genreByGenreId.equals(that.genreByGenreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, genreId, bookByBookId, genreByGenreId);
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id_book", insertable = false, updatable = false, nullable = false) // TODO : TBC
    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id_genre", insertable = false, updatable = false, nullable = false) // TODO : TBC
    public GenreEntity getGenreByGenreId() {
        return genreByGenreId;
    }

    public void setGenreByGenreId(GenreEntity genreByGenreId) {
        this.genreByGenreId = genreByGenreId;
    }
}
