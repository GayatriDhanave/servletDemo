<%@ page import="java.util.*, com.example.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f3;
            text-align: center;
            padding: 40px;
        }
        h2 { color: #333; }
        table {
            margin: auto;
            border-collapse: collapse;
            width: 80%;
            background: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #ccc;
        }
        th {
            background: #007bff;
            color: white;
        }
        tr:nth-child(even) { background: #f9f9f9; }
        form {
            display: inline;
        }
        input[type="submit"] {
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[value="Update"] {
            background: #ffc107;
            color: black;
        }
        input[value="Update"]:hover {
            background: #e0a800;
        }
        input[value="Delete"] {
            background: #dc3545;
            color: white;
        }
        input[value="Delete"]:hover {
            background: #b02a37;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 8px 12px;
            text-decoration: none;
            background: #28a745;
            color: white;
            border-radius: 5px;
        }
        a:hover { background: #218838; }
    </style>
</head>
<body>
    <h2>All Users</h2>
    <table>
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Actions</th>
        </tr>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            for (User u : users) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>
            <td>
                <!-- Update -->
                <form action="updateUser.jsp" method="get">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    <input type="hidden" name="name" value="<%= u.getName() %>">
                    <input type="hidden" name="email" value="<%= u.getEmail() %>">
                    <input type="submit" value="Update">
                </form>

                <!-- Delete -->
                <form action="users" method="post" onsubmit="return confirm('Delete this user?');">
                    <input type="hidden" name="_method" value="delete">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% } %>
    </table>

    <a href="addUser.html">Add New User</a>
    <a href="index.html">Home</a>
</body>
</html>
