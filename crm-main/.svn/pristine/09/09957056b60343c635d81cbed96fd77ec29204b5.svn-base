<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- <script>
	$(function() {

		$("#startDateInput").datepicker({
			changeMonth : true,
			changeYear : true,
			/* numberOfMonths : 1,
			minDate : 0,
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate());
				$("#endDateInput").datepicker("option", "minDate", dt);

			} */
		});
		$("#endDateInput").datepicker({
			maxDate:0, 
			changeMonth : true,
			changeYear : true,
			/* numberOfMonths : 1,
			minDate : 0,
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate());
				$("#startDateInput").datepicker("option", "maxDate", dt);

			} */
		});

	});
 -->


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

<div class="box-list" style="margin-bottom: 3%; margin-top: 3%;">

	<div class="item">
		<div class="row ">
			<h4 class="text-center titleunderline"
				style="margin-top: -10px;">My Leads</h4>
			<div class=" col-md-12">
				<div class="col-sm-8">
					<!-- Start form search -->
					<form:form id="myForm" method="post" class="login-form clearfix"
						action="verified-access-records.html" commandName="viewRecords">

						<div class="row">
							<div class=" col-md-2 fs-mobile-search" style="padding: 0px">
								<div class="form-group find-field-space">
									<label class="hidden-xs">&nbsp;</label>
									<form:input type="ntext" class="form-control" path="firstName"
										placeholder="Name" escapeXml="false"
										style="height: 35px;font-weight: 700;"></form:input>
								</div>
							</div>

							<div class=" col-md-2 fs-mobile-search" style="padding: 0px">
								<div class="form-group find-field-space">
									<label class="hidden-xs">&nbsp;</label>
									<form:input type="ntext" class="form-control" path="startDate"
										id="startDateInput" placeholder="startDate"
										data-date-format="yyyy/mm/dd"
										style="height:35px;font-weight: 700;"></form:input>
								</div>
							</div>

							<div class=" col-md-2 fs-mobile-search" style="padding: 0px">
								<div class="form-group find-field-space">
									<label class="hidden-xs">&nbsp;</label>
									<form:input type="ntext" class="form-control" path="endDate"
										id="endDateInput" placeholder="endDate"
										data-date-format="dd/mm/yyyy"
										style="height:35px;font-weight: 700;"></form:input>
								</div>
							</div>
							<div
								class=" col-md-1 fsl-search fsl-mobile-search  fs-mobile-btnsearch"
								style="padding-bottom: 0px;">
								<div class="form-group home-right">
									<label class="hidden-xs">&nbsp;</label>
									<button class="btn btn-theme btn-success btn-block"
										style="padding: 6px 5px; background-color: #7cb228; border-color: #7cb228;">
										<small><i class="fa fa-search" aria-hidden="true"
											style="font-size: 20px;"></i></small>
									</button>
								</div>
							</div>

						</div>
					</form:form>
				</div>
				<div class="col-sm-4">
					<p>
						<strong class="color-black">Total Verified Data:</strong> <a
							class="" style="color: #1b1818; font-weight: bold;"><c:out
								value="${totalAccessRecordCount}"></c:out></a>
					</p>

					<p>
						<strong class="color-black">Total Accessed Data:</strong> <a
							class="" style="color: #1b1818; font-weight: bold;"><c:out
								value="${totalAccessedCount}"></c:out></a>
					</p>
				</div>
			</div>
			<div class=" col-md-12">
			<div class="warning" style="width: 100%; text-align: left;">
				<c:if test="${not empty infoMessage}">
					<div class="alert alert-info" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>Info!</strong>
						<c:out value="${infoMessage}"></c:out>
					</div>
				</c:if>
			</div>
			</div>
			<c:if test="${!empty searchJobseeker}">
				<div class="row ">
					<div class="col-sm-12">
						<div class="pi-responsive-table-sm">
							<table
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders"
								style="width: 100%;">
								<thead style="background-color: #fff; border-color: #ccc;">
									<!-- Table row -->
									<tr>
										<th><span style="color: #3d454c;">S.No</span></th>
										<th><span style="color: #3d454c;">User Name</span></th>
										<th><span style="color: #3d454c;">Mobile Number</span></th>
										<th><span style="color: #3d454c;">Access Date</span></th>
										<th><span style="color: #3d454c;">Access Data Id</span></th>
										<th><span style="color: #3d454c;">Access Status</span></th>
									</tr>
									<!-- End table row -->
								</thead>
								<tbody>
									<c:forEach items="${searchJobseeker.list}" var="searchResult">
										<tr class="no-margin-top list-capitalize"
											style="line-height: 2em; font-size: 12px;">
											<td>${searchResult.SNo}</td>
											<td>${searchResult.firstName}</td>
											<td>${searchResult.phone}</td>
											<td>${searchResult.accessDate}</td>
											<td>${searchResult.id}</td>
											<td>${searchResult.accessStatus}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<nav style="text-align: center;">

							<ul class="pagination pagination-theme  no-margin">
								<c:if test="${searchJobseeker.currentPage gt 1}">
									<li><a href="profile-view.html?page=1"><span><i
												class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
									<li><a
										href="verified-access-records.html?page=${searchJobseeker.currentPage - 1}&&name=${firstName}&&startDate=${startDate}&&endDate=${endDate}"><span><i
												class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
								</c:if>
								<c:forEach items="${searchJobseeker.noOfPages}" var="i">
									<c:choose>
										<c:when test="${searchJobseeker.currentPage == i}">
											<li class="active"><a
												style="color: #fff; background-color: #34495e">${i}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="verified-access-records.html?page=${i}&&name=${firstName}&&startDate=${startDate}&&endDate=${endDate}">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if
									test="${searchJobseeker.currentPage lt searchJobseeker.totalPages}">
									<li><a
										href="verified-access-records.html?page=${searchJobseeker.currentPage + 1}&&name=${firstName}&&startDate=${startDate}&&endDate=${endDate}"><span><i
												class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
									<li><a
										href="verified-access-records.html?page=${searchJobseeker.lastRecordValue}&&name=${firstName}&&startDate=${startDate}&&endDate=${endDate}"><span><i
												class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>

			</c:if>
		</div>
	</div>

</div>