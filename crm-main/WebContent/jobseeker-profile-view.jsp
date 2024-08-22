<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
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
											var firstname = $("#firstNameInput")
													.val();
											if (firstname == '') {

												$("#firstNameInput").css({
													"border" : "1px solid red",
												});
												isValid = false;
											} else if (!/^[a-zA-Z\s]*$/g
													.test(firstname)) {
												$("#firstNameErr").show();
												$("#firstNameErr")
														.html(
																"Please Enter Character Only");
												isValid = false;
											} else {
												$("#firstNameErr").hide();
												$("#firstNameInput").css({
													"border" : "",
													"background" : ""
												});
											}

											var lastname = $("#lastNameInput")
													.val();
											if (lastname == '') {

												isValid = false;

												$("#lastNameInput").css({
													"border" : "1px solid red",
												});
											} else if (!/^[a-zA-Z\s]*$/g
													.test(lastname)) {
												$("#lastNameErr").show();
												$("#lastNameErr")
														.html(
																"Please Enter Character Only");
												isValid = false;
											} else {
												$("#lastNameErr").hide();
												$("#lastNameInput").css({
													"border" : "",
													"background" : ""
												});
											}

											var password = $("#password").val();

											if (password == '') {

												isValid = false;

												$("#password").css({
													"border" : "1px solid red",
												});
											} else {
												$("#password").css({
													"border" : "",
													"background" : ""
												});
											}

											var confirmPassword = $(
													"#confirmPassword").val();

											if (confirmPassword == '') {

												isValid = false;

												$("#confirmPassword").css({
													"border" : "1px solid red",
												});
											} else {
												$("#confirmPassword").css({
													"border" : "",
													"background" : ""
												});
											}
											if ((password.length > 0 && confirmPassword.length > 0)) {
												if (password != confirmPassword) {

													isValid = false;

													$("#password")
															.css(
																	{
																		"border" : "1px solid red",
																	});

													$("#confirmPassword")
															.css(
																	{
																		"border" : "1px solid red",
																	});

													$("#passwordErr").show();
													$("#passwordErr")
															.html(
																	"Please Enter Password and confirm password is same ");
													$("#confirmPasswordErr")
															.show();
													$("#confirmPasswordErr")
															.html(
																	"Please Enter Password and confirm password is same ");
												} else {

													$("#confirmPasswordErr")
															.hide();
													$("#passwordErr").hide();

													$("#password").css({
														"border" : "",
														"background" : ""
													});

													$("#confirmPassword").css({
														"border" : "",
														"background" : ""
													});
												}

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
												$("#EmailIdErr").show();
												$("#EmailIdErr")
														.html(
																"Please Enter Valid Email ");
												isValid = false;
											} else {
												$("#EmailIdErr").hide();
												$("#EmailIdErr").css({
													"border" : "",
													"background" : ""
												});
											}

											// ConfirmEmail Validations 			
											var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
											var confirmEmailAddress = $(
													"#confirmemailId").val();
											if (confirmEmailAddress == '') {

												isValid = false;
												$("#confirmemailId").css({
													"border" : "1px solid red",
												});

											} else if (!emailValidations
													.test(confirmEmailAddress)) {

												$("#confirmemailIdErr").show();
												$("#confirmemailIdErr")
														.html(
																"Please Enter Valid Confirm Email ");
												isValid = false;
											} else {
												$("#confirmemailIdErr").hide();
												$("#confirmemailId").css({
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
											var phone = $("#contactId").val();
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
												$("#phoneInput").css({
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
												$("#phoneInput").css({
													"border" : "",
													"background" : ""
												});
											}

											var nicInstance = nicEditors
													.findEditor('keySkillsInput');
											var keyskill = nicInstance
													.getContent();

											var keyskillsValue = keyskill
													.replace('<br>', ' ');

											if (keyskillsValue == ' ') {

												isValid = false;
												$("#errorSkill")
														.html(
																"Please enter the Address field");
											} else {
												//	isValid = true;
												$("#errorSkill").hide();
											}

											var status = $("#statusInput")
													.val();

											if (status == '') {

												isValid = false;

												$("#statusInput").css({
													"border" : "1px solid red",
												});
											} else {
												$("#statusInput").css({
													"border" : "",
													"background" : ""
												});
											}

											if (isValid == false)
												e.preventDefault();

										});
					});
