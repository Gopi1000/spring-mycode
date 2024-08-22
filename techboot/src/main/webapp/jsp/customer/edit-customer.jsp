<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.errors {
	color: red;
}
</style>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#customerform input[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>

<main id="main"> <section
	class="container instructor-profile-block checkout-form"> <form:form
	name="myForm" id="customerform" method="post" modelAttribute="customerBo"
	commandName="customerBo" class="contact-form top">
<div class="col-xs-12 col-sm-12">
	<div class="contact-form">
		<h3 class="text-center">Edit Customer</h3>
	</div>

	<c:if test="${userType eq 'admin' }">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;">
				<br>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Company Name <span class="required"><font
										class="f-color">*</font></span></span>
										<form:input type="text" id="companyid" path="companyBO.companyName"
								autocomplete="off" placeholder="Company Name"
								class="form-control element-block" readonly="true" />
							</label>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> First
								Name <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="customerName" path="firstName"
								autocomplete="off" placeholder="First Name"
								class="form-control element-block" />
							<form:errors path="firstName" cssClass="errors" />
							<div id="companyPersonNameErr" style="color: red"></div>
						</div>

					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Last
								Name <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="lastName" path="lastName"
								placeholder="Last Name" class="form-control element-block" />
							<form:errors path="lastName" cssClass="errors"></form:errors>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Email
								Id <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="emailId" path="emailId"
								placeholder="Email Id" class="form-control element-block" />
							<form:errors path="emailId" cssClass="errors"></form:errors>
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:enquiry@techboot.com</label>
						</div>

					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">Date Of
								Birth<font class="f-color">*</font>
							</label>
							<form:input type="text" id="datepicker" path="dateOfBirth"
								placeholder="Date Of Birth" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:MM/DD/YYYY</label>
							<form:errors path="dateOfBirth" cssClass="errors"></form:errors>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Mobile
								Number <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="customerType" path="mobileNumber"
								placeholder="Mobile Number" class="form-control element-block"
								maxlength="10" />
							<form:errors path="mobileNumber" cssClass="errors"></form:errors>
						</div>

					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">
								Whatsapp Number <span class="required"><font
									class="f-color">*</font></span>
							</label>
							<form:input type="text" id="whatsappNumber" path="whatsappNumber"
								placeholder="Whatsapp Number" class="form-control element-block"
								maxlength="10" />
							<form:errors path="whatsappNumber" cssClass="errors"></form:errors>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Address
								<span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="address" path="address"
								placeholder="Address" class="form-control element-block" />
							<form:errors path="address" cssClass="errors"></form:errors>
						</div>

					</div>
				</div>
				<div class="row">
					
						

					
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">
								Specialization <span class="required"><font
									class="f-color">*</font></span>
							</label>
							<form:input type="text" id="specialization" path="specialization"
								placeholder="Specialization" class="form-control element-block" />
							<form:errors path="specialization" cssClass="errors" />
						</div>
					</div>
				</div>
				<br>
			</div>

		</div>
	</c:if>

	<c:if test="${userType eq 'company'}">

		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;">
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> First
								Name <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="customerName" path="firstName"
								autofocus="true" autocomplete="off" placeholder="First Name"
								class="form-control element-block" />
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Last
								Name <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="lastName" path="lastName"
								placeholder="Last Name" class="form-control element-block" />

						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Email
								Id <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="emailId" path="emailId"
								placeholder="Email Id" class="form-control element-block" />

						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato">Date Of
								Birth<font class="f-color">*</font>
							</label>
							<form:input type="text" id="datepicker" path="dateOfBirth"
								placeholder="Date Of Birth" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"></label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Mobile
								Number <span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="customerType" path="mobileNumber"
								placeholder="Mobile Number" class="form-control element-block"
								maxlength="10" />

						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato">
								Whatsapp Number <span class="required"><font
									class="f-color">*</font></span>
							</label>
							<form:input type="text" id="whatsappNumber" path="whatsappNumber"
								placeholder="Whatsapp Number" class="form-control element-block"
								maxlength="10" />

						</div>

					</div>
				</div>

				<div class="row">
					

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato"> Address
								<span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="address" path="address"
								placeholder="Address" class="form-control element-block" />

						</div>
					</div>
				

				
					
					<!-- <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> 	Specialization
								<span class="required"><font class="f-color">*</font></span>
							</label>
							<form:input type="text" id="specialization" path="specialization"
								placeholder="Specialization" class="form-control element-block" />

						</div>
					</div>-->
				
			</div>
		</div>
	</c:if>
	<div class="text-center">
		<button type="submit" id="submit"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold">Submit
		</button>
	</div>
</div>
</form:form> </section> </main>