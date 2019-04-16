package com.alexandria.controllers;
import com.alexandria.entities.CategoryEntity;
import com.alexandria.entities.ProductCategoryEntity;
import com.alexandria.entities.ProductEntity;
import com.alexandria.managers.ProductManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ProductsController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

    @Autowired
    ProductManager productManager;

    List<CategoryEntity> categoryList;
    Set<CategoryEntity> categoryParent;
    static List<ProductEntity> productsList;

    @PostConstruct
    public void init() {
        categoryList = productManager.getCategoriesList();
        categoryParent = productManager.getCategoryParents();
        productsList = productManager.getProductsList();
    }

    //    categoryTree returns the param category (the selected one) and its children (if it has some)
    private List<CategoryEntity> categoryTree(int categoryId) {

        CategoryEntity category = categoryList.stream()
                .filter(cate -> categoryId == cate.getIdCategory())
                .findFirst().orElse(null);
         //

        List<CategoryEntity> cateTree = new ArrayList<>();
        List<CategoryEntity> categories = productManager.findCategoriesFromParent(category);

        if (categories != null) {
            cateTree.addAll(categories); //adds each item of categories in cateTree
        }
            cateTree.add(category); // adds the selected category in cateTree
            return cateTree;
    }


    @RequestMapping(value = "/products")
    public ModelAndView productList(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer categoryId
                                    ) {
        ModelAndView mav = new ModelAndView("products");

        // information to build the category menu
        mav.addObject("categoryList", categoryList); //list of all categories
        mav.addObject("categoryParent", categoryParent); // list of categories with at least one child

        List<ProductEntity> products = null;

        if (categoryId == null || categoryId == 1) {
            // if no category is selected or category 1 is selected (the top one) all the products will be displayed
            products = productsList;
        } else {
            // else we get all the products belonging to the selected category or its children
            List<CategoryEntity> categories = categoryTree(categoryId);
            //products = productManager.findProductsFromCategoriesId(categories);
            products = findProductsFromCategoriesId(categories); // More efficient : no database access
        }

        PagedListHolder<ProductEntity> pagedListHolder = new PagedListHolder<>(products);
        pagedListHolder.setPageSize(12);
        mav.addObject("maxPages", pagedListHolder.getPageCount());

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;

        mav.addObject("page", page);
        if (page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            mav.addObject("productsList", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            mav.addObject("productsList", pagedListHolder.getPageList());
        }

        mav.addObject("category", categoryId);
        return mav;
    }

    @RequestMapping(value = "/product")
    public ModelAndView productList(HttpServletRequest request,
                                    @RequestParam("productId") Integer productId,
                                    @RequestParam(required = false) Integer addP ) {
        ModelAndView mav = new ModelAndView("product");

//        seeking productId in productList
//        findFirst to stop looking for it if found
//        orElse in case ze wouldn't find it
        ProductEntity product = productsList.stream()
                                            .filter(prod -> productId == prod.getIdProduct())
                                            .findFirst().orElse(null);

//        gets the parent page to feed the "back" link
        String referer = request.getHeader("Referer");
        if (addP != null) {
            referer = "/";
        }
        mav.addObject("referer", referer);
        mav.addObject("product", product);
        return mav;
    }

    @Nullable
    static ProductEntity findProductFromId(Integer IdProduct) {

        // Look for product by Id from local productsList
        for(ProductEntity productItem : productsList) {
            if(productItem.getIdProduct() == IdProduct)
                return productItem;
        }
        return null;
    }

    private List<ProductEntity> findProductsFromCategoriesId(List<CategoryEntity> categories) {

        List<ProductEntity> products = new ArrayList<>();

        for(ProductEntity product : productsList)
            for(ProductCategoryEntity productCategory : product.getProductCategoriesByIdProduct())
                if(categories.contains(productCategory.getCategoryByCategoryId())) {
                    products.add(product);
                    break;
                }

        return products;
    }
}

