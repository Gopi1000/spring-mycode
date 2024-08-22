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
		<div id="page-wrapper" >
			<div class="container-fluid">
				<!-- Page Heading -->
				<br>
				<div class="row">
				
					<div class="col-lg-4"><h1 class="page-header"><i class="fa fa-user"></i>
					<c:if test="${not empty adminEditCandidate.userId}">
						Update Candidate
					</c:if>
					<c:if test="${ empty adminEditCandidate.userId}">
						Create Candidate
					</c:if>	
					</h1>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
				<c:if test="${!empty errorMessage}">
				<div class="alert alert-success" style="font-size: 100px;">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<h4 align="center">${errorMessage}</h4>
					</div>
					</c:if>
					<div class="col-lg-4 well">
						<%-- <div>
							Already have an account, click here to <a class="btn btn-info" style="float: right;"
								href='${pageContext.request.contextPath}/login'>Login</a> <br />
						</div> --%>
						<br />
						<form name="login-form" class="login-form" action="createCandidate"
							method="post">

                     <input type="hidden" name="userId" value="${adminEditCandidate.userId}" />
							<div class="form-group">
								<label>User Name<font color="red"> *</font></label> <input
									class="form-control" name="username" type="text"
									placeholder="Username" required="required" value="${adminEditCandidate.userName}">
							</div>


							<div class="form-group">
								<label>Email<font color="red"> *</font></label> <input
									class="form-control" name="email" type="email"
									placeholder="Email" required="required" value="${adminEditCandidate.email}">
							</div>
							<div class="form-group">
								<label>Phone Number<font color="red"> *</font></label> <input
									class="form-control" name="phoneNo" type="number"
									placeholder="Phone Number" required="required" value="${adminEditCandidate.phoneNo}">
							</div>

							<div class="form-group">
								<label>Password<font color="red"> *</font></label> <input
									class="form-control" name="password" type="password"
									placeholder="Password" required="required" value="${adminEditCandidate.password}">
							</div>

							<button type="submit" class="btn btn-success">Save</button>
							<a href='${pageContext.request.contextPath}/company_home'
								class="btn btn-danger">Cancel</a>


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
