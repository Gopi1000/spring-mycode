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
		<div id="page-wrapper" style="height: 750px">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Dashboard 
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Candidate Preview
							</li>
						</ol>
						<div class="col-md-6 ">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">
								<h4>
									 <a href="jobseeker_profile_view.html"> <span class="glyphicon glyphicon-list-alt"></span>
									 Test Details</a>
								</h4>
							</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<c:if test="${not empty testDetails}">
								
									<label class="my-body-font">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Test Taken :
										${test.testCount}<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TotalQuestion Taken: ${profilecount} <br /> <br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Correct Attempts:
										${test.questionCount}
									</label>
									
								</c:if>
							</div>
						</div>
					</div>
				</div>
								<div class="row">
					
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-money fa-fw"></i> My Assessments Details
								</h3>
							</div>
							<div class="panel-body">
								<div class="table-responsive"><c:if test="${ not empty testDetails}">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr>
												<th>Ser.No</th>
												<th>Test Name</th>
												<th>Total Question</th>
												<th>Time Spent</th>
												
											</tr>
										</thead>
										<c:set var="serialNo" value="1"></c:set>
										<c:forEach items="${testDetails}" var="test">
										<tbody>
											<tr>
												<td>${serialNo}</td>
												<%-- <td style="text-transform: capitalize;">${test.testName}</td> --%>
												<td>${test.testType}</td>
												<td>${test.totalQuestion }
												<td>${test.totaltimespent}</td>
												
											</tr>
									
										</tbody>
											<c:set var="serialNo" value="${serialNo+1 }"></c:set>
										</c:forEach>
									</table></c:if>
									
								</div>
								
							</div>
						</div>
					</div>
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
