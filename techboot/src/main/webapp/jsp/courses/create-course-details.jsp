<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="resource/js/nicEdit-latest.js"></script>
<script type="text/javascript">
	bkLib.onDomLoaded(nicEditors.allTextAreas);
</script>
<!-- 
<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#courseformid input[type="text"]').each(function() {
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
 -->




<script type="text/javascript">
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			var priceId = $("#priceId").val();
			if (priceId == "0.0") {
				isValid = false;
				$("#priceId").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}

			var courseName = $("#courseName").val();
			if (courseName == "") {
				isValid = false;
				$("#courseName").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>


<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="courseformid" method="post"
		modelAttribute="courseDetailsBO" commandName="courseDetailsBO"
		class="contact-form top">
	 <form:hidden path="curseBO.companyBO.companyId"/>
		<form:hidden path="curseBO.courseId"/>  

		<h3 class="text-center">Create Course Details</h3>
		<c:if test="${userType eq 'admin' }">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="border: 1px solid #e6e6e6; padding: 50px;">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Course Name <span class="required"><font
											style="color: red;">*</font></span></span> <form:select id="courseName"
										path="curseBO.courseName" style="height: 43px;">
										<form:option value="">--- Select  ---</form:option>
										<form:options itemLabel="courseName" items="${courselist}"
											itemValue="courseId"></form:options>
									</form:select>
								</label>
							</div>
						</div>
						
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
						</div>
						
						 <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Price <span class="required"><font
											style="color: red;">*</font></span></span>
								</label>
								<form:input type="text" path="price" id="priceId"
									class="form-control element-block" placeholder=" Fees" />

							</div>

						</div>
						</div>

					<div class="form-group">
						<label>Course Description</label> <font style="color: red;">*
						</font>
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
					</div> 
			<!-- <div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Save</button>
		</div> -->
		</c:if>
		<c:if test="${userType eq 'company' }">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="border: 1px solid #e6e6e6; padding: 50px;">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Course Name <span class="required"><font
											style="color: red;">*</font></span></span> <form:select id="courseName"
										path="curseBO.courseName" style="height: 43px;">
										<form:option value="">--- Select  ---</form:option>
										<form:options itemLabel="courseName" items="${courselist}"
											itemValue="courseId"></form:options>
									</form:select>
								</label>
							</div>
						</div>
			 		<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Price <span class="required"><font
											style="color: red;">*</font></span></span>
								</label>
								<form:input type="text" path="price" id="priceId"
									class="form-control element-block" placeholder=" Fees" />

							</div>
						</div>
					</div>
					<div class="form-group">
						<label>Course Description</label> <font style="color: red;">*
						</font>
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
			<!-- <div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Save</button>
		</div> -->

		</c:if>
		<div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Save</button>
		</div>
	</form:form>
</section>
</main>
<!-- <h1> hello</h1> -->