</script>
<div class="box-list" style="margin-bottom: 3%; margin-top: 3%;">

	<div class="item">
		<div class="row ">
			<h4 class="text-center no-margin titleunderline"
				style="margin-top: -10px;">My Clients</h4>



			<c:if test="${empty reportSearch}">
				<!--Start form search -->
				<form:form id="myForm" method="post" class="login-form clearfix"
					action="profile-view.html" commandName="searchjobseeker">
					<div class="row">
						<div
							class=" col-md-4 fs-search fsl-mobile-search fs-mobile-skills">
							<div class="form-group home-left">
								<label class="hidden-xs">&nbsp;</label>
								<form:input type="ntext" class="form-control" path="companyName"
									placeholder="Company Name" escapeXml="false"
									style="height: 35px;font-weight: 700;"></form:input>
							</div>
						</div>
						<input type="hidden" value="true" name="search" />
						<div class=" col-md-4 fs-mobile-search" style="padding: 0px">
							<div class="form-group find-field-space">
								<label class="hidden-xs">&nbsp;</label>
								<form:input type="ntext" class="form-control"
									path="emailAddress" placeholder="Email Address"
									style="height:35px;font-weight: 700; border-left:0;border-right:0;"></form:input>

							</div>
						</div>

						<div class=" col-md-3 fs-mobile-search" style="padding: 0px">
							<div class="form-group find-field-space">
								<label class="hidden-xs">&nbsp;</label>
								<form:input type="ntext" class="form-control" path="mobileNo"
									placeholder="Mobile Number" maxlength="10"
									style="height:35px;font-weight: 700;"></form:input>
							</div>
						</div>
						<%-- <div class=" col-md-2 fs-mobile-search" style="padding: 0px">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="location"
								placeholder="Location" style="height:35px;font-weight: 700;"></form:input>
						</div>
					</div> --%>
						<div
							class=" col-md-1 fsl-search fsl-mobile-search  fs-mobile-btnsearch"
							style="padding-bottom: 0px;">
							<div class="form-group home-right">
								<label class="hidden-xs">&nbsp;</label>
								<button class="btn btn-theme btn-success btn-block"
									style="padding: 6px 5px; background-color: #7cb228; border-color: #7cb228;">
									<small><i class="fa fa-search" aria-hidden="true"
										style="font-size: 20px;"></i></small>
								</button>
							</div>
						</div>

						<p class="text-right" style="padding-right: 68px;">
							<a href="#modal-advanced" data-toggle="modal"
								class="fa fa-search-plus"><span
								style="font-family: 'Montserrat', Helvetica, Arial, sans-serif; font-size: 12px;">
									Advanced Search</span></a>
						</p>
					</div>

				</form:form>
				<div class="modal fade" id="modal-advanced">
					<div class="modal-dialog popupwidth">
						<div class="modal-content">
							<form:form id="myForm" method="post" class="job_filters"
								action="profile-view.html" commandName="searchjobseeker">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Advanced Search</h4>
								</div>
								<div class="modal-body">
									<h5>Find Employer</h5>
									<div class="row">
										<div class="col-md-6" style="padding-bottom: 0px;">
											<div class="form-group">
												<label>First Name</label>
												<form:input type="ntext" class="form-control "
													path="firstName" placeholder="First Name"></form:input>
											</div>
										</div>
										<div class="col-md-6" style="padding-bottom: 0px;">
											<div class="form-group">
												<label>Email Address</label>
												<form:input type="ntext" class="form-control"
													path="emailAddress" placeholder=" Email Address"></form:input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6" style="padding-bottom: 0px;">
											<div class="form-group">
												<label>Industry Type</label>
												<form:input type="ntext" class="form-control"
													path="industryType" placeholder=" Industry Type"></form:input>
											</div>
										</div>
										<div class="col-md-6" style="padding-bottom: 0px;">
											<div class="form-group">
												<label>Company Name </label>
												<form:input type="ntext" class="form-control "
													path="companyName" placeholder=" company Name"></form:input>
											</div>
										</div>
									</div>
									<div class="row">
										<%-- <div class="col-md-6" style="padding-bottom: 0px;">
										<div class="form-group">
											<label>Key Skills </label>
											<form:input type="ntext" class="form-control  "
												path="keySkills" placeholder=" Key Skills"></form:input>
										</div>
									</div> --%>
										<div class="col-md-6" style="padding-bottom: 0px;">
											<div class="form-group">
												<label>Mobile No </label>
												<form:input type="ntext" class="form-control  "
													path="mobileNo" placeholder="Mobile No" maxlength="10"></form:input>
											</div>
										</div>
									</div>
									<%-- <div class="row">
									<div class="col-md-6" style="padding-bottom: 0px;">
										<div class="form-group">
											<label>Location</label>
											<form:input type="ntext" class="form-control  "
												path="location" placeholder=" location"></form:input>
										</div>
									</div>
								</div> --%>
								</div>
								<input type="hidden" value="advancedsearch" name="aid" />
								<input type="hidden" value="advancedsearch" name="search" />
								<div class="modal-footer">
									<button type="submit" class="btn btn-success btn-theme">Find
									</button>
									<button type="button" class="btn btn-default btn-theme"
										data-dismiss="modal">Close</button>

								</div>
							</form:form>
						</div>
					</div>
				</div>

			</c:if>


			<c:if test="${reportSearch=='report'}">
				<form:form id="myForm" method="post" class="login-form clearfix"
					action="profile-view.html" commandName="updateProfile">

					<div class="row">
						<div class=" col-md-3 fs-mobile-search" style="padding: 0px">
							<div class="form-group find-field-space">
								<label class="hidden-xs">&nbsp;</label>
								<c:if test="${userType =='admin'}">
									<form:select class="form-control" path="userName"
										style="height: 35px;font-weight: 700;text-transform: capitalize;">
										<form:option value="">Select User</form:option>
										<form:options items="${userBOList}" />
									</form:select>
								</c:if>

							</div>
						</div>

						<div class=" col-md-3 fs-mobile-search" style="padding: 0px">
							<div class="form-group find-field-space">
								<label class="hidden-xs">&nbsp;</label>
								<form:input type="ntext" class="form-control" path="starDate"
									id="startDateInput" placeholder="StartDate"
									data-date-format="yyyy/mm/dd"
									style="height:35px;font-weight: 700;"></form:input>
							</div>
						</div>

						<div class=" col-md-3 fs-mobile-search" style="padding: 0px">
							<div class="form-group find-field-space">
								<label class="hidden-xs">&nbsp;</label>
								<form:input type="ntext" class="form-control" path="endDate"
									id="endDateInput" placeholder="EndDate"
									data-date-format="dd/mm/yyyy"
									style="height:35px;font-weight: 700;"></form:input>
							</div>
						</div>

						<div class=" col-md-2 fs-mobile-search" style="padding: 0px">
							<div class="form-group find-field-space">
								<label class="hidden-xs">&nbsp;</label>
								<form:select class="form-control" path="process"
									style="height:35px;font-weight: 700;">
									<form:option value="all">All</form:option>
									<form:option value="create">Create</form:option>
									<form:option value="edit">Modified</form:option>
								</form:select>
								<%-- <form:input type="ntext" class="form-control" path="process"
										id="endDateInput" placeholder="endDate"
										data-date-format="dd/mm/yyyy"
										style="height:35px;font-weight: 700;"></form:input> --%>
							</div>
						</div>
						<input type="hidden" value="report" name="report" />
						<div
							class=" col-md-1 fsl-search fsl-mobile-search  fs-mobile-btnsearch"
							style="padding-bottom: 0px;">
							<div class="form-group home-right">
								<label class="hidden-xs">&nbsp;</label>
								<button class="btn btn-theme btn-success btn-block"
									style="padding: 6px 5px; background-color: #7cb228; border-color: #7cb228;">
									<small><i class="fa fa-search" aria-hidden="true"
										style="font-size: 20px;"></i></small>
								</button>
							</div>
						</div>

					</div>
				</form:form>

			</c:if>






			<%-- <c:if test="${!empty searchJobseeker}"> --%>
			<div class="warning" style="width: 100%; text-align: left;">
			<%-- 	<c:if test="${not empty successmessage}">
					<div class="alert alert-success" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong></strong>
						<c:out value="${successmessage}"></c:out>
					</div>
				</c:if> --%>
