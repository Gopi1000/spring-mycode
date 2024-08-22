<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<div class="box-list">
	<div class="item">
		<div class="row ">
			<h3 class="text-center no-margin titleunderline underline"
				style="margin-top: -10px;">View Leads</h3>
			<div class="row ">
			<a href="create-leads"
				style="font-size: 26px; color: #7cb228; margin-left:95%;"> <i
				class="fa fa-plus-circle" title="Create New Lead"></i>
			</a>
			</div>
			
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>

			<form:form id="myForm" method="post" class="login-form clearfix"
				action="search-leads?" commandName="searchLeads">
				<div class="row"
					style="border: 4px solid #e6e6e6; margin:15px 15px 15px 15px; background-color: #e1e1e1">
					<div class=" col-md-3">
						<div class="form-group home-left">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="firstName"
								placeholder="Name " escapeXml="false"
								style="height: 35px;font-weight: 700;"></form:input>
						</div>
					</div>
					<input type="hidden" value="true" name="search" />
					<div class="col-md-3">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="emailAddress"
								placeholder="Email Address"
								style="height:35px;font-weight: 700;"></form:input>
						</div>
					</div>

					<div class=" col-md-3">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="mobileNo"
								placeholder="Mobile Number" maxlength="10"
								style="height:35px;font-weight: 700;"></form:input>
						</div>
					</div>
					<div class="col-sm-2">
							<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
								<form:select type="text" path="campaignBO.campaignName"
										class="form-control " style="height: 35px;font-weight: 700;
								   text-transform: capitalize;">
										<form:option value="">-- Select Campaign --   </form:option>
										<form:options items="${listcampaign}" itemLabel="campaignName"
											itemValue="campaignId" />
									</form:select>
							</div>
						</div>

					<div class=" col-md-1" style="padding-bottom: 0px;">
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
			
			<c:if test="${!empty listLeads}">
				<div class="col-sm-12">
							<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
							<display:table id="data" name="${listLeads}"
								requestURI="/view-leads" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="sNo" title="SNo" />
								<display:column property="firstName" title="Name" />
								<display:column property="emailAddress" title="Email Address" />
								<display:column property="address" title="Address" />
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								<display:column property="adminLoginBO.name" title="AssignedTo" />
								</sec:authorize>
								<display:column property="mobileNo" title="MobileNo" />
								<display:column property="campaignBO.campaignName" title="CampaignName" />

								<display:column url="edit-user" media="html" paramId="id"
									paramProperty="leadsId" title="Edit">
									<a href="edit-leads?leadsId=${data.leadsId}"><i
										style="text-align: center;" class="fa fa-pencil"></i></a>
								</display:column>
								<display:column url="delete-user" media="html" paramId="id"
									paramProperty="leadsId" title="Delete">
									<a href="delete-leads?leadsId=${data.leadsId}"
										onclick="return confirm('Are you sure you want to Delete?')"><i
										style="text-align: center;" class="fa fa-trash"></i></a>
								</display:column>

								<display:column url="customer-tracking-status" media="html"
									paramId="id" paramProperty="id" title="View">
									<a href="leads-tracking-status?leadsId=${data.leadsId}"> <i
										style="text-align: center; color: black;" class="fa fa-search"></i>
									</a>
								</display:column>

								<display:column url="/convert-customer" media="html"
									paramId="id" paramProperty="leadsId" title="L & C">
									<i style="text-align: center;" class="fa fa-exchange"></i>
								</display:column>
							</display:table>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<br/>
</div>
</html>