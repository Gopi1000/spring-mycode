<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- <script>
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
			// Email Validations 			
			var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var emailAddress = $("#emailAddress").val();
			if (!emailValidations.test(emailAddress)) {
				$("#emailAddressErr").show();
				$("#emailAddressErr").html("Please Enter Valid Email ");
				var isValid = false;
			}

			else {
				$("#emailAddressErr").hide();
				$("#emailAddressErr").css({
					"border" : "",
					"background" : ""
				});
			}

			var mobile = $("#mobileNumber").val();
			if (!/^[0-9]{1,11}$/.test(mobile)) {
				var isValid = false;
				$("#mobileNumberErr").show();
				$("#mobileNumberErr").html("Please Enter Numbers Only");
			} else if (mobile.length < 10) {
				var isValid = false;
				$("#mobileNumberErr").show();
				$("#mobileNumberErr").html("Must enter 10 Digits Numbers");
			} else {
				var isValid = true;
			}
			if (isValid == false)
				e.preventDefault();

		});
	});
</script> -->
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
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
				if(isStatus == false){
					   alert("Please fill required field's");
					   isValid = false;
				   }
			});

			if (isValid == false)
				e.preventDefault();

		});
	
$(document).ready(function(){
	var isStatus = true;
});
	function emailcheck() {
		var emailAddress = $("#emailAddress").val();
		var courseId = $("#courseId").val();
		$.ajax({
			type : "GET",
			url : 'EmailaddressVerificationss',
			data : 'emailAddress=' + emailAddress +
			       '&courseId=' + courseId,
			success : function(data) {
				if (data == "OK") {
					isStatus = false;
					$("#emailAddressErr").show();
					$("#emailAddressErr").html("EmailAddress already exist's");
				} else {
					$("#emailAddressErr").hide();
					$("#emailAddressErr").css({
						"border" : "",
						"background" : ""
					});
				}
			},
		});
	}


	function mobilecheck() {
		var mobileNumber = $("#mobileNumber").val();
		var courseId = $("#courseId").val();
		$.ajax({
			type : "GET",
			url : 'MobileNumberVerificationss',
			data : 'mobileNumber=' + mobileNumber+
			        '&courseId=' + courseId,
			success : function(response) {
				if (response == "OK") {
					isStatus = false;
					$("#mobileNumberErr").show();
					$("#mobileNumberErr").html("MobileNumber already exist's");
				} else {
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

<style>
.error {
	color: red;
}
</style>



<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post"
		modelAttribute="courseRegistrationBO"
		commandName="courseRegistrationBO" class="contact-form top">

        <form:hidden path="courseBO.companyBO.companyId"/>
		<form:hidden path="courseBO.courseId" id="courseId"/>


		<c:if test="${not empty errorMessage}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>
			</c:if>
			
			<c:if test="${not empty InfoMessage}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Info:</strong>
				<c:out value="${InfoMessage}"></c:out>
			</div>

		</c:if>
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center">Register Course</h3>
			</div>
			<div class="row bg-gray" style="border: 1px solid #e6e6e6;">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Course Name <span class="required"> <font
												style="color: red;">* </font></span>
										</label>
										<form:input type="text" id="" path="courseBO.courseName"
											placeholder="Course Name" class="form-control element-block"
											readonly="true" />

									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Candidate Name <span class="required"><font
												style="color: red;">*</font></span>
										</label>
										<form:input type="text" id="candidateName"
											path="candidateName" placeholder="Candidate Name "
											class="form-control element-block" />
										<form:errors path="candidateName" cssClass="error"></form:errors>
										<div id="candidateNameErr" style="color: red"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Email Address <span class="required"><font
												style="color: red;">*</font></span>
										</label>

										<form:input type="text" id="emailAddress" path="emailAddress"
											placeholder="Email Address" onchange="emailcheck()"
											class="form-control element-block" />
										<form:errors path="emailAddress" cssClass="error"></form:errors>
										<div id="emailAddressErr" style="color: red"></div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<div class="form-group">
										<label class="element-block fw-normal font-lato">
											Mobile Number <span class="required"><font
												style="color: red;">*</font></span>
										</label>
										<form:input type="text" id="mobileNumber" path="mobileNumber"
											onchange="mobilecheck()" placeholder="Mobile Number"
											class="form-control element-block" maxlength="10" />

										<form:errors path="mobileNumber" cssClass="error"></form:errors>
										<div id="mobileNumberErr" style="color: red"></div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="row">
										<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<div class="form-group">
												<label class="element-block fw-normal font-lato">
													Professional <span class="required"><font
														style="color: red;">*</font></span>
												</label>
												<form:input type="text" id="professional"
													path="professional" placeholder="Professional"
													class="form-control element-block" />

												<form:errors path="professional" cssClass="error"></form:errors>

											</div>
										</div>
										<%-- <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<div class="form-group">
												<label class="element-block fw-normal font-lato">
													Schedule <span class="required"><font
														style="color: red;">*</font></span>
												</label>
												<form:input type="text" id="scheme" path="scheme"
													placeholder="Schedule " class="form-control element-block" />
												<form:errors path="scheme" cssClass="error"></form:errors>
												<div id="candidateNameErr" style="color: red"></div>
											</div>
										</div> --%>
										 <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<div class="form-group">
										<label class="element-block fw-normal font-lato"> <span
								class="element-block">schedule<span class="required"><font
										class="f-color">*</font></span></span> <form:select path="scheme"
									id="categoryId"  style="height: 42px;">
									<form:option value="Select">-- Select --   </form:option>
									<form:option value="WeeekEnd">WeekEnd  </form:option>
									<form:option value="WeekDays">WeekDays </form:option>
								</form:select>
							</label>
										
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="text-right contact-form">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Save</button>
		</div>
  </form:form>
</section>
</main>