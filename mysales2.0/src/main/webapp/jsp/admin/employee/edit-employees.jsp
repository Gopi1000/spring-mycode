<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

	<div class="warning">
		<div class="box-list">
			<div class="item">
				<div class="row ">


					<div class="text-center underline">
						<h3>Edit Employees</h3>

					</div>
					<br>


					<c:if test="${functionType eq 'edit'}">

						<form:form id="userForm" method="POST" modelAttribute="editUser"
							action="edit-employees">
						<form:hidden path="id" />
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
											<c:if test="${not empty typeUser }">
										     <form:option value="${typeUser}">${typeUser}</form:option>
											</c:if>
											<form:option value="">Select User</form:option>
											<form:option value="ROLE_CAMPAIGN">ROLE_CAMPAIGN</form:option>
											<form:option value="ROLE_MARKETING">ROLE_MARKETING</form:option>
											<form:option value="ROLE_SALES">ROLE_SALES</form:option>
											
										</form:select>
										<%-- <form:input type="text" path="userType"
									class="form-control required" /> --%>
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

							<div style="text-align: right; margin-right: 31px">

								<div class="form-group">
									<button type="submit" id="btnsubmit"
										class="btn btn-t-primary btn-theme lebal_align mt-20 ">Update</button>
									<a href="view-employees?page=1"><span
										class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
								</div>
							</div>
						</form:form>

					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<br>