<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			var CourseName = $("#courseName").val();
			if (CoourseName == "Select") {
				$("#courseName").css({
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
</script>
<main id="main">
<section class="container instructor-profile-block">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center">View List Of Course Details</h3>
			</div>

			<c:if test="${not empty succesMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${succesMessage}"></c:out>
				</div>
			</c:if>
			<aside class="bg-gray aback cover">
				<!-- course search form -->
				<form:form name="myForm" method="post"
					commandName="courseDetailsObject"
					action="view-searchcourse-details"
					modelAttribute="courseDetailsObject" class="course-search-form">
					<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-row">
									<div class="form-group">
										<form:select id="courseName" path="curseBO.courseName"
											style="height: 43px;">
											<form:option value=" ">---  Courses  ---</form:option>
											<form:options itemLabel="curseBO.courseName"
												items="${courseListAll}" itemValue="curseBO.courseId"></form:options>
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
				</form:form>
			</aside>

			<c:choose>
				<c:when test="${searchelement eq 'searchelement'}">

					<c:if test="${!empty courseList}">
						<div class="table-wrap">
							<!-- topics data table -->
							<table class="table topics-data-table tab-full-responsive"
								style="text-align: left;">
								<thead class="bg-theme  hidden-xs">

									<tr>
										<th>S.No:</th>
										<th>Course Name</th>
										<th><i class="fa fa-inr"></i>Fees</th>
<!-- 										<th>key features</th>
 -->										<th>View</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${courseList}" var="courseDetailslist"
										varStatus="status">
										<tr>

											<td data-title="S.No"><c:out
													value="${courseDetailslist.s_No }"></c:out></td>


											<td data-title="Course Name"><c:out
													value="${courseDetailslist.curseBO.courseName}"></c:out></td>

											<td data-title="Price"><c:out
													value="${courseDetailslist.rupees}"></c:out></td>


											<%-- <td data-title="key features"><c:out
													value="${courseDetailslist.keyfeatures}" escapeXml="false"></c:out></td> --%>

											<td data-title="View"><a
												href="view-courseDetails-details.html?courseDetailsId=${courseDetailslist.courseDetailsId}">
													<i class="fa fa-eye"
													style="margin-center: 10px; color: #ffc000;"></i>
											</a></td>

											<td data-title="Edit"><a
												href="edit-courseDetails.html?courseDetailsId=${courseDetailslist.courseDetailsId}">
													<i class="fa fa-edit"
													style="margin-center: 10px; color: #ffc000;"></i>
											</a></td>
											<td data-title="Delete"><a
												href="delete-courseDetails.html?courseDetailsId=${courseDetailslist.courseDetailsId}">
													<i class="fa fa-trash"
													style="margin-center: 10px; color: #ffc000;"></i>
											</a></td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>

				</c:when>
				<c:otherwise>
					<c:if test="${!empty courseList.list}">
						<div class="table-wrap">
							<!-- topics data table -->
							<table class="table topics-data-table tab-full-responsive"
								style="text-align: center;">
								<thead class="bg-theme  hidden-xs">

									<tr>
										<th>S.No:</th>
										<th>Course Name</th>
										<th><i class="fa fa-inr"></i>Fees</th>
										<th>View</th>
										<th>Edit</th>
										<th>Delete</th>

									</tr>
								</thead>
								<tbody>

									<c:forEach items="${courseList.list}" var="courseDetailslist"
										varStatus="status">
										<tr>

											<td data-title="S.No"><c:out
													value="${courseDetailslist.s_No }"></c:out></td>


											<td data-title="Course Name"><c:out
													value="${courseDetailslist.curseBO.courseName}"></c:out></td>

											<td data-title="Price"><c:out
													value="${courseDetailslist.rupees}"></c:out></td>

											<td data-title="View"><a
												href="view-courseDetails-details.html?courseDetailsId=${courseDetailslist.courseDetailsId}">
													<i class="fa fa-eye"
													style="margin-center: 10px; color: #ffc000;"></i>
											</a></td>

											<td data-title="Edit"><a
												href="edit-courseDetails.html?courseDetailsId=${courseDetailslist.courseDetailsId}">
													<i class="fa fa-edit"
													style="margin-center: 10px; color: #ffc000;"></i>
											</a></td>
											<td data-title="Delete"><a
												href="delete-courseDetails.html?courseDetailsId=${courseDetailslist.courseDetailsId}">
													<i class="fa fa-trash"
													style="margin-center: 10px; color: #ffc000;"></i>
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
								<li><a
									href="view-course?page=${courseList.currentPage + 1}"><span><i
											class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
								<li><a
									href="view-course-?page=${courseList.lastRecordValue}"><span><i
											class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
							</c:if>
						</ul>
					</nav>
				</c:otherwise>
			</c:choose>

		</div>

	</div>
</section>
</main>




