package com.alexandria.controllers;

import com.alexandria.entities.*;
import com.alexandria.managers.Cart;
import com.alexandria.managers.CartImpl;
import com.alexandria.managers.ClientManager;
import com.alexandria.managers.ProductManager;
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
import java.sql.Date;
import java.util.List;

@Controller
@SessionAttributes("client")
public class CartController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

    @Autowired
    ProductManager productManager;

    @Autowired
    ClientManager clientManager;

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

    @RequestMapping(value = "/checkout")
    public ModelAndView checkout(HttpServletRequest request, HttpServletResponse response) {

        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        ModelAndView mav = new ModelAndView("checkout");

        // Set in session via @SessionAttributes("client") to keep info when POST action is called
        mav.addObject("client", userCartSession.getClient());

        mav.addObject("titles", titles);
        mav.addObject("paymentMethods", paymentMethods);
        mav.addObject("countries", countries);

        return mav;
    }

    @RequestMapping(value = "/checkoutProcess", method = RequestMethod.POST)
    public ModelAndView checkoutProcess(HttpServletRequest request, HttpServletResponse response,
                                        @ModelAttribute("client") ClientEntity client, // From session (@SessionAttributes("client"))
                                        @RequestParam("gender") Integer iTitle,
                                        @RequestParam("paymentMethod") Integer iPaymentMethod,
                                        @RequestParam("country") Integer iCountry,
                                        SessionStatus status) {

        // Set values from combobox
        client.setTitleByTitleId(titles.get(iTitle));
        client.setPaymentMethodByPaymentMethodId(paymentMethods.get(iPaymentMethod));
        client.getAddressByInvoiceAddressId().setCountryByCountryId(countries.get(iCountry));

        // Update client in database
        clientManager.updateClient(client);

        /* Set order placed date that indicate the order is no more active */
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");
        Date currentDate = new java.sql.Date(System.currentTimeMillis());
        userCartSession.setDatePlaced( currentDate );

        // Create a new user cart session that replaces the previous one
        request.getSession().setAttribute( "userCartSession", (Cart)new CartImpl(client));

        ModelAndView mav = new ModelAndView("orderResume");

        mav.addObject("userCartSession", userCartSession);
        mav.addObject("message", "Your order has been placed successfully on " + currentDate);

        //Mark Session Complete
        status.setComplete();

        return mav;
    }

    @RequestMapping({"/remProduct"})
    public ModelAndView remProduct(HttpServletRequest request, @RequestParam(value = "idProduct", defaultValue = "") Integer idProduct) {

        if(idProduct > 0) {
            ProductEntity product = productManager.findProductFromId(idProduct);

            if( product != null ) {
                Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");
                userCartSession.removeLineItem(product);
            } else {
                logger.warn("Product not found from id " + idProduct);
            }
        } else {
            logger.warn("Try to remove a product with id <= 0 : " + idProduct);
        }

        return new ModelAndView("redirect:/cartView");
    }
}

