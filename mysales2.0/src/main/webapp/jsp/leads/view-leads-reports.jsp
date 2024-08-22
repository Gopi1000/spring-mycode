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
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="box-list">
	<div class="item">
		<div class="row ">
			<h3 class="text-center no-margin titleunderline underline"
				style="margin-top: -10px;">Leads Report</h3>
			<br>
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
				action="search-leads-report" commandName="searchLeadsReport">
				<div class="row"
					style="border: 1px solid #e1e1e1; margin-left: 14px; margin-right: 14px; background-color: #e1e1e1">
					
					<%-- <sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class=" col-md-3 fs-mobile-search">
							<div class="form-group find-field-space">
								<label class="hidden-xs">&nbsp;</label>
								<form:select class="form-control" path="assignedTo"
									style="height: 35px;font-weight: 700;text-transform: capitalize;">
									<form:option value="">Select User</form:option>
									<form:options items="${userBOList}" itemLabel="name"
										itemValue="id" />
								</form:select>
							</div>
						</div>
					</sec:authorize> --%>
					 <div class=" col-md-3 fs-mobile-search">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="startDate"
								id="startDateInput" placeholder="StartDate"
								data-date-format="dd/mm/yyyy"
								style="height:35px;font-weight: 700;"></form:input>
						</div>
					</div> 

					 <div class=" col-md-3 fs-mobile-search">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="endDate"
								id="endDateInput" placeholder="EndDate"
								data-date-format="dd/mm/yyyy"
								style="height:35px;font-weight: 700;"></form:input>
						</div>
					</div> 
					 <div class=" col-md-2 fs-mobile-search">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:select class="form-control" path="process"
								style="height:35px;font-weight: 700;">
								<form:option value="all">All</form:option>
								<form:option value="create">Create</form:option>
								<form:option value="edit">Leads Tracked</form:option>
							</form:select>
						</div>
					</div>
					<input type="hidden" value="true" name="search" />
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
			<br /> <br>
			<c:if test="${!empty listLeads}">
				<div class="col-sm-12" style="margin-top: -27px">
					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
							<display:table id="data" name="${listLeads}"
								requestURI="/view-leads-reports" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="sNo" title="S No" />
								<display:column property="firstName" title="Name" />
								<display:column property="emailAddress" title="Email Address" />
								<%-- <display:column property="address" title="Address" /> --%>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<display:column property="adminLoginBO.name" title="AssignedTo" />
								</sec:authorize>
								<%-- <display:column property="mobileNo" title="MobileNo" /> --%>
								<display:column property="campaignBO.campaignName"
									title="CampaignName" />
								<display:column property="createdDate" title="Create Date" />
								<display:column property="modifiedDate" title="Modifie Date" />
								<display:column url="customer-tracking-status" media="html"
									paramId="id" paramProperty="id" title="View">
									<a href="leads-tracking-status?leadsId=${data.leadsId}"> <i
										style="text-align: center; color: black;" class="fa fa-search"></i>
									</a>
								</display:column>
							</display:table>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>
<br/>
