<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>product list</title>


</head>
<body>


<br>
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




</body>
</html>