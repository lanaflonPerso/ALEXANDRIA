package com.alexandria.controllers;

import com.alexandria.entities.OrderLineEntity;
import com.alexandria.managers.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Scope("session")
public class CartController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

    @RequestMapping(value = "/cartView")
    public ModelAndView showOrderLines(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("cartView");
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        List<OrderLineEntity> orderLines = userCartSession.getOrderLines();

        mav.addObject("userCartSession", userCartSession);
        mav.addObject("orderLines", orderLines);


        return mav;
    }
}

