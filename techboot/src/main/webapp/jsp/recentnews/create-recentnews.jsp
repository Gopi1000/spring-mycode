<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#recendnewsform input[type="text"]').each(function() {
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

<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="recendnewsform" method="post" modelAttribute="recentNewsBo"
		commandName="recentNewsBo" class="contact-form top"
		enctype="multipart/form-data">
		<div class="col-xs-12 col-sm-12">
			<h3 class="text-center">Create Recent News Details</h3>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="border: 1px solid #e6e6e6">
					<br>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> Title
									Name <span class="required"><font style="color: red;">*</font></span>
								</label>
								<form:input type="text" path="titleName"
									class="form-control element-block" placeholder="Title Name"
									autofocus="true" />

								<form:errors path="titleName" cssColor="errors"
									style="color: red"></form:errors>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> Date <span
									class="required"><font style="color: red;">*</font></span>
								</label>
								<form:input type="text" path="date" id="datepicker"
									class="form-control element-block" placeholder="Date" />
								<form:errors path="date" cssColor="errors" style="color: red"></form:errors>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">
									Descriptions <span class="required"><font
										style="color: red;">*</font></span>
								</label>
								<form:input type="text" path="descriptions"
									class="form-control element-block"
									placeholder="Descriptions  only allow for 110 Charcters"
									maxlength="110" />

								<form:errors path="descriptions" cssColor="errors"
									style="color: red"></form:errors>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> Author
									Name <span class="required"><font style="color: red;">*</font></span>
								</label>
								<form:input type="text" path="authorName"
									class="form-control element-block" placeholder="Author Name" />

								<form:errors path="authorName" cssColor="errors"
									style="color: red"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-md-4 col-xs-12 col-sm-6">
							<label> RecentNews Image</label><font style="color: red;">*</font>
							<input type="file" name="recentNewsLogos" path="recentNewsLogo"
								id="PhotoInput" placeholder="Photo" onchange="fileCheck(this)" />
							<div class="color-black-mute">
								<small>Logo jpeg,jpg,png Formats Only</small>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Save</button>
		</div>
	</form:form>
</section>
</main>
