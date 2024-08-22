<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- header of the page -->
<header id="page-header" class="page-header-stick"> <!-- top bar -->
<div class="top-bar bg-dark text-gray">
	<div class="container">
		<div class="row top-bar-holder">
			<div class="col-xs-9 col">
				<!-- bar links -->
				<ul class="font-lato list-unstyled bar-links">
					<li><a href="tel:+919790590476"> <strong
							class="dt element-block text-capitalize hd-phone">Call :</strong>
							<strong class="dd element-block hd-phone">+919790590476</strong>
							<i
							class="fas fa-phone-square hd-up-phone hidden-sm hidden-md hidden-lg"><span
								class="sr-only">phone</span></i>
					</a></li>
					<li><a href="mailto:enquiry@techboot.com"> <strong
							class="dt element-block text-capitalize hd-phone">Email
								:</strong> <strong class="dd element-block hd-phone">enquiry@techbootsolutions.com</strong>
							<i
							class="fas fa-envelope-square hd-up-phone hidden-sm hidden-md hidden-lg"><span
								class="sr-only">email</span></i>
					</a></li>
				</ul>
			</div>
			<div class="col-xs-3 col justify-end">
				<!-- user links -->
				<ul class="list-unstyled user-links fw-bold font-lato">
					<c:choose>
					    <c:when test="${userType eq 'student' }">
						<li><a href="view-student" class="">My Profile</a><span class="sep">|</span>
						    <a href="student-home" class="">My Home</a> <span class="sep">|</span>
							<a href="logouts" class="">Logout</a></li>
						</c:when>
						<c:when test="${userType eq 'company' }">
						<li><a href="view-companydetails" class="">My Profile</a><span class="sep">|</span>
						    <a href="company-home" class="">My Home</a> <span class="sep">|</span>
							<a href="logouts" class="">Logout</a></li>
						</c:when>
						<c:when test="${userType eq 'admin'}">
							<li><a href="admin-profile" class="">My Profile</a><span class="sep">|</span>
							    <a href="admin-home" class="">Home</a> <span class="sep">|</span>
								<a href="logouts" class="">Logout</a></li>
						</c:when>
						<c:otherwise>
							<li>
							<a href="sign-in" class="">Login</a> <span class="sep">|</span>
								<a href="register-company" class="">Partners</a><span class="sep">|</span>
								
								<a href="student-register">Students</a>
								
								</li>
								
								
								
								
								
						
								
						</c:otherwise>
						
					
						
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- header holder -->
<div class="header-holder">
	<div class="container">
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<!-- logo -->
				<div class="logo">
					<a href="home.html"> <img class="hidden-xs" id="logo"
						src="resource/images/logo-white.png" alt="TechBoot"> <img id="logo"
						class="hidden-sm hidden-md hidden-lg"
						src="resource/images/logo-white.png" alt="TechBoot">
					</a>
				</div>
			</div>
			<div class="col-xs-6 col-sm-9 static-block">
				<!-- nav -->
				<nav id="nav" class="navbar navbar-default">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<!-- navbar collapse -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<!-- main navigation -->
					<ul
						class="nav navbar-nav navbar-right main-navigation text-uppercase font-lato" style="font-weight: bolder;">
						<li><a href="courses-list.html">Courses</a></li>
						<li><a href="events-list.html">Events</a></li>
						<!-- <li><a href="blog.html">Blog </a></li> -->
						<li><a href="contact.html">CONTACT</a></li>
					</ul>
				</div>
				<!-- navbar form -->
				<form action="#" class="navbar-form navbar-search-form navbar-right">
					<a class="fa fa-search" role="button" style="color: white"
						data-toggle="collapse" href="#searchCollapse"
						aria-expanded="false" aria-controls="searchCollapse"><span
						class="sr-only">search opener</span></a>
					<!-- search collapse -->
					<div class="collapse search-collapse" id="searchCollapse">
						<div class="form-group">
							<input type="text" class="form-control"
								placeholder="Search &hellip;">
							<button type="button" class="fa fa-search">
								<span class="sr-only">search</span>
							</button>
						</div>
					</div>
				</form>
				</nav>
			</div>
		</div>
	</div>
</div>
</header>

