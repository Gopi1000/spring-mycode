
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="../css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

</head>
<jsp:include page="header.jsp"></jsp:include>

<body>
	<div id="">
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Quiz Review</h1>
					</div>
				</div>



				<div class="row">
					<div class="col-lg-12 well">

						<c:if test="${!empty errorMessage}">
						<div class="alert alert-success" style="font-size: 100px;">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<h4 align="center">${errorMessage}</h4>
							</div>
						</c:if>
						<c:forEach var="q" items="${sessionScope.markedquestion}"
							varStatus="counter">
							<br>
								${counter.count}. ${q.question}<br />

							<c:forEach var="option" items="${q.questionOptions}"
								varStatus="counter">
								<c:set var="optTemp" value="${fn:split(option, '@')}"></c:set>
									${counter.count}.   ${optTemp[1]}<br />
							</c:forEach>

							<font color="green">Correct Answer :
								${q.correctOptionIndex}</font>
							<br />
							<c:if test='${q.userSelected == q.correctOptionIndex}'>
								<img height="30" width="30"
									src="${pageContext.request.contextPath}/images/correct.png" />
							</c:if>
							<hr>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../js/bootstrap.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="../js/plugins/morris/raphael.min.js"></script>
	<script src="../js/plugins/morris/morris.min.js"></script>
	<script src="../js/plugins/morris/morris-data.js"></script>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>