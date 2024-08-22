
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#campaignform  input[type="text"]').each(function() {
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
	function showMessage() {
		alert("1")
		var value = $('#categoryId').val();
		if (value == 'Sms') {
			alert("11")
			document.getElementById("messageid").style.display = 'block';
		} else if (value == 'WhatsApp') {
			alert("13")
			document.getElementById("whatsappId").style.display = 'block';
		} else {
			alert("12")
			document.getElementById("messageid").style.display = 'none';
		}
	}
</script>
<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="campaignform" method="post"
		modelAttribute="campaign" commandName="campaign"
		class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			<form:hidden path="productService.serviceId" />
			<form:hidden path="course.courseId" />

			<div class="contact-form">
				<h3 class="text-center">Edit Campaign</h3>
			</div>
			<c:if test="${userType eq 'admin' }">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="border: 1px solid #e6e6e6; padding: 50px;">
					<div class="row">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Company Name <span
										class="required"><font class="f-color">*</font></span></span> <form:input
										type="text" id="companyBO.companyName"
										path="companyBO.companyName" placeholder="Company Name"
										class="form-control element-block" readonly="true" />
								</label>
							</div>
						</div>
						<c:choose>
							<c:when test="${companyType eq 'Service'}">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-group">
										<label class="element-block fw-normal font-lato"> <span
											class="element-block">Service Name <span
												class="required"><font class="f-color">*</font></span></span> <form:input
												type="text" id="productService.serviceName"
												path="productService.serviceName" placeholder="Service Name"
												class="form-control element-block" readonly="true" />
										</label>
									</div>
								</div>
								<%-- <form:hidden path="course.courseName" value="select" />
 --%>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${companyType eq 'Training'}">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-group">
										<label class="element-block fw-normal font-lato"> <span
											class="element-block">course Name <span
												class="required"><font class="f-color">*</font></span></span> <form:input
												type="text" id="course.courseName" path="course.courseName"
												placeholder="course Name" class="form-control element-block"
												readonly="true" />
										</label>
									</div>
								</div>
								<%-- 								<form:hidden path="productService.serviceName" value="select" />
 --%>
							</c:when>
						</c:choose>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Category <span class="required"><font
											class="f-color">*</font></span></span> <form:input type="text"
										id="ProductService.serviceName" path="category"
										placeholder="category" class="form-control element-block"
										readonly="true" />
								</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">Start
									Date<font class="f-color">*</font>
								</label>
								<form:input type="text" id="datepicker" path="startedTime"
									placeholder="Started Date" class="form-control element-block" />
								<label class="element-block fw-normal font-lato"></label> <label
									class="element-block fw-normal font-lato"
									style="font-size: 10px">EX:MM/DD/YYYY</label>
							</div>
						</div>

						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">End
									Date<font class="f-color">*</font>
								</label>
								<form:input type="text" id="datepickers" path="endTime"
									placeholder="End Date" class="form-control element-block" />
								<label class="element-block fw-normal font-lato"></label> <label
									class="element-block fw-normal font-lato"
									style="font-size: 10px">EX:MM/DD/YYYY</label>
							</div>
						</div>

						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Campaign Name <span
										class="required"><font class="f-color">*</font></span></span> <form:input
										type="text" id="companyPersonName" path="campaignName"
										placeholder="companyPerson Name"
										class="form-control element-block" />
								</label>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

							<div class="form-group">
								<label class="element-block fw-normal font-lato">Message
									<form:textarea type="text" path="message" id="messageid"
										class="form-control element-block" />
								</label>
							</div>
						</div>
					</div>
				</div>
		</div>
		</c:if>
		<c:if test="${userType eq 'company' }">

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;padding:50px; ">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Company Name <span class="required"><font
										class="f-color">*</font></span></span> <form:input type="text"
									id="companyBO.companyName" path="companyBO.companyName"
									placeholder="Company Name" class="form-control element-block"
									readonly="true" />
							</label>
						</div>
					</div>

					<c:choose>
						<c:when test="${companyType eq 'Service'}">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Service Name <span
											class="required"><font class="f-color">*</font></span></span> <form:input
											type="text" id="ProductService.serviceName"
											path="ProductService.serviceName" placeholder="Service Name"
											class="form-control element-block" readonly="true" />
									</label>
								</div>
							</div>
							<form:hidden path="course.courseName" value="select" />
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${companyType eq 'Training'}">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">course Name <span
											class="required"><font class="f-color">*</font></span></span> <form:input
											type="text" id="course.courseName" path="course.courseName"
											placeholder="course Name" class="form-control element-block"
											readonly="true" />
									</label>
								</div>
							</div>
							<form:hidden path="productService.serviceName" value="select" />
						</c:when>
					</c:choose>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Category <span class="required"><font
										class="f-color">*</font></span></span> <form:input type="text"
									id="ProductService.serviceId" path="category"
									placeholder="category" class="form-control element-block"
									readonly="true" />
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">Start
								Date<font class="f-color">*</font>
							</label>
							<form:input type="text" id="datepicker" path="startedTime"
								placeholder="Started Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"></label> <label
								class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:MM/DD/YYYY</label>
						</div>
					</div>

					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">End Date<font
								class="f-color">*</font>
							</label>
							<form:input type="text" id="datepickers" path="endTime"
								placeholder="End Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"></label> <label
								class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:MM/DD/YYYY</label>
						</div>
					</div>

					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Campaign Name <span
									class="required"><font class="f-color">*</font></span></span> <form:input
									type="text" id="companyPersonName" path="campaignName"
									placeholder="companyPerson Name"
									class="form-control element-block" />
							</label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

						<div class="form-group">
							<label class="element-block fw-normal font-lato">Message
								<form:textarea type="text" path="message" id="messageid"
									class="form-control element-block" />
							</label>
						</div>
					</div>
				</div>
			</div>
			</div>
		</c:if>
		<div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Submit</button>
		</div>
	</form:form>
</section>
</main>
