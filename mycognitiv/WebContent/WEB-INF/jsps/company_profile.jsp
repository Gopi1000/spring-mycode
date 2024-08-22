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
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Company Profile</h1>
						
					</div>
				</div>
				<c:if test="${!empty errorMessage}">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<h4 align="center">${errorMessage}</h4>
				</c:if>
				<c:if test="${!empty profile}">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-list fa-fw"></i> View Company Profile Details
								</h3>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr>
											   <th>S.No</th>
												<th>Company Name</th>
												<th>Company Email</th>
												<th>Company Location Email</th>
												<th>Company PhoneNo</th>
												<th>Company Website</th>
											</tr>
											
										</thead>
										
										<tbody>
											<tr>
											<td>1</td>
											<td><a href='#'>${profile.companyName}</a></td>
												<td>${profile.companyEmail}</td>
												<td>${profile.companyLocation}</td>
												<td>${profile.companyContactNo}</td>
												<td>${profile.companyWebsite}</td>
											</tr>
											
										</tbody>
										
									</table>
								</div>
							</div>
							
						</div>
					</div>
				</div></c:if>
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
