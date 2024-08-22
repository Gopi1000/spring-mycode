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
				<h3 class="text-center">View Enrollment</h3>
			</div>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Error:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${userType eq 'student' }">
				<aside class="bg-gray aback cover">
					<!-- course search form -->
					<form:form name="myForm" method="post"
						action="search-enrollment-course" commandName="searchCourse"
						modelAttribute="searchCourse" class="course-search-form">
						<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="companyName" path="CourseBO.courseName"
													placeholder="courseName" class="form-control element-block" />
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
			<c:if test="${!empty studentCoureslist}">
				<div class="col-sm-12" style="margin-top: -27px">
					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
							<display:table id="data" name="${studentCoureslist}"
								requestURI="/view-course-enrollment" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="s_No" title="S No" />
								<td style="color: #0000FF;"><display:column
										property="courseBO.courseName" title="Course Name" /></td>
								<display:column property="studentRegisterBO.firstName"
									title="Student Name" />
								</td>
								<display:column property="courseBO.fees" title="Fees" />

								<display:column url="edit-course-enrollment" media="html"
									paramId="studentCouresId" paramProperty="studentCouresId"
									title="Edit">
									<a
										href="edit-course-enrollment.html?couresId=${data.courseBO.courseId}&&studentId=${data.studentRegisterBO.studentRegisterId}">
										<i class="fa fa-pencil"
											style="margin-left: 10px; color: #ffc000;"></i></a>
								</display:column>

								<display:column url="delete-course-enrollment" media="html"
									paramId="courseId" paramProperty="courseBO.courseId"
									title="Delete">
									
									<a
										<%-- href="delete-course-enrollment.html?courseId=${data.courseBO.courseId}&&studentId=${data.studentRegisterBO.studentRegisterId}" --%>
										 href="delete-course-enrollment.html?studentCouresId=${data.studentCouresId}"
										onclick="return confirm('Are you sure you want to Delete?')"><i
										class="fa fa-trash" style="margin-left: 10px; color: #ffc000;"></i></a>
								</display:column>
							</display:table>
						</div>
					</div>
				</div>
			</c:if>

			<%-- <c:if test="${!empty enrollmentLists.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Course Name</th>
								<th>Fees</th>
								<th style="width: 71px;">Edit</th>
								<th style="width: 72px;">Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${enrollmentLists.list}" var="course"
								varStatus="status">
								<tr>
									<td data-title="S:No"><c:out value="${course.s_No}"></c:out></td>

									<td data-title="Course Name"><c:out
											value="${course.courseBO.courseName}"></c:out></td>

									<td data-title="Course Name"><c:out
											value="${course.courseBO.fees}"></c:out></td>

									<td data-title="Edit"><a
										href="edit-course-enrollment.html?studentCouresId=${course.studentCouresId}">
											<i class="fa fa-edit"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Delete"><a
										href="delete-course-enrollment.html?courseId=${course.courseBO.courseId}">
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
						<c:if test="${enrollmentLists.currentPage gt 1}">
							<li><a href="view-course-enrollment?page=1&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-course-enrollment?page=${enrollmentLists.currentPage - 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${enrollmentLists.noOfPages}" var="i">
							<c:choose>
								<c:when test="${enrollmentLists.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="view-course-enrollment?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if
							test="${enrollmentLists.currentPage lt enrollmentLists.totalPages}">
							<li><a
								href="view-course-enrollment?page=${enrollmentLists.currentPage + 1}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-course-enrollment?page=${enrollmentLists.lastRecordValue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
   		</c:if> --%>
		</div>
	</div>
</section>
</main>