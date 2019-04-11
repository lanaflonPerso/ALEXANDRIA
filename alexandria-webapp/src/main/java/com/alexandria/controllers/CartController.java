package com.alexandria.controllers;

import com.alexandria.entities.ProductEntity;
import com.alexandria.managers.Cart;
import com.alexandria.managers.ProductManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("session")
public class CartController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

    @Autowired
    ProductManager productManager;

    @RequestMapping(value = "/cartView")
    public ModelAndView showOrderLines(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("cartView");
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        mav.addObject("userCartSession", userCartSession);

        return mav;
    }

    @RequestMapping(value = "/cartUpdateLineItem")
    public ModelAndView updateLineItem(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("idProduct") Integer idProduct,
                                       @RequestParam("quantity") Integer quantity) {

        ProductEntity product = productManager.findProductFromId(idProduct);

        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        userCartSession.updateLineItem(product, quantity);

        ModelAndView mav = new ModelAndView("cartView");

        mav.addObject("userCartSession", userCartSession);

        return mav;
    }

    @RequestMapping({"/remProduct"})
    public ModelAndView remProduct(HttpServletRequest request, @RequestParam(value = "code", defaultValue = "") Integer code) {

        ModelAndView mav = new ModelAndView("redirect:/cartView");

        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        ProductEntity product=null;
        if (code  > 0) {
            product = productManager.findProductFromId(code);
        }
        if (product != null) {

            userCartSession.removeLineItem(product);
        }
        return mav;
    }
}

