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
				<h3 class="text-center">View Course Category</h3>
			</div>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>
			<%-- <aside class="bg-gray aback cover">
				<!-- course search form -->
				<form:form name="myForm" method="post"
					commandName="categorylist" action="search-courseCategoryName"
					modelAttribute="categorylist" class="course-search-form">
					<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-row">
									<div class="form-group">
										<form:select id="courseCategoryName" path="courseCategoryName"
											style="height: 43px;">
											<form:option value=" ">---  CourseCategory  ---</form:option>
											<form:options itemLabel="courseCategoryName"
												items="${courseCatgoryListAll}" itemValue="courseCategoryId"></form:options>
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
					<c:if test="${!empty courseCatgoryList}">
						<div class="table-wrap">
							<!-- topics data table -->
							<table class="table topics-data-table tab-full-responsive"
								style="text-align: left;">
								<thead class="bg-theme  hidden-xs">
									<tr>
										<th>S.No</th>
										<!-- <th>Created Date</th>  -->
										<th style="width: 72px;">Course CategoryName</th>
										<th style="width: 72px;">Edit</th>
										<th style="width: 72px;">Delete</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${courseCatgoryList}" var="courseCatgoryList"
										varStatus="status">
										<tr>
											<td data-title="S.No"><c:out value="${status.count}"></c:out></td>

											 <td data-title="Create Date"><c:out
											value="${courseCatgoryList.createdDate}"></c:out></td> 


											<td data-title="courseCategoryName"><c:out
													value="${courseCatgoryList.courseCategoryName}"></c:out></td>

											<td data-title="Edit"><a
												href="edit-course-category.html?courseCategoryId=${courseCatgoryList.courseCategoryId}">
													<i class="fa fa-edit"
													style="margin-left: 10px; color: #ffc000;"></i>
											</a></td>

											<td data-title="Delete"><a
												href="delete-course-category.html?courseCategoryId=${courseCatgoryList.courseCategoryId}">
													<i class="fa fa-trash"
													style="margin-left: 10px; color: #ffc000;"></i>
											</a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</c:if>
				</c:when>
			</c:choose> --%>
			<%-- <form:form name="myForm" method="post" 
			commandName="courseCategoryList" modelAttribute="courseCategoryList"
			class="course-search-form">
			
			</form:form> --%>
				<c:if test="${userType eq 'admin'}">
				<aside class="bg-gray aback cover">
					<!-- course search form -->
					<form:form name="myForm" method="post" commandName="categoryValue"
						action="search-courseCategoryName" modelAttribute="categoryValue"
						class="course-search-form">
						<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="courseCategoryName" path="courseCategoryName"
													placeholder="courseCategoryName"
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
			<c:if test="${!empty courseCatgoryList.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">
							<tr>
								<th>S.No</th>
								<!-- <th>Created Date</th>  -->
								<th style="width: 72px;">Course CategoryName</th>
								<th style="width: 72px;">Edit</th>
								<th style="width: 72px;">Delete</th>
							</tr>

						</thead>
						<tbody>
							<c:forEach items="${courseCatgoryList.list}"
								var="courseCatgoryList" varStatus="status">
								<tr>
									<td data-title="S.No"><c:out value="${status.count}"></c:out></td>

									<%--  <td data-title="Create Date"><c:out
											value="${courseCatgoryList.createdDate}"></c:out></td> 
 --%>

									<td data-title="courseCategoryName"><c:out
											value="${courseCatgoryList.courseCategoryName}"></c:out></td>

									<td data-title="Edit"><a
										href="edit-course-category.html?courseCategoryId=${courseCatgoryList.courseCategoryId}">
											<i class="fa fa-edit"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Delete"><a
										href="delete-course-category.html?courseCategoryId=${courseCatgoryList.courseCategoryId}">
											<i class="fa fa-trash"
											style="margin-left: 10px; color: #ffc000;"></i>
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
					<c:if test="${courseCatgoryList.currentPage gt 1}">
						<li><a href="view-course-category?page=1"><span><i
									class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
						<li><a
							href="view-course-category?page=${courseCatgoryList.currentPage - 1}"><span><i
									class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
					</c:if>
					<c:forEach items="${courseCatgoryList.noOfPages}" var="i">
						<c:choose>
							<c:when test="${courseCatgoryList.currentPage == i}">
								<li class="active"><a
									style="color: #fff; background-color: #34495e">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="view-course-category?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if
						test="${courseCatgoryList.currentPage lt courseCatgoryList.totalPages}">
						<li><a
							href="view-course-category?page=${courseCatgoryList.currentPage + 1}"><span><i
									class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
						<li><a
							href="view-course-category?page=${courseCatgoryList.lastRecordValue}"><span><i
									class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</section>
</main>




