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



<div class="contact-form-wrapper">

	<div class="box-list">
		<div class="item">
			<div class="row ">

				<div class="text-center underline">
					<h3>Edit Role Privilege</h3>
				</div>
				<br>

				<form:form method="POST" id="addForm" action="edit-role-privilege"
					modelAttribute="updateroleprivilege">

					<div class="col-sm-12">
						<div class="col-sm-3">
							<div class="form-group">
								<label>Role<span class="font10 text-danger">*</span></label>
								<form:select type="text" path="rolename"
									class="form-control required">
									<%-- <form:option value=" ">-- Select --</form:option> --%>
									<form:options items="${roleList}" itemLabel="rolename"
										itemValue="roleid" />
								</form:select>
							</div>
						</div>


						<%-- <div class="col-sm-3">
							<div class="form-group">
								<label>Role<span class="font10 text-danger">*</span></label>
                                  <div>
									<form:checkboxes items="${roleList}" itemLabel="rolename"
										itemValue="roleid" path="rolebo.rolename" />
								</div>
							</div>
						</div>
					</div> --%>


						<div class="col-sm-3">
							<div class="form-group">
								<label>Privilege<span class="font10 text-danger">*</span></label>

								<%-- <c:forEach var="data" items="${privilegelist}">
										<li><form:checkbox path="privilegenames" value="privilegenames" />
											${data.privilegename}</li>
									</c:forEach> --%>

								<div>
								
									<form:checkboxes items="${privilegelist}"
									itemLabel="privilegename" itemValue="privilegeid"
										path="privilegenames" />
								</div>
							</div>
						</div>
					</div>


					<div style="text-align: right; margin-right: 31px">
						<button type="submit" id="btnSubmit"
							class="btn btn-t-primary btn-theme lebal_align mt-20">Submit</button>
						<a href=view-customers?page=1"><span
							class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
					</div>
				</form:form>
				
				
				<%-- <div class="box-list">
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
								
								<display:column property="privilegelist[0].privilegename" title="Privilege Name" />
								
								
								
								


								<display:column url="edit-role-privilege" media="html" paramId="roleid"
									paramProperty="roleid" title="Edit">
									<a href="edit-role-privilege?roleid=${data.roleid}"><i
										style="text-align: center;" class="fa fa-pencil"></i></a>
								</display:column>



								<display:column url="delete-role-privilege" media="html" paramId="roleid"
									paramProperty="roleid" title="Delete">
									<a href="delete-role?roleid=${data.roleid}"
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
</div> --%>
				
				
				
			</div>
		</div>
	</div>
</div>
</div>