package com.alexandria.servlets;

import com.alexandria.persistence.PersistenceUtils;

import java.io.IOException;

@javax.servlet.annotation.WebServlet("/about")
public class About extends javax.servlet.http.HttpServlet {

    public static final String VIEW  = "/WEB-INF/jsp/about.jsp";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setAttribute("serverInfo", getServletContext().getServerInfo());
        request.setAttribute("jdbcUrl", PersistenceUtils.getJdbcUrl());

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
