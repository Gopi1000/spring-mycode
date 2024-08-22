<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
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

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>
<!-- main container of all the page elements -->
<main id="main">
<section class="container instructor-profile-block">
	<form:form name="myForm" method="post" modelAttribute="message"
		commandName="message" class="contact-form ">
		<div class="row">
			<h3 class="text-center">Edit Message</h3>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

						<div class="form-group">
							<label>Create Date</label>
							<form:input type="text" id="datepicker" path="Date"
								placeholder="Date" class="form-control element-block" />
							<label style="font-size: 9px; margin-top: -12px;">Date
								Ex: 08/20/2019</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="margin-top: 0px">
						<label>Create Message</label>
						<div class="form-group">
							<form:textarea type="text" path="message"
								class="form-control element-block" placeholder="Message" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="text-center">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold">Submit
			</button>
		</div>
	</form:form>
</section>
</main>
