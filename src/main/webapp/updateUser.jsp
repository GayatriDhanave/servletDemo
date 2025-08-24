<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fafafa;
            text-align: center;
            padding: 50px;
        }
        h2 { color: #333; }
        form {
            display: inline-block;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input[type="text"], input[type="email"] {
            width: 220px;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background: #007bff;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background: #0056b3;
        }
        a {
            display: block;
            margin-top: 15px;
            text-decoration: none;
            color: #28a745;
        }
    </style>
</head>
<body>
    <h2>Update User</h2>
    <form action="users" method="post">
        <input type="hidden" name="_method" value="put">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        Name:<br>
        <input type="text" name="name" value="<%= request.getParameter("name") %>"><br><br>
        Email:<br>
        <input type="email" name="email" value="<%= request.getParameter("email") %>"><br><br>
        <input type="submit" value="Update">
    </form>
    <a href="users">Back to List</a>
</body>
</html>
