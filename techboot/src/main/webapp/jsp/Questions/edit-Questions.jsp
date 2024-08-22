<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post" modelAttribute="editQuestionBo"
		commandName="editQuestionBo" class="contact-form top">

		<div class="contact-form">
			<h3 class="text-center">Edit Question</h3>
		</div>
		<div class="bg-gray" style="border: 1px solid #e6e6e6;">

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">Question<span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text" id="Question"
								path="question" placeholder="Question"
								class="form-control element-block" />
						</label>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> <span
							class="element-block">QuestionType <span class="required"><font
									class="f-color">*</font></span></span> <form:input type="text"
								id="questionType" path="questionType" placeholder="questionType"
								class="form-control element-block" />
						</label>
					</div>
				</div>
				<%-- <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">subcategory
					<span class="required"> <font style="color: red;">*
							</font></span>
						</label>
						<form:input type="text" id="subcategoryId" path="subCategoryBO.subcategory"
							placeholder="subcategory" class="form-control element-block"
							readonly="true" />

					</div>
				</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">category
					<span class="required"> <font style="color: red;">*
							</font></span>
						</label>
						<form:input type="text" id="categoryId" path="subCategoryBO.categoryBO.category"
							placeholder="subcategory" class="form-control element-block"
							readonly="true" />

					</div>
				</div> --%>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Answer1
							<span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="text" id="ans1" path="ans1" placeholder="ans1"
							class="form-control element-block" />
						<form:errors path="ans1" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">Answer2 <font
							class="f-color">*</font>
						</label>
						<form:input type="text" id="ans2" path="ans2" placeholder="ans2"
							class="form-control element-block" />
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">Answer3 <font
							class="f-color">*</font>
						</label>
						<form:input type="text" id="ans3" path="ans3" placeholder="ans3"
							class="form-control element-block" />
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">Answer4 <font
							class="f-color">*</font>
						</label>
						<form:input type="text" id="ans4" path="ans4" placeholder="ans4"
							class="form-control element-block" />
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">Correct
							Answer <font class="f-color">*</font>
						</label>
						<form:input type="text" id="correctAnswer" path="correctAnswer"
							placeholder="correctAnswer" class="form-control element-block" />
					</div>
				</div>
			</div>
			<div class="text-right">
				<button type="submit" id="submit"
					class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
					Style="min-width: 100px">Submit</button>
			</div>
		</div>

	</form:form>
</section>
</main>