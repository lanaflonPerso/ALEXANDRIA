package com.alexandria.controllers;

import com.alexandria.entities.CategoryEntity;
import com.alexandria.managers.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProductsController {

    @Autowired
    ProductManager productManager;

    List<CategoryEntity> categoryList;


/*    public void init(){
        categoryList = productManager.getCategoriesList();
    }*/

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showProducts(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("products");
        categoryList = productManager.getCategoriesList();

        mav.addObject("productsList", productManager.getProductsList());
        mav.addObject("categoryList", categoryList);
// filtrer les produits par catégorie : https://openclassrooms.com/forum/sujet/jquery-filtrer-les-produits-par-categorie
        return mav;
    }

    @RequestMapping(value = "/productsByCat{catId}")
    public @ResponseBody ModelAndView getAttr(@PathVariable(value="catId") String id) {
        ModelAndView mav = new ModelAndView("products");
        categoryList = productManager.getCategoriesList();

        mav.addObject("categoryList", categoryList);
        mav.addObject("productsList", productManager.findProductsFromCategoriesId(categoryTree(Integer.parseInt(id))));
        return mav;
    }

    //    déplacer
    private List<CategoryEntity> categoryTree(int categoryId) {
        CategoryEntity category = productManager.findCategoryFromId(categoryId);
        List<CategoryEntity> cateTree = new ArrayList<>();
        cateTree.add(category);
        System.out.println("VUUUUUUUUUUUUUUUUUUUUUUUUUUCHE : " + category);
        try {
            cateTree.addAll(productManager.findCategoriesFromParent(category));
        } finally {
            return cateTree;
        }
        }
    }

