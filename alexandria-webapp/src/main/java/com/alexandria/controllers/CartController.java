package com.alexandria.controllers;

import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.CountryEntity;
import com.alexandria.entities.PaymentMethodEntity;
import com.alexandria.entities.ProductEntity;
import com.alexandria.managers.Cart;
import com.alexandria.managers.ClientManager;
import com.alexandria.managers.ProductManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

@Controller
@Scope("session")
public class CartController {

    private static final Logger logger = LogManager.getLogger(ProductsController.class);

    @Autowired
    ProductManager productManager;

    @Autowired
    ClientManager clientManager;

    // Combobox
    List<PaymentMethodEntity> paymentMethods;
    List<CountryEntity> countries;

    @PostConstruct
    public void init() {

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

        mav.addObject("client", userCartSession.getClient());
        mav.addObject("paymentMethods", paymentMethods);
        mav.addObject("countries", countries);

        return mav;
    }

    @RequestMapping(value = "/checkoutProcess", method = RequestMethod.POST)
    public ModelAndView checkoutProcess(HttpServletRequest request, HttpServletResponse response,
                                        @ModelAttribute("client") ClientEntity client,
                                        @RequestParam("iPaymentMethod") Integer iPaymentMethod,
                                        @RequestParam("iCountry") Integer iCountry) {


        // Set values from combobox
        client.setPaymentMethodByPaymentMethodId(paymentMethods.get(iPaymentMethod));
        client.getAddressByInvoiceAddressId().setCountryByCountryId(countries.get(iCountry));

        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");

        /* Set order placed date that indicate the order is no more active */
        Date currentDate = new java.sql.Date(System.currentTimeMillis());
        userCartSession.setDatePlaced( currentDate );

        ModelAndView mav = new ModelAndView("cartView");

        mav.addObject("userCartSession", userCartSession);
        mav.addObject("message", "Your order has been placed successfully on " + currentDate);

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

