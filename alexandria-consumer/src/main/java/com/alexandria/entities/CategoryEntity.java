package com.alexandria.entities;

import com.alexandria.util.AbstractModelObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category", schema = "dbo", catalog = "DB_ALEXANDRIA")
@NamedQueries({
        @NamedQuery(
                name = "CategoryEntity.findFromParent",
                query = "from CategoryEntity as c where c.categoryByParent.idCategory = :parent"
        )
})
public class CategoryEntity extends AbstractModelObject {
    private Integer idCategory;
    private String description;
    private CategoryEntity categoryByParent;
    private Set<CategoryEntity> categoriesByIdCategory;
    private Set<ProductCategoryEntity> productCategoriesByIdCategory;


    public CategoryEntity() {
    }

    public CategoryEntity(CategoryEntity category) {
        this.idCategory = category.idCategory;
        this.description = category.description;
        this.categoryByParent = category.categoryByParent;
        this.categoriesByIdCategory = category.categoriesByIdCategory;
        this.productCategoriesByIdCategory = category.productCategoriesByIdCategory;
    }

    // Init for new record
    public CategoryEntity(Integer idCategory, String description) {
        this.idCategory = idCategory;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
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
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(idCategory, that.idCategory) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, description);
    }

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id_category", nullable = false)
    public CategoryEntity getCategoryByParent() {
        return categoryByParent;
    }

    public void setCategoryByParent(CategoryEntity categoryByParent) {
        this.categoryByParent = categoryByParent;
    }

    @OneToMany(mappedBy = "categoryByParent")
    public Set<CategoryEntity> getCategoriesByIdCategory() {
        return categoriesByIdCategory;
    }

    public void setCategoriesByIdCategory(Set<CategoryEntity> categoriesByIdCategory) {
        this.categoriesByIdCategory = categoriesByIdCategory;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Set<ProductCategoryEntity> getProductCategoriesByIdCategory() {
        return productCategoriesByIdCategory;
    }

    public void setProductCategoriesByIdCategory(Set<ProductCategoryEntity> productCategoriesByIdCategory) {
        this.productCategoriesByIdCategory = productCategoriesByIdCategory;
    }
}
