<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<link href="resources/theme/css/custom.css" rel="stylesheet">
<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
		$(function() {
			$("#datepicker").datepicker({
				changeMonth : true,
				changeYear : true,
				numberOfMonths : 1,
				onSelect : function(selected) {
					var dt = new Date(selected);
					dt.setDate(dt.getDate());
					$("#endDateInput").datepicker("option", "minDate", dt);
				}
			});
			$("#endDateInput").datepicker({
				changeMonth : true,
				changeYear : true,
				numberOfMonths : 1,
				onSelect : function(selected) {
					var dt = new Date(selected);
					var dt2 = new Date(selected);
					dt.setDate(dt.getDate() - 1);
					dt2.setDate(dt.getDate() + 1);
				}
			});

		});
	</script>

<div class="row scrollspy-sidenav pb-20 body-mt-15">
	<c:if test="${not empty successMessage}">
		<div class="alert alert-info" role="alert"
			style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>success!</strong>
			<c:out value="${successMessage}"></c:out>
		</div>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-info" role="alert"
			style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Info!</strong>
			<c:out value="${errorMessage}"></c:out>
		</div>
	</c:if>
</div>

<div class="contact-form-wrapper">

	<div class="box-list">
		<div class="item">
			<div class="row ">

				<div class="text-center underline">
					<h3>Create Product</h3>
				</div>
				<br>

				<form:form method="POST" id="addForm" action="create-productservice"
					modelAttribute="productServiceBO">
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label>Product Name<span class="font10 text-danger">*</span></label>
								<form:input id="firstNameInput" type="text" path="serviceName"
										class="form-control required" placeholder="Product Name"
										maxlength="150" />
									<form:errors path="serviceName" cssClass="error" />
									<div id="firstNameErr" style="color: red;"></div>
								</div>
							</div>
							<div class="col-sm-4">
							<div class="form-group">
								<label>Product Specification<span class="font10 text-danger">*</span></label>
								<form:input id="serviceInput" type="text" path="serviceSpecification"
										class="form-control required" placeholder="Service Specification"
										maxlength="150" />
									<form:errors path="serviceSpecification" cssClass="error" />
									<div id="serviceErr" style="color: red;"></div>
								</div>
							</div>
							<div class="col-sm-4">
							<div class="form-group">
								<label>Duration<span class="font10 text-danger">*</span></label>
								<form:input id="durationInput" type="text" path="duration"
										class="form-control required" placeholder="Duration"
										maxlength="150" />
									<form:errors path="duration" cssClass="error" />
									<div id="durationErr" style="color: red;"></div>
								</div>
							</div>
							
						</div>
						
						<div class="col-sm-12">
								<div class="col-sm-4">
								<div class="form-group">
									<label class="element-block fw-normal font-lato">Start
										Date<span class="font10 text-danger">*</span></font>
									</label>
									<form:input type="text" id="datepicker" path="startDate"
										placeholder="Start Date" class="form-control element-block" />
									<label class="element-block fw-normal font-lato"
										style="font-size: 10px">EX:MM/DD/YYYY</label>
								</div></div>
								<div class="col-sm-4">
							<div class="form-group">
									<label >End
										Date<span class="font10 text-danger">*</span>
									</label>
									<form:input type="text" id="endDateInput" path="endDate"
										placeholder="End Date" class="form-control element-block" />
									<label class="element-block fw-normal font-lato"
										style="font-size: 10px">EX:MM/DD/YYYY</label>
								</div></div>
								<div class="col-sm-4">
								<div class="form-group">
									<label>Price <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="fees"
										class="form-control required" placeholder="Price" id="fees" />
									<form:errors path="fees" class="input_error" />
								</div>
							</div>
							</div>
					</div>
					
					<div style="text-align: right; margin-right: 31px">
							<button type="submit" id="btnSubmit"
								class="btn btn-t-primary btn-theme lebal_align mt-20">Submit</button>
							<a href=view-productservice?page=1"><span
								class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
						</div>
				</form:form>
			</div>
		</div>
	</div>
</div>