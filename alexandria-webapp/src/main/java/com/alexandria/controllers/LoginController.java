package com.alexandria.controllers;

import com.alexandria.entities.ClientEntity;
import com.alexandria.managers.Cart;
import com.alexandria.managers.CartImpl;
import com.alexandria.managers.ClientManager;
import com.alexandria.managers.Login;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    ClientManager clientManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {

        // Bypass login if user session recorded
        ClientEntity client = (ClientEntity)request.getSession().getAttribute( "userSession");
        if(client != null) {
            ModelAndView mav = new ModelAndView("welcome");
            mav.addObject("client", client);
            return mav;
        }

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("login") Login login) {
        ModelAndView mav = null;

        ClientEntity client = clientManager.validateClient(login);

        if (client != null) {
            // Set user session
            request.getSession().setAttribute( "userSession", client);

            // Set user cart session
            request.getSession().setAttribute( "userCartSession", (Cart)new CartImpl(client));

            mav = new ModelAndView("welcome");
            mav.addObject("client", client);
        } else {
            // Reset user session
            request.getSession().setAttribute( "userSession", null);

            // Reset user cart session
            request.getSession().setAttribute( "userCartSession", null);

            mav = new ModelAndView("login");
            mav.addObject("message", "Email or Password is wrong!!");
        }

        return mav;
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public ModelAndView signout(HttpServletRequest request, HttpServletResponse response) {

        // Clear the session
        request.getSession().invalidate();

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        return mav;
    }
}