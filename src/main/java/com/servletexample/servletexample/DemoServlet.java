package com.servletexample.servletexample;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servletexample.servletexample.dao.UserDAO;
import com.servletexample.servletexample.entity.User;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "demoServlet", value = "/users")
//@WebServlet("/users")
public class DemoServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws javax.servlet.ServletException {
        super.init();
        userDAO = new UserDAO();
        System.out.println("Server Initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userDAO.findAll();
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        session.setAttribute("lastAction", "Viewed users");
        out.println("<html><head><style>body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #eef2f3;\n" +
                "            text-align: center;\n" +
                "            padding: 40px;\n" +
                "        }\n" +
                "        h2 { color: #333; }\n" +
                "        table {\n" +
                "            margin: auto;\n" +
                "            border-collapse: collapse;\n" +
                "            width: 80%;\n" +
                "            background: white;\n" +
                "            box-shadow: 0 0 10px rgba(0,0,0,0.1);\n" +
                "        }\n" +
                "        th, td {\n" +
                "            padding: 12px;\n" +
                "            border: 1px solid #ccc;\n" +
                "        }\n" +
                "        th {\n" +
                "            background: #007bff;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        tr:nth-child(even) { background: #f9f9f9; }\n" +
                "        form {\n" +
                "            display: inline;\n" +
                "        }\n" +
                "        input[type=\"submit\"] {\n" +
                "            padding: 5px 10px;\n" +
                "            border: none;\n" +
                "            border-radius: 4px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        input[value=\"Update\"] {\n" +
                "            background: #ffc107;\n" +
                "            color: black;\n" +
                "        }\n" +
                "        input[value=\"Update\"]:hover {\n" +
                "            background: #e0a800;\n" +
                "        }\n" +
                "        input[value=\"Delete\"] {\n" +
                "            background: #dc3545;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        input[value=\"Delete\"]:hover {\n" +
                "            background: #b02a37;\n" +
                "        }\n" +
                "        a {\n" +
                "            display: inline-block;\n" +
                "            margin: 10px;\n" +
                "            padding: 8px 12px;\n" +
                "            text-decoration: none;\n" +
                "            background: #28a745;\n" +
                "            color: black;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        a:hover { background: #218838; }</style></head><body>");
        out.println("<h2>User List</h2>");
        System.out.println("<h2>All Users</h2>\n" +
                "                <table>\n" +
                "                <tr>\n" +
                "                <th>ID</th><th>Name</th><th>Email</th><th>Actions</th>\n" +
                "                </tr>");
        for (User u : users) {
//            out.println("<p>" + u.getId() + " - " + u.getName() + " - " + u.getEmail() + "</p>");
            out.println("<form action=updateUser.jsp method=get>\n" +
                    "                    <input type=hidden name=id value="+u.getId() +">\n" +
                    "                    <input type=hidden name=name value=" +u.getName()+">\n" +
                    "                    <input type=hidden name=email value=" +u.getEmail()+">\n" +
                    "                    <input type=submit value=Update>\n" +
                    "                </form>");
            out.println("<form action=users method=post onsubmit=return confirm('Delete this user?');>\n" +
                    "                    <input type=hidden name=_method value=delete>\n" +
                    "                    <input type=hidden name=id value="+u.getId() +">\n" +
                    "                    <input type=submit value=Delete>\n" +
                    "                </form>");
        }
        out.println("<br>\n" +
                "    <a href=\"addUser.jsp\">Add New User</a>\n" +
                "    <a href=\"index.jsp\">Home</a><br> " +
                "</body></html>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
//            resp.sendRedirect(contextPath);
            resp.setContentType("application/json");
            resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
        resp.sendRedirect("users");
        } else if (method.equals("put")) {
            System.out.println("update");
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");

            User user = new User(id, name, email);
            userDAO.update(user);
            List<User> all = userDAO.findAll();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValueAsString(all);
            resp.sendRedirect("users");
        } else if (method.equals("delete")) {
            System.out.println("delete");
            int id = Integer.parseInt(req.getParameter("id"));
            userDAO.delete(id);
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath);
            resp.sendRedirect("users");
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
