<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<main id="main"> <section
	class="container instructor-profile-block">

<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="contact-form">
			<h3 class="text-center">View List of Event</h3>
		</div>
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${successMessage}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-dancer">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
		<c:if test="${userType eq 'admin'}">
				<aside class="bg-gray aback cover">
					<!-- course search form -->
					<form:form name="myForm" method="post" commandName="eventDetailsObject"
						action="search-event" modelAttribute="eventDetailsObject"
						class="course-search-form">
						<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="titleName" path="titleName"
													placeholder="Title Name"
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
			</c:if>
		<c:if test="${!empty upcomingeventlist.list}">
			<div class="table-wrap">
				<!-- topics data table -->
				<table class="table topics-data-table tab-full-responsive" style="text-align: left;">
					<thead class="bg-theme  hidden-xs">

						<tr>
							<th style="width: 120px;">S.No:</th>
							<th>Title Name</th>
							<th>Date</th>
							<th>Timing</th>
							<th style="width: 93px;">View</th>
							<th style="width: 71px;">Edit</th>
							<th style="width: 72px;">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${upcomingeventlist.list}" var="eventlist"
							varStatus="status">
							<tr>

                                <td data-title="Title Name"><c:out
										value="${eventlist.s_No}"></c:out></td>
								<td data-title="Title Name"><c:out
										value="${eventlist.titleName }"></c:out></td>

								<td data-title="Date"><c:out value="${eventlist.eventDate}"></c:out></td>

								<td data-title="Timing"><c:out value="${eventlist.timing }"></c:out></td>
										
							<td data-title="view"><a
								href="view-event-details.html?eventId=${eventlist.eventId}">
									<i class="fa fa-eye" style="margin-left: 10px;color: #ffc000;"></i>
							</a></td>		

								<td data-title="Edit"><a
									href="edit-event.html?eventId=${eventlist.eventId}"> <i
										class="fa fa-pencil" style="margin-left: 10px;color: #ffc000;"></i>
								</a></td>

								<td data-title="Delete"><a
									href="delete-event.html?eventId=${eventlist.eventId}"> <i
										class="fa fa-trash" style="margin-left: 10px;color: #ffc000;"></i>
								</a></td>
								
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
            </div>
            
            <nav style="text-align: center;">
					<ul class="pagination pagination-theme  no-margin center"
						style="margin-left: 575px;">
						<c:if test="${upcomingeventlist.currentPage gt 1}">
							<li><a href="view-event?page=1&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-event?page=${upcomingeventlist.currentPage - 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${upcomingeventlist.noOfPages}" var="i">
							<c:choose>
								<c:when test="${upcomingeventlist.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="view-event?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if
							test="${upcomingeventlist.currentPage lt upcomingeventlist.totalPages}">
							<li><a
								href="view-event?page=${upcomingeventlist.currentPage + 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-event?page=${upcomingeventlist.lastRecordValue}&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
		</c:if>
	</div>
</div>
</section> </main>
