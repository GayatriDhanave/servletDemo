package com.servletexample.servletexample;

import java.io.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

//    public void init () {
//        message = "Servlet Example!";
//    }


    @Override
    public void init (ServletConfig config) throws ServletException {
        super.init(config);
        message = "Servlet Example!";
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Servlet Example</h1>");
        out.println("</body></html>");
        System.out.println("Post method called!");
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        System.out.println("Post method called!");
    }

//    public void destroy () {
//        System.out.println("Destroying all the resources!");
//    }


    @Override
    public void destroy () {
        super.destroy();
        System.out.println("Destroying all the resources!");
    }
}