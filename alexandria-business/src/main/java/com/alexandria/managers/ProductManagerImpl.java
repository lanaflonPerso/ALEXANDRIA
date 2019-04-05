package com.alexandria.managers;

import com.alexandria.dao.CategoryDao;
import com.alexandria.dao.ProductDao;
import com.alexandria.entities.CategoryEntity;
import com.alexandria.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductManagerImpl implements ProductManager {

    @Autowired
    public ProductDao productDao;
    @Autowired
    public CategoryDao categoryDao;


    @Override
    public List<ProductEntity> getProductsList() {
        return productDao.findAll();
    }

    @Override
    public ProductEntity findProductFromId(int productId) {
        return productDao.find(productId);
    }

    @Override
    public List<CategoryEntity> getCategoriesList() {
        return categoryDao.findAll();
    }

    @Override
    public CategoryEntity findCategoryFromId(int categoryId) {
        return categoryDao.find(categoryId);
    }

    @Override
    public List<ProductEntity> findProductsFromCategoriesId(List<CategoryEntity> categories) {
        return productDao.findAllFromCategoryId(categories);
    }
    @Override
    public List<CategoryEntity> findCategoriesFromParent(CategoryEntity category) {
        return categoryDao.findFromParent(category.getIdCategory());
    }
}
