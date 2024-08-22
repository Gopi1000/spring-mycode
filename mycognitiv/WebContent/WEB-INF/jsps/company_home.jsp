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
		<!-- Navigation -->
		<div id="page-wrapper">
			<div class="container-fluid"
				style="padding-left: 80px; padding-right: 80px;">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
					<%-- <h1>${sessionScope.user}</h1> --%>
						<h1 class="page-header">Dash board</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dash board
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<!-- /.row -->

				<div class="row">
					<c:if test="${not empty errorMessage }">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<h4 align="center">${errorMessage}</h4>
						<br />
					</c:if>
					<div class="col-lg-6 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-edit fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Company</div>
										<div>Profile</div>
									</div>
								</div>
							</div>
							<a href='${pageContext.request.contextPath}/company_profile'>
								<div class="panel-footer">
									<span class="pull-left">view Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					
					<div class="col-lg-6 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-users fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Candidate</div>
										<div>Management!</div>
									</div>
								</div>
							</div>
							<a href='${pageContext.request.contextPath}/view_candidates'>
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					
				</div>

				

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
