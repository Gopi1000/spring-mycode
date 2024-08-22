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
<jsp:include page="header.jsp"></jsp:include>

<body>

	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<h1 class="page-header" align="left">
					<i class="fa fa-user"></i>Company Registration</h1>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
			<c:if test="${!empty errorMessage}">
			<div class="alert alert-success" style="font-size: 100px;">
				<h4 align="center">${errorMessage}</h4>
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
				</c:if>
				<div class="col-lg-4"></div>
				<div class="col-lg-4 well">
					<div>
					<c:if test="${empty sessionScope.userType}">
						Already have an account, click here to <a class="btn btn-info"
							style="float: right;"
							href='${pageContext.request.contextPath}/login'>Login</a>
						<br />
						</c:if>
					</div>
					<br />
					<form name="login-form" class="login-form" action="companyRegister"
						method="post">

						<div class="form-group">
							<label>Company Name<font color="red"> *</font></label> <input
								class="form-control" name="companyName" type="text"
								placeholder="Company Name" required="required">
						</div>

						<div class="form-group">
							<label>Email Address<font color="red"> *</font></label> <input
								class="form-control" name="companyEmail" type="email"
								placeholder="Email Address" required="required">
						</div>

						<div class="form-group">
							<label>Contact Number<font color="red"> *</font></label> <input
								class="form-control" name="companyContactNo" type="number"
								placeholder="Contact Number" required="required">
						</div>

						<div class="form-group">
							<label>Location<font color="red"> *</font></label> <input
								class="form-control" name="companyLocation" type="text"
								placeholder="Company Location" required="required">
						</div>

						<div class="form-group">
							<label>Website<font color="red"> *</font></label> <input
								class="form-control" name="companyWebsite" type="text"
								placeholder="website" required="required">
						</div>

						<div class="form-group">
							<label>Password<font color="red"> *</font></label> <input
								class="form-control" name="password" type="password"
								placeholder="Password" required="required">
						</div>

						<div class="row">
							<div class="col-lg-7"></div>
							<div class="col-lg-5">
								<button type="submit" class="btn btn-success">Register</button>
								<a href='${pageContext.request.contextPath}/company_login'
									class="btn btn-danger">Cancel</a>
							</div>
						</div>


					</form>
				</div>
			</div>
			<!-- /.row -->

		</div>
		<!-- /.container-fluid -->

	</div>
	</br></br>

	<div class="gradient"></div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
