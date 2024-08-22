<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>customer register</title>
</head>
<body>

<form action="customer-create" method="post" modelAttribute="customer">
<table style="background-color: skyblue;  margin:auto;height:270px;border-radius:15px; text-align: center;">
<tr><td><h3 style="color:red;">Registration Page !!!</h3></td><td>
<td></td>
</tr>
<tr><td> customer name : </td><td><input type="text" name="customerName" required path="customerName"></td></tr>
<tr><td> email id: </td><td><input type="text" name="emailid" required path=emailid"></td></tr>
<tr><td> address : </td><td><input type="text" name="address" required path="address"></td></tr>
<tr><td> mobileNo : </td><td><input type="text" name="mobileNo" required path="mobileNo"></td></tr>
<tr><td> password : </td><td><input type="password" name="password" required path="password"></td></tr>
<tr><td><input type="submit" name="submit" value="Register"></td>
<td></td>
</table>
                   
</form>

</body>
</html>