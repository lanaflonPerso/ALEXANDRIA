package com.alexandria.controllers;

import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.OrderLineEntity;
import com.alexandria.managers.Cart;
import com.alexandria.managers.AccountManager;
import com.alexandria.managers.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class AccountController {

    @Autowired
    AccountManager orderManager;

    @Autowired
    ClientManager clientManager;

    List<OrderHeaderEntity> clientOrders;

    @PostConstruct
    public void init() {

    }

    @RequestMapping(value = "/account")
    public ModelAndView showAccount(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("account");
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        mav.addObject("userCartSession", userCartSession);
        return mav;
    }


    @RequestMapping(value = "/orders")
    public ModelAndView orderList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("orders");
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        clientOrders = orderManager.getOrdersList(userCartSession.getClient());

        mav.addObject("orders", clientOrders);

        return mav;
    }

    @RequestMapping(value = "/orderLines")
    public ModelAndView orderLinesList(@RequestParam("orderIndex") Integer orderIndex) {
        ModelAndView mav = new ModelAndView("orderLines");

        OrderHeaderEntity clientOrder = clientOrders.get(orderIndex);
        mav.addObject("clientOrder", clientOrder);

        List<OrderLineEntity> orderLines = orderManager.getOrderLines(clientOrder);
        mav.addObject("orderLines", orderLines);

        return mav;
    }
}
