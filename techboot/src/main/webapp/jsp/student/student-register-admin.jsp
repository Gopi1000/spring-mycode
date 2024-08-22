<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<!-- <script type="text/javascript">
function Validate() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    if (password != confirmPassword) {
        alert("Please enter same password in both");
        return false;
    }
    return true;
}
</script> -->

<script type="text/javascript">
function Check() {
    var email = document.getElementById("emailIdInput").value;
    var confirmemail = document.getElementById("confirmemailAddress").value;
    if ( email != confirmemail) {
        alert("Please enter same Email Adress in both fields");
        return false;
    }else{
    	var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        if (password != confirmPassword) {
            alert("Please enter same password in both");
            return false;
        }else{
   			 return true;
    }
}
</script>



<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			var courName = $("#functionInput").val();
			if (courName == "Select Course") {
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
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			var GenderName = $("#functionInput").val();
			if (GenderName == "Select Gender") {
				
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
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			var language = $("#functionInput").val();
			if (language == "Select Language Type") {
				
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
		$('#btnSubmit').click(function(e) {
			var isValid = true;

			$('form#createstudent input[type="text"]').each(function() {
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
 <!--   <script>
	$(document)
			.ready(
					function() {
						$('#submit')
								.click(
										function(e) {
											var isValid = true;
											/* $('input[type="text"]')
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
 */
											// Cheracter Validations 
											/* var companyPersonName = $(
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
											} */

											var firstname = $("#firstName")
													.val();
											if(firstname==''){
											  $("#firstName").css({"border" : "1px solid red",});
											   isValid=false;
											}
											
											else if (!/^[a-zA-Z\s]*$/g
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
											function mobilecheck() {
												var mobileNo = $("#mobileNoInput").val()
												$.ajax({
													type : "GET",
													url : 'mobileNoVerification',
													data : 'mobileNo=' + mobileNo,
													success : function(response) {
														if(response=="MOBILENO"){
										    				$("#mobileNoErr").show();
										    				$("#mobileNoErr").html("Mobile number already exist's")
										    				isStatus = false;
										    			}else{
										    				isStatus = true;
										    				$("#mobileNoErr").hide();
															$("#mobileNoErr").css({
																"border" : "",
																"background" : ""
															});

											var mobile = $("#mobileNoInput")
													.val();
											if (!/^[0-9]{1,11}$/.test(mobile)) {
												var isValid = false;
												$("#mobileNoErr").show();
												$("#mobileNoErr")
														.html(
																"Please Enter Numbers Only");
											} else if (mobile.length < 10) {
												var isValid = false;
												$("#mobileNoErr").show();
												$("#mobileNoErr")
														.html(
																"Must enter 10 Digits Numbers");
											} else {
												var isValid = true;
											}
											if (isValid == false)
												e.preventDefault();

										});
					});
	
</script>  -->
<script type="text/javascript">


	function emailcheck() {
		var emailAddress = $("#emailIdInput").val()
		$.ajax({
			type : "GET",
			url : 'emailAddressVerification',
			data : 'emailAddress=' + emailAddress,
			success : function(response) {
				if(response=="EMAILADDRESS"){
    				$("#emailIdInputErr").show();
    				$("#emailIdInputErr").html("EmailAddress already exist's")
    				isStatus = false;
    			}else{
    				isStatus = true;
    				$("#emailIdInputErr").hide();
					$("#emailIdInputErr").css({
						"border" : "",
						"background" : ""
					});
					 //email validations
   		         var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
   					var emailAddress = $("#emailIdInput").val();
   					if (!emailValidations.test(emailAddress)) {
   						$("#emailIdInputErr").show();
   						$("#emailIdInputErr").html("Please Enter Valid Email ");
   						var isValid = false;
   					}else {
   						$("#emailIdInputErr").hide();
   						$("#emailIdInputErr").css({
   							"border" : "",
   							"background" : ""
   						});
   					}
					
					
    			}
    		},
    	});
    }
	</script>
	
	<!-- function website() {
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
</script> -->

<main id="main"> <!-- <header
	class="heading-banner text-white bgCover"
	style="background-image: url(resource/images/img23.jpg);">
	<div class="container holder">
		<div class="align">
			<h1>Partners</h1>
		</div>
	</div>
</header> -->
<!-- <nav class="breadcrumb-nav">
	<div class="container">
		breadcrumb
		<ol class="breadcrumb">
			<li><a href="home.html">Home</a></li>
			<li class="active">Registration</li>
		</ol>
	</div>
</nav> -->
<!-- <br> -->
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post"  
		modelAttribute="studentRegisterBO" commandName="studentRegisterBO" id="createstudent"
		class="contact-form top" enctype="multipart/form-data">

		<c:if test="${not empty errorMessage}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>

		</c:if>

		<div class="contact-form">
			<h3 class="text-center">Create Student</h3>
		</div>
		<!-- <div class="bg-gray" style="border: 1px solid #e6e6e6;">
 -->
 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="border: 1px solid #e6e6e6;">
						<br>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> First
							Name <span class="required"> <font style="color: red;">*
							</font></span>
						</label>
						<form:input type="text" id="firstName" path="firstName"
							 placeholder="First Name"
							class="form-control element-block" />
						<form:errors path="firstName" cssClass="error" style="color: red" />
						<div id="firstNameErr" style="color: red"></div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Last
							Name <span class="required"><font style="color: red;">*</font></span>
						</label>
						<form:input type="text" id="lastName" path="lastName"
							placeholder="Last Name " class="form-control element-block" />

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

						<form:input type="text" id="emailIdInput" path="emailAddress" onchange="emailcheck()"
							placeholder="Email Address" class="form-control element-block" />
						<form:errors path="emailAddress" cssClass="error"
							style="color: red" />
						<div id="emailIdInputErr" style="color: red"></div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Confirm
							Email Address <span class="required"><font
								style="color: red;">*</font></span>
						</label>
						<form:input type="text" id="confirmemailAddress"
							path="confirmemailAddress" placeholder="Confirm Email Address"
							class="form-control element-block" />

						<form:errors path="confirmemailAddress" cssClass="error"
							style="color: red" />
						<div id="confirmemailAddressErr" style="color: red"></div>
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
							maxlength="10" />

						<form:errors path="password" cssClass="error" style="color: red" />
						<div id="passwordErr" style="color: red"></div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Confirm
							Password <span class="required"><font style="color: red;">*</font></span>
						</label>
						<form:input type="password" id="confirmPassword"
							path="confirmPassword" placeholder="Confirm Password"
							class="form-control element-block" maxlength="10" />

						<form:errors path="confirmPassword" cssClass="error"
							style="color: red" />
						<div id="confirmPasswordErr" style="color: red"></div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Mobile
							Number<span class="required"><font style="color: red;">*</font></span>
						</label>
						<form:input type="text" id="mobileNoInput" path="mobileNo" onchange="mobilecheck()"
							placeholder="Mobile Number" class="form-control element-block"
							maxlength="10" />

						<form:errors path="mobileNo" cssClass="error" style="color: red" />
						<div id="mobileNoErr" style="color: red"></div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Whatspp
							Number<span class="required"><font style="color: red;">*</font></span>
						</label>
						<form:input type="text" id="whatsAppNo" path="whatsAppNo"
							placeholder="Whatsapp Number" class="form-control element-block"
							maxlength="10" />

					</div>
				</div>
			</div>

			<div class="row">

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label>Course <span class="required"><font
								class="f-color">*</font></span></label>
						<form:select type="text" class="element-block fw-normal font-lato"
							id="functionInput" path="courseBO.courseName"
							style="height: 42px;">
							<form:option value="Select Course">Select Course </form:option>
							<form:options items="${courselist}" itemLabel="courseName"
								itemValue="courseId" />
						</form:select>


					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Language
							<span class="required"><font style="color: red;">*</font></span>
						</label>
						<%-- <form:input type="text" id="language" path="language"
							placeholder="Language" class="form-control element-block" /> --%>
						<form:select type="text" class="element-block fw-normal font-lato"
							id="functionInput" path="language" style="height: 42px;">
							<form:option value="Select Language Type">Select Language </form:option>
							<form:options items="${languageList}" />
						</form:select>

					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Gender <span
							class="required"><font style="color: red;">*</font></span>
						</label>
						<form:select type="text" class="element-block fw-normal font-lato"
							id="functionInput" path="gender" style="height: 42px;">
							<form:option value="Select Gender">Select Gender </form:option>
							<form:options items="${genderList}" />
						</form:select>

					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Marital
							Status<span class="required"><font style="color: red;">*</font></span>
						</label>

						<form:select type="text" class="element-block fw-normal font-lato"
							id="functionInput" path="maritalStatus" style="height: 42px;">
							<form:option value="Select MaritalStatus Type">Select MaritalStatus </form:option>
							<form:options items="${maritalList}" />
						</form:select>

						<form:errors path="maritalStatus" cssClass="error"
							style="color: red" />
						<div id="maritalStatusErr" style="color: red"></div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> State <span
							class="required"><font style="color: red;">*</font></span>
						</label>
						<%-- <form:input type="text" id="state" path="state"
							placeholder="State" class="form-control element-block" /> --%>

						<form:select type="text" class="element-block fw-normal font-lato"
							id="functionInput" path="state" style="height: 42px;">
							<form:option value="Select State Type">Select State </form:option>
							<form:options items="${StateList}" />
						</form:select>
						<form:errors path="state" cssClass="error" style="color: red" />
						<div id="stateErr" style="color: red"></div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">Address<span
							class="required"><font style="color: red;">*</font></span>
						</label>
						<form:input type="text" id="address" path="address"
							placeholder="Address " class="form-control element-block" />
					</div>
				</div>
			</div>

			<div class="row">

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label>City <span class="required"><font
								class="f-color">*</font></span></label>
						<form:select type="text" class="element-block fw-normal font-lato"
							id="functionInput" path="city" style="height: 42px;">
							<form:option value="Select city Type">Select City </form:option>
							<form:options items="${cityList}" />
						</form:select>
					</div>

				</div>
			</div>
			</div>

			<!-- <div class="text-center contact-form">
				<button type="submit" id="submit"
					class="btn btn-theme btn-warning text-uppercase font-lato fw-bold">Submit
				</button>
			</div> -->
			<div class="text-right">
			<button type="submit" id="btnSubmit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"   onclick="return Check()"
				Style="min-width: 100px">Submit</button>
		</div>
	</form:form>

</section>
</main>