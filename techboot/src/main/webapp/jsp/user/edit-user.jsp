<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<script>
function submit()
{
	var x=document.getElementById("password");
	var y=document.getElementById("password1");
	if(x!=y)
		{
		 document.getElementById("p").innerHTML = "Password does not match";
		return false;}	
	}
	
function showPassword()
 {
	  var x = document.getElementById("password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}

function showPassword1()
{
	  var x = document.getElementById("password1");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}
</script>


<main id="main">
<section class="container instructor-profile-block checkout-form">
 <div class="col-xs-12 col-sm-12">
			<div class="contact-form">

<div class="contact-form " style="margin-bottom: -40px">
				<h3 class="text-center">Edit Password</h3>
			</div>
	<form:form name="myForm" action="edit-user" id="editUser" method="post" modelAttribute="login"
		commandName="login" class="align learn-search-form text">
			  
			  	
						<div class="form-group">
						<label class="fw-normal font-lato"> User Type :
						<span><i  style="color: orange">${ login.userType }</i></span>
						</label>
					</div>
				
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-6">
					<div class="form-group">
						<label class="fw-normal font-lato"> Name :
						<div>
						 <span style="color: orange;" > ${ login.name }	</span>
						 </div>
						</label>
					</div>
					</div> 
									
				 <div class="col-lg-4 col-md-4 col-xs-12 col-sm-6">
					<div class="form-group">
						<label class="fw-normal font-lato"> Email Id :
						<div>
							 <span style="color: orange" ><b>${login.emailAddress }</b></span>
							 </div>
						</label>						
					</div>
				</div>
			<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="fw-normal font-lato"> <span
								class="" style="margin-right: 430px;"> Password
									<span class="required"></span>
							</span> <form:input  type="password" path="password" id="password"
							class="form-control element-block"  autofocus="true" />
							</label>
							<form:errors path="password" cssClass="error"
								style="color: red" />
							<div id="passwordErr" style="color: red"></div>
						</div>
						<input type="checkbox" onclick="showPassword()">Show Password
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="fw-normal font-lato"> <span
								class="" style="margin-right: 384px;">ConfirmPassword
									<span class="required"></span>
							</span> <form:input type="password" id="password1" path="confirmPassword"
									placeholder="Confirm Password" class="form-control element-block"/>
							</label>
						</div>
						<input type="checkbox" onclick="showPassword1()">Show Password
					</div>
				</div> 
			
			<p id="p"> </p>
			
						
						<form:hidden path="userType" value="${ login.userType }"/>
						<form:hidden path="emailAddress" value="${ login.emailAddress }"/>
						  <form:hidden path="mobileNo" value="${ login.mobileNo }"/>
						 <form:hidden path="name" value="${ login.name }"/>
						 <form:hidden path="loginId" value="${ login.loginId }"/> 						
			
			
							
		<div class="text-left">
			<button type="submit" id="submit"  onfocus="submit()"
					class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
				Style="min-width: 100px">Submit</button>
		</div> 
		
		
		
	</form:form>
	</div>
		</div>
	
</section>
</main>