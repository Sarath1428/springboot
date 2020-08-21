<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
</head>
<h2>Registration </h2>
<body>
<form action="registerUser" method="post">
<pre>
First Name: <input type="text" name="firstName">
Last Name: <input type="text" name="lastName">
User Name: <input type="text" name="email">
Password: <input type="password" name="password">
Confirm Password: <input type="password" name="confirmpassword">
<input type="submit" value="register">
<a href="login1">login</a>
</pre>

</form>
</body>
</html>