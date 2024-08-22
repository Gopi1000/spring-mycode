<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	function fileCheck(obj) {
		var fileExtension = [ 'jpeg', 'jpg', 'png', 'bmp', 'gif' ];
		if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
				fileExtension) == -1) {
			document.getElementById("PhotoInput").value = '';
			alert("Only '.jpeg','.jpg', 'gif', '.png',  '.bmp' formats are allowed.");
		}
	}
</script>
<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#eventform input[type="text"]').each(function() {
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

<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="eventform" method="post" modelAttribute="eventsBO"
		commandName="eventsBO" class="contact-form top"
		enctype="multipart/form-data">
		<div class="col-xs-12 col-sm-12">
			<h3 class="text-center">Create Events Details</h3>
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
											path="companyBO.companyName" id="companyId"
											onchange="companyService()" style="height: 42px;">
											<form:option value="Select">-- Select --</form:option>
											<form:options itemLabel="companyName" items="${companylist}"
												itemValue="companyId"></form:options>
										</form:select>
									</label>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> Event
										Name <span class="f-color">*</span>
									</label>
									<form:input type="text" path="titleName"
										class="form-control element-block" placeholder="Event Name" />

									<form:errors path="titleName" cssColor="errors"
										style="color: red"></form:errors>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> Date<span
										class="f-color">*</span>
									</label>
									<form:input type="text" path="date" id="datepicker"
										class="form-control element-block" placeholder="Date" />
									<form:errors path="date" id="datepicker" cssColor="errors"
										style="color: red"></form:errors>
									<div id="datepicker" path="date" style="color: red"></div>

								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Timing <span class="f-color">*</span>
									</label>
									<form:input type="text" path="timing"
										class="form-control element-block" placeholder="Timing " />

									<form:errors path="timing" cssColor="errors" style="color: red"></form:errors>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Address <span class="f-color">*</span>
									</label>
									<form:input type="text" path="address"
										class="form-control element-block" placeholder="Address" />

									<form:errors path="address" cssColor="errors"
										style="color: red"></form:errors>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<label> Event Logo </label><span class="f-color">*</span> <input
									type="file" name="eventsLogos" path="eventsLogo"
									id="PhotoInput" placeholder="Photo" onchange="fileCheck(this)" />
								<div class="color-black-mute">
									<small>Logo jpeg,jpg,png Formats Only</small>
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
						style="border: 1px solid #e6e6e6;">
						<br>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> Event
										Name <span class="f-color">*</span>
									</label>
									<form:input type="text" path="titleName"
										class="form-control element-block" placeholder="Event Name" />

									<form:errors path="titleName" cssColor="errors"
										style="color: red"></form:errors>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> Date<span
										class="f-color">*</span>
									</label>
									<form:input type="text" path="date" id="datepicker"
										class="form-control element-block" placeholder="Date" />
									<form:errors path="date" id="datepicker" cssColor="errors"
										style="color: red"></form:errors>
									<div id="datepicker" path="date" style="color: red"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Timing <span class="f-color">*</span>
									</label>
									<form:input type="text" path="timing"
										class="form-control element-block" placeholder="Timing " />

									<form:errors path="timing" cssColor="errors" style="color: red"></form:errors>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Address <span class="f-color">*</span>
									</label>
									<form:input type="text" path="address"
										class="form-control element-block" placeholder="Address" />

									<form:errors path="address" cssColor="errors"
										style="color: red"></form:errors>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<label> Event Logo </label><span class="f-color">*</span> <input
									type="file" name="eventsLogos" path="eventsLogo"
									id="PhotoInput" placeholder="Photo" onchange="fileCheck(this)" />
								<div class="color-black-mute">
									<small>Logo jpeg,jpg,png Formats Only</small>
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


