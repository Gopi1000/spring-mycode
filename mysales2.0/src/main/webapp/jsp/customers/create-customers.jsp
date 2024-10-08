<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
	$(function() {
		$("#startDateInput").datepicker({
			changeMonth : true,
			changeYear : true,
			numberOfMonths : 1,
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate());
				$("#endDateInput").datepicker("option", "minDate", dt);
			}
		});
		$("#endDateInput").datepicker({
			changeMonth : true,
			changeYear : true,
			numberOfMonths : 1,
			onSelect : function(selected) {
				var dt = new Date(selected);
				var dt2 = new Date(selected);
				dt.setDate(dt.getDate() - 1);
				dt2.setDate(dt.getDate() + 1);
			}
		});

	});
</script>

<script>
		$(document)
				.ready(
						function() {
							$('#btnSubmit')
									.click(
											function(e) {

												var isValid = true;
												var firstname = $(
														"#firstNameInput")
														.val();
												if (firstname == '') {

													$("#firstNameInput")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													isValid = false;
												} else if (!/^[a-zA-Z\s]*$/g
														.test(firstname)) {
													$("#firstNameErr").show();
													$("#firstNameErr")
															(
																	"Please Enter Character Only");
													isValid = false;
												} else {
													$("#firstNameErr").hide();
													$("#firstNameInput").css({
														"border" : "",
														"background" : ""
													});
												}

												var isValid = true;
												var lastname = $(
														"#lastNameInput")
														.val();
												if (firstname == '') {

													$("#lastNameInput")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													isValid = false;
												} else if (!/^[a-zA-Z\s]*$/g
														.test(lastname)) {
													$("#lastNameErr").show();
													$("#lastNameErr")
															(
																	"Please Enter Character Only");
													isValid = false;
												} else {
													$("#lastNameErr").hide();
													$("#lastNameInput").css({
														"border" : "",
														"background" : ""
													});
												}

												// Email Validations 			
												var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
												var emailAddress = $(
														"#emailIdInput").val();
												if (emailAddress == '') {

													isValid = false;
													$("#emailIdInput").css({
														"border" : "1px solid red",
													});
												} else if (!emailValidations
														.test(emailAddress)) {
													$("#input_error").show();
													$("#input_error")
															.html(
																	"Please Enter Valid Email ");
													isValid = false;
												} else {
													$("#input_error").hide();
													$("#emailIdInput").css({
														"border" : "",
														"background" : ""
													});
												}

												var siteRegex = /(http(s)?:\\)?([\w-]+\.)+[\w-]+[.com|.in|.org]+(\[\?%&=]*)?/

													var companySite = $(
															"#companyWebsiteInput")
															.val();

													if (companySite == '') {

														$("#companyWebsiteInput").css({
															"border" : "1px solid red",
														});
														isValid = false;
													} else if (!siteRegex
															.test(companySite)) {

														$("#websiteErr").show();
														$("#websiteErr")
																.html(
																		"Please Enter Valid WebSite ");
														isValid = false;
													} else {

														$("#websiteErr").hide();
														$("#companyWebsiteInput").css({
															"border" : "",
															"background" : ""
														});
													}

													//Company Name validations 				

													var companyName = $(
															"#companyNameId").val();

													if (companyName == '') {

														isValid = false;

														$("#companyNameId").css({
															"border" : "1px solid red",
														});
													} else {
														$("#companyNameId").css({
															"border" : "",
															"background" : ""
														});
													}

													var industryType = $(
															"#IndustryTypeId").val();

													if (industryType == '') {

														isValid = false;

														$("#IndustryTypeId").css({
															"border" : "1px solid red",
														});
													} else {
														$("#IndustryTypeId").css({
															"border" : "",
															"background" : ""
														});
													}

													// Phone Number Validations						
													/* var phone = $("#contactId").val();
													if (phone == '') {

														isValid = false;
														$("#contactId").css({
															"border" : "1px solid red",
														});
													} else if (!/^[0-9]{1,10}$/
															.test(phone)) {
														isValid = false;
														$("#contacterror").show();
														$("#contacterror")
																.html(
																		"Please Enter Numbers Only");
													} else if (phone.length != 10) {
														isValid = false;
														$("#contacterror").show();
														$("#contacterror")
																.html(
																		"Must enter 10 Digits Numbers");
													} else {
														$("#contacterror").hide();
														$("#contactId").css({
															"border" : "",
															"background" : ""
														});
													}

													var mobile = $("#mobileId").val();
													if (mobile == '') {

														isValid = false;
														$("#mobileId").css({
															"border" : "1px solid red",
														});
													} else if (!/^[0-9]{1,10}$/
															.test(mobile)) {
														isValid = false;
														$("#mobileerror").show();
														$("#mobileerror")
																.html(
																		"Please Enter Numbers Only");
													} else if (mobile.length != 10) {
														isValid = false;
														$("#mobileerror").show();
														$("#mobileerror")
																.html(
																		"Must enter 10 Digits Numbers");
													} else {
														$("#mobileerror").hide();
														$("#mobileId").css({
															"border" : "",
															"background" : ""
														}); */
													}
											if (isValid == false)
												e.preventDefault();
										});
					});
