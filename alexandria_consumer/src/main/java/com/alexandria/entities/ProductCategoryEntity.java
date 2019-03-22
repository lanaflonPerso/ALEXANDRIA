package com.alexandria.entities;

import com.alexandria.windows.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_category", schema = "dbo", catalog = "DB_ALEXANDRIA")
@IdClass(ProductCategoryEntityPK.class)
public class ProductCategoryEntity extends AbstractModelObject {
    private Integer categoryId;
    private Integer productId;
    private ProductEntity productByProductId;
    private CategoryEntity categoryByCategoryId;

    public ProductCategoryEntity() {
    }

    // Init for new record
    public ProductCategoryEntity(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @Id
    @Column(name = "category_id", nullable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Id
    @Column(name = "product_id", nullable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryEntity that = (ProductCategoryEntity) o;
        return categoryId.equals(that.categoryId) &&
                productId.equals(that.productId) &&
                productByProductId.equals(that.productByProductId) &&
                categoryByCategoryId.equals(that.categoryByCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, productId, productByProductId, categoryByCategoryId);
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product", insertable = false, updatable = false, nullable = false) // TODO : TBC
    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id_category", insertable = false, updatable = false, nullable = false) // TODO : TBC
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

}
