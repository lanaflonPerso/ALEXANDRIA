package com.alexandria.entities;

import antlr.Utils;
import com.alexandria.util.AbstractModelObject;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "product", schema = "dbo", catalog = "DB_ALEXANDRIA")
@NamedQueries({
        @NamedQuery(
                name = "ProductEntity.findFromName",
                query = "from ProductEntity as p where upper(p.name) like upper(concat('%', :name, '%'))"
        ),
        @NamedQuery(
                name = "ProductEntity.findAllFromCategoryId",
                query = "select p.productByProductId from ProductCategoryEntity as p where p.categoryByCategoryId in ( :categories )"
        )
})
public class ProductEntity extends AbstractModelObject implements Serializable {
    private Integer idProduct;
    private String name;
    private Integer stock;
    private BigDecimal priceExVat;
    private byte[] picture;
    private BookEntity bookByIdProduct;
    private Set<OrderLineEntity> orderLinesByIdProduct;
    private List<ProductCategoryEntity> productCategoriesByIdProduct; // TODO : TBC : Set has changed to List for JTable databinding


    public ProductEntity() {
    }

    public ProductEntity(ProductEntity product) {
        this.idProduct = product.idProduct;
        this.name = product.name;
        this.stock = product.stock;
        this.priceExVat = product.priceExVat;
        this.picture = product.picture;
        this.bookByIdProduct = product.bookByIdProduct;
        this.orderLinesByIdProduct = product.orderLinesByIdProduct;
        this.productCategoriesByIdProduct = product.productCategoriesByIdProduct;

    }

    // Init for new record
    public ProductEntity(String name, Integer stock, BigDecimal priceExVat, byte[] picture, BookEntity bookByIdProduct) {
        this.name = name;
        this.stock = stock;
        this.priceExVat = priceExVat;
        this.picture = picture;
        this.bookByIdProduct = bookByIdProduct;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "stock", nullable = false)
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "price_ex_vat", nullable = false, precision = 4)
    public BigDecimal getPriceExVat() {
        return priceExVat;
    }

    public void setPriceExVat(BigDecimal priceExVat) {
        this.priceExVat = priceExVat;
    }

    @Lob
    @Column(name = "picture", nullable = true)
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    // TODO : TBC
    @Transient
    public ImageIcon getImage() {
        return new ImageIcon(picture);
    }
    @Transient
    public void setImage(File imageFile) {

        String format = FilenameUtils.getExtension(imageFile.getName());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(10000);
        BufferedImage img = null;
        try {
            img = ImageIO.read(Utils.class.getResourceAsStream(imageFile.getCanonicalPath()));
            ImageIO.write(img, format, outputStream);
            outputStream.flush();
            picture = Base64.getEncoder().encode(outputStream.toByteArray());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    pour le web (affichage de l'image)
/*    public String base64Image;

    @Transient
    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.picture);
        return base64Image;
    }*/
@Transient
String base64Image;
    @Transient
    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.picture);
        return base64Image;
    }
    @Transient
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(idProduct, that.idProduct) &&
                Objects.equals(name, that.name) &&
                Objects.equals(stock, that.stock) &&
                Objects.equals(priceExVat, that.priceExVat) &&
                Arrays.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idProduct, name, stock, priceExVat);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    @OneToOne(mappedBy = "productByProductId")
    public BookEntity getBookByIdProduct() {
        return bookByIdProduct;
    }

    public void setBookByIdProduct(BookEntity bookByIdProduct) {
        this.bookByIdProduct = bookByIdProduct;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Set<OrderLineEntity> getOrderLinesByIdProduct() {
        return orderLinesByIdProduct;
    }

    public void setOrderLinesByIdProduct(Set<OrderLineEntity> orderLinesByIdProduct) {
        this.orderLinesByIdProduct = orderLinesByIdProduct;
    }

    // By default, the JPA @ManyToOne and @OneToOne annotations are fetched EAGERly
    // , while the @OneToMany and @ManyToMany relationships are considered LAZY.
    @OneToMany(mappedBy = "productByProductId", fetch = FetchType.EAGER) // TODO : TBC
    public List<ProductCategoryEntity> getProductCategoriesByIdProduct() {
        return productCategoriesByIdProduct;
    }

    public void setProductCategoriesByIdProduct(List<ProductCategoryEntity> productCategoriesByIdProduct) {
        this.productCategoriesByIdProduct = productCategoriesByIdProduct;
    }

    @Override
    public String toString() {
        return name;
    }
}
