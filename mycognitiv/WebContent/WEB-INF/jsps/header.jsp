<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0"); //prevents caching at the proxy server
	response.setHeader("Cache-Control", "no-cache");
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>myCognitiv</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />


</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${pageContext.request.contextPath}"
			style="color: #fff; font-size: 18px; font-style: italic; font-weight: bold; margin-left: 25px;"><i
			class="fa fa-check-circle" aria-hidden="true"></i> mycognitiv<b
			class=""></b></a>
	</div>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-right top-nav">
			<c:if test='${empty sessionScope.user}'>
				<ul class="nav navbar-nav" style="float: right;">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Login <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<%-- <li><a href="${pageContext.request.contextPath}/login?user=admin"><i
									class="fa fa-user" aria-hidden="true"></i> Admin</a></li> --%>
							<li><a
								href="${pageContext.request.contextPath}/login?user=candidate"><i
									class="fa fa-user" aria-hidden="true"></i> Candidate</a></li>
							<li><a
								href="${pageContext.request.contextPath}/login?user=company"><i
									class="fa fa-user" aria-hidden="true"></i> Company</a></li>
							<li><a
								href="${pageContext.request.contextPath}/login?user=professor"><i
									class="fa fa-user" aria-hidden="true"></i> Professor</a></li>
						</ul></li>
				</ul>

			</c:if>

			<c:if test='${not empty sessionScope.user}'>
				<c:if test="${userType == 'Admin'}">
					<ul class="nav navbar-nav" style="float: right;">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Company <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/company_register'><i
										class="fa fa-upload fa-fw" style="font-size: 16px;"></i>
										Create Company </a></li>
								<li><a
									href='${pageContext.request.contextPath}/view_companies'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										Companies </a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Category <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/create_category'><i
										class="fa fa-pencil fa-fw" style="font-size: 16px;"></i>
										Create Category </a></li>
								<li><a
									href='${pageContext.request.contextPath}/view_category'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										Category </a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Sub Category <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/create_subcategory'><i
										class="fa fa-pencil fa-fw" style="font-size: 16px;"></i>
										Create SubCategory </a></li>
								<li><a
									href='${pageContext.request.contextPath}/view_subcategory'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										SubCategory </a></li>
								<%-- <li><a
									href='${pageContext.request.contextPath}/edit_priority'><i
										class="fa fa-edit fa-fw" style="font-size: 16px;"></i> Edit
										Priority </a></li> --%>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Question <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/upload_questions'><i
										class="fa fa-upload fa-fw" style="font-size: 16px;"></i>
										Upload Questions </a></li>

								<li><a
									href='${pageContext.request.contextPath}/create_question'><i
										class="fa fa-pencil fa-fw" style="font-size: 16px;"></i>
										Create Questions </a></li>

								<li><a
									href='${pageContext.request.contextPath}/edit_questions'><i
										class="fa fa-edit fa-fw" style="font-size: 16px;"></i> Search
										Questions </a></li>


								<li><a
									href='${pageContext.request.contextPath}/list_questions'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> List
										Questions </a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Candidate <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/create_candidate'><i
										class="fa fa-pencil fa-fw" style="font-size: 16px;"></i>
										Create Candidate </a></li>
								<li><a
									href='${pageContext.request.contextPath}/view_candidates'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										Candidates </a></li>
								<li><a
									href='${pageContext.request.contextPath}/view_testanswer'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										Candidate Answer </a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Test <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/admin_create_test'><i
										class="fa fa-pencil fa-fw" style="font-size: 16px;"></i>
										Create Test </a></li>
								<li><a
									href='${pageContext.request.contextPath}/coding_test'><i
										class="fa fa-pencil fa-fw" style="font-size: 16px;"></i>
										Coding Test </a></li>
								<li><a
									href='${pageContext.request.contextPath}/view_admin_test'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										Test </a></li>

							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">My Home <b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu" style="margin-left: -95px;">
								<li><a href='${pageContext.request.contextPath}/admin_home'><i
										class="fa fa-fw fa-home" style="font-size: 16px;"></i> Home </a></li>
								<li><a
									href='${pageContext.request.contextPath}/admin_profile'><i
										class="fa fa-fw fa-user" style="font-size: 16px;"></i> Profile
								</a></li>


								<li><a href='${pageContext.request.contextPath}/logout'><i
										class="fa fa-fw fa-power-off" style="font-size: 16px;"></i>
										Log Out </a></li>
							</ul></li>
					</ul>
				</c:if>


			</c:if>
			<!-- for company start -->
			<c:if test='${not empty sessionScope.user}'>

				<c:if test="${userType =='Company'}">
					<ul class="nav navbar-nav" style="float: right;">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Candidate <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/create_candidate'><i
										class="fa fa-pencil fa-fw" style="font-size: 16px;"></i>
										Create Candidate </a></li>
								<li><a
									href='${pageContext.request.contextPath}/view_candidates'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										Candidates </a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">Review Test <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href='${pageContext.request.contextPath}/view_testanswer'><i
										class="fa fa-list fa-fw" style="font-size: 16px;"></i> View
										Candidate Answer </a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">${sessionScope.user}
								<b class="caret"></b>
						</a>
							<ul class="dropdown-menu" style="margin-left: -95px;">
								<li><a
									href='${pageContext.request.contextPath}/company_home'><i
										class="fa fa-fw fa-home" style="font-size: 16px;"></i> Home </a></li>
								<li><a
									href='${pageContext.request.contextPath}/company_profile'><i
										class="fa fa-fw fa-user" style="font-size: 16px;"></i> Profile
								</a></li>

								<li><a href='${pageContext.request.contextPath}/logout'><i
										class="fa fa-fw fa-power-off" style="font-size: 16px;"></i>
										Log Out </a></li>
							</ul></li>
					</ul>
				</c:if>
			</c:if>
			<!-- for company end -->
			<c:if test='${not empty sessionScope.user}'>
				<c:if test="${userType == 'Candidate'}">
					<ul class="nav navbar-nav" style="float: right;">
						<li><a
							href='${pageContext.request.contextPath}/view_admin_test'><i
								class="fa fa-list fa-fw" style="font-size: 16px;"></i> My
								Assignments </a></li>
						<!-- <li><a href="takeTestCategory?user=candidate"><span
								class="glyphicon glyphicon-pencil"></span> Test To Be Taken </a></li> -->
						<li><a
							href='${pageContext.request.contextPath}/view_teststatus'><span
								class="glyphicon glyphicon-list-alt"></span> My Assessments </a></li>
						<li><a
							href='${pageContext.request.contextPath}/view_markedquestion'><span
								class="glyphicon glyphicon-check"></span> Marked Question </a></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">My Home <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/login"><i
										class="fa fa-fw fa-home" aria-hidden="true"></i> Home</a></li>
								<li><a
									href="${pageContext.request.contextPath}/candidate_details"><i
										class="fa fa-fw fa-user" aria-hidden="true"></i> Profile</a></li>
								<li><a href="${pageContext.request.contextPath}/logout"><i
										class="fa fa-fw fa-power-off" aria-hidden="true"></i> Logout</a></li>
							</ul></li>
					</ul>
				</c:if>
				<c:if test="${userType == 'Professor'}">
					<ul class="nav navbar-nav" style="float: right;">
						<li><a style="color: #fff;"
							href='${pageContext.request.contextPath}/edit_questions'><i
								class="fa fa-fw fa-edit"></i> Edit Question</a></li>
						<li><a style="color: #fff;"></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="color: #fff;">My Home <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/logout"><i
										class="fa fa-fw fa-power-off" aria-hidden="true"></i> Logout</a></li>
							</ul></li>
					</ul>
				</c:if>
			</c:if>
		</ul>
	</div>
	</nav>


	<div class="gradient"></div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="js/plugins/morris/raphael.min.js"></script>
	<script src="js/plugins/morris/morris.min.js"></script>
	<script src="js/plugins/morris/morris-data.js"></script>
</body>
</html>