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
						<h1 class="page-header">Dashboard</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dashboard
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<!-- /.row -->

				<div class="row">
					
					<div class="col-lg-6 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-edit fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Admin</div>
										<div>Profile</div>
									</div>
								</div>
							</div>
							<a href='${pageContext.request.contextPath}/admin_profile'>
								<div class="panel-footer">
									<span class="pull-left">view Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-6 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-upload fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Upload</div>
										<div>Questions!</div>
									</div>
								</div>
							</div>
							<a href='${pageContext.request.contextPath}/upload_questions'>
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
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
					<div class="col-lg-6 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-thumbs-o-up fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Review</div>
										<div>Results!</div>
									</div>
								</div>
							</div>
							<a href='${pageContext.request.contextPath}/view_results'>
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>

				<%-- <div class="row">
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-clock-o fa-fw"></i> Candidate Details
								</h3>
							</div>
							<div class="panel-body">
								<div class="list-group">
								<c:if test="${not empty userList}">
								<c:forEach items="${userList}" var="candidate">
									<a href='${pageContext.request.contextPath}/login?userId=${candidate.userId}' class="list-group-item"> <span class="badge">${candidate.email}
											</span> <i class="fa fa-fw fa-calendar"></i> <span style="text-transform: capitalize;">${candidate.userName}</span>
									</a> </c:forEach></c:if>
								</div>
								
							</div>
						</div>
					</div>				
				</div> --%>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
