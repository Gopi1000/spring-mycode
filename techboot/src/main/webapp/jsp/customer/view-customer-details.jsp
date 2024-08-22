
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
		<h3 class="text-center">Customer Details</h3>
	</div>
	<div class="row">

		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
			style="border: 1px solid #e6e6e6;">
			<br>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

							<label><i class="glyphicon glyphicon-user"> </i><span
								class="fafa_line_space"></span> First Name</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${viewcustomer.firstName}</span></span>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class=" glyphicon glyphicon-user"></i><span
								class="fafa_line_space"></span> Last Name</label>
						</div>
						<div class="col-lg-6 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${viewcustomer.lastName}</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

							<label><i class="fa fa-envelope"> </i><span
								class="fafa_line_space"></span> Email Id</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${viewcustomer.emailId}</span></span>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-calendar"></i><span
								class="fafa_line_space"></span> Date Of Birth</label>
						</div>
						<div class="col-lg-6 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${viewcustomer.dob}</span></span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

							<label><i class="fa fa-mobile-phone"> </i><span
								class="fafa_line_space"></span> Mobile Number</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">${viewcustomer.mobileNumber}</span></span>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-whatsapp"></i><span
								class="fafa_line_space"></span> Whatsapp Number</label>
						</div>
						<div class="col-lg-6 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">
									${viewcustomer.whatsappNumber}</span></span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

							<label><i class="fa fa-industry"> </i><span
								class="fafa_line_space"></span> Industry Type</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">
									${viewcustomer.industry}</span></span>
						</div>
					</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-address-card"></i><span
								class="fafa_line_space"></span> Address</label>
						</div>
						<div class="col-lg-6 col-md-3 col-sm-6 col-xs-6">
							<span>: <span class="fafa_line_space1">
									${viewcustomer.address}</span></span>
						</div>
					</div>

				<%-- 	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-user-circle-o"></i><span
								class="fafa_line_space"></span> Specialization</label>
						</div>
						<div class="col-lg-6 col-md-3 col-sm-6 col-xs-6">
							<span>: <span class="fafa_line_space1">
									${viewcustomer.specialization}</span></span>
						</div>
					</div> --%>
				</div>
			</div>

			<%-- <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

							<label><i class="fa fa-users"> </i><span
								class="fafa_line_space"></span>CustomerType</label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">
									${viewcustomer.customerType}</span></span>
						</div>
					</div>

				
				</div>
			</div> --%>
		</div>
	</div>
	<br>
	<div class="text-right">
		<div>
			<a href="view-customer.html"> <input type="button" value="Back"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold" />
			</a>
		</div>
	</div>
</section>
</main>