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
		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Quiz Results</h1>
						<ol class="breadcrumb">
							<li><i class="fa fa-dashboard"></i> <a
								href='${pageContext.request.contextPath}'>Dashboard</a></li>
							<li class="active"><i class="fa fa-edit"></i> Quiz Results</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<h3 align="center">Quiz Result</h3>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<tr>
									<td class="head">Quiz :</td>
									<td><span id="lblSubject">${sessionScope.examName}</span></td>
								</tr>
								<tr>
									<td class="head">Starting Time :</td>
									<td><span id="lblStime">${sessionScope.started}</span></td>
								</tr>


								<tr>
									<td class="head">No. of Questions :</td>
									<td><span id="lblNquestions">${sessionScope.totalNumberOfQuizQuestions}</span></td>
								</tr>

								<tr>
									<td class="head">No. of correct answers :</td>
									<td><span id="lblNcans">${requestScope.result}</span></td>
								</tr>

							</table>
						</div>

						<c:if test="${requestScope.result >= (sessionScope.totalNumberOfQuizQuestions / 2)}">
							<h3 align="center">Passed</h3>
						</c:if>
						<c:if test="${requestScope.result < (sessionScope.totalNumberOfQuizQuestions / 2)}">
							<h3 align="center">Failed</h3>
						</c:if>
						<br />
						<div class="row">
							<div class="col-lg-4">
								<a class="btn btn-lg btn-success"
									href='${pageContext.request.contextPath}/exam/review'>Review
									Answers</a>
							</div>
							<div class="col-lg-4">
								<a class="btn btn-lg btn-info"
									href='${pageContext.request.contextPath}/view_admin_test'>Take Another Exam</a>
							</div>
							<div class="col-lg-4">
								<a class="btn btn-lg btn-warning"
									href='${pageContext.request.contextPath}/admin_home'>Back to HomePage</a>
							</div>
						</div>
						<br />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>