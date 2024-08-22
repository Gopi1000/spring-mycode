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

		<div class="contact-form">
			<h3 class="text-center">Create Message</h3>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">

						<div class="form-group">
							<label class="element-block fw-normal font-lato">Date</label>
							<form:input type="text" id="datepicker" path="Date"
								placeholder="Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:01/30/2019</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">Message
								<form:textarea type="text" path="message"
									class="form-control element-block" placeholder="Message" />
							</label>
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