<c:if test="${not empty successmessage}">
					<div class="alert alert-success" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong></strong>
						<c:out value="${successmessage}"></c:out>
					</div>
				</c:if>

				<c:if test="${not empty infoMessage}">
					<div class="alert alert-info" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>Info!</strong>
						<c:out value="${infoMessage}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty errormessage}">
					<div class="alert alert-warning" role="alert">
						<strong>Info!</strong>
						<c:out value="${errormessage}"></c:out>
					</div>
				</c:if>
			</div>
			<%-- </c:if> --%>

			<c:if test="${!empty searchJobseeker}">
				<div class="row ">

					<!-- <a href="#">Create Employer</a> -->

					<div class="col-sm-12">
						<div class="pi-responsive-table-sm">
							<table
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders"
								style="width: 100%;">
								<thead style="background-color: #fff; border-color: #ccc;">
									<!-- Table row -->
									<tr>
										<th><span style="color: #3d454c;">S.No</span></th>
										<th><span style="color: #3d454c;"> Name</span></th>
										<th><span style="color: #3d454c;">Company Name</span></th>
										<th><span style="color: #3d454c;">Email Address</span></th>
										<c:if test="${empty reportSearch}">
											<th><span style="color: #3d454c;">Mobile Number</span></th>
										</c:if>

										<c:if test="${!empty reportSearch}">
											<th><span style="color: #3d454c;">Assigned</span></th>
											<th><span style="color: #3d454c;">Created Date</span></th>
											<th><span style="color: #3d454c;">Modified Date</span></th>
											<th><span style="color: #3d454c;">Status</span></th>

										</c:if>
										<th><span style="color: #3d454c;">Edit</span></th>
										<th><span style="color: #3d454c;">Delete</span></th>
										<th><span style="color: #3d454c;">view</span></th>
										<!-- <th><span style="color: #3d454c;">Send Mail</span></th>
										<c:if test="${empty reportSearch}">
											<th><span style="color: #3d454c;">Migration</span></th> -->
										</c:if>


									</tr>
									<!-- End table row -->
								</thead>
								<tbody>
									<c:forEach items="${searchJobseeker.list}" var="searchResult">
										<tr class="no-margin-top list-capitalize"
											style="line-height: 2em; font-size: 12px;">
											<td>${searchResult.sNo}</td>
											<%-- <td>${searchResult.firstName}</td> --%>
											<c:if test="${searchResult.status == 'opened'}">
												<td style="color: green;">${searchResult.firstName}</td>
											</c:if>
											<c:if test="${searchResult.status == 'Closed'}">
												<td style="color: #FA5858;">${searchResult.firstName}</td>
											</c:if>
											<c:if test="${searchResult.status=='Inprocess'}">
												<td style="color: #0000FF;">${searchResult.firstName}</td>
											</c:if>


											<td>${searchResult.companyName}</td>
											<td style="word-break: break-all;">${searchResult.emailAddress}</td>
											<c:if test="${empty reportSearch}">
												<td>${searchResult.mobileNo}</td>
											</c:if>

											<c:if test="${!empty reportSearch}">
												<td>${searchResult.assignedname}</td>
												<td>${searchResult.createdDate}</td>
												<td>${searchResult.modifiedDate}</td>
												<td>${searchResult.status}</td>
												<td><span style="margin-left: 38px;"> <a
														href="profile-view.html?id=${searchResult.id}&&update=true&&page=${paging}&&updateReport=true&&name=${firstName}&&mobileno=${mobileNo}&&industrytype=${industryType}&&mail=${emailAddress}&&company=${companyName}&&report=${reportSearch}">
															<i style="text-align: center; color: black;"
															class=" fa fa-pencil"></i>
													</a></span></td>

											</c:if>
											<c:if test="${empty reportSearch}">
												<td><span style="margin-left: 38px;"> <a
														href="profile-view.html?id=${searchResult.id}&&update=true&&page=${paging}&&name=${firstName}&&mobileno=${mobileNo}&&industrytype=${industryType}&&mail=${emailAddress}&&company=${companyName}&&report=${reportSearch}">
															<i style="text-align: center; color: black;"
															class=" fa fa-pencil"></i>
													</a></span></td>
											</c:if>
											<c:if test="${!empty reportSearch}">
												<td><span style="margin-left: 38px;"> <a
														href="delete-employer.html?id=${searchResult.id}&&deleteReport=true"
														onclick="return confirm('Are you sure you want to Delete?')">
															<i style="text-align: center; color: black;"
															class="fa fa-trash"></i>
													</a></span></td>
											</c:if>
											<c:if test="${empty reportSearch}">
												<td><span style="margin-left: 38px;"> <a
														href="delete-employer.html?id=${searchResult.id}"
														onclick="return confirm('Are you sure you want to Delete?')">
															<i style="text-align: center; color: black;"
															class="fa fa-trash"></i>
													</a></span></td>
												<%-- <td><span style="margin-left: 38px;"> <a
											</c:if>
										<td><span style="margin-left: 38px;"> <a
												href="employer-view.html?id=${searchResult.id}"> <i
													style="text-align: center; color: black;"
													class="fa fa-search"></i>
											</a></span></td> --%>
											</c:if>
											<c:if test="${empty reportSearch}">
												<td><a href="employer-view.html?id=${searchResult.id}"
													data-toggle="modal" data-target="#myModal"> <i
														style="text-align: center; color: black;"
														class="fa fa-search"></i>

												</a></td>
											</c:if>
											<c:if test="${!empty reportSearch}">
												<td><a
													href="employer-view.html?id=${searchResult.id}&&reports=true"
													data-toggle="modal" data-target="#myModal"> <i
														style="text-align: center; color: black;"
														class="fa fa-search"></i>

												</a></td>
												
											</c:if>

											<!--   <td><span style="margin-left: 38px;"> <a
													href="send-mail.html?email=${searchResult.emailAddress}&&name=${searchResult.firstName}&&id=${searchResult.id}"onclick="return confirm('Are you sure you want to send a  Mail?')">
														<i style="text-align: center; color: black;"
														class="fa fa-envelope" aria-hidden="true"></i>
												</a></span></td>
												<c:if test="${empty reportSearch}">
											
												<td><span> <a
													href="migration-sendmail.html?id=${searchResult.id}"> <input type="submit" value = "C2M"
														onclick="return confirm('Are you sure you want to Migrate?')"
														style="text-align: center; color: green; font-size: 14px;"
														class="fa fa-check-square" aria-hidden="true"/>
												</a></span></td> 
												</c:if> -->
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

						<nav style="text-align: center;">

							<ul class="pagination pagination-theme  no-margin">
								<c:if test="${searchJobseeker.currentPage gt 1}">
									<li><a
										href="profile-view.html?page=1&&report=${reportSearch}"><span><i
												class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
									<li><a
										href="profile-view.html?page=${searchJobseeker.currentPage - 1}&&name=${firstName}&&mobileno=${mobileNo}&&industrytype=${industryType}&&mail=${emailAddress}&&company=${companyName}&&report=${reportSearch}"><span><i
												class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
								</c:if>
								<c:forEach items="${searchJobseeker.noOfPages}" var="i">
									<c:choose>
										<c:when test="${searchJobseeker.currentPage == i}">
											<li class="active"><a
												style="color: #fff; background-color: #34495e">${i}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="profile-view.html?page=${i}&&name=${firstName}&&mobileno=${mobileNo}&&industrytype=${industryType}&&mail=${emailAddress}&&company=${companyName}&&report=${reportSearch}">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if
									test="${searchJobseeker.currentPage lt searchJobseeker.totalPages}">
									<li><a
										href="profile-view.html?page=${searchJobseeker.currentPage + 1}&&name=${firstName}&&mobileno=${mobileNo}&&industrytype=${industryType}&&mail=${emailAddress}&&company=${companyName}&&report=${reportSearch}"><span><i
												class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
									<li><a
										href="profile-view.html?page=${searchJobseeker.lastRecordValue}&&name=${firstName}&&mobileno=${mobileNo}&&industrytype=${industryType}&&mail=${emailAddress}&&company=${companyName}&&report=${reportSearch}"><span><i
												class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>



