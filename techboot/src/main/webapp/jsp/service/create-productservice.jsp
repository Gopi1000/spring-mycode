<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.error {
	color: red;
}
<!--
</style>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

<script>
	$(function() {
		$("#datepickers").datepicker();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			var CompanyName = $("#companyName").val();
			if (CompanyName == "Select") {
				$("#companyName").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}
		});

	});
</script>

<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#serviceform input[type="text"]').each(function() {
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
			});

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>

<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="serviceform" method="post" modelAttribute="productService"
		commandName="productService" class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>

			</c:if>
			<div class="contact-form">
				<h3 class="text-center">Create Service</h3>
			</div>
			<c:if test="${userType eq 'admin' }">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="border: 1px solid #e6e6e6;">
						<br>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Company Name <span
											class="required"><font class="f-color">*</font></span></span> <form:select
											id="companyName" path="companyBO.companyName"
											style="height: 43px;">
											<form:option value="Select">Select</form:option>
											<form:options itemLabel="companyName" items="${companyList}"
												itemValue="companyId"></form:options>
										</form:select>
									</label>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Service Name <span class="required"><font
											class="f-color">*</font></span>
									</label>
									<form:input type="text" id="serviceName" path="serviceName"
										placeholder="Service Name" class="form-control element-block" />
									<form:errors path="serviceName" cssClass="error"></form:errors>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Specification <span class="required"><font
											class="f-color">*</font></span>
									</label>
									<form:input type="text" id="duration"
										path="ServiceSpecification" placeholder="ServiceSpecification"
										class="form-control element-block" />
									<form:errors path="ServiceSpecification" cssClass="error"></form:errors>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Duration <span class="required"><font class="f-color">*</font></span>
									</label>
									<form:input type="text" id="duration" path="duration"
										placeholder="Duration" class="form-control element-block" />
									<form:errors path="duration" cssClass="error"></form:errors>
								</div>
							</div>
						</div>
					<%--	<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">Start
										Date<font class="f-color">*</font>
									</label>
									<form:input type="text" id="datepicker" path="startDate"
										placeholder="Start Date" class="form-control element-block" />
									<label class="element-block fw-normal font-lato"
										style="font-size: 10px">EX:MM/DD/YYYY</label>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

								<div class="form-group">
									<label class="element-block fw-normal font-lato">End
										Date<font class="f-color">*</font>
									</label>
									<form:input type="text" id="datepickers" path="endDate"
										placeholder="End Date" class="form-control element-block" />
									<label class="element-block fw-normal font-lato"
										style="font-size: 10px">EX:MM/DD/YYYY</label>
								</div>
							</div>
						</div>--%>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Service Charge<span class="required"><font
												class="f-color">*</font></span></span> <form:input type="text" id="fees"
											path="fees" placeholder="fees"
											class="form-control element-block" />
									</label>
								</div>
							</div>
						</div>
						<br>
					</div>
				</div>
			</c:if>
			<c:if test="${userType eq 'company' }">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="border: 1px solid #e6e6e6;padding: 50px;">
						<br>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Service Name <span class="required"><font
											class="f-color">*</font></span>
									</label>
									<form:input type="text" id="serviceName" path="serviceName"
										placeholder="Service Name" class="form-control element-block"
										autofocus="true" />
									<form:errors path="serviceName" cssClass="error"></form:errors>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Service Charge<span class="required"><font
												class="f-color">*</font></span></span> <form:input type="text" id="fees"
											path="fees" placeholder="fees"
											class="form-control element-block" />
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Specification <span class="required"><font
											class="f-color">*</font></span>
									</label>
									<form:input type="text" id="duration"
										path="ServiceSpecification" placeholder="ServiceSpecification"
										class="form-control element-block" />
									<form:errors path="ServiceSpecification" cssClass="error"></form:errors>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Duration <span class="required"><font class="f-color">*</font></span>
									</label>
									<form:input type="text" id="duration" path="duration"
										placeholder="weekly/monthly/byweekly" class="form-control element-block" />
									<form:errors path="duration" cssClass="error"></form:errors>
								</div>
							</div>
						</div>
												<br>
					</div>
				</div>
			</c:if>
		</div>

		<div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Save</button>
		</div>

	</form:form>
</section>
</main>