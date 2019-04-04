package com.alexandria.controllers;

import com.alexandria.entities.*;
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
  List<CountryEntity> countries;
  List<PaymentMethodEntity> paymentMethods;

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

    // TODO : use Spring form with databinding
    client.setTitleByTitleId( titles.get(iTitle) );

    clientManager.register(addDummies(client)); // FIXME : Add dummies data as some fields are not null in database

    return new ModelAndView("welcome", "client", client);
  }

  private ClientEntity addDummies(ClientEntity client) {

    countries = clientManager.getCountriesList();
    paymentMethods = clientManager.getPaymentMethodsList();

    client.setPhone("phone");

    AddressEntity invoiceAddress = new AddressEntity(
        "line1", "line2", "city", "state", "postalCode", countries.get(0));

    AddressEntity deliveryAddress = invoiceAddress;

    client.setAddressByInvoiceAddressId(invoiceAddress);
    client.setAddressByDeliveryAddressId(deliveryAddress);

    client.setPaymentMethodByPaymentMethodId( paymentMethods.get(0));

    return client;
  }
}
