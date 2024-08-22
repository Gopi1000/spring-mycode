<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="resource/js/nicEdit-latest.js"></script>
<script type="text/javascript">
	bkLib.onDomLoaded(nicEditors.allTextAreas);
</script>

<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#myform input[type="text"]').each(function() {
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
	<form:form name="myForm" id="myform" method="post"
		modelAttribute="courseDetails" commandName="courseDetails"
		class="contact-form top">
		<h3 class="text-center">Edit Course Details</h3>
		<form:hidden path="curseBO.courseId" />

		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="border: 1px solid #e6e6e6;">
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Course Name <span class="required"><font
										style="color: red;">*</font></span></span> <form:input type="text"
									path="curseBO.courseName" id="courseName"
									placeholder="Course Name" class="form-control element-block"
									readonly="true" />
							</label>
						</div>
					</div>
				<!-- </div> -->
				 	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Price <span class="required"><font
										style="color: red;">*</font></span></span> <form:input type="text"
									path="price" id="price" placeholder="Price"
									class="form-control element-block" onfocus="this.blur();" />
							</label>
						</div>
					</div>
				</div>  

				<div class="form-group adminbox">
					<label>Course Description</label> <font style="color: red;">*</font>
					<form:textarea type="text" class="form-control" maxlength="50000"
						path="description" id="DescriptionInput"
						placeholder="Job Description" />
					<form:errors path="description" cssClass="error" />
				</div>

				<div class="form-group adminbox">
					<label>Designed</label> <font style="color: red;">* </font>
					<form:textarea type="text" class="form-control" maxlength="50000"
						path="designed" id="DescriptionInput"
						placeholder="Job Description" />
					<form:errors path="description" cssClass="error" />
				</div>

				<div class="form-group adminbox">
					<label>Curriculum</label> <font style="color: red;">* </font>
					<form:textarea type="text" class="form-control" maxlength="50000"
						path="curriculum" id="DescriptionInput"
						placeholder="Job Description" />
					<form:errors path="description" cssClass="error" />

				</div>

				<div class="form-group adminbox">
					<label>Classes Schedule</label> <font style="color: red;">*
					</font>
					<form:textarea type="text" class="form-control" maxlength="50000"
						path="classesSchedule" id="DescriptionInput"
						placeholder="Job Description" />
					<form:errors path="description" cssClass="error" />
				</div>
				<div class="form-group adminbox">
					<label>key Features</label> <font style="color: red;">* </font>
					<form:textarea type="text" class="form-control" maxlength="50000"
						path="keyfeatures" id="DescriptionInput"
						placeholder="Job Description" />
					<form:errors path="description" cssClass="error" />

				</div>
			</div>
		</div>
		<div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Submit</button>
		</div>
	</form:form>
</section>
</main>


