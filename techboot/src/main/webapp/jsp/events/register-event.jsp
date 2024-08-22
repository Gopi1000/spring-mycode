<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="req" value="${pageContext.request}"></c:set>
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" ></c:set>
<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			$('input[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					isValid = true;
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
			// Cheracter Validations 
			var companyPersonName = $("#candidateName").val();
			if (!/^[a-zA-Z\s]*$/g.test(companyPersonName)) {
				$("#candidateNameErr").show();
				$("#candidateNameErr").html("Please Enter Character Only");
				var isValid = false;
			} else {
				$("#candidateNameErr").hide();
				$("#candidateNameErr").css({
					"border" : "",
					"background" : ""
				});
			}
			//email Validations
			var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var emailAddress = $("#emailAddress").val();
			if (!emailValidations.test(emailAddress)) {
				$("#emailAddressErr").show();
				$("#emailAddressErr").html("Please Enter Valid Email ");
				var isValid = false;
			} else {
				$("#emailAddressErr").hide();
				$("#emailAddressErr").css({
					"border" : "",
					"background" : ""
				});
			}
			//moblie verifications
			var mobileNumber = $("#mobileNumber").val();
			if (!/^[0-9]{1,11}$/.test(mobileNumber)) {
				$("#mobileNumberErr").show();
				$("#mobileNumberErr").html("Please Enter Numbers Only");
				var isValid = false;
			} else if (mobileNumber.length < 10) {
				$("#mobileNumberErr").show();
				$("#mobileNumberErr").html("Must enter 10 Digits Numbers");
				var isValid = false;
			} else {
				$("#mobileNumberErr").hide();
				$("#mobileNumberErr").css({
					"border" : "",
					"background" : ""
				});
			}

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>
<script>
	function emailcheck() {
		var emailAddress = $("#emailAddress").val();
		var eventId = $("#eventsId").val();
		$.ajax({
			type : "GET",
			url : '${baseURL}/EmailaddressVerifications',
			data : 'emailAddress=' + emailAddress + '&eventId=' + eventId,
			success : function(data) {
				if (data != '') {
					emailAddress = false;
					$("#emailAddressErr").show();
					$("#emailAddressErr").html("EmailAddress already exist's")
				} else {
					emailAddress = true;
					$("#emailAddressErr").hide();
					$("#emailAddressErr").css({
						"border" : "",
						"background" : ""
					});
				}
			},
		});
	}
</script>
<script>
	function mobilecheck() {
		var mobileNumber = $("#mobileNumber").val();
		var eventId = $("#eventsId").val();
		$.ajax({
			type : "GET",
			url : '${baseURL}/MobileNumberVerifications',
			data : 'mobileNumber=' + mobileNumber + '&eventId=' + eventId,
			success : function(response) {
				if (response != '') {
					mobileNumber = false;
					$("#mobileNumberErr").show();
					$("#mobileNumberErr").html("MobileNumber already exist's")
				} else {
					mobileNumber = true;
					$("#mobileNumberErr").hide();
					$("#mobileNumberErr").css({
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
	style="background-image: url(${baseURL}/resource/images/img23.jpg);">
	<div class="container holder">
		<div class="align">
			<h1>Enroll yourself</h1>
		</div>
	</div>
</header> <!-- breadcrumb nav -->
<nav class="breadcrumb-nav">
	<div class="container">
		<!-- breadcrumb -->
		<ol class="breadcrumb">
			<li><a href="home.html">Home</a></li>
			<li class="active">Enroll yourself to Event</li>
		</ol>
	</div>
</nav>
<br>
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post" modelAttribute="eventRegisterBo"
		commandName="eventRegisterBo" class="contact-form top">
		<form:hidden path="eventBo.eventId" id="eventsId" />
		<form:hidden path="eventBo.companyBO.companyId" />

		<c:if test="${not empty errorMessage}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
		
			
				
							<div class="row">
								<div class="col-lg-10 col-md-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Events Name <span class="required"> <font
												style="color: red;">* </font></span>
										</label>
										<form:input type="text" id="eventBo.titleName"
											path="eventBo.titleName" placeholder="title Name"
											class="form-control element-block" readonly="true" />

									</div>
								</div>

								<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Candidate Name <span class="required"><font
												style="color: red;">*</font></span>
										</label>
										<form:input type="text" id="candidateName"
											path="candidateName" placeholder="Candidate Name"
											class="form-control element-block" />
										<form:errors path="candidateName" cssClass="error"
											style="color: red" />
										<div id="candidateNameErr" style="color: red"></div>
									</div>
								</div>
							</div>
						
					<div class="row">
					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
							
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Email Address <span class="required"><font
												style="color: red;">*</font></span>
										</label>

										<form:input type="text" id="emailAddress" path="emailAddress"
											placeholder="Email Address" onchange="emailcheck()"
											class="form-control element-block" />
										<form:errors path="emailAddress" cssClass="error"
											style="color: red" />
										<div id="emailAddressErr" style="color: red"></div>
									</div>							
						</div>
					
					
						
							
								<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Mobile Number <span class="required"><font
												style="color: red;">*</font></span>
										</label>
										<form:input type="text" id="mobileNumber" path="mobileNumber"
											onchange="mobilecheck()" placeholder="Mobile Number"
											class="form-control element-block" maxlength="10" />

										<form:errors path="mobileNumber" cssClass="error"
											style="color: red" />
										<div id="mobileNumberErr" style="color: red"></div>
									</div>
								</div>
							
							
								<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
											<div class="form-group">
												<label class="element-block fw-normal font-lato">
													Qualification <span class="required"><font
														style="color: red;">*</font></span>
												</label>
												<form:input type="text" id="qualification"
													path="Qualification" placeholder="Qualification"
													class="form-control element-block" />

												<form:errors path="qualification" cssClass="error"
													style="color: red" />
											</div>
										</div>
										
								<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
											<div class="form-group">
												<label class="element-block fw-normal font-lato">
													Professional <span class="required"><font
														style="color: red;">*</font></span>
												</label>
												<form:input type="text" id="professional"
													path="professional" placeholder="Professional"
													class="form-control element-block" />

												<form:errors path="professional" cssClass="error"
													style="color: red" />
											</div>
										</div>
									</div>
								
						
						
		
			<div class="text-center contact-form">
				<button type="submit" id="submit"
					class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
					Style="min-width: 100px">Save</button>
			</div>
		
	</form:form>
</section>
</main>