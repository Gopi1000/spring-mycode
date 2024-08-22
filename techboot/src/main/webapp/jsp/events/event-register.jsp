<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<style>
.error {
	color: red;
}
</style>
<script>
	$(document).ready(function() {
		$('#submits').click(function(e) {
			var isValid = true;

			$('input[type="text"]').each(function() {
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
			// Email Validations 			
			var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var emailAddress = $("#emailIdInput").val();
			if (!emailValidations.test(emailAddress)) {
				$("#emailIdInputErr").show();
				$("#emailIdInputErr").html("Please Enter Valid Email ");
				var isValid = false;
			} else {
				$("#emailIdInputErr").hide();
				$("#emailIdInputErr").css({
					"border" : "",
					"background" : ""
				});
			

			if (isValid == false)
				e.preventDefault();

		});
	});
	}
</script>


<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post" modelAttribute="eventRegister"
		commandName="eventRegister" class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center"> Book Your Sheet</h3>
			</div>
			<form:hidden path="eventsBO.eventId" />
			<div class="row bg-gray" style="border: 1px solid #e6e6e6;">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="border: 1px solid #e6e6e6;">
					<br>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

							<div class="form-group">
								<label class="element-block fw-normal font-lato"> Event
									Title <font style="color: red;">* </font>
								</label>
								<form:input type="text" id="address" path="eventsBO.titleName"
									placeholder="Event Title" class="form-control element-block"
									readonly="true" />

							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">
									Candidate Name <font style="color: red;">* </font>
								</label>
								<form:input type="text" id="customerName" path="candidateName"
									autocomplete="off" placeholder="Candidate Name"
									class="form-control element-block" />

								<form:errors path="candidateName" cssClass="error"></form:errors>
								<%-- <form:errors path="firstName" cssClass="errors" /> --%>
								<!-- <div id="companyPersonNameErr" style="color: red"></div> -->
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> Email
									Address <font style="color: red;">* </font>
								</label>
								<form:input type="text" id="emailIdInput" path="emailAddress"
									placeholder="Email Address" class="form-control element-block" />

								<form:errors path="emailAddress" cssClass="error"></form:errors>
								<div id="emailIdInputErr" style="color: red"></div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> Gender
									<font style="color: red;">* </font>
								</label>
								<form:radiobutton path="gender" value="Male" />
								Male
								<form:radiobutton path="gender" value="Female" />
								Female
								<form:radiobutton path="gender" value="Others" />
								Others <br>
								<form:errors path="gender" cssClass="error"></form:errors>

							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

							<div class="form-group">
								<label class="element-block fw-normal font-lato">
									Qualification<font style="color: red;">* </font>
								</label>
								<form:input type="text" id="qualification" path="qualification"
									placeholder="Qualification" class="form-control element-block" />
								<form:errors path="qualification" cssClass="error"></form:errors>

							</div>

						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">
									Professional <font style="color: red;">* </font>
								</label>
								<form:input type="text" id="professional" path="professional"
									placeholder="Professional" class="form-control element-block" />
								<form:errors path="professional" cssClass="error"></form:errors>
								<%-- <form:errors path="customerType" cssClass="errors"></form:errors> --%>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">
									Contact Number <font style="color: red;">* </font>
								</label>
								<form:input type="text" id="contactNumber" path="contactNumber"
									placeholder="Contact Number" class="form-control element-block"
									maxlength="10" />
								<form:errors path="contactNumber" cssClass="error"></form:errors>

								<
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

							<div class="form-group">
								<label class="element-block fw-normal font-lato">
									Address <font style="color: red;">* </font>
								</label>
								<form:input type="text" id="address" path="address"
									placeholder="Address" class="form-control element-block" />
								<form:errors path="address" cssClass="error"></form:errors>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<br>
		<div class="text-center contact-form">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold">Submit
			</button>
		</div>

	</form:form>
</section>
</main>