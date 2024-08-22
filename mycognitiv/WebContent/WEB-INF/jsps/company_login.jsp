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

	<%-- <div id='cssmenu'>
		<ul>
			<li class=''><a href='${pageContext.request.contextPath}'><span>Home</span></a></li>
			<li><a href='${pageContext.request.contextPath}/login'><span>Login</span></a></li>
			<li><a href='${pageContext.request.contextPath}/register'><span>Register</span></a></li>
			<li class='#'><a href='#'><span>Submit a Question</span></a></li>
			<li class=''><a href='#'><span>Feedback</span></a></li>
			<li><a href='#'><span>Contribute</span></a></li>
			<li><a href='#'><span>Contact us</span></a></li>
		</ul>
	</div> --%>

	
		<div id="page-wrapper" style="height: 530px;">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
				<div class="col-lg-4"></div>
					<div class="col-lg-4 ">
						<h1 class="page-header" align="left">
						<i class="fa fa-sign-in"></i>Login</h1>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<h4 align="center">${errorMessage}</h4>
					<div class="col-lg-4"></div>
					<div class="col-lg-4 well">
						<div>
							Don`t have an account, click here to <a class="btn btn-info" style="float: right;"
								href='${pageContext.request.contextPath}/company_register'>Register</a>
							<br />
						</div>
						<br />
						<form  action="candidate_login"
							method="post">

							<div class="form-group">
								<label>Email Address<font color="red"> *</font></label> <input
									class="form-control" name="username" type="email" required="required"
									placeholder="User Name">
							</div>
							
							<div class="form-group">
								<label>Password<font color="red"> *</font></label> <input
									class="form-control" name="password" type="password" required="required"
									placeholder="Password">
							</div>
							<div class="col-lg-7">	
								<a href='${pageContext.request.contextPath}/reset_password'
								>Reset Password</a>
							</div>
							<div class="col-lg-5">
							<button type="submit" id="btnSubmit" class="btn btn-success">Login</button>
							<a href='${pageContext.request.contextPath}'
								class="btn btn-danger">Cancel</a>
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
