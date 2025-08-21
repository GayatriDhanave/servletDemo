<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="getData" method="GET">
    <button value="submit">Call get method</button>
</form>
<br>
<form action="postData" method="POST">
    <button value="submit">Call post method</button>
</form>
</body>
</html>