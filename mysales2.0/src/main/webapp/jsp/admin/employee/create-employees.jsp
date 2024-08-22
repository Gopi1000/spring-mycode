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

		<div class="box-list">
			<div class="item">
				<div class="row ">

					<c:if test="${functionType eq 'add'}">
						<div class="text-center underline">
							<h3>Create Employees</h3>
						</div>
						<br>
						<form:form method="POST" id="addForm"
							action="create-employees" modelAttribute="user">
							<div class="col-sm-12">
								<div class="col-sm-4">
									<div class="form-group">
										<label path="Name"> Name <span
											class="font10 text-danger">*</span></label>
										<form:input id="inputFirstName" type="text" path="name"
											class="form-control required" placeholder="Name"
											maxlength="150" />
										<form:errors path="name" class="input_error" />
									</div>
								</div>


								<div class="col-sm-4">
									<div class="form-group">
										<label path="EmailAddress">Email Address <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="emailAddress"
											class="form-control required" placeholder="EmailAddress" />
										<form:errors path="emailAddress" class="input_error" />
									</div>
								</div>

								<div class="col-sm-4">
									<div class="form-group">
										<label path="User Type">User Type<span
											class="font10 text-danger">*</span></label>
										<form:select type="text" path="userType"
											class="form-control required">
											<form:option value="">Select User</form:option>
											<form:option value="ROLE_CAMPAIGN">ROLE_CAMPAIGN</form:option>
											<form:option value="ROLE_MARKETING">ROLE_MARKETING</form:option>
											<form:option value="ROLE_SALES">ROLE_SALES</form:option>


										</form:select>
										<%-- <form:input type="text" path="userType" class="form-control required"
										placeholder="usertype" /> --%>
										<form:errors path="userType" class="input_error" />
									</div>
								</div>


							</div>
							<div class="col-sm-12">

								<div class="col-sm-4">
									<div class="form-group">
										<label path="password"> Password <span
											class="font10 text-danger">*</span></label>
										<form:input type="password" path="password"
											class="form-control required" placeholder="password"
											maxlength="8" />
										<form:errors path="password" class="input_error" />
									</div>
								</div>

								<div class="col-sm-4">
									<div class="form-group">
										<label path="Confirm password"> Confirm password <span
											class="font10 text-danger">*</span></label>
										<form:input type="password" path="confirmPassword"
											class="form-control required" placeholder="confirm password"
											maxlength="8" />
										<form:errors path="confirmPassword" class="input_error" />
									</div>
								</div>





								<div class="col-sm-4">
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


							<div style="text-align: right; margin-right: 31px">
								<button type="submit" id="btnsubmit"
									class="btn btn-t-primary btn-theme lebal_align mt-20"
									style="text-align: right;">Submit</button>
								<a href="view-employees?page=1"><span
									class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
							</div>
						</form:form>
					</c:if>

				</div>
			</div>
		</div>
	</div>
</div>
<br>



