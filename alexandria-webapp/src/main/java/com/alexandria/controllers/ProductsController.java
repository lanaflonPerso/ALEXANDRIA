package com.alexandria.controllers;

import com.alexandria.entities.CategoryEntity;
import com.alexandria.managers.Cart;
import com.alexandria.managers.ProductManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

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

        // TODO ----------------------------------------------------
        // Get user cart session
        Cart userCartSession = (Cart)request.getSession().getAttribute( "userCartSession");
        mav.addObject("userCartSession", userCartSession);
        //  TODO:
        //   -> Utiliser "userCartSession" dans products.jsp pour le databinding
        //      -> ATTENTION au null si le "userCartSession" n'a pas été initialisé lors du login/register
        //          -> pas d'accès à products.jsp tant que l'utilisateur n'est pas loggé (cf. header.jspf )
        //   -> Récupérer "userCartSession" dans la méthode POST du ProductsController via l'argument "@ModelAttribute("userCartSession") Cart userCartSession"
        //   -> Si "userCartSession" n'est pas utilisé pour le databinding faire le "...getSession()..." dans la méthode POST.
        // TODO ----------------------------------------------------

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

