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



<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body {
	background:
		url("${pageContext.request.contextPath}/images/background.jpg");
}
</style>
<title>Review Exam</title>
</head>

<body>

	<div id="wrapper">

		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href='${pageContext.request.contextPath}'>SB
				Admin</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<c:if test='${not empty sessionScope.user}'>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i>
						${sessionScope.user} <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a></li>
						<li><a href="#"><i class="fa fa-fw fa-envelope"></i>
								Inbox</a></li>
						<li><a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li><a href='${pageContext.request.contextPath}/logout'><i
								class="fa fa-fw fa-power-off"></i> Log Out</a></li>
					</ul></li>
			</c:if>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li><a href='${pageContext.request.contextPath}'><i
						class="fa fa-fw fa-dashboard"></i> Dashboard</a></li>
				<li><a href='${pageContext.request.contextPath}/login'><i
						class="fa fa-key"></i> Login</a></li>
				<li><a href='${pageContext.request.contextPath}/register'><i
						class="fa fa-user"></i> Register</a></li>
				<li class="active"><a href="javascript:;"
					data-toggle="collapse" data-target="#demo"><i
						class="fa fa-fw fa-arrows-v"></i> Test <i
						class="fa fa-fw fa-caret-down"></i></a>
					<ul id="demo" class="collapse">
						<li><a href="takeExam?test=java">Java</a></li>
						<li><a href="takeExam?test=javascript">JavaScript</a></li>
						<li><a href="takeExam?test=sql">SQL</a></li>
						<li><a href="takeExam?test=python">Python</a></li>
						<li><a href="takeExam?test=css">CSS</a></li>
						<li><a href="takeExam?test=php">PHP</a></li>
						<li><a href="takeExam?test=linux">Linux</a></li>
						<li><a href="takeExam?test=mongodb">MongoDB</a></li>
					</ul></li>
			</ul>
		</div>
		</nav>
		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Quiz Review</h1>
						<ol class="breadcrumb">
							<li><i class="fa fa-dashboard"></i> <a
								href='${pageContext.request.contextPath}'>Dashboard</a></li>
							<li class="active"><i class="fa fa-edit"></i> Quiz Review</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<h3 align="center">Quiz Result</h3>

						<h1 align="center">Quiz Review</h1>
						<br>

						<c:forEach var="q" items="${requestScope.questionsL}"
							varStatus="counter">
							<br>
${counter.count}. ${q.question}<br />
							<br />
							<c:forEach var="option" items="${q.questionOptions}"
								varStatus="counter">
${counter.count}.   ${option}<br />
							</c:forEach>


							<br>
							<font color="#1334F1">You Choosed : ${q.userSelected}</font>
							<br />
							<font color="green">Correct Answer :
								${q.correctOptionIndex+1}</font>
							<br />
							<br />
							<c:if test='${q.userSelected == q.correctOptionIndex+1}'>
								<img height="30" width="30"
									src="${pageContext.request.contextPath}/images/correct.png" />
							</c:if>

							<c:if test='${q.userSelected != q.correctOptionIndex+1}'>
								<img height="30" width="30"
									src="${pageContext.request.contextPath}/images/redcross.png" />
							</c:if>
							<br />
_____________________________________________________________________________________________________________________________________________________________________<br>
						</c:forEach>
						<br /> <br />
						<div align="center">
							<a href="${pageContext.request.contextPath}"><img height="50"
								width="50"
								src="${pageContext.request.contextPath}/images/home.jpg"></img></a>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="js/plugins/morris/raphael.min.js"></script>
	<script src="js/plugins/morris/morris.min.js"></script>
	<script src="js/plugins/morris/morris-data.js"></script>



</body>
</html>