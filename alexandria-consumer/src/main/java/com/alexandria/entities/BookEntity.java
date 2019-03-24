package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "dbo", catalog = "DB_ALEXANDRIA")
@NamedQueries({
        @NamedQuery(
                name="BookEntity.findAll",
                query ="from BookEntity ")
})
public class BookEntity extends AbstractModelObject {
    private Integer idBook;
    private String isbn;
    private String title;
    private Integer nbPages;
    private ProductEntity productByProductId;
    private AuthorEntity authorByAuthorId;
    private PublisherEntity publisherByPublisherId;
    private CollectionEntity collectionByCollectionId;
    private List<BookGenreEntity> bookGenresByIdBook; // // TODO : TBC : Set has changed to List for JTable databinding


    public BookEntity() {
    }

    public BookEntity(BookEntity book) {
        this.idBook = book.idBook;
        this.isbn = book.isbn;
        this.title = book.title;
        this.nbPages = book.nbPages;
        this.productByProductId = book.productByProductId;
        this.authorByAuthorId = book.authorByAuthorId;
        this.publisherByPublisherId = book.publisherByPublisherId;
        this.collectionByCollectionId = book.collectionByCollectionId;
        this.bookGenresByIdBook = book.bookGenresByIdBook;
    }

    // Init for new record
    public BookEntity(String isbn, String title, Integer nbPages, AuthorEntity authorByAuthorId, PublisherEntity publisherByPublisherId, CollectionEntity collectionByCollectionId) {
        this.isbn = isbn;
        this.title = title;
        this.nbPages = nbPages;
        this.authorByAuthorId = authorByAuthorId;
        this.publisherByPublisherId = publisherByPublisherId;
        this.collectionByCollectionId = collectionByCollectionId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", nullable = false)
    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "isbn", nullable = false, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "nb_pages", nullable = true)
    public Integer getNbPages() {
        return nbPages;
    }

    public void setNbPages(Integer nbPages) {
        this.nbPages = nbPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(idBook, that.idBook) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(title, that.title) &&
                Objects.equals(nbPages, that.nbPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, isbn, title, nbPages);
    }

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product", nullable = false)
    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id_author", nullable = false)
    public AuthorEntity getAuthorByAuthorId() {
        return authorByAuthorId;
    }

    public void setAuthorByAuthorId(AuthorEntity authorByAuthorId) {
        this.authorByAuthorId = authorByAuthorId;
    }

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id_publisher", nullable = false)
    public PublisherEntity getPublisherByPublisherId() {
        return publisherByPublisherId;
    }

    public void setPublisherByPublisherId(PublisherEntity publisherByPublisherId) {
        this.publisherByPublisherId = publisherByPublisherId;
    }

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id_collection", nullable = false)
    public CollectionEntity getCollectionByCollectionId() {
        return collectionByCollectionId;
    }

    public void setCollectionByCollectionId(CollectionEntity collectionByCollectionId) {
        this.collectionByCollectionId = collectionByCollectionId;
    }

    // TODO : TBC : Added specific Hibernate annotation : @Fetch(value = FetchMode.SUBSELECT)
    //  -> to avoid org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags
    //  -> caused by an already set annotation fetch = FetchType.EAGER for getBookByIdProduct in ProductEntity
    //  -> it would not have happened if bookGenresByIdBook was a Set instead of a List because the order of the elements is already set (no need for extra queries)
    //  but the List was needed for JTable databinding
    //  -> There is an other specific Hibernate annotation to handle without annotation fetch = FetchType.EAGER : @LazyCollection(LazyCollectionOption.FALSE) but it is more queries consumming
    // TODO 1 : Instead of using a specific Hibernate annotation use a JPA annotation : @OrderColumn (the name of a field to be ordered)
    //   But keep in mind it could create empty elements in the list if the chosen field does not have values starting from 0
    // TODO 2 : better use LAZY and when you really need to load the collections use: Hibernate.initialize( myEntity.getListOfThings() ) to fetch the data.
    //  There is no JPA equivalent to Hibernate.initialize, use instead myEntity.getListOfThings().size() since the object is loaded.
    //  -> Cf. https://stackoverflow.com/questions/4334970/hibernate-cannot-simultaneously-fetch-multiple-bags
    //      -> Cf. https://stackoverflow.com/questions/29368563/jpa-equivalent-command-to-hibernate-initialize
    //      -> Cf. https://stackoverflow.com/questions/4306463/how-to-test-whether-lazy-loaded-jpa-collection-is-initialized
    @OneToMany(mappedBy = "bookByBookId", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<BookGenreEntity> getBookGenresByIdBook() {
        return bookGenresByIdBook;
    }

    public void setBookGenresByIdBook(List<BookGenreEntity> bookGenresByIdBook) {
        this.bookGenresByIdBook = bookGenresByIdBook;
    }
}
