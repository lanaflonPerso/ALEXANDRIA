package com.alexandria.controllers;
import com.alexandria.entities.CategoryEntity;
import com.alexandria.entities.ProductEntity;
import com.alexandria.managers.Cart;
import com.alexandria.managers.ProductManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static jdk.nashorn.internal.objects.NativeArray.lastIndexOf;

@Controller
public class ProductsController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

    @Autowired
    ProductManager productManager;

    List<CategoryEntity> categoryList;
    Set<CategoryEntity> categoryParent;
    List<ProductEntity> productsList;

    @PostConstruct
    public void init() {
        categoryList = productManager.getCategoriesList();
        categoryParent = productManager.getCategoryParents();
        productsList = productManager.getProductsList();
    }

    //    categoryTree returns the param category (the selected one) and its children (if it has some)
    private List<CategoryEntity> categoryTree(int categoryId) {
//        CategoryEntity category = productManager.findCategoryFromId(categoryId);

        // useful ?
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


    @RequestMapping(value = "/products{catId}")

    public ModelAndView productList(@RequestParam(required = false) Integer page, @PathVariable(value = "catId") Integer id) {
        ModelAndView mav = new ModelAndView("products");

        mav.addObject("categoryList", categoryList);
        mav.addObject("categoryParent", categoryParent);

        List<ProductEntity> products = null;

        if (id == null || id == 1) {
            products = productsList;
        } else {
            List<CategoryEntity> categories = categoryTree(id);
            products = productManager.findProductsFromCategoriesId(categories);
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

        mav.addObject("category", id);
        return mav;
    }

    @RequestMapping({"/addProduct"})
    public ModelAndView listProductHandler(HttpServletRequest request, @RequestParam(value = "code", defaultValue = "") Integer code) {
        String referer = request.getHeader("Referer");
        referer = referer.substring(referer.lastIndexOf('/') + 1);
        ModelAndView mav = new ModelAndView("redirect:/" + referer);

        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        ProductEntity product=null;
        if (code  > 0) {
        product = productManager.findProductFromId(code);
        }
        if (product != null) {

            userCartSession.addLineItem(product);
        }
        return mav;
    }

    @RequestMapping(value = "/product{productId}")
    public ModelAndView productList(@PathVariable(value = "productId") int productId, HttpServletRequest request ) {
        ModelAndView mav = new ModelAndView("product");

//        seeking productId in productList
//        findFirst to stop looking for it if found
//        orElse in case ze wouldn't find it
        ProductEntity product = productsList.stream()
                                            .filter(prod -> productId == prod.getIdProduct())
                                            .findFirst().orElse(null);

//        gets the parent page to feed the "back" link
        String referer = request.getHeader("Referer");

        mav.addObject("referer", referer);
        mav.addObject("product", product);
        return mav;
    }
}

