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
				style="margin-top: -10px;">View Contact Details</h3>

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

			<br>
			<c:if test="${!empty contactlist}">
				<div class="col-sm-12" style="margin-top: -27px">
					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
						
							<display:table id="data" name="${contactlist}"
								requestURI="/view-contact" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="sNo" title="SNo" />

								<%-- <c:if test="${data.status == 'opened'}">
									<td style="color: green;"><display:column
											property="firstName" title="Name" /></td>
								</c:if>
								<c:if test="${data.status == 'Closed'}">
									<td style="color: #FA5858;"><display:column
											property="firstName" title="Name" /></td>

								</c:if> --%>
								<display:column property="primaryaddress"
									title="Primary Address" />
								<display:column property="permanentaddress"
									title="Permanent Address" />
								<display:column property="primarycontactnumber"
									title="Primary Contact Number" />
								<display:column property="permanentcontactnumber"
									title="Permanent Contact Number" />
								<display:column property="customerownername"
									title="Customer Name" />



								<display:column url="edit-contact" media="html" paramId="contactid"
									paramProperty="contactid" title="Edit">
									<a href="edit-contact?contactid=${data.contactid}"><i
										style="text-align: center;" class="fa fa-pencil"></i></a>
								</display:column>



								<display:column url="delete-contact" media="html" paramId="contactid"
									paramProperty="contactid" title="Delete">
									<a href="delete-contact?contactid=${data.contactid}"
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