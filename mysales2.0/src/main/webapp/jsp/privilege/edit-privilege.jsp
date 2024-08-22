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
					<h3>Edit Privilege</h3>
				</div>
				<br>
				<!-- form post a job -->
				<form:form id="myForm" method="post" class="login-form clearfix"
					commandName="editprivilege" modelAttribute="updateprivilege"
					action="edit-privilege">
					<!-- Start Warning Message  -->
					<%-- <center> --%>
						


                 <form:input type="hidden" path="privilegeid" class="form-control" />

                  

                <div class="col-sm-12">
						<div class="col-sm-3">
							<div class="form-group">
								<label>Privilege Name </label>
								<form:input type="text" path="privilegename"
									id="privilegenameInput" class="form-control"
									placeholder="privilegename" />
								<form:errors path="privilegename" cssClass="error" />
								<div id="input_error" style="color: red;"></div>
							</div>
						</div>


						
					</div>


					<div style="text-align: right; margin-right: 31px">
						<button type="submit" id="btnSubmit"
							class="btn btn-t-primary btn-theme lebal_align mt-20">Update&Submit</button>
						<a href=view-role?page=1"><span
							class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
</div>
