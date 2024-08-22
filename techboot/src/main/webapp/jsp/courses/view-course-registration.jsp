<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL"
	value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<title>View course registration</title>

<main id="main">
<section class="container instructor-profile-block">
	<div class="contact-form">
		<h3 class="text-center">View Course Registrations Details</h3>
	</div>
	<aside class="bg-gray aback cover">
		<!-- course search form -->
		<form:form name="myForm" method="post"
			commandName="courseRegistrationBO"
			action="${baseURL}/search-course.html"
			modelAttribute="courseRegistrationBO" class="course-search-form">
			<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
				<div class="row">
					<div class="form-holder">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<form:select id="courseName" path="courseBO.courseName"
										style="height: 43px;">
										<form:option value=" ">---  Courses  ---</form:option>
										<form:options itemLabel="courseName" items="${courselist}"
											itemValue="courseId"></form:options>
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
	<div class="table-wrap">
		<!-- topics data table -->
		<table class="table topics-data-table tab-full-responsive"
			style="text-align: center;">
			<thead class="bg-theme  hidden-xs">
				<tr>
					<th style="width: 120px;">S.No</th>
					<th>Company Name</th>
					<th>Course Name</th>
					<th>Candidate Name</th>
					<th>Email Address</th>
					<th style="text-align: center;">Mobile Number</th>
					<th>view</th>



				</tr>

			</thead>
			<tbody>
				<c:forEach items="${courseList.list}" var="courseregisterlist"
					varStatus="status">
					<tr>
						<td data-title="S.No"><c:out
								value="${courseregisterlist.s_No}"></c:out></td>

						<td data-title="Company Name"><c:out
								value="${courseregisterlist.courseBO.companyBO.companyName}"></c:out></td>

						<td data-title="Course Name"><c:out
								value="${courseregisterlist.courseBO.courseName}"></c:out></td>

						<td data-title="Candidate Name"><c:out
								value="${courseregisterlist.candidateName}"></c:out></td>

						<td data-title="Email Address"><c:out
								value="${courseregisterlist.emailAddress}"></c:out></td>

						<td data-title="Mobile Number" style="text-align: center;"><c:out
								value="${courseregisterlist.mobileNumber}"></c:out></td>
								
						<td data-title="View"><a
							href="view-course-registration-details.html?courseRegisterId=${courseregisterlist.courseRegisterId}">
								<i class="fa fa-eye"
								style="margin-center: 10px; color: #ffc000;"></i>
						</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<nav style="text-align: center;">
		<ul class="pagination pagination-theme  no-margin center"
			style="margin-left: 575px;">
			<c:if test="${courseList.currentPage gt 1}">
				<li><a
					href="view-course-registration?page=1&&search=${searchvalue}"><span><i
							class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
				<li><a
					href="view-course-registration?page=${courseList.currentPage - 1}&&search=${searchvalue}"><span><i
							class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
			</c:if>
			<c:forEach items="${courseList.noOfPages}" var="i">
				<c:choose>
					<c:when test="${courseList.currentPage == i}">
						<li class="active"><a
							style="color: #fff; background-color: #34495e">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="view-course-registration?page=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${courseList.currentPage lt courseList.totalPages}">
				<li><a
					href="view-course-registration?page=${courseList.currentPage + 1}"><span><i
							class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
				<li><a
					href="view-course-registration?page=${courseList.lastRecordValue}"><span><i
							class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
			</c:if>
		</ul>
	</nav>
</section>
</main>