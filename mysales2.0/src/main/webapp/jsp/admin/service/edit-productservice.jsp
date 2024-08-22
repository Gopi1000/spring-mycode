<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<script>
	$(function() {
		$("#datepickers").datepicker();
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

<div class="warning">
	<div class="box-list">
		<div class="item">
			<div class="row ">
				<div class="text-center underline">
					<h3>Edit Product</h3>
				</div><br>
				<form:form method="POST" id="addForm" action="edit-productservice"
					modelAttribute="productBO">
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label> Product Name <span class="font10 text-danger">*</span></label>
								<form:input id="inputFirstName" type="text" path="serviceName"
									class="form-control required" placeholder="Product Name"
									maxlength="150" />
								<form:errors path="serviceName" class="input_error" />
							</div>
						</div>
						<form:hidden path="serviceId" />

						<div class="col-sm-4">
							<div class="form-group">
								<label>Product Specification <span
									class="font10 text-danger">*</span></label>
								<form:input type="text" path="serviceSpecification"
									class="form-control required" placeholder="Specification" />
								<form:errors path="serviceSpecification" class="input_error" />
							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<label>Duration <span class="font10 text-danger">*</span></label>
								<form:input type="text" path="duration"
									class="form-control required" placeholder="duration" />
								<form:errors path="duration" class="input_error" />
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
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label>End Date<span class="font10 text-danger">*</span>
								</label>
								<form:input type="text" id="datepickers" path="endDate"
									placeholder="End Date" class="form-control element-block" />
								<label class="element-block fw-normal font-lato"
									style="font-size: 10px">EX:MM/DD/YYYY</label>
							</div>
						</div>
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
				<button type="submit" id="btnsubmit"
					class="btn btn-t-primary btn-theme lebal_align mt-20">Update</button>
				<a href=view-productservice?page=1"><span
					class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
			</div>
			</form:form>
		</div>
	</div>
</div>