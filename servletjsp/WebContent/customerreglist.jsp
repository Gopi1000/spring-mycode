<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body>


<br>
<br>




<table style="width: 100%;">

<tr>
<th style="border: 1px solid black; background: skyblue">customerId</th>
<th style="border: 1px solid black; background: skyblue">customerName</th>
<th style="border: 1px solid black; background: skyblue">emailId</th>
<th style="border: 1px solid black; background: skyblue">address</th>
<th style="border: 1px solid black; background: skyblue">mobileNo</th>
<th style="border: 1px solid black; background: skyblue">password</th>
<th style="border: 1px solid black; background: skyblue">edit</th>
<th style="border: 1px solid black; background: skyblue">delete</th>

</tr>
<c:forEach items="${carlist}" var="vv">


<tr>

<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.customerId }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.customerName }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.emailid }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.address }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.mobileNo }">
</c:out></td>
<td style="border: 1px solid black; text-align: center"><c:out
value="${vv.password }">
</c:out></td>


<td style="border: 1px solid black; text-align: center"><a href="edit_value?id=${vv.customerId}">Edit Value</a></td>
<td style="border: 1px solid black; text-align: center"><a href="delete_value?id=${vv.customerId}">delete Value</a></td>
</tr>
</c:forEach>
</table>
<br>
<br>
<br>
<br>




</body>
</html>