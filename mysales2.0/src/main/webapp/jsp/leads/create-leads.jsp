<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link href="resources/theme/css/custom.css" rel="stylesheet">
<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>



<script type="text/javascript">
	//<![CDATA[
	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'bold', 'italic', 'underline', 'ol', 'ul',
					'strikeThrough', 'html', 'image' ]
		}).panelInstance('keySkillsInput');
		new nicEditor({
			buttonList : [ 'bold', 'italic', 'underline', 'ol', 'ul',
					'strikeThrough', 'html', 'image' ]
		}).panelInstance('descriptionInput');
		new nicEditor({
			buttonList : [ 'bold', 'italic', 'underline', 'ol', 'ul',
					'strikeThrough', 'html', 'image' ]
		}).panelInstance('descriptionInput');
	});

	//]]>
</script>



<script>
	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline', 'ol',
					'ul', 'strikeThrough', 'html' ]
		}).panelInstance('inputAddress');
	});
</script>

<div class="row scrollspy-sidenav pb-20 body-mt-15">
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

												/* var isValid = true;
												var lastname = $(
														"#lastNameInput").val();
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
													$("#emailIdInput")
															.css(
																	{
																		"border" : "1px solid red",
																	});
												} else if (!emailValidations
														.test(emailAddress)) {
													$("#input_error").show();
													$("#input_error")
															("Please Enter Valid Email ");
													isValid = false;
												} else {
													$("#input_error").hide();
													$("#input_error").css({
														"border" : "",
														"background" : ""
													});
												}
												// Phone Number Validations						
												var phone = $("#contactId")
														.val();
												if (phone == '') {

													isValid = false;
													$("#contactId")
															.css(
																	{
																		"border" : "1px solid red",
																	});
												} else if (!/^[0-9]{1,10}$/
														.test(phone)) {
													isValid = false;
													$("#contacterror").show();
													$("#contacterror")
															(
																	"Please Enter Numbers Only");
												} else if (phone.length != 10) {
													isValid = false;
													$("#contacterror").show();
													$("#contacterror")
															(
																	"Must enter 10 Digits Numbers");
												} else {
													$("#contacterror").hide();
													$("#phoneInput").css({
														"border" : "",
														"background" : ""
													});
												}

												var mobile = $("#mobileId")
														.val();
												if (mobile == '') {

													isValid = false;
													$("#mobileId")
															.css(
																	{
																		"border" : "1px solid red",
																	});
												} else if (!/^[0-9]{1,10}$/
														.test(mobile)) {
													isValid = false;
													$("#mobileerror").show();
													$("#mobileerror")
															(
																	"Please Enter Numbers Only");
												} else if (mobile.length != 10) {
													isValid = false;
													$("#mobileerror").show();
													$("#mobileerror")
															(
																	"Must enter 10 Digits Numbers");
												} else {
													$("#mobileerror").hide();
													$("#phoneInput").css({
														"border" : "",
														"background" : ""
													});
												}

												var siteRegex = /(http(s)?:\\)?([\w-]+\.)+[\w-]+[.com|.in|.org]+(\[\?%&=]*)?/

												var companySite = $(
														"#companyWebsiteInput")
														.val();

												if (companySite == '') {

													/* $("#companyWebsiteInput")
															.css(
																	{
																		"border" : "1px solid red",
																	}); 
													isValid = false;
												} else if (!siteRegex
														.test(companySite)) {

													$("#input_error").show();
													$("#input_error")
															(
																	"Please Enter Valid WebSite ");
													isValid = false;
												} else {

													$("#input_error").hide();
													$("#companyWebsiteInput")
															.css(
																	{
																		"border" : "",
																		"background" : ""
																	});
												} */

												if (isValid == false)
													e.preventDefault();

											});
						});
	</script>
	<script>
		$(document).ready(function() {
			$('#btnsubmit').click(function(e) {
				var isValid = true;
				$('input[type="text"].required').each(function() {
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

					if (isValid == false)
						e.preventDefault();

				});
			});
		});

		$(document).ready(function() {
			$('#btnsubmit').click(function(e) {
				var isValid1 = true;
				$('input[id="password"].required').each(function() {
					if ($.trim($(this).val()) == '') {
						isValid1 = false;
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
				if (isValid1 == false)
					e.preventDefault();

			});
		});
	</script>







	<div class="warning">

		<c:if test="${not empty successMessage}">
			<div class="alert alert-info" role="alert"
				style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>success!</strong>
				<c:out value="${successMessage}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-info" role="alert"
				style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Info!</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
	</div>

	<div class="contact-form-wrapper">

		<div class="box-list">
			<div class="item">
				<div class="row ">

					<div class="text-center underline">
						<h3>Create Leads</h3>
					</div>
					<br>

					<form:form method="POST" id="addForm" action="create-leads"
						modelAttribute="leadsBO">
						<div class="col-sm-12">
							<div class="col-sm-3">
								<div class="form-group">
									<label>Campaign Name</label>
									<form:select type="text" path="campaignBO.campaignName"
										class="form-control ">
										<form:option value="">-- Select --   </form:option>
										<form:options items="${listcampaign}" itemLabel="campaignName"
											itemValue="campaignId" />
									</form:select>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label> First Name <span class="font10 text-danger">*</span></label>
									<form:input id="firstNameInput" type="text" path="firstName"
										class="form-control required" placeholder="First Name"
										maxlength="150" />
									<form:errors path="firstName" cssClass="error" />
									<div id="firstNameErr" style="color: red;"></div>
								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<label>Last Name </label>
									<form:input type="text" path="lastName"
										class="form-control " placeholder="Last Name"
										id="lastNameInput" />
									<form:errors path="lastName" class="input_error" />
									<div id="lastNameErr" style="color: red;"></div>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label>Email Address </label>
									<form:input type="text" path="emailAddress" id="emailIdInput"
										class="form-control" placeholder="Email Address" />
									<form:errors path="emailAddress" cssClass="error" />
									<div id="input_error" style="color: red;"></div>
								</div>
							</div>

						</div>

						<div class="col-sm-12">
							<div class="col-sm-3">
								<div class="form-group">
									<label>Primary Contact No<span class="font10 text-danger">*</label>
									<form:input type="text" path="contactNo"
										class="form-control" placeholder="Contact No"
										 id="contactId" />
									<form:errors path="contactNo" cssClass="error" />
									<div id="contacterror" style="color: red;"></div>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label> Contact No <span class="font10 text-danger">*</label>
									<form:input id="mobileId" type="text" path="mobileNo"
										class="form-control" placeholder="Mobile Number"
										 />
									<form:errors path="mobileNo" class="input_error" />
									<div id="mobileerror" style="color: red;"></div>
								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<label>Company Name</label>
									<form:input path="companyName" class="form-control "
										placeholder="Company Name" />
									<%-- <form:errors path="companyName" class="input_error" /> --%>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label>Industry</label>
									<form:input path="industryType" class="form-control "
										placeholder="Industry" />
									<%-- <form:errors path="industryType" class="input_error" /> --%>
								</div>
							</div>


						</div>

						<div class="col-sm-12">
							<div class="col-sm-3">
								<div class="form-group">
									<label>Website</label>
									<form:input path="website" class="form-control "
										id="companyWebsiteInput" placeholder="Website" />
									<form:errors path="website" cssClass="error" />
									<div id="input_error" style="color: red;"></div>
									<%-- <form:errors path="website" class="input_error" /> --%>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label>Address</label>
									<form:input type="text" path="address"
										class="form-control" placeholder="Address" />
									<form:errors path="address" class="input_error" />
								</div>
							</div>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
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
								</div></sec:authorize>
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
</div>


