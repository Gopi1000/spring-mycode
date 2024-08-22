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
		<div id="page-wrapper" style="height: 570px;">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header" align="left">My Assessments</h1>

					</div>
				</div>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-success" style="font-size: 100px;">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<h4 align="center">${errorMessage}</h4>
					</div>
				</c:if>
				<c:if test="${!empty testtaken}">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-list fa-fw"></i> My Assessments
									</h3>
								</div>
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th>Serial No</th>
													<th>Test Name</th>
													<th>Review</th>
													<th>Result</th>
												</tr>

											</thead>
											<c:set var="indexVal" value="1"></c:set>
											<c:forEach items="${testtaken}" var="takentest">
												<tbody>
													<tr>
														<td>${indexVal}</td>
														<td>${takentest.testType}</td>
														<td><a
															href="viewTeststatus?userId=${takentest.userId}&testId=${takentest.testId}"
															class="btn btn-primary btn-sm"> <span
																class="glyphicon glyphicon-book"></span>Review
														</a></td>
														<td>${takentest.overAllResult}</td>


														<%-- <td><a href='${pageContext.request.contextPath}/deleteCategory?categoryId=${category.categoryId}'><i class="fa fa-trash-o fa-lg"></i></a></td> --%>
													</tr>

												</tbody>
												<c:set var="indexVal" value="${indexVal+1}"></c:set>
											</c:forEach>
										</table>
										

										<button onclick="history.back(-1)" value="back"
											style="float: right;">Back</button>
									</div>
								</div>

							</div>
						</div>
					</div>
				</c:if>
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