<!-- Start Create Employer -->



<c:if test="${type=='add'}">
	<div class="box-list" style="margin-bottom: 5%;">
		<div class="col-sm-12" style="padding-top: 2%;">
			<div class="form-group">
				<div class="row clearfix">
					<div class="col-xs-6">
						<span onclick="educationNextBt('personal');"
							style="padding-right: 75px; font-weight: 800;"
							class="btn btn-theme btn-success">Create Employer</span>

					</div>
				</div>
			</div>
		</div>

		<div class="item">
			<!-- form post a job -->
			<form:form id="myForm" method="post" class="login-form clearfix"
				commandName="createProfile" enctype="multipart/form-data"
				modelAttribute="createProfile" action="create-employer.html">
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

							<div class="col-xs-4" style="display: block" id="addCompany">
								<label>First Name</label><font style="color: red;"> * </font>
								<form:input type="text" id="firstNameInput"
									class="form-control " path="firstName" placeholder="First Name"
									maxlength="100" />
								<form:errors path="firstName" cssClass="error" />
								<div id="firstNameErr" style="color: red;"></div>
							</div>

							<div class="col-xs-4 ">
								<label>Last Name</label><font style="color: red;"> * </font>
								<form:input type="text" class="form-control " path="lastName"
									id="lastNameInput" placeholder="Last Name" maxlength="100" />
								<form:errors path="lastName" cssClass="error" />
								<div id="lastNameErr" style="color: red;"></div>
							</div>
							<div class="col-xs-4">
								<label>Email </label><font style="color: red;"> * </font>
								<form:input type="email" class="form-control "
									path="emailAddress" id="emailIdInput"
									placeholder="Email Address" maxlength="100" />
								<form:errors path="emailAddress" cssClass="error" />
								<div id="EmailIdErr" style="color: red"></div>
							</div>
						</div>
					</div>


					 <div class="form-group">
						<div class="row clearfix">

						 	<!--  <div class="col-xs-4" style="display: block" id="addCompany">
								<label>Confirm Email</label><font style="color: red;"> *
								</font>
								<form:input type="email" id="confirmemailId"
									class="form-control " path="confirmEmailAddress"
									placeholder="Confirm Email" maxlength="100" />
								<form:errors path="confirmEmailAddress" cssClass="error" />
								<div id="confirmemailIdErr" style="color: red;"></div>
							</div>

							<div class="col-xs-4 ">
								<label>Password</label><font style="color: red;"> * </font>
								<form:input type="password" class="form-control "
									path="password" id="password" placeholder="Password"
									maxlength="8" />
								<form:errors path="password" cssClass="error" />
								<div id="passwordErr" style="color: red"></div>
							</div>  
							
							
							<div class="col-xs-4">
								<label>Confirm Password </label><font style="color: red;">
									* </font>
								<form:input type="password" class="form-control "
									path="confirmPassword" id="confirmPassword"
									placeholder="Confirm Password" maxlength="8" />
								<form:errors path="confirmPassword" cssClass="error" />
								<div id="confirmPasswordErr" style="color: red"></div>
							</div>
						</div>
					</div>  -->


					<div class="form-group">
						<div class="row clearfix">

							<div class="col-xs-4" style="display: block" id="addCompany">
								<label>Company</label><font style="color: red;"> * </font>
								<form:input type="text" id="companyNameId" class="form-control "
									path="companyName" placeholder="Company Name" maxlength="100" />
								<form:errors path="companyName" cssClass="error" />
							</div>

							<div class="col-xs-4 ">
								<label>Industry</label><font style="color: red;"> * </font>
								<form:input type="text" class="form-control "
									path="industryType" id="IndustryTypeId"
									placeholder="IndustryType" maxlength="100" />
								<form:errors path="industryType" cssClass="error" />
							</div>
							<div class="col-xs-4">
								<label>WebSite </label><font style="color: red;"> * </font>
								<form:input type="text" class="form-control " path="website"
									id="companyWebsiteInput" placeholder="WebSite" maxlength="100" />
								<form:errors path="website" cssClass="error" />
								<div id="websiteErr" style="color: red"></div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row clearfix">

							<div class="col-xs-6" style="display: block" id="addCompany">
								<label>Contact</label><font style="color: red;"> * </font>
								<form:input type="text" id="contactId" class="form-control "
									path="contactNo" placeholder="ContactNo" maxlength="10" />
								<form:errors path="contactNo" cssClass="error" />
								<div id="contacterror" style="color: red"></div>
							</div>

							<div class="col-xs-6">
								<label>Mobile</label><font style="color: red;"> * </font>
								<form:input type="text" class="form-control " path="mobileNo"
									id="mobileId" placeholder="MobileNo" maxlength="10" />
								<form:errors path="mobileNo" cssClass="error" />
								<div id="mobileerror" style="color: red"></div>


							</div>

						</div>
					</div>

					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6 job-seek-2">
								<label>Address</label><font style="color: red;"> * </font>
								<form:textarea type="text" class="form-control rtext"
									path="address" rows="4" id="keySkillsInput"
									placeholder="Address" />
								<form:errors path="address" cssClass="error" />
								<div id="errorSkill" style="color: red;"></div>
							</div>

						</div>
					</div>


					<div class="form-group ">
						<input type="submit" id="btnSubmit" value="Submit"
							class="btn btn-t-primary btn-theme" /><a
							href="profile-view.html"> <input type="button" value="Cancel"
							class="btn btn-t-primary btn-theme" /></a>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</c:if>


