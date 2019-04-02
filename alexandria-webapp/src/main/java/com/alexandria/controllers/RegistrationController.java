package com.alexandria.controllers;

import com.alexandria.entities.AddressEntity;
import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.CountryEntity;
import com.alexandria.entities.TitleEntity;
import com.alexandria.managers.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {

  @Autowired
  ClientManager clientManager;

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("register");
    mav.addObject("client", new ClientEntity());

    // TODO : tmp
    request.setAttribute("titles", clientManager.getTitlesList());
    request.setAttribute("countries", clientManager.getCountriesList());
    //

    return mav;
  }

  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addClient(HttpServletRequest request, HttpServletResponse response,
                              @ModelAttribute("client") ClientEntity client) {


    clientManager.register(map(client, request));

    return new ModelAndView("welcome", "firstname", client.getFirstName());
  }

  private ClientEntity map(ClientEntity client, HttpServletRequest request) {

    client.setTitleByTitleId(new TitleEntity(Integer.parseInt((String) request.getAttribute("gender")), request.getParameter("gender")));

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
            new CountryEntity(Integer.parseInt((String)request.getAttribute("country")), request.getParameter("country"))
    );

    AddressEntity deliveryAddress = invoiceAddress;

    client.setAddressByInvoiceAddressId(invoiceAddress);
    client.setAddressByDeliveryAddressId(deliveryAddress);

    return client;
  }
}
