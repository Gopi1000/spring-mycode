<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript"
	src="resources/theme/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="resources/theme/script/jquery-ui.js"></script>

<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript">
	//<![CDATA[

	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'bold', 'italic', 'underline', 'ol', 'ul',
					'strikeThrough', 'html', 'image' ]
		}).panelInstance('descValId');

	});

	//]]>
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
	$("#myModalClose").click(function() {
		window.location.replace("view-report");
	});
</script>

<div
	style="height: 500px; overflow: scroll; overflow-x: hidden; overflow-y: scroll;">
	<p style="height: -2px;">
		<script>
			var count = 0;
			$(document).ready(function() {
				$('#adddes').click(function() {
					$("#ADDID").hide();
					$("#DESCID").show();
				});
			});
		</script>

		<script>
			$(document)
					.ready(
							function() {
								$('#btnSubmit')
										.click(
												function(e) {
													var description = $(
															"#descValId").val();
													if (description == '') {
														isValid = false;
														$("#descValId")
																.css(
																		{
																			"border" : "1px solid red",
																		});
													} else {
														$("#descValId").css({
															"border" : "",
															"background" : ""
														});
													}
													var date = $("#dateValId")
															.val();
													if (date == '') {
														isValid = false;
														$("#dateValId")
																.css(
																		{
																			"border" : "1px solid red",
																		});
													} else {
														$("#dateValId").css({
															"border" : "",
															"background" : ""
														});
													}
													var nicInstance = nicEditors
															.findEditor('descValId');
													var description = nicInstance
															.getContent();
													var describtionValue = keyskill
															.replace('<br>',
																	' ');
													if (describtionValue == ' ') {
														isValid = false;
														$("#errorSkill")
																(
																		"Please enter the description field");
													} else {
														isValid = true;
														$("#errorSkill").hide();
													}
													if (isValid == false)
														e.preventDefault();
												});
							});
		</script>

		<script>
			$(document).ready(
					function() {
						$('#submitBtId').click(
								function() {
									var isValid = true;
									var description = $("#descValId").val();
									var date = $("#dateValId").val();
									var id = $("#empId").val();
									var html = "";
									if (isValid == true) {
										$.ajax({
											type : "POST",
											url : 'update-tracking-status',
											data : 'description=' + description
													+ '&date=' + date + '&id='
													+ id,
											success : function(data) {
												if (data != null) {
													location.reload();
												}
											},
										});
									}
									if (isValid == false)
										e.preventDefault();
								});

					});
		</script>
		<!-- <div class="box-list" id="viewID"> -->
	<div class="item">
		<div class="row">
			<div class="modal-header">
				<button type="button" class="close" id="myModalClose"
					data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title underline" id="myModalLabel">Client Communication
					History</h4>
			</div>

			<div class="desc list-capitalize">
				<div class="row clearfix"
					style="line-height: 2em; font-weight: bold;">


					<div class="col-xs-6">
						<label style="font-weight: initial;">First
							Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewemployer.firstName}"></c:out>
					</div>

					<div class="col-xs-6" style="word-break: break-all;">
						<label style="font-weight: initial;">Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<label> <c:out value="${viewemployer.emailAddress}"></c:out></label>
					</div>

					<div class="col-xs-6">
						<label style="font-weight: initial;">Contact&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewemployer.contactNo}"></c:out>
					</div>


					<div class="col-xs-6">
						<label style="font-weight: initial;">Mobile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewemployer.mobileNo}"></c:out>
					</div>

					<div class="col-xs-6">
						<label style="font-weight: initial;">Company&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewemployer.companyName}">
						</c:out>
					</div>

					<div class="col-xs-6">
						<label style="font-weight: initial;">Industry&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<c:out value="${viewemployer.industryType}"></c:out>
					</div>
				</div>
			</div>
			<div class="row clearfix"
				style="font-family: arial; font-size: 12px;">
				<c:if test="${!empty dateList}">
					<h4>Email Communication</h4>
					<c:forEach items="${dateList}" var="searchResult">
						<div class="col-xs-6">
							<div style="margin-bottom: 0px;">
								<i class="fa fa-calendar"></i><span
									style="font-family: arial; font-size: 12px; margin-left: 5px;"><b>Mail
										Sended Date:</b> <c:out value="${searchResult}" /></span><br />
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>

			<div class="row clearfix"
				style="font-family: arial; font-size: 12px;">
				<div class="col-xs-12"
					style="border: 1px solid #e1e1e1; background-color: #e1e1e1; padding: 10px;">
					<c:if test="${!empty describe}">
						<c:forEach var="describes" items="${describe}">

							<div
								style="border: 1px solid #fff; border-bottom: 0px solid #fff; padding: 10px; line-height: 1.4em;">
								<c:set var="dateParts" value="${fn:split(describes,':')}" />
								<div style="margin-bottom: 0px;">
									<i class="fa fa-calendar"></i><span
										style="font-family: arial; font-size: 12px; margin-left: 5px;"><b>Follow
											Up On &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</b> <c:out
											value="${dateParts[1]}" /></span> <br /> <br /> <i
										class="fa fa-tags"></i><span
										style="font-family: arial; font-size: 12px; margin-left: 5px;"><b>Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</b></span>
									<span style="font-family: arial; font-size: 12px;"><c:out
											value="${dateParts[0]}" /></span>
								</div>
							</div>

						</c:forEach>
					</c:if>
				</div>
				<c:if test="${viewemployer.status!='Closed'}">
					<c:if test="${empty reports}">
						<div class="col-sm-12" style="margin-top: 10px;">
							<div id="ADDID" style="float: right;">
								<div class="form-group">
									<span class="btn btn-theme btn-xs btn-default" id="adddes"><i
										class="fa fa-plus"></i></span>

								</div>
							</div>
							<div id="DESCID" style="display: none; line-height: 1.5em;">
								<div class="col-sm-12">
									<label>Descriptions:</label>
									<textarea name="desc" name="description"
										placeholder="Description" class="form-control rtext"
										id="descValId"></textarea>
									<div id="errorSkill" style="color: red;"></div>
								</div>
								<div class="col-sm-12">
									<div class="form-group">
										<label>Appointment Date :</label> <input type="text"
											class="form-control" id="dateValId">
									</div>
								</div>
								</br>
								<div class="col-sm-3" style="margin-top:10px;">
									<div class="form-group">
										<input type="button" id="submitBtId" value="Submit"
											style="padding-top: 5px;"> <input type="hidden"
											value="${viewemployer.id}" name="desc" id="empId"
											onclick="personal();" />
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
</div>

