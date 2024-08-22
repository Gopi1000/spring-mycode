<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


<div class="contact-form-wrapper">

	<div class="box-list">
		<div class="item">
			<div class="row ">
				<div class="text-center underline">
					<h3>Edit Contact</h3>
				</div>
				<br>
				<!-- form post a job -->
				<form:form id="myForm" method="post" class="login-form clearfix"
					commandName="updatecontact" modelAttribute="updatecontact"
					action="edit-contact">
					<!-- Start Warning Message  -->
					<%-- <center> --%>
						


                 <form:input type="hidden" path="contactid" class="form-control" />

                  <form:input type="hidden" path="customerownername" class="form-control" />

                   <form:input type="hidden" path="clientbo.id" class="form-control" />

                <div class="col-sm-12">
						<div class="col-sm-3">
							<div class="form-group">
								<label>primary contact number </label>
								<form:input type="text" path="primarycontactnumber"
									id="primarycontactnumberInput" class="form-control"
									placeholder="primarycontactnumber" />
								<form:errors path="primarycontactnumber" cssClass="error" />
								<div id="input_error" style="color: red;"></div>
							</div>
						</div>


						<div class="col-sm-3">
							<div class="form-group">
								<label>permanent contact number<span class="font10 text-danger">*</label>
									
								<form:input type="text" path="permanentcontactnumber"
									class="form-control" placeholder="permanentcontactnumber"
									id="permanentcontactnumberId" />
								<form:errors path="permanentcontactnumber" cssClass="error" />
								<div id="permanentcontactnumbererror" style="color: red;"></div>
							</div>
						</div>


						<div class="col-sm-3">
							<div class="form-group">
								<label> primary address <span class="font10 text-danger">*</span></label>
								<form:input id="primaryaddressInput" type="text"
									path="primaryaddress" class="form-control required"
									placeholder="primaryaddress" maxlength="150" />
								<form:errors path="primaryaddress" cssClass="error" />
								<div id="primaryaddressErr" style="color: red;"></div>
							</div>
						</div>


						<div class="col-sm-3">
							<div class="form-group">
								<label>permanent address </label>
								<form:input type="text" path="permanentaddress"
									class="form-control " placeholder="permanentaddress"
									id="permanentaddressInput" />
								<form:errors path="permanentaddress" class="input_error" />
								<div id="permanentaddressErr" style="color: red;"></div>
							</div>
						</div>
					</div>


					<div style="text-align: right; margin-right: 31px">
						<button type="submit" id="btnSubmit"
							class="btn btn-t-primary btn-theme lebal_align mt-20">Update&Submit</button>
						<a href=view-customers?page=1"><span
							class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
</div>
