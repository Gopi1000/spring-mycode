<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.error {
	color: red;
}
</style>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

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
			$('input[type="file"]').each(function() {
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

			// Email Validations 			
			var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var secondaryEmail = $("#secondaryEmailInput").val();
			if (!emailValidations.test(secondaryEmail)) {
				$("#secondaryEmailErr").show();
				$("#secondaryEmailErr").html("Please Enter Valid Email ");
				var isValid = false;
			} else {
				$("#secondaryEmailErr").hide();
				$("#secondaryEmailErr").css({
					"border" : "",
					"background" : ""
				});
			}

			// Cheracter Validations 	
			var firstname = $("#firstNameInput").val();
			if (!/^[a-zA-Z\s]*$/g.test(firstname)) {
				$("#firstNameErr").show();
				$("#firstNameErr").html("Please Enter Character Only");
				var isValid = false;
			} else {
				$("#firstNameErr").hide();
				$("#firstNameErr").css({
					"border" : "",
					"background" : ""
				});
			}

			var firstname = $("#lastNameId").val();
			if (!/^[a-zA-Z\s]*$/g.test(firstname)) {
				$("#lastNameErr").show();
				$("#lastNameErr").html("Please Enter Character Only");
				var isValid = false;
			}

			else {
				$("#lastNameErr").hide();
				$("#lastNameErr").css({
					"border" : "",
					"background" : ""
				});
			}
			var firstname = $("#companyPersonNameId").val();
			if (!/^[a-zA-Z\s]*$/g.test(firstname)) {
				$("#companyPersonNameErr").show();
				$("#companyPersonNameErr").html("Please Enter Character Only");
				var isValid = false;
			} else {
				$("#companyPersonNameErr").hide();
				$("#companyPersonNameErr").css({
					"border" : "",
					"background" : ""
				});
			}

			//mobile validation 
			var mobileNumber = $("#contectNumberId").val();
			if (!/^[0-9]{1,11}$/.test(mobileNumber)) {
				$("#mobileerror").show();
				$("#mobileerror").html("Please Enter Numbers Only");
				var isValid = false;
			} else if (mobileNumber.length < 10) {
				$("#mobileerror").show();
				$("#mobileerror").html("Must enter 10 Digits Numbers");
				var isValid = false;
			} else {
				$("#mobileerror").hide();
				$("#mobileerror").css({
					"border" : "",
					"background" : ""
				});
			}

			var passwordIds = $("#passwordId").val();
			if (passwordIds == '') {
				$("#passwordId").css({
					"border" : "1px solid red",
				});
				var isValid = false;
			} else {
				$("#passwordId").hide();
				$("#passwordId").css({
					"border" : "",
					"background" : ""
				});
			}

			var conformPasswordId = $("#conformPassword").val();
			if (conformPasswordId == '') {

				$("#conformPassword").css({
					"border" : "1px solid red",
				})

			} else {
				$("#conformPassword").hide();
				$("#conformPassword").css({
					"border" : "",
					"background" : ""
				});
			}
			//industry validations
			var industryType = $("#functionInput").val();
			if (industryType == "Select Industry Type") {
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
	$(document).ready(function() {
		var isStatus = true;
	});
	function companycheck() {
		var companyName = $("#companyName").val()
		$.ajax({
			type : "GET",
			url : 'companyNameValidations',
			data : 'companyName=' + companyName,
			success : function(response) {
				if (response == "COMPANYNAME") {
					$("#companyNameErr").show();
					$("#companyNameErr").html("CompanyName already exist's")
					isStatus = false;
				} else {
					$("#companyNameErr").hide();
					$("#companyNameErr").css({
						"border" : "",
						"background" : ""
					});
				}
			},
		});
	}
	function emailcheck() {
		var emailAddress = $("#emailIdInput").val()
		$
				.ajax({
					type : "GET",
					url : 'emailAddressVerifications',
					data : 'emailAddress=' + emailAddress,
					success : function(response) {
						if (response == "EMAILADDRESS") {
							$("#emailIdInputErr").show();
							$("#emailIdInputErr").html(
									"EmailAddress already exist's")
							isStatus = false;
						} else {
							isStatus = true;
							$("#emailIdInputErr").hide();
							$("#emailIdInputErr").css({
								"border" : "",
								"background" : ""
							});
							//email validations
							var emailValidations = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
							var emailAddress = $("#emailIdInput").val();
							if (!emailValidations.test(emailAddress)) {
								$("#emailIdInputErr").show();
								$("#emailIdInputErr").html(
										"Please Enter Valid Email ");
								var isValid = false;
							} else {
								$("#emailIdInputErr").hide();
								$("#emailIdInputErr").css({
									"border" : "",
									"background" : ""
								});
							}
						}
					},
				});
	}
	function website() {
		var website = $("#companyWebsiteInput").val()
		$.ajax({
			type : "GET",
			url : 'websiteVerifications',
			data : 'website=' + website,
			success : function(response) {
				if (response == "WEBSITE") {
					$("#companyWebSiteErr").show();
					$("#companyWebSiteErr").html(
							"Company Website already exist's")
					isStatus = false;
				} else {
					$("#companyWebSiteErr").hide();
					$("#companyWebSiteErr").css({
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
	<form:form name="myForm" method="post" modelAttribute="StudentCouresBO"
		commandName="StudentCouresBO" class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Error:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<div class="contact-form">
				<h3 class="text-center">Edit Enrollment Course</h3>
			</div>
			<div class="row" style="border: 1px solid #e6e6e6;">
				<br>
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
							<form:hidden path="studentCouresId"/>
							<form:hidden path="StudentRegisterBO.studentRegisterId"/>
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
		</div>
	</form:form>
</section>
</main>