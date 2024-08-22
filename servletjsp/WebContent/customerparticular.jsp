<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="edit_product" method="post">

<table style="border:4px solid black;width:650px;text-align:center;margin-left:370px;height:310px;border-radius:45px">
<tr><th><h2 style="color: navy;font-size:35px;">Product Edit Forms</h2></th></tr>

<tr><td><h2 style="margin-left:-265px;margin-top:20px">Id<label style="color: red">*</label> <input type="text" name="productName" maxlength="45" placeholder="Enter Product Name" value="${particular.customerId}"></h2></td></tr>
<tr><td><h2 style="margin-left:345px;margin-top:-50px ">customername<label style="color: red">*</label> <input type="text" name="customername" maxlength="45" placeholder="Enter Brand Name" value="${particular.customerName}"></h2></td></tr>
<tr><td><h2 style="margin-left:-185px;margin-top:0px">emailid<label style="color: red">*</label> <input type="text" name="emailid" maxlength="45" placeholder="Enter Model" value="${particular.emailid}"></h2></td></tr>
<tr><td><h2 style="margin-left:355px ;margin-top:-50px">address<label style="color: red">*</label> <input type="text" name="address" placeholder="Enter Price" value="${particular.address}"></h2></td></tr>
<tr><td><h2 style="margin-left:-225px;margin-top:0px">mobileNo<label style="color: red">*</label> <input type="text" name="mobileNo" maxlength="10" placeholder="Enter StockLeft" value="${particular.mobileNo}"></h2></td></tr>
<tr><td><h2 style="margin-left:315px;margin-top:-50px">password<label style="color: red">*</label> <input type="text" name="password" maxlength="10" placeholder="Enter Warrenty" value="${particular.password}"></h2></td></tr>
<tr><td><h2 style="margin-left:-5px ;margin-top:10px"><input type="submit" name="submit" value="SaveEdit"></h2></td></tr>
<input type="hidden" name="id" value="${particular.customerId}" >
</body>
</html>