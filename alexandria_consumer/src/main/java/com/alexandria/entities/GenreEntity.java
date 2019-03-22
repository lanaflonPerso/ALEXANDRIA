package com.alexandria.entities;

import com.alexandria.windows.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genre", schema = "dbo", catalog = "DB_ALEXANDRIA")
@NamedQueries({
        @NamedQuery(name = "GenreEntity.findAll", query = "from GenreEntity")
})
public class GenreEntity extends AbstractModelObject {
    private Integer idGenre;
    private String description;
    private Set<BookGenreEntity> bookGenresByIdGenre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre", nullable = false)
    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
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
        GenreEntity that = (GenreEntity) o;
        return Objects.equals(idGenre, that.idGenre) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenre, description);
    }

    @OneToMany(mappedBy = "genreByGenreId")
    public Set<BookGenreEntity> getBookGenresByIdGenre() {
        return bookGenresByIdGenre;
    }

    public void setBookGenresByIdGenre(Set<BookGenreEntity> bookGenresByIdGenre) {
        this.bookGenresByIdGenre = bookGenresByIdGenre;
    }
}
