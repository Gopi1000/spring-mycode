<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.errors {
	color: red;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {

			if (document.getElementById("uploadFiles").files.length == 0) {
				$("#fileErr").show();
				$("#fileErr").html("Download the Templete first");
				$("#fileError").html("Choose downloaded Templete only");
				e.preventDefault();
			} else {
				$("#fileErr").hide();
				$("#fileError").hide();
			}
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			var CompanyName = $("#companyName").val();
			if (CompanyName == "Select") {
				$("#companyName").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}
			var CompanyName = $("#serviceName").val();
			if (CompanyName == "Select") {
				$("#serviceName").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}
			var CompanyName = $("#category").val();
			if (CompanyName == "Select") {
				$("#category").css({
					"border" : "1px solid red",
				});
			} else {
				$(this).css({
					"border" : "",
					"background" : ""
				});
			}

		});
	});
</script>

<script>
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;

			$('form#mycampaign input[type="text"]').each(function() {
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

<script type="text/javascript">
	function showMessage() {

		var value = $('#categoryId').val();
		if (value == 'Sms') {
			document.getElementById("messageid").style.display = 'block';
			document.getElementById("whatsappId").style.display = 'none';
			document.getElementById("templateId").style.display = 'none';
		} else if (value == 'WhatsApp') {
			document.getElementById("whatsappId").style.display = 'block';
			document.getElementById("messageid").style.display = 'none';
			document.getElementById("templateId").style.display = 'none';
		} else {
			document.getElementById("templateId").style.display = 'block';
			document.getElementById("messageid").style.display = 'none';
			document.getElementById("whatsappId").style.display = 'none';
		}
	}
</script>

<!-- <script>
	function companyService() {
		var companyId = $("#companyId").val();
		alert("company " + companyId);

		$.ajax({
			type : "GET",
			url : 'retriveService',
			data : 'companyId=' + companyId,
			success : function(data) {
				var len = data.length;
				var html = '<option value="">Select</option>';
				var len = data.length;

				for (var i = 0; i < len; i++) {
					html += '<option value="' +data[i].serviceId+ '">'
							+ data[i].serviceName + '</option>';
				}
				html += '</option>';
				$('#serviceName').html(html);
			},
		});
	}
</script> -->
<<script>
function companyType(){
		var companyId = $("#companyId").val();
		/* alert("company " + companyId); */

		$.ajax({
			type : "GET",
			url : 'getCompanyType',
			data : 'companyId=' + companyId,
			success :function(response){
				 alert(response);
					if(response=="Service"){
						document.getElementById("serviceNameBlock").style.display = 'block';
						document.getElementById("courseNameBlock").style.display = 'none';
						$.ajax({
							type : "GET",
							url : 'retriveService',
							data : 'companyId=' + companyId,
							success : function(data) {
								var len = data.length;
								var html = '<option value="">Select</option>';
								var len = data.length;

								for (var i = 0; i < len; i++) {
									html += '<option value="' +data[i].serviceId+ '">'
											+ data[i].serviceName + '</option>';
								}
								html += '</option>';
								$('#serviceName').html(html);
							},
						});
						
					}else{
						document.getElementById("serviceNameBlock").style.display = 'none';
						document.getElementById("courseNameBlock").style.display = 'block';
						$.ajax({
							type : "GET",
							url : 'retriveCourse',
							data : 'companyId=' + companyId,
							success : function(data) {
								var len = data.length;
								var html = '<option value="">Select</option>';
								var len = data.length;

								for (var i = 0; i < len; i++) {
									html += '<option value="' +data[i].courseId+ '">'
											+ data[i].courseName + '</option>';
								}
								html += '</option>';
								$('#courseName').html(html);
							},
						});
			}
			}					
		});
	}
</script>
<main id="main"> <section
	class="container instructor-profile-block checkout-form"> <form:form
	name="myForm" id="mycampaign" method="post" modelAttribute="campaignBO"
	commandName="campaignBO" class="contact-form top"
	enctype="multipart/form-data">
<div class="col-xs-12 col-sm-12">
	<div class="contact-form">
		<h3 class="text-center">Create Campaign</h3>
	</div>

	<c:if test="${userType eq 'admin' }">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;">
				<br>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Company Name <span class="required"><font
										class="f-color">*</font></span></span> <form:select
									path="companyBO.companyName" id="companyId"
									onchange="companyType()" style="height: 42px;">
									<form:option value="Select">-- Select --</form:option>
									<form:options itemLabel="companyName" items="${companylist}"
										itemValue="companyId"></form:options>
								</form:select>
							</label>
						</div>
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Category <span class="required"><font
										class="f-color">*</font></span></span> <form:select path="category"
									id="categoryId" onchange="showMessage()" style="height: 42px;">
									<form:option value="Select">-- Select --   </form:option>
									<form:option value="Sms">SMS   </form:option>
									<form:option value="WhatsApp">WHATSAPP </form:option>
									<form:option value="Email">EMAIL  </form:option>
								</form:select>
							</label>
							<form:errors path="category" cssClass="errors" />
						</div>
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					
						<div class="form-group"id="serviceNameBlock"style="display: none">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Service Name <span class="required"><font
										class="f-color">*</font></span></span>
										 <form:select
									path="productService.serviceName" id="serviceName"
									style="height: 42px;">
									<form:option value="Select">-- Select --</form:option>
									<form:options itemLabel="serviceName" items="${listservices}"
										itemValue="serviceId"></form:options>
								</form:select>
								
							</label>
						</div>
					
						<div class="form-group"id="courseNameBlock"style="display: none">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Course Name <span class="required"><font
										class="f-color">*</font></span></span> <form:select id="courseName"
									path="course.courseName" style="height: 42px;">
									<form:option value="Select">--Select--</form:option>
									<form:options itemLabel="courseName" items="${listCourse}"
										itemValue="courseId"></form:options>
								</form:select>
								
							</label>
						</div>
					</div>
					
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					

				</div>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">
								Campaign Name <span class="required"><font
									class="f-color">*</font></span>
							</label>
							<form:input type="text" id="Campiagn Name" path="campaignName"
								placeholder="Campiagn Name" class="form-control element-block" />
							<form:errors path="campaignName" cssClass="errors" />
						</div>
					</div>

					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">Started
								Date<font class="f-color">*</font>
							</label>
							<form:input type="text" id="datepicker" path="startedTime"
								placeholder="Started Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:MM/DD/YYYY</label>
							<form:errors path="startedTime" cssClass="errors" />
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">End Date<font
								class="f-color">*</font></label>
							<form:input type="text" id="datepickers" path="endTime"
								placeholder="End Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:MM/DD/YYYY</label>
							<form:errors path="endTime" cssClass="errors" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

						<div class="form-group" id="messageid" style="display: none;">
							<label class="element-block fw-normal font-lato">Message<span
								class="required"><font class="f-color"><font
										class="f-color">*</font></font></span></label>
							<form:textarea type="text" path="message" id="messageid"
								class="form-control element-block"
								placeholder="Message  only allow for 4000 Charcters"
								/>
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">Note:Please Avoid the special Character( & ) and Single Code ( ' ) Double code( "" )</label>
						</div>
						<div class="form-group" id="whatsappId" style="display: none;">
							<label class="element-block fw-normal font-lato">Whatsapp
								Message<span class="required"><font class="f-color">*</font></span>
							</label>
							<form:textarea type="text" path="message" id="whatsappId"
								class="form-control element-block"
								placeholder=" Whatsapp Message" />

						</div>

						<div class="form-group" id=templateId style="display: none;">
							<label>Template</label> <input type="file" name="Template"
								onchange="fileCheck(this)" id="uploadFiles" size="50"
								placeholder="File" />
							<div class="color-black-mute">
								<!-- <small>Logo jpeg,jpg,png Formats Only</small> -->
							</div>
						</div>
					</div>
				</div>
				<br>
			</div>
		</div>
	</c:if>
	<c:if test="${userType eq 'company' }">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;padding: 50px;">
				<div class="row">
				
				<c:choose>
				<c:when test="${companyType eq 'Service'}">
				 <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Service Name <span class="required"><font
										class="f-color">*</font></span></span> <form:select id="serviceName"
									path="productService.serviceName" style="height: 42px;">
									<form:option value="Select">--Select--</form:option>
									<form:options itemLabel="serviceName" items="${listservice}"
										itemValue="serviceId"></form:options>
								</form:select>
							</label>
						</div>
					</div>
					<form:hidden path="course.courseName" value="select"/>
				</c:when>
				<c:otherwise>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Course Name <span class="required"><font
										class="f-color">*</font></span></span> <form:select id="courseName"
									path="course.courseName" style="height: 42px;">
									<form:option value="Select">--Select--</form:option>
									<form:options itemLabel="courseName" items="${listCourse}"
										itemValue="courseId"></form:options>
								</form:select>
							</label>
						</div>
					</div>
					<form:hidden path="productService.serviceName" value="select"/>
				</c:otherwise>
				</c:choose>
					
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato"> <span
								class="element-block">Category <span class="required"><font
										class="f-color">*</font></span></span> <form:select path="category"
									id="categoryId" onchange="showMessage()" style="height: 42px;">
									<form:option value="Select">--Select--   </form:option>
									<form:option value="Sms">SMS   </form:option>
									<form:option value="Email">EMAIL  </form:option>
									<form:option value="WhatsApp">WHATSAPP </form:option>
								</form:select>
							</label>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">
								Campaign Name <span class="required"><font
									class="f-color">*</font></span>
							</label>
							<form:input type="text" id="companyPersonName"
								path="campaignName" placeholder="Campaign Name"
								class="form-control element-block" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">Start
								Date<font class="f-color">*</font>
							</label>
							<form:input type="text" id="datepicker" path="startedTime"
								placeholder="Start Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"></label>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">End Date<font
								class="f-color">*</font></label>
							<form:input type="text" id="datepickers" path="endTime"
								placeholder=" End Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"></label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group" id="messageid" style="display: none;">
							<label class="element-block fw-normal font-lato">Message<span
								class="required"><font class="f-color"><font
										class="f-color">*</font></font></span></label>
							<form:textarea type="text" path="message" id="messageid"
								class="form-control element-block"
								placeholder="Message  only allow for 150 Charcters"
								maxlength="150" />
						</div>
						<div class="form-group" id="whatsappId" style="display: none;">
							<label class="element-block fw-normal font-lato">Whatsapp
								Message<span class="required"><font class="f-color">*</font></span>
							</label>
							<form:textarea type="text" path="message" id="whatsappId"
								class="form-control element-block"
								placeholder=" Whatsapp Message" />
						</div>
						<div class="form-group" id=templateId style="display: none;">
							<label>Template</label> <input type="file" name="Template"
								onchange="fileCheck(this)" id="uploadFiles" size="50"
								placeholder="File" />
							<div class="color-black-mute">
								<!-- <small>Logo jpeg,jpg,png Formats Only</small> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
	</c:if>
</div>
	<div class="text-right">
		<button type="submit" id="submit"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"Style ="min-width: 100px">Save
		</button>
	</div>

</form:form> </section> </main>