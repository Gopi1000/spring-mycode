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

<script type="text/javascript">
	function fileCheck(obj) {
		var fileExtension = [ 'jpeg', 'jpg', 'png', 'bmp', 'gif' ];
		if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
				fileExtension) == -1) {
			document.getElementById("PhotoInput").value = '';
			alert("Only '.jpeg','.jpg', 'gif', '.png',  '.bmp' formats are allowed.");
		}
	}
</script>
<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			var industryType = $("#functionInput").val();
			if (industryType == "") {
				$("#functionInput").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}
		});
	});
</script>
<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			$('input[type="file"]').each(function() {
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
		});
	});
</script>
<script>
	$(document)
			.ready(
					function() {
						$('#submit')
								.click(
										function(e) {
											var isValid = true;
											$('input[type="text"]')
													.each(
															function() {
																if ($.trim($(
																		this)
																		.val()) == '') {
																	isValid = false;
																	$(this)
																			.css(
																					{
																						"border" : "1px solid red",
																					});
																} else {
																	$(this)
																			.css(
																					{
																						"border" : "",
																						"background" : ""
																					});
																}
															});

											// Cheracter Validations 
											var companyPersonName = $(
													"#companyPersonName").val();
											if (!/^[a-zA-Z\s]*$/g
													.test(companyPersonName)) {
												$("#companyPersonNameErr")
														.show();
												$("#companyPersonNameErr")
														.html(
																"Please Enter Character Only");
												var isValid = false;
											} else {
												$("#companyPersonNameErr")
														.hide();
												$("#companyPersonNameErr").css(
														{
															"border" : "",
															"background" : ""
														});
											}

											var firstname = $("#firstName")
													.val();
											if (!/^[a-zA-Z\s]*$/g
													.test(firstname)) {
												$("#firstNameErr").show();
												$("#firstNameErr")
														.html(
																"Please Enter Character Only");
												var isValid = false;
											}

											else {
												$("#firstNameErr").hide();
												$("#firstNameErr").css({
													"border" : "",
													"background" : ""
												});
											}

											var lastname = $("#lastName").val();
											if (!/^[a-zA-Z\s]*$/g
													.test(lastname)) {
												$("#lastNameErr").show();
												$("#lastNameErr")
														.html(
																"Please Enter Character Only");
												var isValid = false;
											}

											else {
												$("#lastNameErr").hide();
												$("#lastNameErr").css({
													"border" : "",
													"background" : ""
												});
											}

											// Email Validations 			
											var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
											var emailAddress = $(
													"#emailAddress").val();
											if (!emailValidations
													.test(emailAddress)) {
												$("#emailIdInputErr").show();
												$("#emailIdInputErr")
														.html(
																"Please Enter Valid Email ");
												var isValid = false;
											}

											else {
												$("#emailIdInputErr").hide();
												$("#emailIdInputErr").css({
													"border" : "",
													"background" : ""
												});
											}
											// Email Validations 			
											var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
											var secondaryEmail = $(
													"#secondaryEmail").val();
											if (!emailValidations
													.test(secondaryEmail)) {
												$("#secondaryEmailErr").show();
												$("#secondaryEmailErr")
														.html(
																"Please Enter Valid Email ");
												var isValid = false;
											}

											else {
												$("#secondaryEmailErr").hide();
												$("#secondaryEmailErr").css({
													"border" : "",
													"background" : ""
												});
											}
											var website = $("#companyWebSite")
													.val();
											var websiteValidations = /(http(s)?:\\)?([\w-]+\.)+[\w-]+[.com|.in|.org]+(\[\?%&=]*)?/g;
											if (!websiteValidations
													.test(website)) {
												$("#websiteErr").show();
												$("#websiteErr")
														.html(
																"Please Enter Website Formats ");
												var isValid = false;
											} else {
												$("#websiteErr").hide();
												$("#websiteErr").css({
													"border" : "",
													"background" : ""
												});
											}

											var mobile = $("#contectNumberId")
													.val();
											if (!/^[0-9]{1,11}$/.test(mobile)) {
												var isValid = false;
												$("#mobileerror").show();
												$("#mobileerror")
														.html(
																"Please Enter Numbers Only");
											} else if (mobile.length < 10) {
												var isValid = false;
												$("#mobileerror").show();
												$("#mobileerror")
														.html(
																"Must enter 10 Digits Numbers");
											} else {
												var isValid = true;
											}
											if (isValid == false)
												e.preventDefault();

										});
					});
	function companycheck() {
		var companyName = $("#companyName").val()
		$.ajax({
			type : "GET",
			url : 'companyNameValidations',
			data : 'companyName=' + companyName,
			success : function(response) {
				if (response == "COMPANYNAME") {
					isValid = true;
					$("#companyNameErr").show();
					$("#companyNameErr").html("CompanyName already exist's")
				} else {
					isValid = false;
					$("#companyNameErr").hide();
					$("#companyNameErr").css({
						"border" : "",
						"background" : ""
					});
				}
			},
		});
	}
	function emailcheck() {
		var emailAddress = $("#emailAddress").val()
		$.ajax({
			type : "GET",
			url : 'emailAddressVerifications',
			data : 'emailAddress=' + emailAddress,
			success : function(response) {
				if (response == "EMAILADDRESS") {
					isValid = false;
					$("#emailIdInputErr").show();
					$("#emailIdInputErr").html("EmailAddress already exist's")
				} else {
					isValid = true;
					$("#emailIdInputErr").hide();
					$("#emailIdInputErr").css({
						"border" : "",
						"background" : ""
					});
				}
			},
		});
	}
	function website() {
		var website = $("#companyWebSite").val()
		alert("hai" + website)
		$.ajax({
			type : "GET",
			url : 'websiteVerifications',
			data : 'website=' + website,
			success : function(response) {
				alert("hai" + response)
				if (response == "WEBSITE") {
					isValid = true;
					$("#websiteErr").show();
					$("#websiteErr").html("Company Website already exist's")
				} else {
					isValid = false;
					$("#websiteErr").hide();
					$("#websiteErr").css({
						"border" : "",
						"background" : ""
					});
				}

			},
		});

	}
