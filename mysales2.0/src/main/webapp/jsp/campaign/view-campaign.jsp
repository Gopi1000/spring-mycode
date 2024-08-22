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
<title>View Campaign</title>
</head>
<div class="box-list">
	<div class="item">
		<div class="row ">
			<h3 class="text-center no-margin titleunderline underline"
				style="margin-top: -10px;">View Campaign</h3>
			<div class="row ">
				<a href="create-campaign" title="Create New Campaign"
					style="font-size: 26px; color: #7cb228; margin-left: 95%;"> <i
					class="fa fa-plus-circle"></i>
				</a>
			</div>
			<div class="warning">

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
			</div>

			<!--Start form search -->
			<form:form id="myForm" method="post" class="login-form clearfix"
				action="search-campaign" commandName="searchCampaign">
				<div class="row"
					style="border: 4px solid #e6e6e6; margin: 15px 15px 15px 15px; background-color: #e1e1e1">
					<div class=" col-md-3	">
						<div class="form-group home-left">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="campaignName"
								placeholder="Campaign Name " escapeXml="false"
								style="height: 35px;font-weight: 700;"></form:input>
						</div>
					</div>

					<div class=" col-md-3 fs-mobile-search">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:select type="text" path="productServiceBO.serviceName"
								class="form-control "
								style="height: 35px;font-weight: 700;
								   text-transform: capitalize;">
								<form:option value="">-- Select Products --   </form:option>
								<form:options items="${productList}" itemLabel="serviceName"
									itemValue="serviceId" />
							</form:select>
						</div>
					</div>

					<div class=" col-md-3 fs-mobile-search">
						<div class="form-group find-field-space">
							<label class="hidden-xs">&nbsp;</label>
							<form:select class="form-control" path="campaignMode"
								id="campaignMode"
								style="height: 35px;font-weight: 700;text-transform: capitalize;">
								<form:option value="">-- Campaign Mode --   </form:option>
								<form:option value="Sms">SMS   </form:option>
								<form:option value="WhatsApp">WHATSAPP </form:option>
								<form:option value="Email">EMAIL  </form:option>
							</form:select>

						</div>
					</div>


					<input type="hidden" value="true" name="search" />

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



			<c:if test="${!empty listcampaign}">
				<div class="col-sm-12">
					<!-- <hr style="border: 1px solid #e1e1e1;"> -->


					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
							<display:table id="data" name="${listcampaign}"
								requestURI="/view-campaign" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="sNo" title="SNo" />
								<display:column property="productServiceBO.serviceName"
									title="Product Name" />
								<display:column property="campaignName" title="Campaign Name" />
								<display:column property="campaignMode" title="Campaign Mode" />
								<display:column url="edit-user" media="html" paramId="id"
									paramProperty="id" title="Edit">
									<a href="edit-campaign?campaignid=${data.campaignId}"><i
										style="text-align: center;" class="fa fa-pencil"></i></a>
								</display:column>
								<display:column url="delete-user" media="html" paramId="id"
									paramProperty="id" title="Delete">
									<a href="delete-campaign?campaignid=${data.campaignId}"
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
	<br />
</div>
</html>