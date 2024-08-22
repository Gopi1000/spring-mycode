<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>updated page</title>
</head>
<body>

<form action="change-product" method="post"  modelAttribute=product>

<table style="background-color: skyblue;  margin:auto;height:270px;border-radius:15px; text-align: center;">
<tr><td><h3 style="color:red;">Product Edit Forms</h3></td><td>
<td></td>
</tr>
<tr><td> product Type    : </td><td><input type="text" name="productType" value="${particular.productType}"></td></tr>
<tr><td> brand Name      : </td><td><input type="text" name="brandName" value="${particular.brandName}"></td></tr>
<tr><td> modelNo         : </td><td><input type="text" name="modelNo" value="${particular.modelNo}"></td></tr>
<tr><td> quantity        : </td><td><input type="text" name="quantity" value="${particular.quantity}"></td></tr>
<tr><td> price           : </td><td><input type="text" name="price"value="${particular.price}"></td></tr>
<tr><td> discount Price  : </td><td><input type="text" name="discountPrice"value="${particular.discountPrice}"></td></tr>
<tr><td><input type="submit" name="submit" value="SaveEdit"></td>
<input type="hidden" name="id" value="${particular.productId}" >
</body>
</html>