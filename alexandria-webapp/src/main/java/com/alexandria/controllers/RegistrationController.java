package com.alexandria.controllers;

import com.alexandria.entities.*;
import com.alexandria.managers.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class RegistrationController {

  @Autowired
  ClientManager clientManager;

  // TODO : tmp
  List<TitleEntity> titles; // TODO : pourquoi clientManager.getXxxList(); plante ici ?
  List<CountryEntity> countries;
  List<PaymentMethodEntity> paymentMethods;
  //

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("register");
    mav.addObject("client", new ClientEntity());

    // TODO : tmp
    titles = clientManager.getTitlesList();
    countries = clientManager.getCountriesList();
    paymentMethods = clientManager.getPaymentMethodsList();

    request.setAttribute("titles", titles);
    request.setAttribute("countries", countries);
    request.setAttribute("paymentMethods", paymentMethods);
    //

    return mav;
  }

  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addClient(HttpServletRequest request, HttpServletResponse response,
                              @ModelAttribute("client") ClientEntity client) {

    clientManager.register(map(client, request));

    return new ModelAndView("welcome", "client", client);
  }

  private ClientEntity map(ClientEntity client, HttpServletRequest request) {

    client.setTitleByTitleId( titles.get(Integer.parseInt(request.getParameter("gender"))) );

    client.setFirstName(request.getParameter("firstName"));
    client.setLastName(request.getParameter("lastName"));
    client.setEmail(request.getParameter("email"));
    client.setPhone(request.getParameter("phone"));
    client.setPassword(request.getParameter("password"));

    AddressEntity invoiceAddress = new AddressEntity(
            request.getParameter("line1"),
            request.getParameter("line2"),
            request.getParameter("city"),
            request.getParameter("state"),
            request.getParameter("postalCode"),
            countries.get(Integer.parseInt(request.getParameter("country")))
    );

    AddressEntity deliveryAddress = invoiceAddress;

    client.setAddressByInvoiceAddressId(invoiceAddress);
    client.setAddressByDeliveryAddressId(deliveryAddress);

    client.setPaymentMethodByPaymentMethodId( paymentMethods.get(Integer.parseInt(request.getParameter("paymentMethod"))) );

    return client;
  }
}
