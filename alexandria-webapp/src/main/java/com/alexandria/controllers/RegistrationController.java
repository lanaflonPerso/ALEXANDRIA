package com.alexandria.controllers;

import com.alexandria.entities.*;
import com.alexandria.managers.Cart;
import com.alexandria.managers.CartImpl;
import com.alexandria.managers.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class RegistrationController {

  @Autowired
  ClientManager clientManager;

  // Combobox
  List<TitleEntity> titles;

  @PostConstruct
  public void init() {
    titles = clientManager.getTitlesList();
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {

    ModelAndView mav = new ModelAndView("register");

    mav.addObject("client", new ClientEntity());

    mav.addObject("titles", titles);

    return mav;
  }

  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addClient(HttpServletRequest request, HttpServletResponse response,
                                @ModelAttribute("client") ClientEntity client,
                                @RequestParam("gender") Integer iTitle) {

        ModelAndView mav = null;

        boolean isEmailAlreadyRegistered = clientManager.isEmailAlreadyRegistered(client.getEmail());

        if(!isEmailAlreadyRegistered) {

            // TODO : use Spring form select with databinding instead @RequestParam
            client.setTitleByTitleId( titles.get(iTitle) );

            // FIXME : Cf. addDummies method's comments
            clientManager.register(addDummies(client));

            // Set user session
            request.getSession().setAttribute( "userSession", client);

            // Set user cart session
            request.getSession().setAttribute( "userCartSession", (Cart)new CartImpl(client));

            mav = new ModelAndView("welcome", "client", client);

        } else {

            mav = new ModelAndView("register");
            mav.addObject("message", "Email already registered !!");
            mav.addObject("titles", titles);
        }

        return mav;
    }

    // FIXME : Add dummies data as some fields doesn't accept null in database
    private ClientEntity addDummies(ClientEntity client) {

        CountryEntity dummyCountry = clientManager.getCountriesListRange(0, 1).get(0);
        PaymentMethodEntity dummyPaymentMethod = clientManager.getPaymentMethodsListRange(0, 1).get(0);

        client.setPhone("phone");

        AddressEntity invoiceAddress = new AddressEntity(
            "line1", "line2", "city", "state", "postalCode", dummyCountry);

        AddressEntity deliveryAddress = invoiceAddress;

        client.setAddressByInvoiceAddressId(invoiceAddress);
        client.setAddressByDeliveryAddressId(deliveryAddress);

        client.setPaymentMethodByPaymentMethodId( dummyPaymentMethod );

        return client;
    }
}
