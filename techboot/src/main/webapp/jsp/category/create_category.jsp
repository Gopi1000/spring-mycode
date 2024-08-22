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
			
			var categoryName = $("#categoryName").val();
			if(null!=categoryName && !categoryName == ''){
			$.ajax({
				type : "GET",
				url : 'check_categoryName',
				data : 'categoryName='+ categoryName,
				async: false,
				success : function(data) {
					if(data == "OK"){
						$("#CategoryNameErr").show();
 						$("#CategoryNameErr").html("CourseCategoryName already exist's");
						isValid = false;
				   }else{
					   $("#CategoryNameErr").hide();
					   $("#CategoryNameErr").css({
							"border" : "",
							"background" : ""
						});
					isValid = true;
					}
				}
				});
			}
			if (isValid == false)
				e.preventDefault();
	
		});
	});
			
			
			
</script>
<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post"
		modelAttribute="CategoryBO" commandName="CategoryBO"
		class="contact-form top" enctype="multipart/form-data">
		<div class="col-xs-12 col-sm-12">
			<h3 class="text-center">Create Category</h3>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success" style="margin-right: -1%;margin-left: -1%;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Error:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success" style="margin-right: -1%;margin-left: -1%;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>
			
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="border: 1px solid #e6e6e6;">
						<br>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										 CategoryName<span class="f-color">*</span>
									</label>
									<form:input type="text" path="category"
										id="categoryName" class="form-control element-block"
										placeholder="CategoryName"/>
											<form:errors path="category" cssColor="errors"
										style="color: red"></form:errors>
										<div id="CategoryNameErr" style="color: red" ></div>
										
										
										
								</div>
							</div>
						</div>
					</div>
				</div>
			
		</div>
		<div class="text-right">
		<button type="submit" id="submit"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"Style ="min-width: 100px">Save
		</button>
	</div>

	</form:form>
</section>
</main>