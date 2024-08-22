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
	<h3 class="text-center">View SMS Tracking</h3>
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
						<label><i class="fas fa-user-circle"> </i><span
							class="fafa_line_space"> </span>Current Date</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">
								${SmsTraking.trackingDate}</span></span>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-envelope"></i><span
							class="fafa_line_space"> </span>Customer Name </label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">
								${SmsTraking.customerName}</span></span>
					</div>
				</div>
			</div>
		</div>



		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<label><i class="fa fa-mobile-phone"> </i><span
							class="fafa_line_space"> </span>Mobile Number</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span>:<span class="fafa_line_space1">
								${SmsTraking.mobileNumber}</span></span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-3 col-md-6 col-sm-6 col-xs-6">
							<label style="margin-left: 14px"><i
								class=" fas fa-comment-dots"> </i><span class="fafa_line_space">
							</span>Message </label>
						</div>
						<div class="col-lg-9 col-md-6 col-sm-6 col-xs-6">
							<span>:<span class="fafa_line_space1">
									${SmsTraking.message}</span></span>
						</div>
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
</section> </main>


