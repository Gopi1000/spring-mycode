<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>

<script>
	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline', 'ol',
					'ul', 'strikeThrough', 'html' ]
		}).panelInstance('inputAddress');
	});
</script>

<div class="row scrollspy-sidenav pb-20 body-mt-15">
	<script>
		$(document).ready(function() {
			$('#btnsubmit').click(function(e) {
				var isValid = true;
				$('input[type="text"].required').each(function() {
					if ($.trim($(this).val()) == '') {
						isValid = false;
						$(this).css({
							"border" : "1px solid red",
						});
					} else {
						$(this).css({
							"border" : "",
							"background" : ""
						});
					}

					if (isValid == false)
						e.preventDefault();

				});
			});
		});

		$(document).ready(function() {
			$('#btnsubmit').click(function(e) {
				var isValid1 = true;
				$('input[id="password"].required').each(function() {
					if ($.trim($(this).val()) == '') {
						isValid1 = false;
						$(this).css({
							"border" : "1px solid red",
						});
					} else {
						$(this).css({
							"border" : "",
							"background" : ""
						});
					}
				});
				if (isValid1 == false)
					e.preventDefault();

			});
		});
	</script>
	<div class="warning">

		<c:if test="${not empty successMessage}">
			<div class="alert alert-info" role="alert"
				style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>success!</strong>
				<c:out value="${successMessage}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-info" role="alert"
				style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Info!</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
	</div>

	<div class="contact-form-wrapper">

		<div class="box-list" style="margin-bottom: 3%; margin-top: 2%;">
			<div class="item">
				<div class="row ">

					<c:if test="${functionType eq 'add'}">
						<div class="text-center">
							<h3>CreateUser</h3>

						</div>
						<!-- 	<h4 class="text-center no-margin titleunderline"
							style="margin-top: 10px;">Create User</h4> -->

						<form:form method="POST" id="addForm" action="create-user.html"
							modelAttribute="user">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<div class="form-group">
										<label path="Name"> Name <span
											class="font10 text-danger">*</span></label>
										<form:input id="inputFirstName" type="text" path="name"
											class="form-control required" placeholder="Name"
											maxlength="150" />
										<form:errors path="name" class="input_error" />
									</div>
								</div>


								<div class="col-sm-3">
									<div class="form-group">
										<label path="EmailAddress">EmailAddress <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="emailAddress"
											class="form-control required" placeholder="EmailAddress" />
										<form:errors path="emailAddress" class="input_error" />
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<label path="password"> password <span
											class="font10 text-danger">*</span></label>
										<form:input type="password" path="password"
											class="form-control required" placeholder="password"
											maxlength="8" />
										<form:errors path="password" class="input_error" />
									</div>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-3">
									<div class="form-group">
										<label path=" Confirm password"> Confirm password <span
											class="font10 text-danger">*</span></label>
										<form:input type="password" path="confirmPassword"
											class="form-control required" placeholder="confirm password"
											maxlength="8" />
										<form:errors path="confirmPassword" class="input_error" />
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<label path="User Type">User Type<span
											class="font10 text-danger">*</span></label>
										<form:select type="text" path="userType"
											class="form-control required">
											<form:option value="">Select User</form:option>
											<form:option value="admin">admin</form:option>
											<form:option value="user">user</form:option>
										</form:select>
										<%-- <form:input type="text" path="userType" class="form-control required"
										placeholder="usertype" /> --%>
										<form:errors path="userType" class="input_error" />
									</div>
								</div>


								<div class="col-sm-3">
									<div class="form-group">
										<label path="Mobile No"> Mobile No <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="mobileNo"
											class="form-control required" placeholder="Mobileno"
											maxlength="10" />
										<form:errors path="mobileNo" class="input_error" />
									</div>
								</div>

							</div>

							<button type="submit" id="btnsubmit"
								class="btn btn-t-primary btn-theme lebal_align mt-20">Submit</button>
							<a href="create-user.html?page=1"><span
								class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
						</form:form>
						
					</c:if>
					<div class="warning">

						<c:if test="${not empty Successmessage}">
							<div class="alert alert-info" role="alert"
								style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>success!</strong>
								<c:out value="${Successmessage}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty errorMessages}">
							<div class="alert alert-info" role="alert"
								style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Info!</strong>
								<c:out value="${errorMessages}"></c:out>
							</div>
						</c:if>
					</div>
					<c:if test="${functionType eq 'edit'}">
						<!-- <h4 class="text-center" style="margin-top: 10px;">Edit User</h4> -->
						<div class="text-center">
							<h3>Edit User</h3>
						</div>
						<form:form id="userForm" method="POST" modelAttribute="editUser">
							<div class="col-sm-12">
								<div class="col-sm-4">
									<div class="form-group">
										<label path="Name"> Name <span
											class="font10 text-danger">*</span></label>
										<form:input id="inputFirstName" type="text" path="name"
											class="form-control required" maxlength="150" />
										<form:errors path="name" class="input_error" />
									</div>
								</div>


								<div class="col-sm-4">
									<div class="form-group">
										<label path="EmailAddress">EmailAddress <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="emailAddress"
											class="form-control required" />
										<form:errors path="emailAddress" class="input_error" />
									</div>
								</div>

								<div class="col-sm-4">
									<div class="form-group">
										<label path="password"> password <span
											class="font10 text-danger">*</span></label>
										<form:input type="password" path="password"
											class="form-control required" maxlength="8" />
										<form:errors path="password" class="input_error" />
									</div>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-4">
									<div class="form-group">
										<label path=" Confirm password"> Confirm password <span
											class="font10 text-danger">*</span></label>
										<form:input type="password" path="confirmPassword"
											class="form-control required" />
										<form:errors path="confirmPassword" class="input_error" />
									</div>
								</div>

								<div class="col-sm-4">
									<div class="form-group">
										<label path="User Type">User Type <span
											class="font10 text-danger">*</span></label>
										<form:select type="text" path="userType"
											class="form-control required">
											<form:option value="">Select User</form:option>
											<form:option value="admin">admin</form:option>
											<form:option value="user">user</form:option>
										</form:select>
										<%-- <form:input type="text" path="userType" class="form-control required" /> --%>
										<form:errors path="userType" class="input_error" />
									</div>
								</div>


								<div class="col-sm-4">
									<div class="form-group">
										<label path="Mobile No"> Mobile No <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="mobileNo"
											class="form-control required" maxlength="10" />
										<form:errors path="mobileNo" class="input_error" />
									</div>
								</div>
							</div>


							<div class="col-sm-4">
								<div class="form-group">
									<button type="submit" id="btnsubmit"
										class="btn btn-t-primary btn-theme lebal_align mt-20 ">Update</button>
									<a href="create-user.html?page=1"><span
										class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
								</div>
							</div>

						</form:form>

					</c:if>

					<div class="warning">

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
					</div>




					<c:if test="${!empty userBOList}"> 
						<div class="col-sm-12">
							<hr style="border: 1px solid #e1e1e1;">
							<div class="text-center"></div>
							<br />
							<h3>View User Table</h3>
						
							<div class="pi-responsive-table-sm">
								<div class="pi-section-w pi-section-white piTooltips">
									 <display:table id="data" name="${userBOList}"
										requestURI="/create-user.html" pagesize="10" export="false"
										class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
										<display:column property="sNo" title="SNO" /> 
										<c:if test="${data.status =='Active'}">
											<display:column url="active-deactive-user.html" media="html"
												paramId="id" title="Name">
												<a
													href="active-deactive-user.html?status=${data.status},${data.id}"
													onclick="return confirm('Are you sure you want to Deactive?')"
													style="color: green;"> <c:out value="${data.name }"></c:out></a>
											</display:column>
										</c:if>
										<c:if test="${data.status =='De-Active'}">
											<display:column url="active-deactive-user.html" media="html"
												paramId="id" title="Name">
												<a
													href="active-deactive-user.html?status=${data.status},${data.id}"
													onclick="return confirm('Are you sure you want to active?')"
													style="color: red;"> <c:out value="${data.name }"></c:out></a>
											</display:column>
										</c:if>
										<display:column property="emailAddress" title="Email" />
										<display:column property="userType" title="User Type" />
										<display:column property="mobileNo" title="Mobile" />
										<display:column url="edit-user.html" media="html" paramId="id"
											paramProperty="id" title="Edit">
											<a href="edit-user.html?id=${data.id}"><i
												style="text-align: center;" class="fa fa-pencil"></i></a>
										</display:column>
										<display:column url="delete-user.html" media="html"
											paramId="id" paramProperty="id" title="Delete">
											<a href="delete-user.html?id=${data.id}"
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
		</div>
	</div>
</div> 


