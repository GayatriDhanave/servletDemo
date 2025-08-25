package com.servletexample.servletexample;

import java.io.*;
import java.util.List;

import com.servletexample.servletexample.dao.UserDAO;
import com.servletexample.servletexample.entity.User;
//import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")

public class HelloServlet extends HttpServlet {

    private UserDAO userDAO;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        userDAO = new UserDAO();
//        System.out.println("Server Initialized");
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userDAO.findAll();
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        session.setAttribute("lastAction", "Viewed users");

        out.println("<h2>User List</h2>");
        for (User u : users) {
            out.println("<p>" + u.getId() + " - " + u.getName() + " - " + u.getEmail() + "</p>");
        }
    }


    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String method = req.getParameter("_method");
        System.out.println("doPost");

    if (method == null) {
        System.out.println("add user");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userDAO.save(user);

        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        resp.sendRedirect(contextPath);
//        resp.sendRedirect("users");
    } else if (method.equals("put")) {
        System.out.println("update");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = new User(id, name, email);
        userDAO.update(user);

        resp.sendRedirect("users");
    } else if (method.equals("delete")) {
        System.out.println("delete");
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.delete(id);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath);
    }
}


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = new User(id, name, email);
        userDAO.update(user);

        resp.getWriter().println("User updated successfully!");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.delete(id);
        resp.getWriter().println("User deleted successfully!");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet is being destroyed...");
    }
}

//http://localhost:8080/servletExample_war_exploded/