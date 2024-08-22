
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main id="main">
<section class="container instructor-profile-block">

	<div class="contact-form">
		<h3 class="text-center">View Course Registration Details</h3>
	</div>
	<div class="row">
		<div class="col-sm-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;">
				<br>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-industry"> </i><span
									class="fafa_line_space">Course Name</span></label>
							</div>

							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${courseRegistrationBO.courseBO.courseName}</span></span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-user-circle-o"></i><span
									class="fafa_line_space">Candidate Name</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${courseRegistrationBO.candidateName}</span></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-industry"> </i><span
									class="fafa_line_space">Email Address</span></label>
							</div>

							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${courseRegistrationBO.emailAddress}</span></span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-calendar"></i><span
									class="fafa_line_space">Mobile Number</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${courseRegistrationBO.mobileNumber}</span></span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-calendar"></i><span
									class="fafa_line_space">Professional</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${courseRegistrationBO.professional}</span></span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-calendar"></i><span
									class="fafa_line_space">Schedule</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${courseRegistrationBO.scheme}</span></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="text-right">
		<input type="button" value="Back"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
			onclick="history.go(-1)">
	</div>
</section>
</main>