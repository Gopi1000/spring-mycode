<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta charset="ISO-8859-1">
<title>product register</title>
</head>
<body>

<!--  <form action="product-create" method="post" modelAttribute="product">
<table style="background-color: skyblue;  margin:auto;height:270px;border-radius:15px; text-align: center;">
<tr><td><h3 style="color:red;">Product Register Page !!!</h3></td><td>
<td></td>
</tr>
<tr><td> product Type   : </td><td><input type="text" name="productType" required path="productType"></td></tr>
<tr><td> brand Name     : </td><td><input type="text" name="brandName" required path="brandName"></td></tr>
<tr><td> modelNo        : </td><td><input type="text" name="modelNo" required path="modelNo"></td></tr>
<tr><td> quantity       : </td><td><input type="text" name="quantity" required path="quantity"></td></tr>
<tr><td> price          : </td><td><input type="text" name="price" required path="price"></td></tr>
<tr><td> discount Price : </td><td><input type="text" name="discountPrice" required path="discountPrice"></td></tr>
<tr><td><input type="submit" name="submit" value="Register"></td>
<td></td>
</table>
                   
</form>-->

<form:form action="product-create" method="post" modelAttribute="product">

<table style="background-color: skyblue;  margin:auto;height:270px;border-radius:15px; text-align: center;">
<tr><td><h3 style="color:red;">Product Register Page !!!</h3></td><td>
<td></td>
</tr>

<tr><td> Product Type </td><td> <form:input path="productType"/> <form:errors path="productType"></form:errors> </td></tr>

<tr><td> brand Name </td><td> <form:input path="brandName"/> <form:errors path="brandName"></form:errors> </td></tr>

<tr><td> modelNo </td><td> <form:input path="modelNo"/> <form:errors path="modelNo"></form:errors> </td></tr>

<tr><td> quantity </td><td> <form:input path="quantity"/> <form:errors path="quantity"></form:errors> </td></tr>

<tr><td> price </td><td> <form:input path="price"/> <form:errors path="price"></form:errors> </td></tr>

<tr><td> discount Price </td><td> <form:input path="discountPrice"/> <form:errors path="discountPrice"></form:errors> </td></tr>

<tr><td> <form:button>Register</form:button></td>

<td></td>
</table>

</form:form>








<%-- <br>
<br>




<table style="width: 100%;">

<tr>
<th style="border: 1px solid black; background: skyblue">product_Id</th>
<th style="border: 1px solid black; background: skyblue">product_Type</th>
<th style="border: 1px solid black; background: skyblue">brand_Name</th>
<th style="border: 1px solid black; background: skyblue">model_No</th>
<th style="border: 1px solid black; background: skyblue">quantity</th>
<th style="border: 1px solid black; background: skyblue">price</th>
<th style="border: 1px solid black; background: skyblue">discount_Price</th>
<th style="border: 1px solid black; background: skyblue">edit</th>
<th style="border: 1px solid black; background: skyblue">delete</th>

</tr>
<c:forEach items="${carlist}" var="vv">


<tr>

<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.productId }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.productType }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.brandName }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.modelNo }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.quantity }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.price }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.discountPrice }">
</c:out></td>

<td style="border: 1px solid black; text-align: center"><a href="edit-value?id=${vv.productId}">Edit Value</a></td>
<td style="border: 1px solid black; text-align: center"><a href="delete-value?id=${vv.productId}">delete Value</a></td>
</tr>
</c:forEach>
</table>
<br>
<br>
<br>
<br>
 --%>





</body>
</html>