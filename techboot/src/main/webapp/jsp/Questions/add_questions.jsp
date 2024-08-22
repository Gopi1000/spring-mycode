<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- <style>
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
</script> -->
<script>
 $(document).ready(function(){
		var isStatus = true;
	});
	 function questioncheck(){
		 var question = $("#question").val()
		 $.ajax({
			 type : "GET",
			 url : 'questionValidations',
			 data : 'question=' + question,
			 success : function(response){
				 alert(response);
				if(response=="QUESTIONS"){
					$("#questionNameErr").show();
					$("#questionNameErr").html("question already exist's")
					isStatus = false;
				}else{
					   $("#questionNameErr").hide();
					   $("#questionNameErr").css({
							"border" : "",
							"background" : ""
						});
				}
			 },
		 });
	  }
 </script>
<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post" modelAttribute="QuestionsBO"
		commandName="QuestionsBO" class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>

			</c:if>
			<div class="contact-form">
				<h3 class="text-center">Create Question</h3>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="border: 1px solid #e6e6e6;">
					<br>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">SubcategoryName <span
										class="required"><font class="f-color">*</font></span></span> <form:select
										id="subcategory" path="subCategoryBO.subcategory"
										 style="height: 42px;">
										<form:option value="">-- Select SubCategoryName--</form:option>
										<form:options itemLabel="subcategory"
											items="${subCategoryList}"
											itemValue="subcategoryId"></form:options>
									</form:select>
								</label>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">
									QuestionType <span class="required"><font
										class="f-color">*</font></span>
								</label>
								<form:select type="text"
									class="element-block fw-normal font-lato" id="questionType"
									path="questionType" style="height: 42px;">
									<form:option value="">Select Question Type</form:option>
									<form:options items="${questionTypeList}" />
								</form:select>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> <span
									class="element-block">Question<span class="required"><font
											class="f-color">*</font></span></span> <form:input type="text"
										path="question" placeholder="question" onchange="questioncheck()" autofocus="true"
										class="form-control element-block" />
										<form:errors path="question" cssClass="error"></form:errors>
								<div id="questionNameErr" style="color: red"></div>
								</label>
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">
										Answer1 <span class="required"><font class="f-color">*</font></span>
									</label>
									<form:input type="text" id="ans1" path="ans1"
										placeholder="ans1" class="form-control element-block" />
									<form:errors path="ans1" cssClass="error"></form:errors>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">Answer2
									<font class="f-color">*</font>
								</label>
								<form:input type="text" id="ans2" path="ans2" placeholder="ans2"
									class="form-control element-block" />
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">Answer3
									<font class="f-color">*</font>
								</label>
								<form:input type="text" id="ans3" path="ans3" placeholder="ans3"
									class="form-control element-block" />
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">Answer4
									<font class="f-color">*</font>
								</label>
								<form:input type="text" id="ans4" path="ans4" placeholder="ans4"
									class="form-control element-block" />
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="form-group">
								<label class="element-block fw-normal font-lato">Correct Answer
									<font class="f-color">*</font>
								</label>
								<form:input type="text" id="correctAnswer" path="correctAnswer" placeholder="correctAnswer"
									class="form-control element-block" />
							</div>
						</div>
					</div>
					<br>
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