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
	<div id="">
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Registration Success</h1>
						<ol class="breadcrumb">
							<li><i class="fa fa-dashboard"></i> <a
								href='${pageContext.request.contextPath}'>Dash Board</a></li>
							<li class="active"><i class="fa fa-edit"></i> Success</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<div class="alert alert-success">
						<%-- <c:set var="userType" scope="session" value="${requestScope.userType}"/>  --%>
						<c:if test='${not empty requestScope.newUser}'> 
						<c:if test='${requestScope.userType eq Company}'>
							<h3>
								Congratulation ${requestScope.newUser} your account created
								successfully , <a class="btn btn-info"
									href="${pageContext.request.contextPath}/company_login">login </a> to
								take the exam.
							</h3>
							</c:if>
							</c:if>
						</div>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->
		</div>

	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
