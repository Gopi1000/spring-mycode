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
					<h3>Create GST</h3>
				</div>
				<br>
				<form:form method="POST" id="addForm" action="create-gst"
					modelAttribute="gstBO">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label>Sgst Value<span class="font10 text-danger">*</span></label>
								<form:input id="firstNameInput" type="text" path="sgst"
									class="form-control required" placeholder="Sgst Value"
									maxlength="150" />
								<form:errors path="sgst" cssClass="error" />
								<div id="firstNameErr" style="color: red;"></div>
							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<label>Cgst Value<span class="font10 text-danger">*</span></label>
								<form:input id="firstNameInput" type="text" path="cgst"
									class="form-control required" placeholder="Cgst Value"
									maxlength="150" />
								<form:errors path="cgst" cssClass="error" />
								<div id="firstNameErr" style="color: red;"></div>
							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<label class="element-block fw-normal font-lato"> Date<span
									class="font10 text-danger">*</span></font>
								</label>
								<form:input type="text" id="datepicker" path="startDate"
									placeholder="Date" class="form-control element-block" />
								<label class="element-block fw-normal font-lato"
									style="font-size: 10px">EX:MM/DD/YYYY</label>
							</div>
						</div>

					</div>

					<br />
					<div class="row">
						<div class="col-sm-1" style="float: right;">
							<a href=view-gst?page=1"><span
								class="btn btn-t-primary btn-theme lebal_align">Cancel</span></a>
						</div>
						<div class="col-sm-1" style="float: right;">
							<button type="submit" id="btnsubmit"
								class="btn btn-t-primary btn-theme lebal_align">Submit</button>
						</div>
					</div>
				</form:form>

			</div>
		</div>
	</div>
</div>
