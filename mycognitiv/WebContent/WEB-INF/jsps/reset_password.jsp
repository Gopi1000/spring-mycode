<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>

<%   
  
String name=request.getParameter("emailAddress");  
  
session.setAttribute("emailAddress",name);  
  
  
%>  

	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<br><br><br>
			<div class="row" style="margin-top: -35px;">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<h1 class="page-header">Reset Password</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<h4 align="center">${errorMessage}</h4>
				<div class="col-lg-4"></div>
				<div class="col-lg-4 well">
					<br />
					<form name="login-form" class="login-form" action="ResetPassword"
						method="post">
						<div class="form-group">
							<label>Email Address<font color="red"> *</font></label> <input
								class="form-control" name="emailAddress" type="text"
								required="required" placeholder="Email Address">
						</div>

						<div class="row">
							<div class="col-lg-7"></div>
							<div class="col-lg-5">
							<button type="submit" class="btn btn-success"
									>Continue</button>
								<a href='${pageContext.request.contextPath}'
									class="btn btn-danger">Cancel</a>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /.row -->

		</div>
		<br> <br> <br> <br>
		<br> <br> <br> <br>
		<br> <br> <br> <br>
		<br>
		<!-- /.container-fluid -->

	</div>

	<div class="gradient"></div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
