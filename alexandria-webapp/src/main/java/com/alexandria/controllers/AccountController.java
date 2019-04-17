package com.alexandria.controllers;

import com.alexandria.entities.*;
import com.alexandria.managers.AccountManager;
import com.alexandria.managers.Cart;
import com.alexandria.managers.CartImpl;
import com.alexandria.managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@SessionAttributes("client")
public class AccountController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

    @Autowired
    AccountManager orderManager;

    @Autowired
    ClientManager clientManager;

    List<OrderHeaderEntity> clientOrders;

    // Combobox
    List<TitleEntity> titles;
    List<PaymentMethodEntity> paymentMethods;
    List<CountryEntity> countries;

    @PostConstruct
    public void init() {

        titles = clientManager.getTitlesList();
        paymentMethods = clientManager.getPaymentMethodsList();
        countries = clientManager.getCountriesList();
    }

    @RequestMapping(value = "/account")
    public ModelAndView account(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("account");
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        mav.addObject("client", userCartSession.getClient());

        mav.addObject("titles", titles);
        mav.addObject("paymentMethods", paymentMethods);
        mav.addObject("countries", countries);

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

    @RequestMapping(value = "/accountUpdate", method = RequestMethod.POST)
    public ModelAndView accountUpdate(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("client") ClientEntity client, // From session (@SessionAttributes("client"))
                                      @RequestParam("gender") Integer iTitle,
                                      @RequestParam("paymentMethod") Integer iPaymentMethod,
                                      @RequestParam("countryInvoice") Integer iCountryInvoice,
                                      @RequestParam("countryDelivery") Integer iCountryDelivery,
                                      SessionStatus status
                                        ) {

        // Set values from combobox
        client.setTitleByTitleId(titles.get(iTitle));
        client.setPaymentMethodByPaymentMethodId(paymentMethods.get(iPaymentMethod));
        client.getAddressByInvoiceAddressId().setCountryByCountryId(countries.get(iCountryInvoice));
        client.getAddressByDeliveryAddressId().setCountryByCountryId(countries.get(iCountryDelivery));

        // Update client in database
        clientManager.updateClient(client);

        //Update client in session
        request.getSession().setAttribute("userSession", client);

        //Mark Session Complete
        status.setComplete();

        // Set model & view
        ModelAndView mav = new ModelAndView("redirect:/products");


        return mav;
    }
}
