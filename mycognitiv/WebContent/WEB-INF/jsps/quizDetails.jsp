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
						<h1 class="page-header">Quiz Details</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<div class="well">
							<h2 align="center">Instruction for ${sessionScope.exams}
								Quiz :</h2>
							<ul style="list-style-type: disc">
								<li>Quiz contains
									${sessionScope.totalNumberOfQuizQuestions} Questions</li>
								<li>Total time for the Quiz is ${sessionScope.quizDuration}
									Minutes</li>
								<li>You can finish the exam at any time</li>
								<li>Read the question carefully before answering</li>
								<li>You can change your answers before submitting</li>
								<li>Good luck for the test.</li>
							</ul>
							<div class="row">
								<div class="col-lg-4"></div>
								<div class="col-lg-4">
									<button class="btn btn-success"
										onclick="location.href='${pageContext.request.contextPath}/exam'">Start
										Exam</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>