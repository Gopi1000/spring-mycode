<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>





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
		});
	});	
</script>
<script type="text/javascript">

	
				
			//industry validations
			var industryType = $("#functionInput").val();
			if (industryType == "Select Student Name") {
				isValid = false;
				$("#functionInput").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}
			if (isStatus == false) {
				alert("Please fill required field's");
				isValid = false;
			}
			if (isValid == false) {
				e.preventDefault();
			}
		});
	});
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			var courseName = $("#functionInput").val();
			if (courseName == "Select Course Type") {
				$("#functionInput").css({
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

<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post" modelAttribute="studentCouresBO"
		commandName="studentCouresBO" class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Error:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<div class="contact-form">
				<h3 class="text-center">Enrollment Course</h3>
			</div>

			<div class="row" style="border: 1px solid #e6e6e6;">
				<br>
				<c:choose>
					<c:when test="${userType eq 'admin' }">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-2 col-md-12 col-sm-12 col-xs-12"></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label>Enrollment Course <span class="required"><font
											class="f-color">*</font></span></label>
									<form:select type="text"
										class="element-block fw-normal font-lato" id="functionInput"
										path="courseBO.courseName" style="height: 42px;">
										<form:option value="Select Course Type">Select Course</form:option>
										<form:options items="${courselist}" itemLabel="courseName"
											itemValue="courseId" />
									</form:select>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label>Student Name <span class="required"><font
											class="f-color">*</font></span></label>
									<form:select type="text"
										class="element-block fw-normal font-lato" id="functionInput"
										path="studentLoginId.StudentRegisterBO.firstName"
										style="height: 42px;">
										<form:option value="Select Student Name">Select Student Name</form:option>
										<form:options items="${studentList}" itemLabel="firstName"
											itemValue="studentRegisterId" />
									</form:select>
								</div>
							</div>
							<br>
							<div class="col-lg-2 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<div class="text-right">
										<button type="submit" id="submitid"
											class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
											Style="margin-bottom: 27px; margin-top: 6px;">Save</button>
									</div>
								</div>
							</div>
						</div>

					</c:when>

					<c:otherwise>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-2 col-md-12 col-sm-12 col-xs-12"></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label>Enrollment Course <span class="required"><font
											class="f-color">*</font></span></label>
									<form:select type="text"
										class="element-block fw-normal font-lato" id="functionInput"
										path="courseBO.courseName" style="height: 42px;">
										<form:option value="Select Course Type">Select Course</form:option>
										<form:options items="${courselist}" itemLabel="courseName"
											itemValue="courseId" />
									</form:select>
								</div>
							</div>
							<br>
							<div class="col-lg-2 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<div class="text-right">
										<button type="submit" id="submitid"
											class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
											Style="margin-bottom: 27px; margin-top: 6px;">Save</button>
									</div>
								</div>
							</div>
						</div>
			</div>

			</c:otherwise>
			</c:choose>
		</div>
	</form:form>
</section>
</main>