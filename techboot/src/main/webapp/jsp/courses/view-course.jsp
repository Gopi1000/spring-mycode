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
				<h3 class="text-center">View List Of Course</h3>
			</div>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>
			<aside class="bg-gray aback cover">
				<!-- course search form -->
				<form:form name="myForm" method="post" commandName="courseObject"
					action="view-search-course" modelAttribute="courseObject"
					class="course-search-form">
					<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-row">
									<div class="form-group">
										<label class="element-block fw-normal font-lato"> <form:input
												type="text" id="courseName" path="courseName"
												placeholder="Course Name" class="form-control element-block" />
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
			<c:if test="${!empty courseList.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No</th>
								<th>Course Name</th>
								<th>Tutor Name</th>
								<th>Course Category</th>
								<th><i class="fa fa-inr"></i>Fees</th>
								<th style="width: 93px;">View</th>
								<th style="width: 93px;">Edit</th>
								<th style="width: 93px;">Delete</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach items="${courseList.list}" var="courselist"
								varStatus="status">
								<tr>
									<td data-title="S.No"><c:out value="${courselist.s_No }"></c:out></td>

									<td data-title="Title Name"><c:out
											value="${courselist.courseName }"></c:out></td>

									<td data-title="Author Name"><c:out
											value="${courselist. authorName}"></c:out></td>
											
											
											
											<td data-title="courseCategory"><c:out
											value="${courselist.courseCategoryBO.courseCategoryName}"></c:out></td>

									<td data-title="Price"><c:out
											value="${courselist.rupees}"></c:out></td>
											
											
                                     <td data-title="View"><a
										href="view-details-course.html?courseId=${courselist.courseId}">
											<i class="fa fa-eye"style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a
										href="edit-course.html?courseId=${courselist.courseId}">
											<i class="fa fa-edit" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
									<td data-title="Delete"><a
										href="delete-course.html?courseId=${courselist.courseId}">
											<i class="fa fa-trash" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<nav style="text-align: center;">
				<ul class="pagination pagination-theme  no-margin center"
					style="margin-left: 575px;">
					<c:if test="${courseList.currentPage gt 1}">
						<li><a href="view-course?page=1&&search=${searchvalue}"><span><i
									class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
						<li><a
							href="view-course?page=${courseList.currentPage - 1}&&search=${searchvalue}"><span><i
									class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
					</c:if>
					<c:forEach items="${courseList.noOfPages}" var="i">
						<c:choose>
							<c:when test="${courseList.currentPage == i}">
								<li class="active"><a
									style="color: #fff; background-color: #34495e">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="view-course?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${courseList.currentPage lt courseList.totalPages}">
						<li><a href="view-course?page=${courseList.currentPage + 1}"><span><i
									class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
						<li><a href="view-course-?page=${courseList.lastRecordValue}"><span><i
									class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
					</c:if>
				</ul>
			</nav>
		</div>

	</div>
</section>
</main>




