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
		<h3 class="text-center"> Service Details</h3>
	</div>

	
			<div class="row">
		<div class="col-sm-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;padding: 50px;">
				
				<br>
				<section class="text-center bg-gray">
					<blockquote class="testimonial-quote">
						<span class="avatar rounded-circle element-block"> <img
							class="rounded-circle"
							src="read_images.html?imgName=${productService.companyBO.imageName}&&image=companyLogo">
						</span>
					</blockquote>
				</section>
				<div class="row">
				 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					 <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12"> 
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-industry"> </i><span
								class="fafa_line_space">Company Name</span></label>
						</div>

						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.companyBO.companyName}</span></span>
						</div>
					 </div>
					<!-- <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-user-circle-o"></i><span
								class="fafa_line_space">Company Person Name</span></label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.companyBO.companyPersonName}</span></span>
						</div>
					</div>
				</div> 
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-rupee"> </i><span
								class="fafa_line_space">Email</span></label>
						</div>

						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.companyBO.conformEmailAddress}</span></span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="far fa-calendar-times"></i><span
								class="fafa_line_space">Website</span></label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.companyBO.companyWebSite}</span></span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-calendar"> </i><span
								class="fafa_line_space">CompanyType</span></label>
						</div>

						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.companyBO.companyType}</span></span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-calendar"></i><span
								class="fafa_line_space">Contact Number</span></label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.companyBO.contectNumber}</span></span>
						</div>
						</div>-->
						
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-calendar"></i><span
								class="fafa_line_space">Service Name</span></label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.serviceName}</span></span>
						</div>
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-calendar"></i><span
								class="fafa_line_space">service charge</span></label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.fees}</span></span>
						</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-calendar"></i><span
								class="fafa_line_space">Duration</span></label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${productService.duration}</span></span>
						</div>
						</div>
											
					</div>
				</div>
			</div>
		</div>
	</div>


	<br>
	<div class="text-right">
		<div><!-- "view-service.html -->
			<a href="view-service.html"> <input type="button" value="Back"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold" />
			</a>
		</div>
	</div>
	
	
	
	
</section>