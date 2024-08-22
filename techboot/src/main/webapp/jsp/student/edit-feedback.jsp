<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" method="post" modelAttribute="feedBackBo"
		commandName="feedBackBo" class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			
			<div class="contact-form">
				<h3 class="text-center"> Edit Feedback</h3>
			</div>

			<div class="row" style="border: 1px solid #e6e6e6;">
				<!-- <br> -->

				<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> -->

					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div class="form-group">
							<label>Feedback <span class="required"><font
									class="f-color">*</font></span></label>
							<form:select type="text"
								class="element-block fw-normal font-lato" 
								path="courseBO.courseName" style="height: 42px;">
								<form:option value="Select Course Type">Select Cousers</form:option>
								<form:options items="${enrollmentList}"
									itemLabel="courseBO.courseName" itemValue="courseBO.courseId" />
							</form:select>
						</div>
					</div>
					
					<!-- <br> -->

					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

						<div class="form-group">
						 
							<label>Star Rating <span class="required"><font
									class="f-color">*</font></span></label>

							<form:select path="starRating" class="form-control required"
								placeholder="starRating" id="genderId">
								<form:option value="starRating">Star Rating</form:option>
								<form:option value="*">*</form:option>
								<form:option value="**">**</form:option>
								<form:option value="***">***</form:option>
								<form:option value="****">****</form:option>
								<form:option value="*****">*****</form:option>
							</form:select>
						</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div class="form-group">
								<label>Command<span class="font10 text-danger"> *</span></label>
								 <form:input type="text" path="command" 
									class="form-control required" placeholder="Command"
									maxlength="150" />
								<form:errors path="command" class="input_error" />
							</div>
							</div>
							</br>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-group">
								<div class="text-right">
									<button type="submit" 
										class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
										Style="margin-bottom: 27px; margin-top: 6px;">Save</button>
								</div>
							</div>
						</div>
					<!-- </div> -->
				</div>
			</div>


		</div>
	</form:form>
</section>
</main>

</body>
</html>