</script>

<main id="main"> <header
	class="heading-banner text-white bgCover"
	style="background-image: url(resource/images/img23.jpg);">
	<div class="container holder">
		<div class="align">
			<h1>Partners</h1>
		</div>
	</div>
</header>
<nav class="breadcrumb-nav">
	<div class="container">
		<!-- breadcrumb -->
		<ol class="breadcrumb">
			<li><a href="home.html">Home</a></li>
			<li class="active">Registration</li>
		</ol>
	</div>
</nav>
<br>
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post" modelAttribute="companyBo"
		commandName="companyBo" class="contact-form top"
		enctype="multipart/form-data">

		<c:if test="${not empty errorMessage}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>

		</c:if>

		<div class="contact-form">
			<h3 class="text-center">Registration</h3>
		</div>
		<div class="bg-gray" style="border: 1px solid #e6e6e6;">

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Company
							Name <span class="required"> <font style="color: red;">*
							</font></span>
						</label>
						<form:input type="text" id="companyName" path="companyName"
							onchange="companycheck()" placeholder="Company Name"
							class="form-control element-block" />
						<form:errors path="companyName" cssClass="error"
							style="color: red" />
						<div id="companyNameErr" style="color: red"></div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">
							Chairperson Name <span class="required"><font
								style="color: red;">*</font></span>
						</label>
						<form:input type="text" id="companyPersonName"
							path="companyPersonName" placeholder="Chairperson Name "
							class="form-control element-block" />

						<form:errors path="companyPersonName" cssClass="error"
							style="color: red" />
						<div id="companyPersonNameErr" style="color: red"></div>
					</div>
				</div>


			</div>
	
		<div class="row">

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

				<div class="form-group">
					<label class="element-block fw-normal font-lato"> First
						Name <span class="required"><font style="color: red;">*</font></span>
					</label>

					<form:input type="text" id="firstName" path="firstName"
						placeholder="First Name" class="form-control element-block" />
					<form:errors path="firstName" cssClass="error" style="color: red" />
					<div id="firstNameErr" style="color: red"></div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Last Name
						<span class="required"><font style="color: red;">*</font></span>
					</label>
					<form:input type="text" id="lastName" path="lastName"
						placeholder="Last Name" class="form-control element-block" />

					<form:errors path="lastName" cssClass="error" style="color: red" />
					<div id="lastNameErr" style="color: red"></div>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Email
						Address <span class="required"><font style="color: red;">*</font></span>
					</label>
					<form:input type="text" id="emailAddress" path="emailAddress"
						onchange="emailcheck()" placeholder="Email Address"
						class="form-control element-block" />

					<form:errors path="emailAddress" cssClass="error"
						style="color: red" />
					<div id="emailIdInputErr" style="color: red"></div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Confirm
						Email Address<span class="required"><font
							style="color: red;">*</font></span>
					</label>
					<form:input type="text" id="secondaryEmail"
						path="conformEmailAddress" placeholder="Confirm Email Address"
						class="form-control element-block" />

					<form:errors path="emailAddress" cssClass="error"
						style="color: red" />
					<div id="secondaryEmailErr" style="color: red"></div>
				</div>
			</div>
		</div>
		
		<div class="row">

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Password
						<span class="required"><font style="color: red;">*</font></span>
					</label>
					<form:input type="password" id="password" path="password"
						placeholder="Password" class="form-control element-block"
						maxlength="8" />
					<form:errors path="password" cssClass="error" id="passwordId"  style="color: red"></form:errors>


				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Confirm
						Password <span class="required"><font style="color: red;">*</font></span>
					</label>
					<form:input type="password" id="conformPassword" path="conformPassword"
						placeholder="Conform Password" class="form-control element-block"
						maxlength="8" />
					<form:errors path="conformPassword" cssClass="error"
							id="conformPassword"  style="color: red"></form:errors>

				</div>
			</div>
		</div>
		<div class="row">

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Company
						WebSite<span class="required"><font style="color: red;">*</font></span>
					</label>
					<form:input type="text" id="companyWebSite" path="companyWebSite"
						onchange="website()" placeholder="Company WebSite"
						class="form-control element-block" />

					<form:errors path="companyWebSite" cssClass="error"
						style="color: red" />
					<div id="websiteErr" style="color: red"></div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="form-group">
					<label> Company	Type <span class="required"><font class="f-color">*</font></span></label>
					
					<form:select type="text"
								class="element-block fw-normal font-lato" id="companyType"
								path="companyType" style="height: 42px;">
								<form:option value="">Select Company Type</form:option>
								<form:options items="${companyTypeList}" />
							</form:select>
							<form:errors path="companyType" style="color: red" />
				</div>
			</div>					
		 </div>
		
		<div class="row">

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Contact
						Number <span class="required"><font style="color: red;">*</font></span>
					</label>
					<form:input type="text" id="contectNumberId" path="contectNumber"
						placeholder="Contact Number" class="form-control element-block"
						maxlength="10" />

					<form:errors path="contectNumber" cssClass="error"
						style="color: red" />
					<div id="mobileerror" style="color: red"></div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Address<span
						class="required"><font style="color: red;">*</font></span>
					</label>
					<form:input type="text" id="address" path="address"
						placeholder="Address" class="form-control element-block" />
					<form:errors path="address" cssClass="error"
						style="color: red" />
					<div id="addressErr" style="color: red"></div>	
				</div>
			</div>
		</div>
		
		<div class="row">
			
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="form-group">
							<label>Industry Type <span class="required"><font
									class="f-color">*</font></span></label>
							<form:select type="text"
								class="element-block fw-normal font-lato" id="functionInput"
								path="industry" style="height: 42px;">
								<form:option value="">Select Industry Type</form:option>
								<form:options items="${industryList}" />
							</form:select>
							<form:errors path="industry" style="color: red" />

						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label>Company Logo</label> <font style="color: red;">* </font> <input
							type="file" name="companyLogos" path="companyLogo"
							id="PhotoInput" placeholder="Photo" onchange="fileCheck(this)" />
						<div class="color-black-mute">
							<small>Logo jpeg,jpg,png Formats Only</small>
						</div>
						
					</div>
				</div>
			
		
		<div class="text-center contact-form">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold">Submit
			</button>
		</div>
		</div>
		

	</form:form>

</section>
</main>