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
				style="margin-top: -10px;">View Roles Privilege</h3>

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

			<c:if test="${!empty viewroleprivilegelist}">
				<div class="col-sm-12" style="margin-top: -27px">
					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">

							<display:table id="data" name="${viewroleprivilegelist}"
								requestURI="/view-role-privilege" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="sno" title="SNo" />


								<display:column property="rolename" title="Role Name" />
								
								<display:column property="privilegelist" title="Privilege Name" />
								
								
								
								


								<display:column url="edit-role-privilege" media="html" paramId="roleid"
									paramProperty="roleid" title="Edit">
									<a href="edit-role-privilege?roleid=${data.roleid}"><i
										style="text-align: center;" class="fa fa-pencil"></i></a>
								</display:column>



								<display:column url="delete-role-privilege" media="html" paramId="roleid"
									paramProperty="roleid" title="Delete">
									<a href="delete-role-privilege?roleid=${data.roleid}"
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