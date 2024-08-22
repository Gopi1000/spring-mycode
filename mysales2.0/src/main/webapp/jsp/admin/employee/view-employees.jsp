<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Employees</title>
</head>
<div class="box-list">
	<div class="item">
		<div class="row ">
			<h3 class="text-center no-margin titleunderline underline"
				style="margin-top: -10px;">View Employees</h3>
			<br>
			
			<a href="create-employees" title="Create New Employee"
				style="font-size: 26px; color: #7cb228; margin-left:95%;"> <i
				class="fa fa-plus-circle"></i>
			</a>
			 </br>

			<c:if test="${not empty successmessages}">
				<div class="alert alert-info" role="alert"
					style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>success!</strong>
					<c:out value="${successmessages}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty infoMessagemessage}">
				<div class="alert alert-info" role="alert"
					style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Info!</strong>
					<c:out value="${infoMessagemessage}"></c:out>
				</div>
			</c:if>

			<form:form id="myForm" method="post" class="login-form clearfix"
				action="search-employees" commandName="searchEmployees">
				<div class="row"
					style="border: 4px solid #e6e6e6; margin:15px 15px 15px 15px; background-color: #e1e1e1">
					<div class=" col-md-3">
						<div class="form-group home-left">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="name"
								placeholder="First Name " escapeXml="false"
								style="height: 35px;font-weight: 700;"></form:input>
						</div>
					</div>
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
							<form:select type="text" path="userType" class="form-control ">
								<form:option value="">Select User</form:option>
								<form:option value="ROLE_CAMPAIGN">ROLE_CAMPAIGN</form:option>
								<form:option value="ROLE_MARKETING">ROLE_MARKETING</form:option>
								<form:option value="ROLE_SALES">ROLE_SALES</form:option>
								<form:option value="ROLE_ADMIN">ROLE_ADMIN</form:option>


							</form:select>
						</div>
					</div>
					<!-- <input type="hidden" value="true" name="listcampaign" /> -->

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
			

			<c:if test="${!empty userBOList}">
				<div class="col-sm-12">
					<!-- <hr style="border: 1px solid #e1e1e1;"> -->



					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
							<display:table id="data" name="${userBOList}"
								requestURI="/view-employees" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
								<display:column property="sNo" title="SNO" />
								<c:if test="${data.status =='Active'}">
									<display:column url="active-deactive-user" media="html"
										paramId="id" title="Name">
										<a
											href="active-deactive-user?status=${data.status},${data.id}"
											onclick="return confirm('Are you sure you want to Deactive?')"
											style="color: green;"> <c:out value="${data.name }"></c:out></a>
									</display:column>
								</c:if>
								<c:if test="${data.status =='De-Active'}">
									<display:column url="active-deactive-user" media="html"
										paramId="id" title="Name">
										<a
											href="active-deactive-user?status=${data.status},${data.id}"
											onclick="return confirm('Are you sure you want to active?')"
											style="color: red;"> <c:out value="${data.name }"></c:out></a>
									</display:column>
								</c:if>
								<display:column property="emailAddress" title="Email" />
								<display:column property="userType" title="User Type" />
								<display:column property="mobileNo" title="Mobile" />
								<display:column url="edit-employees" media="html"
									paramId="id" paramProperty="id" title="Edit">
									<a href="edit-employees?id=${data.id}"><i
										style="text-align: center;" class="fa fa-pencil"></i></a>
								</display:column>
								<display:column url="delete-employees" media="html"
									paramId="id" paramProperty="id" title="Delete">
									<a href="delete-employees?id=${data.id}"
										onclick="return confirm('Are you sure you want to Delete?')"><i
										style="text-align: center;" class="fa fa-trash"></i></a>
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