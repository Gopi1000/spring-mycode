<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>customer login</title>
</head>
<body>
<form action="loginpage" method="post">

<table style="background-color: lightgreen; margin-left: 20px;margin-left: 20px;">

<tr><td><h3 style="color:red;">Login Page !!!</h3></td><td>
<td></td>
</tr>
<tr><td> Name : </td><td><input type="text" name="customername" required></td></tr>
<tr><td> Password : </td><td><input type="password" name="password" required></td></tr>
<tr><td><input type="submit" name="submit" value="login"></td>
<td><a href="register.jsp">Registration</a></tr>
</table>
                   
</form>

</body>
</html>