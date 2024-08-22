<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<main id="main">
<section class="container instructor-profile-block">
	<div class="contact-form">
		<h3 class="text-center">View Event Registration Details</h3>
	</div>
	<div class="row">
		<div class="col-sm-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;">

				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-industry"> </i><span
									class="fafa_line_space">Events Name</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.eventBo.titleName}</span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-user-circle-o"></i><span
									class="fafa_line_space">Candidate Name </span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.candidateName}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-envelope"> </i> <span
									class="fafa_line_space">Email Address</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.emailAddress}</span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-mobile-phone"> </i> <span
									class="fafa_line_space">Mobile Number</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.mobileNumber}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fas fa-user-graduate"> </i><span
									class="fafa_line_space">Qualification </span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.qualification}</span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fas fa-user-graduate"> </i><span
									class="fafa_line_space">Professional </span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.professional}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fas fa-user-circle"> </i><span
									class="fafa_line_space"> Gender </span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.gender}</span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-address-card"> </i><span
									class="fafa_line_space"> Address </span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:&nbsp;&nbsp; ${eventRegisterBo.address}</span>
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