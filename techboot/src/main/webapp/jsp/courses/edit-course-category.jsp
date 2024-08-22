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
	<form:form name="myForm" id="courseform" method="post"
		modelAttribute="editcategoryBO" commandName="editcategoryBO"
		class="contact-form top" enctype="multipart/form-data">
		<div class="col-xs-12 col-sm-12">
			<h3 class="text-center">Edit Course Details</h3>
			<c:if test="${userType eq 'admin' }">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="border: 1px solid #e6e6e6;">
						<br>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										CourseCategoryName<span class="f-color">*</span>
									</label>
									<form:input type="text" path="courseCategoryName"
										id="courseCategoryName" class="form-control element-block"
										placeholder="CourseCategoryName" />
											<form:errors path="courseCategoryName" cssColor="errors"
										style="color: red"></form:errors>										
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
		<div class="text-right">
		<button type="submit" id="submit"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"Style ="min-width: 100px">Save
		</button>
	</div>

	</form:form>
</section>
</main>