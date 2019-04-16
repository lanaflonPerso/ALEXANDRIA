package com.alexandria.controllers;

import com.alexandria.dao.DAOFactory;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    List<ShippingMethodEntity> shippingMethods;

    @PostConstruct
    public void init() {

        titles = clientManager.getTitlesList();
        paymentMethods = clientManager.getPaymentMethodsList();
        countries = clientManager.getCountriesList();
        shippingMethods = new DAOFactory().getShippingMethodDao().findAll(); // TODO : should use a manager that does not exist yet
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
        mav.addObject("shippingMethods", shippingMethods);

        return mav;
    }

    @RequestMapping(value = "/checkoutProcess", method = RequestMethod.POST)
    public ModelAndView checkoutProcess(HttpServletRequest request, HttpServletResponse response,
                                        @ModelAttribute("client") ClientEntity client, // From session (@SessionAttributes("client"))
                                        @RequestParam("gender") Integer iTitle,
                                        @RequestParam("paymentMethod") Integer iPaymentMethod,
                                        @RequestParam("countryInvoice") Integer iCountryInvoice,
                                        @RequestParam("countryDelivery") Integer iCountryDelivery,
                                        @RequestParam("shippingMethod") Integer iShippingMethod,
                                        SessionStatus status) {

        // Set values from combobox
        client.setTitleByTitleId(titles.get(iTitle));
        client.setPaymentMethodByPaymentMethodId(paymentMethods.get(iPaymentMethod));
        client.getAddressByInvoiceAddressId().setCountryByCountryId(countries.get(iCountryInvoice));
        client.getAddressByDeliveryAddressId().setCountryByCountryId(countries.get(iCountryDelivery));

        // Update client in database
        clientManager.updateClient(client);

        /* Set order placed date that indicate the order is no more active */
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");
        userCartSession.setDatePlaced( new java.sql.Date(System.currentTimeMillis()) );

        // Set shipping method from combobox
        userCartSession.setShippingMethod(shippingMethods.get(iShippingMethod));

        // Create a new user cart session that replaces the previous one
        request.getSession().setAttribute( "userCartSession", (Cart)new CartImpl(client));

        //Mark Session Complete
        status.setComplete();

        // Set model & view
        ModelAndView mav = new ModelAndView("orderResume");

        mav.addObject("userCartSession", userCartSession);

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        mav.addObject("message", "Your order has been placed successfully on " + LocalDateTime.now().format(myFormatObj));

        return mav;
    }

    @RequestMapping({"/addProduct"})
    public ModelAndView listProductHandler(HttpServletRequest request,
                                           @RequestParam(value = "idProduct", defaultValue = "") Integer idProduct) {

        String referer = request.getHeader("Referer");
        referer = referer.substring(referer.lastIndexOf('/') + 1);
        ModelAndView mav = new ModelAndView("redirect:/" + referer);

        if (idProduct  > 0) {
            ProductEntity product = productManager.findProductFromId(idProduct);

            // TODO : Check the available product stock in the jsp (product & products)
            //  but since the products (all categories) are read once at startup their stocks are not updated
            //  whereas the products(specifics categories) are read from database when selected and so are updated with the actual stocks.
            if (product != null && product.getStock() >= 1) {
                Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");
                userCartSession.addLineItem(product);
            }
        }
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

    @RequestMapping({"/emptycart"})
    public ModelAndView clearProducts(HttpServletRequest request){
        Cart userCartSession = (Cart) request.getSession().getAttribute("userCartSession");
        userCartSession.clearOrderLines();
        return new ModelAndView("redirect:/cartView");
    }
}

