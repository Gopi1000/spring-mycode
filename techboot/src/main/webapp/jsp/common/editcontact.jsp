<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<body>
<h1>editcontact.jsp page</h1>
<form:form action="editcontact1" modelAttribute="editcontact">
name
<form:input path="yourName"/>

email:
<form:input path="emailAddress"/>
message:
<form:input path="message"/>
contact:
<form:input path="contactNumber"/>

<form:button value="ok">ok</form:button>
</form:form>
</body>
								

</html>