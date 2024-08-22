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

<div class="row scrollspy-sidenav pb-20 body-mt-15">
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

	<script>
		$(document)
				.ready(
						function() {
							$('#btnsubmit')
									.click(
											function(e) {

												var isValid = true;
												var firstname = $(
														"#productNameInput")
														.val();
												if (firstname == '') {

													$("#productNameInput")
															.css(
																	{
																		"border" : "1px solid red",
																	});
													isValid = false;
												} else if (!/^[a-zA-Z\s]*$/g
														.test(firstname)) {
													$("#productNameErr").show();
													$("#productNameErr")
															(
																	"Please Enter Character Only");
													isValid = false;
												} else {
													$("#productNameErr").hide();
													$("#productNameInput").css({
														"border" : "",
														"background" : ""
													});
												}
												if (isValid1 == false)
													e.preventDefault();
											});
										});
		</script>

	<script>
		$(function() {
			$("#startDateInput").datepicker({
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






	<div class="warning">

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
						<h3>Create Campaign</h3>

					</div>
					<br>

					<form:form method="POST" id="addForm" action="create-campaign"
						modelAttribute="campaignBO">
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label>Product Name<span class="font10 text-danger">*</span></label>
									<form:select type="text" path="productServiceBO.serviceName"
										class="form-control required">
										<form:option value=" ">-- Select --   </form:option>
										<form:options items="${productList}"  itemLabel="serviceName" 
											itemValue="serviceId" /> 
									</form:select>
								</div>
							</div>


							<div class="col-sm-4">
								<div class="form-group">
									<label>Campaign Name <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="campaignName"
										class="form-control required" placeholder="Campaign Name" />
									<form:errors path="campaignName" class="input_error" />
								</div>
							</div>

							<%-- <c:if test="${!empty userBOList}"> --%>
								<div class="col-sm-4">
									<div class="form-group">
										<label>Campaign Mode <span class="font10 text-danger">*</span></label>
										<form:select type="text" path="campaignMode"
											class="form-control required">
											<form:option value="Select">-- Select --   </form:option>
											<form:option value="Sms">SMS   </form:option>
											<form:option value="WhatsApp">WHATSAPP </form:option>
											<form:option value="Email">EMAIL  </form:option>
										</form:select>

									</div>
								</div>
							<%-- </c:if> --%>




						</div>




						<div class="col-sm-12">



 
							<div class="col-sm-4">
								<div class="form-group">
									<label>Owner Names<span class="font10 text-danger">*</span></label>
									<form:select type="text" path="campaignOwner"
										class="form-control required">
										<form:option value="Select">-- Select --   </form:option>
										<form:options items="${userBOList}" itemLabel="name" 
											itemValue="id" />

									</form:select>
								</div>
							</div>
					<div class="col-sm-4">
								<div class="form-group">
									<label>Start Date<span class="font10 text-danger">*</span></label>
									<label class="hidden-xs">&nbsp;</label>
									<form:input type="ntext" class="form-control"
										path="startedTime" id="startDateInput"
										placeholder="Start Date" data-date-format="dd/mm/yyyy"></form:input>
								</div>
							</div>

							<div class="col-sm-4">
								<div class="form-group">
									<label>End Date<span class="font10 text-danger">*</span></label>
									<label class="hidden-xs">&nbsp;</label>
									<form:input type="ntext" class="form-control" path="endTime"
										id="endDateInput" placeholder="End Date"
										data-date-format="dd/mm/yyyy"></form:input>
								</div>
							</div>

						</div>
				</div>

				<div style="text-align: right; margin-right: 31px">
					<button type="submit" id="btnsubmit"
						class="btn btn-t-primary btn-theme lebal_align mt-20">Submit</button>
					<a href=view-campaign?page=1"><span
						class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
				</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
</div>
<br>

