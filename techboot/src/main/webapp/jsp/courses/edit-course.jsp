<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.errors {
	color: red;
}
</style>

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

			$('form#courseform input[type="text"]').each(function() {
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
	<form:form name="myForm" id="courseform" method="post" modelAttribute="editCourseBo"
		commandName="editCourseBo" class="contact-form top"
		enctype="multipart/form-data">
       <form:hidden path="companyBO.companyId"/>
       <input type="hidden" name="imageName" value="${editCourseBo.imageName}"/>
		<div class="col-xs-12 col-sm-12">
			<h3 class="text-center">Edit Course Details</h3>
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
											class="required"><font class="f-color">*</font></span></span>
											<form:input type="text" path="companyBO.companyName" id="companyName"
											placeholder="Company Name" class="form-control element-block"
											readonly="true"/>
									</label>
								</div>
								</div>
								
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Category <span
											class="required"><font class="f-color">*</font></span></span> <form:select
											path="courseCategoryBO.courseCategoryId" id="categoryName"
											onchange="companyService()" style="height: 42px;">
											<form:option value="0">-- Select --</form:option>
											<form:options itemLabel="courseCategoryName" items="${courseCategoryList}"
												itemValue="courseCategoryId"></form:options>
										</form:select>
									</label>
								</div>
						</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Course Name <span class="f-color">*</span>
									</label>
									<form:input type="text" path="courseName"
										class="form-control element-block" placeholder=" Course Name" />
									<form:errors path="courseName" cssColor="errors"
										style="color: red"></form:errors>

								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Author Name<span class="f-color">*</span>
									</label>
									<form:input type="text" path="authorName" id="authorName"
										class="form-control element-block" placeholder="Author Name" />
									<form:errors path="authorName" cssColor="errors"
										style="color: red"></form:errors>

								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> Fees
										<span class="f-color">*</span>
									</label>
									<form:input type="text" path="fees"
										class="form-control element-block" placeholder=" fees" />
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
								<label> Course Logo </label><span class="f-color">*</span> <input
									type="file" name="coursesLogos" path="courseLogo"
									id="PhotoInput" placeholder="Photo" onchange="fileCheck(this)" />
								<div class="color-black-mute">
									<small>Logo jpeg,jpg,png Formats Only</small>
								</div>
							</div>
						</div>
					</div>
			</c:if>
			<c:if test="${userType eq 'company'}">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="border: 1px solid #e6e6e6;">
						<br>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Company Name <span
											class="required"><font class="f-color">*</font></span></span>
											<form:input type="text" path="companyBO.companyName" id="companyName"
											placeholder="Company Name" class="form-control element-block"
											readonly="true"/>
									</label>
								</div>
								</div>
								
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Category <span
											class="required"><font class="f-color">*</font></span></span> <form:select
											path="courseCategoryBO.courseCategoryId" id="categoryName"
											onchange="companyService()" style="height: 42px;">
											<form:option value="0">-- Select --</form:option>
											<form:options itemLabel="courseCategoryName" items="${courseCategoryList}"
												itemValue="courseCategoryId"></form:options>
										</form:select>
									</label>
								</div>
						</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Course Name <span class="f-color">*</span>
									</label>
									<form:input type="text" path="courseName"
										class="form-control element-block" placeholder=" Course Name" />
									<form:errors path="courseName" cssColor="errors"
										style="color: red"></form:errors>

								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Author Name<span class="f-color">*</span>
									</label>
									<form:input type="text" path="authorName" id="authorName"
										class="form-control element-block" placeholder="Author Name" />
									<form:errors path="authorName" cssColor="errors"
										style="color: red"></form:errors>

								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> Fees
										<span class="f-color">*</span>
									</label>
									<form:input type="text" path="fees"
										class="form-control element-block" placeholder=" fees" />
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
								<label> Course Logo </label><span class="f-color">*</span> <input
									type="file" name="coursesLogos" path="courseLogo"
									id="PhotoInput" placeholder="Photo" onchange="fileCheck(this)" />
								<div class="color-black-mute">
									<small>Logo jpeg,jpg,png Formats Only</small>
								</div>
							</div>
						</div>
					</div>
			</c:if>
		</div>
		<div class="text-right">
			<button type="submit" id="submit"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Submit</button>
		</div>
	</form:form>
</section>
</main>