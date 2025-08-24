package com.servletexample.servletexample;

import java.io.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAO();

        // ServletConfig demo
        String appName = config.getInitParameter("appName");
        System.out.println("ServletConfig appName: " + appName);

        // ServletContext demo
        ServletContext context = config.getServletContext();
        context.setAttribute("globalMessage", "Hello from ServletContext!");
    }

    // READ all users
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userDAO.findAll();
        PrintWriter out = resp.getWriter();

        // Session tracking demo
        HttpSession session = req.getSession();
        session.setAttribute("lastAction", "Viewed users");

        out.println("<h2>User List</h2>");
        for (User u : users) {
            out.println("<p>" + u.getId() + " - " + u.getName() + " - " + u.getEmail() + "</p>");
        }
    }

    // CREATE a user
    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String method = req.getParameter("_method");

    if (method == null) { // CREATE
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userDAO.save(user);

        resp.sendRedirect("users");
    } else if (method.equals("put")) { // UPDATE
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = new User(id, name, email);
        userDAO.update(user);

        resp.sendRedirect("users");
    } else if (method.equals("delete")) { // DELETE
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.delete(id);
        resp.sendRedirect("users");
    }
}

    // UPDATE user
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = new User(id, name, email);
        userDAO.update(user);

        resp.getWriter().println("User updated successfully!");
    }

    // DELETE user
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