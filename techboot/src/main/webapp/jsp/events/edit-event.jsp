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
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('input[type="file"]').each(function() {
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

<main id="main"><!-- <header
	
	 <!-- feedback block -->
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="eventform" method="post" modelAttribute="event"
		commandName="event" class="contact-form top"
		enctype="multipart/form-data">
		<form:hidden path="imageName" />
		<h3 class="text-center">Edit Events Details</h3>
		<div class="row">
			<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Title
						Name <span class="f-color">*</span>
					</label>
					<form:input type="text" path="titleName"
						class="form-control element-block" placeholder="Title Name" />

					<form:errors path="titleName" cssColor="errors" style="color: red"></form:errors>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
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
			<div class="col-xs-12 col-sm-6">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Timing <span
						class="f-color">*</span>
					</label>
					<form:input type="text" path="timing"
						class="form-control element-block" placeholder="Timing " />

					<form:errors path="timing" cssColor="errors" style="color: red"></form:errors>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="form-group">
					<label class="element-block fw-normal font-lato"> Address <span
						class="f-color">*</span>
					</label>
					<form:input type="text" path="address"
						class="form-control element-block" placeholder="Address" />

					<form:errors path="address" cssColor="errors" style="color: red"></form:errors>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-4">
				<label> Event Logo </label><span class="f-color">*</span> <input
					type="file" name="eventsLogos" path="eventsLogo" id="PhotoInput"
					placeholder="Photo" onchange="fileCheck(this)" />
				<div class="color-black-mute">
					<small>Logo jpeg,jpg,png Formats Only</small>
				</div>
			</div>
		</div>
		<form:hidden path="companyBO.companyId" />
		<div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Submit</button>
		</div>
	</form:form>
</section>
</main>


