<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>updated page</title>
</head>
<body>

<form action="change-customer" method="post"  modelAttribute=customer>

<table style="background-color: skyblue;  margin:auto;height:270px;border-radius:15px; text-align: center;">
<tr><td><h3 style="color:red;">Edit Forms</h3></td><td>
<td></td>
</tr>
<tr><td> customer name : </td><td><input type="text" name="customerName" value="${particular.customerName}"></td></tr>
<tr><td> email id: </td><td><input type="text" name="emailid" value="${particular.emailid}"></td></tr>
<tr><td> address : </td><td><input type="text" name="address" value="${particular.address}"></td></tr>
<tr><td> mobileNo : </td><td><input type="text" name="mobileNo" value="${particular.mobileNo}"></td></tr>
<tr><td> password : </td><td><input type="password" name="password"value="${particular.password}"></td></tr>
<tr><td><input type="submit" name="submit" value="SaveEdit"></td>
<input type="hidden" name="id" value="${particular.customerId}" >
</body>
</html>