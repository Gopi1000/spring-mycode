<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<main id="main">
<section class="container instructor-profile-block top">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center">View List of Service</h3>
			</div>

			<c:if test="${not empty succesmessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${succesmessage}"></c:out>
				</div>
			</c:if>

			<c:if test="${not empty infoMessage}">
				<div class="alert alert-info">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>info:</strong>
					<c:out value="${infoMessage}"></c:out>
				</div>
			</c:if>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>

			</c:if>

			<aside class="bg-gray aback cover">
				<!-- course search form -->
				<form:form name="myForm" method="post" commandName="productService"
					action="search-service" modelAttribute="productService"
					class="course-search-form">
					<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-row">
									<div class="form-group">
										<label class="element-block fw-normal font-lato"> <form:input
												type="text" id="serviceName" path="serviceName"
												placeholder="Service Name"
												class="form-control element-block" />
										</label>
									</div>
								</div>
							</div>

							<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12">
								<div class="text-center">
									<button type="submit" id="submit" class=" bac btn-warning">Search
									</button>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</aside>

			<c:if test="${!empty serviceList.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme hidden-xs" style="font-size: 10px">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Service Name</th>
								<th><i class="fa fa-inr"></i> Service Charge</th>
								<th>Duration</th>
								<th style="width: 93px;">View</th>
								<th style="width: 71px;">Edit</th>
								<th style="width: 72px;">Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${serviceList.list}" var="listService"
								varStatus="status">
								<tr>
									<td data-title="S:No"><c:out value="${listService.s_No }"></c:out></td>

									<td data-title="ServiceName"><c:out
											value="${listService.serviceName }"></c:out></td>

									<td data-title="Fees"><c:out value="${listService.rupees}"></c:out></td>

									<td data-title="Duration"><c:out
											value="${listService.duration }"></c:out></td>


									<td data-title="View"><a
										href="view-serviceDetails.html?serviceid=${listService.serviceId}">
											<i class="fa fa-eye"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a
										href="edit-service.html?serviceid=${listService.serviceId}">
											<i class="fa fa-edit"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Delete"><a
										href="delete-service.html?serviceid=${listService.serviceId}">
											<i class="fa fa-trash"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<nav style="text-align: center;">
					<ul class="pagination pagination-theme  no-margin center"
						style="margin-left: 575px;">
						<c:if test="${serviceList.currentPage gt 1}">
							<li><a href="view-service?page=1"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-service?page=${serviceList.currentPage - 1}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${serviceList.noOfPages}" var="i">
							<c:choose>
								<c:when test="${serviceList.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="view-service?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${serviceList.currentPage lt serviceList.totalPages}">
							<li><a
								href="view-service?page=${serviceList.currentPage + 1}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-service?page=${serviceList.lastRecordValue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</section>
</main>