<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>

<script>
	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline', 'ol',
					'ul', 'strikeThrough', 'html' ]
		}).panelInstance('inputAddress');
	});
</script>
<script>
	$(document).ready(function() {
		$('#dateValId').datepicker({

			changeMonth : true,
			changeYear : true,
		});
	});
</script>


<script>
	$(document).ready(function() {
		$('#nextdateValId').datepicker({

			changeMonth : true,
			changeYear : true,
		});
	});
</script>
<script>
	$(document).ready(function() {
		$('#btnsubmit').click(function(e) {
			var isValid = true;
			$('input[type="text"].required').each(function() {
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

				if (isValid == false)
					e.preventDefault();

			});
		});
	});

	$(document).ready(function() {
		$('#btnsubmit').click(function(e) {
			var isValid1 = true;
			$('input[id="password"].required').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid1 = false;
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
			if (isValid1 == false)
				e.preventDefault();

		});
	});
</script>

<!-- <div class="warning"> -->

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
<!-- </div> -->

<div class="contact-form-wrapper" style="margin-top: 50px;">
	<div class="box-list">
	<div class="item">
		<div class="text-center underline">
			<h3>Leads Details</h3>
		</div>
		<div class="item" style="background-color: #e1e1e1">

			<div class="desc list-capitalize">
				<div class="row clearfix" style="line-height: 2em;">


					<div class="col-xs-4">
						<label style="font-weight: initial; font-weight: bold;">
							Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewleads.firstName}"></c:out>

					</div>



					<div class="col-xs-4">
						<label style="font-weight: initial; font-weight: bold;">Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewleads.emailAddress}"></c:out>
					</div>

					<div class="col-xs-4">
						<label style="font-weight: initial; font-weight: bold;">Contact&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewleads.contactNo}"></c:out>
					</div>


					<div class="col-xs-4">
						<label style="font-weight: initial; font-weight: bold;">Mobile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewleads.mobileNo}"></c:out>
					</div>

					<div class="col-xs-4">
						<label style="font-weight: initial; font-weight: bold;">Company&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewleads.companyName}">
						</c:out>
					</div>

					<div class="col-xs-4">
						<label style="font-weight: initial; font-weight: bold;">Industry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewleads.industryType}"></c:out>
					</div>
				</div>
				<div class="row clearfix" style="line-height: 2em;">
				<div class="col-xs-12">
						<label style="font-weight: initial; font-weight: bold;">Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewleads.address}"></c:out>
					</div>
				</div>
			</div>

		</div>
		<c:if test="${!empty leadsBOList}">
			<div class="text-center underline">
				<h3>Leads Tracking History</h3>
			</div>
			<div class="pi-responsive-table-sm">
				<div class="pi-section-w pi-section-white piTooltips">
					<display:table id="data" name="${leadsBOList}"
						requestURI="/leads-tracking-status" pagesize="10" export="false"
						class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
						<display:column property="date" title="Action Date" />
						<display:column property="description" title="Description" />
						<display:column property="nextAppointmentDate" title="Next Appointment Date" />
					</display:table>
				</div>
			</div>
		</c:if>

		<div class="text-center underline">
			<h3>Update Leads Tracking Status</h3>
		</div>
		<div class="item">
			<form:form method="POST" id="addForm" action="leads-tracking-status"
				modelAttribute="viewleads">
				<div class="row">
					<div class="col-sm-4">
						<div class="form-group">

							<!-- <label>Appointment Date <span
											class="font10 text-danger"> *</span></label> <input type="text"
											class="form-control" id="dateValId"> -->
							<label>Action Date <span class="font10 text-danger">
									*</span></label>
							<form:input path="date" class="form-control" id="dateValId" />
						</div>
					</div>
					
					
					<div class="col-sm-4">
						<div class="form-group">
						
						<label>Next Appointment Date <span class="font10 text-danger">
									*</span></label>
							<form:input path="nextAppointmentDate" class="form-control" id="nextdateValId" />
						</div>
					</div>
				</div>
				<form:hidden path="leadsId" />
				<div class="row">
					<div class="col-md-12">

						<label>Descriptions <span class="font10 text-danger">
								*</span></label>

						<form:textarea path="description" ame="description"
							placeholder="Description" class="form-control rtext"
							id="descValId" />
						<div id="errorSkill" style="color: red;"></div>

					</div>
				</div>
				</br>
				<div class="row">
					<div class="col-md-12">
						<div style="text-align: right; margin-right: 31px;">
							<button type="submit" id="btnsubmit"
								class="btn btn-t-primary btn-theme lebal_align mt-20">Submit</button>
							<a href="view-leads"><span
								class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		</div>
	</div>
</div>


