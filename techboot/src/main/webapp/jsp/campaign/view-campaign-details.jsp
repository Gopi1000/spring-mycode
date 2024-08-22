<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<main id="main"> <section
	class="container instructor-profile-block">
<div class="contact-form">
	<h3 class="text-center">View Campaign Details</h3>
</div>
<div class="row">

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
		style="border: 1px solid #e6e6e6; padding-bottom: 18px;">
		<br>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-industry "> </i><span
							class="fafa_line_space"></span> Company Name</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">${campaignBo.companyBO.companyName}</span></span>
					</div>
				</div>
				<c:if test="${companyType eq 'Service' }">

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-user-circle-o "> </i><span
							class="fafa_line_space"></span> Service Name</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">${campaignBo.productService.serviceName}</span></span>
					</div>
				</div>
				</c:if>
				<c:if test="${companyType eq 'Training' }">

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-user-circle-o "> </i><span
							class="fafa_line_space"></span> Course Name</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">${campaignBo.course.courseName}</span></span>
					</div>
				</div>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-envelope "> </i><span
							class="fafa_line_space"></span> Category </label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">${campaignBo.category}</span></span>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-calendar "> </i><span
							class="fafa_line_space"></span> Starting Date</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">${campaignBo.stDate}</span></span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-calendar "> </i><span
							class="fafa_line_space"></span> Ending Date</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">${campaignBo.edDate}</span></span>
					</div>
				</div>

			</div>
		</div>


		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
						<label><i class="fa fa-address-card"></i>
							&nbsp;&nbsp;Campaign Message</label>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-6 col-xs-6"
						style="margin-left: -7px">
						<span>:&nbsp;&nbsp;&nbsp;${campaignBo.message}</span>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<br>
<div class="text-right">
	<div>
		<a href=view-campaign> <input type="button" value="Back"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold" />
		</a>
	</div>
</div>
</section> </main>
