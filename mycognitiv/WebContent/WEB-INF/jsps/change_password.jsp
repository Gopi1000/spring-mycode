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
<%@page import="com.scube.mycognitiv.bo.UserBO"%>


</head>
<jsp:include page="header.jsp"></jsp:include>

<body>

	<%
		String name = (String) session.getAttribute("emailAddress");
	%>

	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<br>
			<br>
			<br>
			<div class="row" style="margin-top: -35px;">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<h1 class="page-header">Reset Password</h1>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<h4 align="center">${errorMessage}</h4>
				<div class="col-lg-4"></div>
				<div class="col-lg-4 well">
					<br />
					<form name="login-form" class="login-form" action="ChangePassword"
						method="post">
						<div class="form-group">
							<label>Email Address<font color="red"> *</font></label><input
								class="form-control" name="emailAddress" type="text"
								required="required" placeholder="Email Address">
							<%-- <%=name%><%session.setAttribute("emailAddress", request.getParameter("emailAddress"));%> --%>
						</div>

						<div class="form-group">
							<label>New Password<font color="red"> *</font></label> <input
								class="form-control" name="password" type="password"
								required="required" placeholder="Password">
						</div>

						<div class="form-group">
							<label>Confirm Password<font color="red"> *</font></label> <input
								class="form-control" name="confirmPassword" type="password"
								required="required" placeholder="Confirm Password">
						</div>

						<%-- <div class="row">
							<a href='${pageContext.request.contextPath}'
								class="btn btn-danger" style="float: right;">Cancel</a>
							<button type="submit" class="btn btn-success"
								style="float: right;">Save</button>
						</div> --%>
						<div class="row">
							<div class="col-lg-7"></div>
							<div class="col-lg-5">
								<button type="submit" class="btn btn-success">Save</button>
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
		<!-- /.container-fluid -->

	</div>

	<div class="gradient"></div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
