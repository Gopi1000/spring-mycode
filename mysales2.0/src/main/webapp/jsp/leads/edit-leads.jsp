
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>

<div class="warning">
	<div class="box-list">
		<div class="item">
			<div class="row ">


				<div class="text-center underline">
					<h3>Edit Leads</h3>

				</div>
				<form:form method="POST" id="addForm" action="edit-leads"
					modelAttribute="leadsBO">
					<div class="col-sm-12">
						<div class="col-sm-3">
							<div class="form-group">
								<label>Campaign Name</label>
								<form:select type="text" path="campaignBO.campaignId"
									class="form-control ">
									 <form:option value="${campaignId}">${campaignName}</form:option> 
									<form:option value="">-- Select --   </form:option>
									<form:options items="${listcampaign}" itemLabel="campaignName"
										itemValue="campaignId" />
								</form:select>
							</div>
						</div>
						<form:hidden path="leadsId" />

						<div class="col-sm-3">
							<div class="form-group">
								<label> First Name <span class="font10 text-danger">*</span></label>
								<form:input id="inputFirstName" type="text" path="firstName"
									class="form-control required" placeholder="First Name"
									maxlength="150" />
								<form:errors path="firstName" class="input_error" />
							</div>
						</div>


						<div class="col-sm-3">
							<div class="form-group">
								<label>Last Name <span class="font10 text-danger">*</span></label>
								<form:input type="text" path="lastName"
									class="form-control required" placeholder="Last Name" />
								<form:errors path="lastName" class="input_error" />
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label>Email Address <span class="font10 text-danger">*</span></label>
								<form:input type="text" path="emailAddress"
									class="form-control required" placeholder="Email Address" />
								<form:errors path="emailAddress" class="input_error" />
							</div>
						</div>

					</div>

					<div class="col-sm-12">
						<div class="col-sm-3">
							<div class="form-group">
								<label>Primary Contact No<span class="font10 text-danger">*</span></label>
								<form:input type="text" path="contactNo"
									class="form-control required" placeholder="Contact No"
									 />
								<form:errors path="contactNo" class="input_error" />
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label> Contact Number <span class="font10 text-danger">*</span></label>
								<form:input id="inputFirstName" type="text" path="mobileNo"
									class="form-control required" placeholder="Mobile Number"
									 />
								<form:errors path="mobileNo" class="input_error" />
							</div>
						</div>


						<div class="col-sm-3">
							<div class="form-group">
								<label>Company Name</label>
								<form:input path="companyName" class="form-control"
									placeholder="Company Name" />
								<%-- <form:errors path="companyName" class="input_error" /> --%>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label>Industry Type</label>
								<form:input path="industryType" class="form-control"
									placeholder="Industry Type" />
								<%-- <form:errors path="industryType" class="input_error" /> --%>
							</div>
						</div>


					</div>

					<div class="col-sm-12">
						<div class="col-sm-3">
							<div class="form-group">
								<label>Website</label>
								<form:input path="website" class="form-control"
									placeholder="Website" />
								<%-- <form:errors path="website" class="input_error" /> --%>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label>Address<span class="font10 text-danger">*</span></label>
								<form:input type="text" path="address"
									class="form-control required" placeholder="Address" />
								<form:errors path="address" class="input_error" />
							</div>
						</div>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="col-sm-3">
								<div class="form-group">
									<label>Assign  Employee<span class="font10 text-danger">*</span></label>
									<form:select type="text" path="userName"
										class="form-control required">
									 <form:option value="${usrId}">${userName}</form:option> 
										<form:option value="">-- Select --   </form:option>
										<form:options items="${userBOList}" itemLabel="name"
											itemValue="id" />
									</form:select>
								</div>
							</div>
							</sec:authorize>

					</div>




					<div style="text-align: right; margin-right: 31px">
						<button type="submit" id="btnsubmit"
							class="btn btn-t-primary btn-theme lebal_align mt-20">Update</button>
						<a href=view-leads?page=1"><span
							class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>