</script>
<div class="contact-form-wrapper">

	<div class="box-list">
		<div class="item">
			<div class="row ">
				<div class="text-center underline">
					<h3>Create Customer</h3>

				</div>
				<br>
				<!-- form post a job -->
				<form:form id="myForm" method="post" class="login-form clearfix"
					commandName="createProfile" modelAttribute="createProfile"
					action="create-customers">
					<!-- Start Warning Message  -->
					<center>
						<div class="warning" style="width: 100%; text-align: left;">
							<c:if test="${not empty infoMessage}">
								<div class="alert alert-info">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Info!</strong>
									<c:out value="${infoMessage}"></c:out>
								</div>
							</c:if>
						</div>
					</center>
					<!-- End Warning Message  -->
					<div id="updateExperience1">
						<div class="form-group">
							<div class="row clearfix">


								<div class="col-sm-3">
									<div class="form-group">
										<label>Assign Employee<span class="font10 text-danger">*</span></label>
										<form:select type="text" path="userName"
											class="form-control required">
											<%-- <form:option value="${usrId}">${userName}</form:option> --%>
											<form:option value="Select">-- Select --   </form:option>
											<form:options items="${userBOList}" itemLabel="name"
												itemValue="id" />

										</form:select>

									</div>
								</div>


								<div class="col-xs-3" style="display: block" id="addCompany">
									<label>First Name</label><font style="color: red;"> * </font>
									<form:input type="text" class="form-control " path="firstName"
										id="firstNameInput" placeholder="First Name" maxlength="100" />
									<form:errors path="firstName" cssClass="error" />
									<div id="firstNameErr" style="color: red;"></div>
								</div>

								<div class="col-xs-3">
									<label>Last Name</label><font style="color: red;"> * </font>
									<form:input type="text" class="form-control " path="lastName"
										id="lastNameInput" placeholder="Last Name" maxlength="100" />
									<form:errors path="lastName" cssClass="error" />
									<div id="lastNameErr" style="color: red;"></div>
								</div>
								<div class="col-xs-3">
									<label>Email </label><font style="color: red;"> * </font>
									<form:input type="email" class="form-control "
										path="emailAddress" id="emailIdInput"
										placeholder="Email Address" maxlength="100" />
									<form:errors path="emailAddress" cssClass="error" />
									<div id="input_error" style="color: red"></div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="row clearfix">
								<div class="col-xs-3">
									<label>Primary Contact No</label><font style="color: red;"> * </font>
									<form:input type="text" class="form-control " path="mobileNo"
										id="mobileId" placeholder="MobileNo" />
									<form:errors path="mobileNo" cssClass="error" />
									<div id="mobileerror" style="color: red"></div>
								</div>

								<div class="col-xs-3" style="display: block" id="addCompany">
									<label>Contact No</label><font style="color: red;"> * </font>
									<form:input type="text" id="contactId" class="form-control "
										path="contactNo" placeholder="ContactNo" />
									<form:errors path="contactNo" cssClass="error" />
									<div id="contacterror" style="color: red"></div>
								</div>

								<div class="col-xs-3" style="display: block" id="addCompany">
									<label>Company</label>
									<form:input type="text" id="companyNameId"
										class="form-control " path="companyName"
										placeholder="Company Name" maxlength="100" />
									<form:errors path="companyName" cssClass="error" />
								</div>

								<div class="col-xs-3 ">
									<label>Industry</label>
									<form:input type="text" class="form-control "
										path="industryType" id="IndustryTypeId"
										placeholder="IndustryType" maxlength="100" />
									<form:errors path="industryType" cssClass="error" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="row clearfix">
								<div class="col-xs-3">
									<label>WebSite </label>
									<form:input type="text" class="form-control " path="website"
										id="companyWebsiteInput" placeholder="WebSite" maxlength="100" />
									<form:errors path="website" cssClass="error" />
									<div id="websiteErr" style="color: red"></div>
								</div>

								<div class="col-xs-3">
									<label>Address</label><font style="color: red;"> * </font>
									<form:input type="text" class="form-control " path="address"
										id="mobileId" placeholder="Address" maxlength="100" />
									<form:errors path="address" cssClass="error" />
									<div id="mobileerror" style="color: red"></div>


								</div>


							</div>
						</div>
					</div>
					<div style="text-align: right; margin-right: 31px">
							<button type="submit" id="btnSubmit"
								class="btn btn-t-primary btn-theme lebal_align mt-20">Submit</button>
							<a href=view-leads?page=1"><span
								class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
						</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
