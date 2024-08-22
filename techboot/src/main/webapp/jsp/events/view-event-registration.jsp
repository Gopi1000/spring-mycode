<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<main id="main">
<section class="container instructor-profile-block">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center">View List of Event Registration</h3>
			</div>
			<aside class="bg-gray aback cover">
				<!-- course search form -->
				<form:form name="myForm" method="post" commandName="eventRegisterBo"
					action="search-event-registration" modelAttribute="eventRegisterBo"
					class="course-search-form">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<div class="form-holder">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<form:select id="eventsName" path="eventBo.eventsName"
												style="height: 43px;">
												<form:option value="">---Upcoming Events---</form:option>
												<form:options itemLabel="eventsName" items="${eventslists}"
													itemValue="eventId"></form:options>
											</form:select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<form:select id="eventsBo.titleName" path="eventBo.titleName"
												style="height: 43px;">
												<form:option value="">----Past Events---</form:option>
												<form:options itemLabel="titleName" items="${eventslist}"
													itemValue="eventId"></form:options>
											</form:select>
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
					</div>
				</form:form>
			</aside>

			<c:if test="${!empty eventregistrationlist.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th>S.No:</th>
								<th style="width: 93px;">Events Name</th>
								<th style="width: 93px;">Candidate Name</th>
								<th style="width: 93px;">Email Address</th>
								<!-- <th>View</th> -->
							</tr>

						</thead>
						<tbody>
							<c:forEach items="${eventregistrationlist.list}"
								var="registerlist" varStatus="status">
								<tr>
									<td data-title="S.No"><c:out value="${registerlist.s_No}"></c:out></td>

									<td data-title="Events Name"><c:out
											value="${registerlist.eventBo.titleName}"></c:out></td>

									<td data-title="Candidate Name"><c:out
											value="${registerlist.candidateName}"></c:out></td>

									<td data-title="Email Address"><c:out
											value="${registerlist.emailAddress}"></c:out></td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<nav style="text-align: center;">
					<ul class="pagination pagination-theme  no-margin center"
						style="margin-left: 575px;">
						<c:if test="${eventregistrationlist.currentPage gt 1}">
							<li><a
								href="view-events-registration?page=1&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-events-registration?page=${eventregistrationlist.currentPage - 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${eventregistrationlist.noOfPages}" var="i">
							<c:choose>
								<c:when test="${eventregistrationlist.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="view-events-registration?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if
							test="${eventregistrationlist.currentPage lt eventregistrationlist.totalPages}">
							<li><a
								href="view-events-registration?page=${eventregistrationlist.currentPage + 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-events-registration?page=${eventregistrationlist.lastRecordValue}&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</section>
</main>