<!-- Update Employer -->

<c:if test="${!empty update}">
	<div class="box-list" style="margin-bottom: 5%;">
		<div class="col-sm-12" style="padding-top: 2%;">
			<div class="form-group">
				<div class="row clearfix">
					<div class="col-xs-6">
						<span onclick="educationNextBt('personal');"
							style="padding-right: 75px; font-weight: 800;"
							class="btn btn-theme btn-success">Update Employer</span>
						<!-- <span
							onclick="educationNextBt('educational');"
							style="padding-right: 75px; font-weight: 800; margin-left: -5px; border-left: 1px solid #fff;"
							class="btn btn-theme btn-success">Educational</span> <span
							onclick="educationNextBt('experience');"
							style="padding-right: 75px; font-weight: 800; margin-left: -5px; border-left: 1px solid #fff;"
							class="btn btn-theme btn-success">Experience</span> -->
					</div>
				</div>
			</div>
		</div>

		<div class="item">
			<!-- form post a job -->
			<form:form id="myForm" method="post" class="login-form clearfix"
				commandName="updateProfile" enctype="multipart/form-data"
				modelAttribute="updateProfile">
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

							<div class="col-xs-4" style="display: block" id="addCompany">
								<label>First Name</label>
								<form:input type="text" id="firstNameInput" class="form-control"
									path="firstName" placeholder="First Name" maxlength="150" />
								<form:errors path="firstName" cssClass="error" />
								<div id="firstNameErr" style="color: red;"></div>
							</div>

							<div class="col-xs-4 ">
								<label>Last Name</label>
								<form:input type="text" class="form-control" path="lastName"
									id="lastNameInput" placeholder="Last Name" maxlength="150" />
								<form:errors path="lastName" cssClass="error" />
								<div id="lastNameErr" style="color: red;"></div>
							</div>
							<div class="col-xs-4">
								<label>Email </label>
								<form:input type="email" class="form-control"
									path="emailAddress" id="emailIdInput"
									placeholder="Email Address" maxlength="100" />
								<form:errors path="emailAddress" cssClass="error" />
								<div id="EmailIdErr" style="color: red"></div>
							</div>
						</div>
					</div>


					<div class="form-group">
						<div class="row clearfix">

							<!--  <div class="col-xs-4" style="display: block" id="addCompany">
								<label>Confirm Email</label>
								<form:input type="text" id="confirmemailId" class="form-control"
									path="confirmEmailAddress" placeholder="Confirm Email"
									maxlength="100" />
								<form:errors path="confirmEmailAddress" cssClass="error" />
								<div id="confirmemailIdErr" style="color: red;"></div>
							</div>

							<div class="col-xs-4 ">
								<label>Password</label>
								<form:input type="password" class="form-control" path="password"
									id="password" placeholder="Password" maxlength="8" />
								<form:errors path="password" cssClass="error" />
								<div id="passwordErr" style="color: red"></div>
							</div>
							<div class="col-xs-4">
								<label>Confirm Password </label>
								<form:input type="password" class="form-control"
									path="confirmPassword" id="confirmPassword"
									placeholder="Confirm Password" maxlength="100" />
								<form:errors path="confirmPassword" cssClass="error" />
								<div id="errorSalary" style="color: red"></div>
								<div id="confirmPasswordErr" style="color: red;"></div>
							</div>
						</div>
					</div>-->


					<div class="form-group">
						<div class="row clearfix">

							<div class="col-xs-4" style="display: block" id="addCompany">
								<label>Company</label>
								<form:input type="text" id="companyNameId" class="form-control"
									path="companyName" placeholder="Company Name" maxlength="100" />
								<form:errors path="companyName" cssClass="error" />

							</div>

							<div class="col-xs-4 ">
								<label>Industry</label>
								<form:input type="text" class="form-control" path="industryType"
									id="IndustryTypeId" placeholder="industryType" maxlength="100" />
								<form:errors path="IndustryType" cssClass="error" />
							</div>
							<div class="col-xs-4">
								<label>WebSite </label>
								<form:input type="text" class="form-control" path="website"
									id="companyWebsiteInput" placeholder="WebSite" maxlength="100" />
								<form:errors path="website" cssClass="error" />
								<div id="errorSalary" style="color: red"></div>
								<div id="websiteErr" style="color: red;"></div>
							</div>
						</div>
					</div>





					<div class="form-group">
						<div class="row clearfix">

							<div class="col-xs-6" style="display: block" id="addCompany">
								<label>Contact</label>
								<form:input type="text" id="contactId" class="form-control"
									path="contactNo" placeholder="ContactNo" maxlength="10" />
								<form:errors path="contactNo" cssClass="error" />
								<div id="contacterror" style="color: red;"></div>
							</div>

							<div class="col-xs-6">
								<label>Mobile</label>
								<form:input type="text" class="form-control" path="mobileNo"
									id="mobileId" placeholder="MobileNo" maxlength="10" />
								<form:errors path="mobileNo" cssClass="error" />
								<div id="mobileerror" style="color: red;"></div>
							</div>

						</div>
					</div>


					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6 job-seek-2">
								<label>Address</label>
								<form:textarea type="text" class="form-control rtext"
									path="address" rows="4" id="keySkillsInput"
									placeholder="Key Skills" />
								<form:errors path="address" cssClass="error" />
								<div id="errorSkill" style="color: red;"></div>
							</div>
							<div class="col-xs-6 job-seek-2">
								<label>Status</label> <font style="color: red;">* </font>
								<form:select type="text" id="statusInput" name="status"
									path="status" class="form-control">
									<form:option value="">Select Status</form:option>
									<form:option value="opened">Opened</form:option>
									<form:option value="Inprocess">Inprocess</form:option>
									<form:option value="Closed">Closed</form:option>
								</form:select>
								<form:errors path="status" cssClass="error" />
							</div>
						</div>
					</div>


					<div class="form-group ">
						<input type="submit" id="btnSubmit" value="Submit"
							class="btn btn-t-primary btn-theme" /><a
							href="profile-view.html"> <input type="button" value="Cancel"
							class="btn btn-t-primary btn-theme" /></a>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</c:if>
<div class="modal fade" id="myModal">
	<div class="modal-dialog popupwidth">
		<div class="modal-content"></div>
		<!-- <div class="modal-header"> -->
		<!--  <button type="button" class="close" data-dismiss="modal"
			onclick="window.location.reload();" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button></div>
			 <div class="modal-footer">
			 <button type="button" class="btn btn-default" data-dismiss="modal"
				onclick="window.location.reload();">Close</button>   -->

		<!-- </div> -->

	</div>
</div>
