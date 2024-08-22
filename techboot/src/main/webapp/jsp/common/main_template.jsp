<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
<title><%=request.getAttribute("title")%></title>
 <meta name="description" content="<%=request.getAttribute("meta")%>" />

<!-- set the encoding of your site -->
<meta charset="utf-8">
<!-- set the viewport width and initial-scale on mobile devices -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- set the HandheldFriendly -->
<meta name="HandheldFriendly" content="True">
<!-- set the description -->
<meta name="description" content="Technology training and placements">
<!-- set the Keyword -->
<meta name="keywords" content="">
<meta name="author" content="Techboot solutions">

<!-- Link -->

<link rel="stylesheet" href="resource/css/font-awesome.css">
<link rel="stylesheet" href="resource/css/font-awesome 4.7.0.css">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<link rel="stylesheet" href="resource/css/style.css">
<link rel="stylesheet" href="resource/css/colors.css">
<link rel="stylesheet" href="resource/css/responsive.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
<script src="resource/js/jquery.js"></script>
<script src="resource/js/jquery.main.js"></script>
 <script src="resource/js/plugins.js"></script> 
</head>
<body>
	<div id="wrapper">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		
	</div>
</body>
</html>

