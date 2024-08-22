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
			<br><br>
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<h1 class="page-header" align="left">
					<i class="fa fa-user"></i>Candidate Registration</h1>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<h4 align="center">${errorMessage}</h4>
				<div class="col-lg-4"></div>
				<div class="col-lg-4 well">
					<div>
						Already have an account, click here to <a class="btn btn-info"
							style="float: right;"
							href='${pageContext.request.contextPath}/login'>Login</a> <br />
					</div>
					<br />
					<form name="login-form" class="login-form" action="checkRegister"
						method="post">

						<div class="form-group">
							<label>User Name<font color="red"> *</font></label> <input
								class="form-control" name="username" type="text"
								placeholder="Username" required="required" minlength="3">
						</div>


						<div class="form-group">
							<label>Email<font color="red"> *</font></label> <input
								class="form-control" name="email" type="email"
								placeholder="Email" required="required">
						</div>
						<div class="form-group">
							<label>Phone Number<font color="red"> *</font></label> <input
								class="form-control" name="phoneNo" type="number"
								placeholder="Phone Number" required="required" minlength="10"
								maxlength="10">
						</div>

						<div class="form-group">
							<label>Password<font color="red"> *</font></label> <input
								class="form-control" name="password" type="password"
								placeholder="Password" required="required" minlength="5"
								maxlength="8">
						</div>

						<div class="row">
							<div class="col-lg-7"></div>
							<div class="col-lg-5">
								<button type="submit" class="btn btn-success">Register</button>
								<a href='${pageContext.request.contextPath}'
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

	<div class="gradient"></div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
