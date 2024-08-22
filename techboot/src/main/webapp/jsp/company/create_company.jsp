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
			}else{
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
         $('form#companyform input[type="text"]').each(function() {
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
			}else if (mobileNumber.length < 10) {
                $("#mobileerror").show();
				$("#mobileerror").html("Must enter 10 Digits Numbers");
				var isValid = false;
			}else {
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
			 var industryType=$("#functionInput").val();
			   if(industryType=="Select Industry Type"){
				   isValid = false;
				$("#functionInput").css({
					"border" : "1px solid red",
				});
			}else{
				$(this).css({
					"border" : "",
					"background" : ""
				});
			  }
			   if(isStatus == false){
				   alert("Please fill required field's");
				   isValid = false;
			   }
			   if (isValid == false){
					e.preventDefault();
			   }
			});
	});
$(document).ready(function(){
	var isStatus = true;
});
 function companycheck(){
	 var companyName = $("#companyName").val()
	 $.ajax({
		 type : "GET",
		 url : 'companyNameValidations',
		 data : 'companyName=' + companyName,
		 success : function(response){
			 alert(response);
			if(response=="COMPANYNAME"){
				$("#companyNameErr").show();
				$("#companyNameErr").html("CompanyName already exist's")
				isStatus = false;
			}else{
				   $("#companyNameErr").hide();
				   $("#companyNameErr").css({
						"border" : "",
						"background" : ""
					});
			}
		 },
	 });
  }
 function emailcheck(){
    	var emailAddress = $("#emailIdInput").val()
    	$.ajax({
    		type : "GET",
    		url : 'emailAddressVerification',
    		data : 'emailAddress=' + emailAddress,
    		success : function(response){
    			if(response=="EMAILADDRESS"){
    				$("#emailIdInputErr").show();
    				$("#emailIdInputErr").html("EmailAddress already exist's")
    				isStatus = false;
    			}else{
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
    						$("#emailIdInputErr").html("Please Enter Valid Email ");
    						var isValid = false;
    					}else {
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
  function website(){
	  var website = $("#companyWebsiteInput").val()
	  $.ajax({
		 type : "GET",
		 url : 'websiteVerifications',
		 data : 'website=' + website,
		 success : function(response){
			 if(response=="WEBSITE"){
				 $("#companyWebSiteErr").show();
 				$("#companyWebSiteErr").html("Company Website already exist's") 
 				isStatus = false;
			 }else{
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

<main id="main"> <section
	class="container instructor-profile-block checkout-form"> <form:form
	name="myForm"  id="companyform" method="post" modelAttribute="companybo"
	commandName="companybo" class="contact-form top"
	enctype="multipart/form-data">
	<div class="col-xs-12 col-sm-12">
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Error:</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
	<div class="contact-form">
		<h3 class="text-center">Create Company</h3>
	</div>
	<div class="row" style="border: 1px solid #e6e6e6;">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="row">
				<br>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Company
							Name <span class="required"> <font class="f-color">*
							</font></span>
						</label>
						<form:input type="text" id="companyName" path="companyName" onchange="companycheck()"
							autofocus="true" placeholder="Company name"
							class="form-control element-block" />
						<form:errors path="companyName" cssClass="error"></form:errors>
						<div id="companyNameErr" style="color: red" ></div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato">
							Chairperson Name <span class="required"><font
								class="f-color">*</font></span>
						</label>
						<form:input type="text" id="companyPersonNameId"
							path="companyPersonName" placeholder="ChairPerson Name"
							class="form-control element-block" />
						<form:errors path="companyPersonName" cssClass="error" />
						<div id="companyPersonNameErr" style="color: red"></div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> First
							Name <span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="text" id="firstNameInput" path="firstName"
							placeholder="First Name" class="form-control element-block" />
						<form:errors path="firstName" cssClass="error" style="color: red" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Last
							Name <span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="text" id="lastNameId" path="lastName"
							placeholder="Last Name" class="form-control element-block" />
						<form:errors path="lastName" cssClass="error" style="color: red" />
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Email
							Address <span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="text" id="emailIdInput" path="emailAddress" onchange="emailcheck()"
							placeholder="Email Address" class="form-control element-block" />

						<form:errors path="emailAddress" cssClass="error"
							style="color: red" />
							<div id="emailIdInputErr" style="color: red"></div>
						<label class="element-block fw-normal font-lato"
							style="font-size: 10px">EX:enquiry@techboot.com</label>
							</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Confirm
							Email Address <span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="text" id="secondaryEmailInput"
							path="conformEmailAddress" placeholder="Confirm Email Address"
							class="form-control element-block" />
						<form:errors path="conformEmailAddress" cssClass="error"
							style="color: red" />
						<label class="element-block fw-normal font-lato"
							style="font-size: 10px">EX:enquiry@techboot.com</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Password
							<span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="password" id="passwordId" path="password"
							placeholder="Password" class="form-control element-block" maxlength="10" />
						<form:errors path="password" cssClass="error" id="passwordId"></form:errors>


					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Conform
							Password <span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="password" id="conformPassword"
							path="conformPassword" placeholder="Conform Password"
							class="form-control element-block" maxlength="10" />
						<form:errors path="conformPassword" cssClass="error"
							id="conformPassword"></form:errors>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Company
							WebSite<span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="text" id="companyWebsiteInput" onchange="website()"
							path="companyWebSite" placeholder="Company WebSite"
							class="form-control element-block" />
							<div id="companyWebSiteErr" style="color: red"></div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<div class="form-group">
					<label> Company	Type <span class="required"><font class="f-color">*</font></span></label>
					<form:input type="text" id="companytypeInput" 
							path="companyType" placeholder="companyType"
							class="form-control element-block" />
					<%--<form:select type="text"
								class="element-block fw-normal font-lato" id="companyType"
								path="companyType" style="height: 42px;">
								<form:option value="">Select Company Type</form:option>
								<form:options items="${companyTypeList}" />
							</form:select>--%>
							<form:errors path="companyType" style="color: red" />
				</div>
			</div>			
				
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Contact
							Number <span class="required"><font class="f-color">*</font></span>
						</label>
						<form:input type="text" id="contectNumberId" path="contectNumber"
							placeholder="Contact Number" class="form-control element-block"
							maxlength="10" />

						<form:errors path="contectNumber" cssClass="error"
							style="color: red" />
						<div id="mobileerror" style="color: red"></div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label class="element-block fw-normal font-lato"> Address<span
							class="required"><font class="f-color">*</font></span></label>
						<form:input type="text" id="address" path="address"
							placeholder="Address" class="form-control element-block" />
						<form:errors path="address" cssClass="error" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group">
						<label>Industry Type <span class="required"><font
								class="f-color">*</font></span></label>
						<form:select type="text"
							class="element-block fw-normal font-lato drop" id="functionInput" path="industry">
							<form:option value="Select Industry Type">Select Industry Type</form:option>
							<form:options items="${industryList}" />
						</form:select>
						<form:errors path="industry" cssClass="errors" />

					</div>

				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<label>Company Logo</label> <font class="f-color">*</font> <input
						type="file" name="companyLogos" path="companyLogo" id="PhotoInput"
						placeholder="Photo" onchange="fileCheck(this)" />
					<div class="color-black-mute">
						<small>Logo jpeg,jpg,png Formats Only</small>
					</div>
				</div>

			</div>

			<br>
		</div>
	</div>
	</div>
	<div class="text-right">
		<button type="submit" id="submit"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"Style ="min-width: 100px">Save
		</button>
	</div>
</form:form> </section> </main>