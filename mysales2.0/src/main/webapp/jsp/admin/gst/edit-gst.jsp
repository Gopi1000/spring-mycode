<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script>
	$(function() {
		$("#datepicker").datepicker();
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

			if (isValid == false)
				e.preventDefault();

		});
	});
</script>

<div class="contact-form-wrapper">
	<div class="box-list">
		<div class="item">
			<div class="row ">
				<div class="text-center underline">
					<h3>Edit Gst</h3>
				</div>
				<br>
				<form:form method="POST" id="addForm" action="edit-gst"
					modelAttribute="gstBO">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label> SGst Value <span class="font10 text-danger">*</span></label>
								<form:input id="inputFirstName" type="text" path="sgst"
									class="form-control required" placeholder="Sgst Value"
									maxlength="150" />
								<form:errors path="sgst" class="input_error" />
							</div>
						</div>
						<form:hidden path="gstId" />

						<div class="col-sm-4">
							<div class="form-group">
								<label>CGst Value <span class="font10 text-danger">*</span></label>
								<form:input id="inputFirstName" type="text" path="cgst"
									class="form-control required" placeholder="Cgst Value"
									maxlength="150" />
								<form:errors path="cgst" class="input_error" />
							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<label>Date <span class="font10 text-danger">*</span></label>
								<form:input id="inputFirstName" type="text" path="beginDate"
									class="form-control required" placeholder="Date"
									maxlength="150" />
								<form:errors path="beginDate" class="input_error" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-1" style="float: right;">
							<a href=view-gst?page=1"><span
								class="btn btn-t-primary btn-theme lebal_align">Cancel</span></a>
						</div>
						<div class="col-sm-1" style="float: right;">
							<button type="submit" id="btnsubmit"
								class="btn btn-t-primary btn-theme lebal_align">Update</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
