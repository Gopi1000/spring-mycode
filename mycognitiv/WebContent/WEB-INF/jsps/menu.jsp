<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
</head>
<ul class="nav navbar-nav side-nav" style="margin-bottom: 80px;">
	<li><a style="color: #444;"
		href='${pageContext.request.contextPath}/candidate_details'> <span
			class="glyphicon glyphicon-user"></span> <b>My Profile</b></a></li>
	<li><a style="color: #444;"
		href='${pageContext.request.contextPath}/view_admin_test'><span
			class="glyphicon glyphicon-pencil"></span><b>View My Assessments</b></a></li>
	<li><a style="color: #444;" href="takeTestCategory?user=candidate"><span
			class="glyphicon glyphicon-pencil"></span><b>Test To Be Taken</b></a>
		<ul id="demo" class="collapse">
		</ul></li>
	<li><a style="color: #444;"
		href='${pageContext.request.contextPath}/view_teststatus'> <span
			class="glyphicon glyphicon-list-alt"></span> <b>My Assessments</b></a></li>
	<li><a style="color: #444;"
		href='${pageContext.request.contextPath}/view_markedquestion'> <span
			class="glyphicon glyphicon-check"></span> <b>Marked Question</b></a></li>

</ul>
<div class="gradient"></div>

</html>