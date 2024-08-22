<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<header id="page-header  ">
	<div class=" menu-sc menubar">
		<div class="header-holder" style="padding: 1px">
			<div class="row">
				<div class="col-xs-12 col-sm-9 static-block">
					<!-- nav -->
					<nav id="nav" class="navbar navbar-default" style="height: 40px;">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>
						<c:choose>

							<c:when test="${userType eq 'student'}">
								<div class="collapse navbar-collapse"
									id="bs-example-navbar-collapse-1"
									style="margin-right: 560px; margin-bottom: 13px">

									<ul
										class="nav navbar-nav navbar-right main-navigation font-lato">
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Student</a> <i
											class="fa fa-caret-down space"></i>
											<ul class="dropdown-menu">
												<li><a href="view-student">View Profile</a></li>

											</ul></li>

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Course
												Enrollment</a> <i class="fa fa-caret-down space"></i>
											<ul class="dropdown-menu">
												<li><a href="create-course-enrollment"> Create
														Enrollment</a></li>
												<li><a href="view-course-enrollment">View
														Enrollment</a></li>
												<li><a href="feedback"> Feedback</a></li>
												<li><a href="view-feedback">View Feedback</a></li>


											</ul></li>



									</ul>
								</div>
							</c:when>

							<c:when test="${userType eq 'admin'}">
								<div class="collapse navbar-collapse"
									id="bs-example-navbar-collapse-1"
									style="margin-right: -204px; margin-bottom: 13px">
									<!-- main navigation -->
									<ul
										class="nav navbar-nav navbar-right main-navigation font-lato">

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Users</a> <i
											class="fa fa-caret-down space" style="margin-right: 5px;"></i>
											<ul class="dropdown-menu">

												<li><a href="list-users">List User</a></li>
											</ul></li>



										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Company</a> <i
											class="fa fa-caret-down space" style="margin-right: 5px;"></i>
											<ul class="dropdown-menu">
												<li><a href="create-company">Create Company</a></li>
												<li><a href="view-company">View Company</a></li>
											</ul></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Service</a> <i
											class="fa fa-caret-down space" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="create-service">Create Service</a></li>
												<li><a href="view-service">View Service</a></li>
											</ul></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Campaign</a> <i
											class="fa fa-caret-down space" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="create-campaign">Create Campaign</a></li>
												<li><a href="view-campaign">View Campaign</a></li>

											</ul></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Customer</a> <i
											class="fa fa-caret-down space" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="create-customer">Create Customer</a></li>
												<li><a href="view-customer">View Customer</a></li>
											</ul></li>

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Testimonial</a> <i
											class="fa fa-caret-down space" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="create-testimonial">Create Testimonial</a></li>
												<li><a href="view-testimonial">View Testimonial</a></li>
											</ul></li>

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Recent news</a> <i
											class="fa fa-caret-down space" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="create-recentNews">Create Recent news</a></li>
												<li><a href="view-recentNews">View Recent news</a></li>

											</ul></li>

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Courses</a> <i
											class="fa fa-caret-down" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="create-course-category">Create Course
														category</a></li>
												<li><a href="view-course-category">View Course
														category</a></li>
												<li><a href="create-course">Create Course</a></li>
												<li><a href="view-course">View Course</a></li>
												<li><a href="create-course-details">Create Course
														Details</a></li>
												<li><a href="listview-course-details">View Course
														Details</a></li>

												<li><a href="view-course-registration">View Course
														Registration</a>
											</ul></li>

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Question</a> <i
											class="fa fa-caret-down space" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="add_questions">Create Question </a></li>
												<li><a href="view-addQuestions">List Question</a></li>
											</ul></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Event</a> <i
											class="fa fa-caret-down" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="create-event">Create Events</a></li>
												<li><a href="view-event">View Events</a></li>
												<li><a href="view-events-registration">View Events
														Registration</a></li>
											</ul></li>

										<li class="dropdown"><a href="view-contact"
											class="dropdown-toggle" data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Contact</a> <i
											class="fa fa-caret-down space" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="view-contact">View Contact</a></li>
											</ul></li>

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Student</a> <i
											class="fa fa-caret-down" aria-hidden="true"></i>
											<ul class="dropdown-menu">
												<li><a href="student-register">Create Student</a></li>
												<li><a href="view-student-register">View Student </a></li>

												<li><a href="create-course-enrollment">Create
														Course Enrollment</a></li>
												<li><a href="view-course-enrollment">View Course
														Enrollment</a></li>
											</ul></li>


										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;"> Reporting</a> <i
											class="fa fa-caret-down space"></i>
											<ul class="dropdown-menu">
												<li><a href="student-general-reporting"> Student
														Reporting </a></li>
												<li><a href="vieww"> Student enrolement</a></li>
												<li><a href="viewcompany"> company enrollement</a></li>
												<li><a href="viewcampaign"> campaign enrollement</a></li>



											</ul></li>

										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">Category</a> <i
											class="fa fa-caret-down space" style="margin-right: 5px;"></i>
											<ul class="dropdown-menu">
												<li><a href="create-category">Create Category</a></li>
												<li><a href="view-category">View Category</a></li>
											</ul></li>
											
											<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-haspopup="true"
											aria-expanded="false"
											style="font-size: 14px; font-weight: bold;">SubCategory</a> <i
											class="fa fa-caret-down space" style="margin-right: 5px;"></i>
											<ul class="dropdown-menu">
												<li><a href="create-subcategory">Create SubCategory</a></li>
												<li><a href="view-subcategory"> View SubCategory</a></li>
											</ul></li>
											

									</ul>
									</li>

									</ul>
								</div>
							</c:when>




							<c:otherwise>
								<c:if test="${companyType eq 'Service' }">
									<div class="collapse navbar-collapse"
										id="bs-example-navbar-collapse-1"
										style="margin-right: 300px; margin-bottom: 13px">
										<!-- main navigation -->
										<ul
											class="nav navbar-nav navbar-right main-navigation font-lato">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Company</a> <i
												class="fa fa-caret-down space" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="view-company-user">View Company</a></li>
												</ul></li>

											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Service</a> <i
												class="fa fa-caret-down space" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="create-service">Create Service</a></li>
													<li><a href="view-service">View Service</a></li>
												</ul></li>
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Campaign</a> <i
												class="fa fa-caret-down space" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="create-campaign">Create Campaign</a></li>
													<li><a href="view-campaign">View Campaign</a></li>
												</ul></li>
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Customer</a> <i
												class="fa fa-caret-down space" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="create-customer">Create Customer</a></li>
													<li><a href="view-customer">View Customer</a></li>
												</ul></li>
										</ul>
									</div>
								</c:if>
								<c:if test="${companyType eq 'Training' }">
									<div class="collapse navbar-collapse"
										id="bs-example-navbar-collapse-1"
										style="margin-right: 300px; margin-bottom: 13px">
										<!-- main navigation -->
										<ul
											class="nav navbar-nav navbar-right main-navigation font-lato">
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Company</a> <i
												class="fa fa-caret-down space" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="view-company-user">View Company</a></li>
												</ul></li>

											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Courses</a> <i
												class="fa fa-caret-down" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<!-- <li><a href="create-course-category">Create Course
														category</a></li>
												<li><a href="view-course-category">View Course
														category</a></li> -->
													<li><a href="create-course">Create Course</a></li>
													<li><a href="view-course">View Course</a></li>
													<li><a href="create-course-details">Create Course
															Details</a></li>
													<li><a href="listview-course-details">View Course
															Details</a></li>

													<li><a href="view-course-registration">View Course
															Registration</a>
												</ul></li>

											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Campaign</a> <i
												class="fa fa-caret-down space" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="create-campaign">Create Campaign</a></li>
													<li><a href="view-campaign">View Campaign</a></li>
												</ul></li>

											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Event </a> <i
												class="fa fa-caret-down space" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="create-event">Create Events</a></li>
													<li><a href="view-event">View Events</a></li>
													<li><a href="view-events-registration">View Events
															Registration</a></li>
												</ul></li>

											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false"
												style="font-size: 14px; font-weight: bold;">Student</a> <i
												class="fa fa-caret-down" aria-hidden="true"></i>
												<ul class="dropdown-menu">
													<li><a href="student-register">Create Student</a></li>
													<li><a href="view-student-register">View Student </a></li>

													<li><a href="create-course-enrollment">Create
															Course Enrollment</a></li>
													<li><a href="view-course-enrollment">View Course
															Enrollment</a></li>
												</ul></li>


										</ul>
									</div>
								</c:if>
							</c:otherwise>

						</c:choose>
					</nav>
				</div>
			</div>
		</div>
	</div>

</header>

<br>
