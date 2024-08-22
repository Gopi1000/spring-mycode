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
		<h3 class="text-center">Company Details</h3>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
			style="border: 1px solid #e6e6e6;">
			<br>

			<section class="text-center bg-gray">
				<blockquote class="testimonial-quote">
					<span class="avatar rounded-circle element-block"> <img
						class="rounded-circle"
						src="read_images.html?imgName=${viewCompanyDetails.imageName}&&image=companyLogo">
					</span>
				</blockquote>
			</section>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-industry"> </i>&nbsp;&nbsp;Company
								Name</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${viewCompanyDetails.companyName}</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-user-circle-o">&nbsp;&nbsp; </i>ChairPerson
								Name </label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${viewCompanyDetails.companyPersonName}</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-user-circle-o"> </i>
								&nbsp;&nbsp;First Name</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp; ${viewCompanyDetails.firstName}</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-user-circle-o"> </i>
								&nbsp;&nbsp;Last Name </label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp; ${viewCompanyDetails.lastName}</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-envelope"> </i>&nbsp;&nbsp;Email
								Address </label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${viewCompanyDetails.emailAddress}</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-envelope"> </i>&nbsp;&nbsp;Conform
								Email Address </label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${viewCompanyDetails.conformEmailAddress}</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-globe"> </i> &nbsp;&nbsp;Company
								WebSite</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${viewCompanyDetails.companyWebSite}</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-building-o"> </i>
								&nbsp;&nbsp;Company Type</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;${viewCompanyDetails.companyType}</span>
						</div>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-mobile-phone"> </i>
								&nbsp;&nbsp;Contact Number</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;${viewCompanyDetails.contectNumber}</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-industry"> </i>
								&nbsp;&nbsp;Industry</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;${viewCompanyDetails.industry}</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
							<label><i class="fa fa-address-card"></i>
								&nbsp;&nbsp;Address</label>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-6 col-xs-6"
							style="margin-left: -7px">
							<span>:&nbsp;&nbsp;&nbsp;${viewCompanyDetails.address}</span>
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
			Style="min-width: 100px" onclick="history.go(-1)">
	</div>
</section>
</main>