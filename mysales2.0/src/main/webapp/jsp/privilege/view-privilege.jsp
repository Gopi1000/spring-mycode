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

<div class="box-list">
	<div class="item">
		<div class="row ">
			<h3 class="text-center no-margin titleunderline underline"
				style="margin-top: -10px;">View Privilege</h3>

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
			
			<!--Start form search -->
			
			<form:form id="myForm" method="post" class="login-form clearfix"
				action="search-privilege" commandName="searchprivilege">
				<div class="row"
					style="border: 4px solid #e6e6e6; margin:15px 15px 15px 15px; background-color: #e1e1e1">
					<div class=" col-md-4">
						<div class="form-group home-left">
							<label class="hidden-xs">&nbsp;</label>
							<form:input type="ntext" class="form-control" path="privilegename"
								placeholder="Privilege Name " escapeXml="false"
								style="height: 35px;font-weight: 700;"></form:input>
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

			<br>
			<c:if test="${!empty viewprivilege}">
				<div class="col-sm-12" style="margin-top: -27px">
					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
						
							<display:table id="data" name="${viewprivilege}"
								requestURI="/view-privilege" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="sno" title="SNo" />

								<%-- <c:if test="${data.status == 'opened'}">
									<td style="color: green;"><display:column
											property="firstName" title="Name" /></td>
								</c:if>
								<c:if test="${data.status == 'Closed'}">
									<td style="color: #FA5858;"><display:column
											property="firstName" title="Name" /></td>

								</c:if> --%>
								<display:column property="privilegename"
									title="Privilege Name" />
								<%-- <display:column property="permanentaddress"
									title="Permanent Address" />
								<display:column property="primarycontactnumber"
									title="Primary Contact Number" />
								<display:column property="permanentcontactnumber"
									title="Permanent Contact Number" />
								<display:column property="customerownername"
									title="Customer Name" /> --%>



								<display:column url="edit-privilege" media="html" paramId="privilegeid"
									paramProperty="privilegeid" title="Edit">
									<a href="edit-privilege?privilegeid=${data.privilegeid}"><i
										style="text-align: center;" class="fa fa-pencil"></i></a>
								</display:column>



								<display:column url="delete-privilege" media="html" paramId="privilegeid"
									paramProperty="privilegeid" title="Delete">
									<a href="delete-privilege?privilegeid=${data.privilegeid}"
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