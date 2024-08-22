<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#companyform input[type="text"]').each(function() {
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

			// Email Validations 			
			var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var emailAddress = $("#emailIdInput").val();
			if (!emailValidations.test(emailAddress)) {
				$("#emailIdInputErr").show();
				$("#emailIdInputErr").html("Please Enter Valid Email ");
				var isValid = false;
			}
			// Email Validations 			
			var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var secondaryEmail = $("#secondaryEmailInput").val();
			if (!emailValidations.test(secondaryEmail)) {
				$("#secondaryEmailErr").show();
				$("#secondaryEmailErr").html("Please Enter Valid Email ");
				var isValid = false;
			}

			// Cheracter Validations 	
			var firstname = $("#firstNameInput").val();
			if (!/^[a-zA-Z\s]*$/g.test(firstname)) {
				$("#firstNameErr").show();
				$("#firstNameErr").html("Please Enter Character Only");
				var isValid = false;
			}

			var firstname = $("#lastNameId").val();
			if (!/^[a-zA-Z\s]*$/g.test(firstname)) {
				$("#lastNameErr").show();
				$("#lastNameErr").html("Please Enter Character Only");
				var isValid = false;
			}

			var firstname = $("#companyPersonNameId").val();
			if (!/^[a-zA-Z\s]*$/g.test(firstname)) {
				$("#companyPersonNameErr").show();
				$("#companyPersonNameErr").html("Please Enter Character Only");
				var isValid = false;
			}

			//mobile validation 
			var mobile = $("#contectNumberId").val();
			if (!/^[0-9]{1,11}$/.test(mobile)) {
				var isValid = false;
				$("#mobileerror").show();
				$("#mobileerror").html("Please Enter Numbers Only");
			} else if (mobile.length < 10) {
				var isValid = false;
				$("#mobileerror").show();
				$("#mobileerror").html("Must enter 10 Digits Numbers");
			} else {
				$("#mobileerror").hide();
				$("#mobileNoInput").css({
					"border" : "",
					"background" : ""
				});
			}

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>
<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="companyform" method="post" modelAttribute="companyBo"
		commandName="companyBo" class="contact-form top">

		<div class="contact-form">
			<h3 class="text-center">Edit Company</h3>
		</div>
		<div class="bg-gray" style="border: 1px solid #e6e6e6;">

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Company Name <span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text"
								id="companyName" path="companyName" placeholder="company name"
								class="form-control element-block" />
						</label>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">ChairPerson Name <span
								class="required"><font class="f-color">*</font></span></span> <form:input
								type="text" id="companyPersonNameId" path="companyPersonName"
								placeholder="companyPerson Name"
								class="form-control element-block" />
						</label>
						<form:errors path="companyPersonName" cssClass="error"
							style="color: red" />
						<div id="companyPersonNameErr" style="color: red"></div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">First Name <span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text"
								id="firstNameInput" path="firstName" placeholder="first Name"
								class="form-control element-block" />
						</label>
						<form:errors path="firstName" cssClass="error" style="color: red" />
						<div id="firstNameErr" style="color: red"></div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Last Name <span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text"
								id="lastNameId" path="lastName" placeholder="Last Name"
								class="form-control element-block" />
						</label>
						<form:errors path="lastName" cssClass="error" style="color: red" />
						<div id="lastNameErr" style="color: red"></div>
					</div>
				</div>
			</div>
			<div class="row">

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Email Address <span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text"
								id="emailIdInput" path="emailAddress"
								placeholder="Email Address" class="form-control element-block" />
						</label>
						<form:errors path="emailAddress" cssClass="error"
							style="color: red" />
						<div id="emailIdInputErr" style="color: red"></div>
						<label class="element-block fw-normal font-lato"
							style="font-size: 10px">EX:enquiry@techboot.com</label>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Conform EmailAddress<span
								class="required"><font class="f-color">*</font></span></span> <form:input
								type="text" id="secondaryEmailInput" path="conformEmailAddress"
								placeholder="conform Email Address"
								class="form-control element-block" />
						</label>
						<form:errors path="conformEmailAddress" cssClass="error"
							style="color: red" />
						<div id="secondaryEmailErr" style="color: red"></div>
						<label class="element-block fw-normal font-lato"
							style="font-size: 10px">EX:enquiry@techboot.com</label>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Company WebSite<span
								class="required"><font class="f-color">*</font></span></span> <form:input
								type="text" id="companyWebsiteInput" path="companyWebSite"
								placeholder="Company WebSite" class="form-control element-block" />
						</label>
						<form:errors path="companyWebSite" cssClass="error"
							style="color: red" />
						<div id="websiteErr" style="color: red"></div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Company Type <span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text"
								id="companyType" path="companyType" placeholder="Company Type"
								class="form-control element-block" maxlength="" />
						</label>
					</div>
				</div>
			</div>
			<div class="row">

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Contact Number <span
								class="required"><font class="f-color">*</font></span></span> <form:input
								type="text" id="contectNumberId" path="contectNumber"
								placeholder="Contect Number" class="form-control element-block"
								maxlength="10" />
						</label>
						<form:errors path="conformEmailAddress" cssClass="error"
							style="color: red" />
						<div id="mobileerror" style="color: red"></div>

					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Address<span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text" id="address"
								path="address" placeholder="Address"
								class="form-control element-block" />
						</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label>Industry Type <span class="required"><font
								class="f-color">*</font></span></label>
						<form:select type="text" class="element-block fw-normal font-lato"
							path="industry" id="functionInput" style="height: 43px;">
							<form:option value="">Select Industry Type</form:option>
							<form:options items="${industryList}" />
						</form:select>
						<form:errors path="industry" cssClass="errors" />
					</div>
				</div>
			</div>

			<div class="text-right">
				<button type="submit" id="submit"
					class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
					Style="min-width: 100px">Submit</button>
			</div>
		</div>

	</form:form>
</section>
</main>