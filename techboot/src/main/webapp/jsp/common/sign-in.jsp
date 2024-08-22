<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script>
	 $('form:first *:input[type!=hidden]:first').focus(); 
</script>

<script>
	$(document).ready(function() {
		$('#submitid').click(function(e) {
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

					isValid = true;
				}
			});

			// Email Validations 			
			var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var emailAddress = $("#emailIdInput").val();
			if (!emailValidations.test(emailAddress)) {
				$("#emailIdInputErr").show();
				$("#emailIdInputErr").html("Please Enter Valid Email ");
				var isValid = false;
			}

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>
<main id="main"> <!-- intro learn search block -->
<section class="intro-learn-search-block bg-cover text-center"
	style="background-image: url(resource/images/img82.jpg);">
	<div class="container holder">
		<div class="align">
		<div class="contact-form " style="margin-bottom: -40px">
				<h3 class="text-center">Login Form</h3>
			</div>
			<form:form name="myForm" method="post" modelAttribute="login"
				commandName="login" class="align learn-search-form text">
			<c:if test="${not empty succesmessage}">
			   <div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${succesmessage}"></c:out>
			    </div>
                 </c:if>
                 <c:if test="${not empty errorMessage}">
			    <div class="alert alert-danger">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Error:</strong>
				<c:out value="${errorMessage}"></c:out>
			    </div>
			    </c:if>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="fw-normal font-lato"> <span
								class="" style="margin-right: 384px;"> Email
									Address<span class="required"></span>
							</span> <form:input type="text" id="emailIdInput" path="emailAddress"
									placeholder="Email Address" class="form-control element-block" autofocus="true" />
							</label>
							<form:errors path="emailAddress" cssClass="error"
								style="color: red" />
							<div id="emailIdInputErr" style="color: red"></div>


						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block" style="margin-right: 407px;">Password
									<span class="required"></span>
							</span> <form:input type="password" id="password" path="password"
									placeholder="Password" class="form-control element-block"  maxlength="8"/>
							</label>
						</div>
					</div>
				</div>


				<!-- learning cources list -->
				<ul class="list-unstyled learning-cources-list text-left">
					<li><a href="#" class="forget-link">Forgot Password?</a></li>
								
					
                    
					<li><label for="rem2"
						class="custom-check-wrap fw-normal font-lato"> <input
							type="checkbox" id="rem2"
							class="customFormReset"> 
                            <span
							  class="fake-label element-block"style="font-size: 15px;font-weight:normal;
                             line-height: 29px;"> 
							   Remember me</span>
					</label></li>
					<li>
					<button type="submit" id="submitid" class="btn btn-theme btn-warning text-uppercase font-lato fw-bold""
							style="height: 35px; padding:5px;font-size: 12px;">Login</button>
					</li>
				</ul>
			</form:form>
		</div>
	</div>
</section>
</main>
