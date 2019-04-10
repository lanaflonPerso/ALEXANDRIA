package com.alexandria.managers;

import com.alexandria.entities.CategoryEntity;
import com.alexandria.entities.ProductEntity;

import java.util.List;
import java.util.Set;

public interface ProductManager {

    List<ProductEntity> getProductsList();
    ProductEntity findProductFromId(int productId);
    List<CategoryEntity> getCategoriesList();
    CategoryEntity findCategoryFromId(int categoryId);
    List<ProductEntity> findProductsFromCategoriesId(List<CategoryEntity> categories);

    List<CategoryEntity> findCategoriesFromParent(CategoryEntity category);
    Set<CategoryEntity> getCategoryParents();
}
