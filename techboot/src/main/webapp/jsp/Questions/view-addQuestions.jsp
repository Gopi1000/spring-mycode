<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#submit').click(function(e) {
			var isValid = true;
			var CourseSubCategoryName = $("#CourseSubCategoryName").val();
			if (CourseSubCategoryName == "Select") {
				$("#CourseSubCategoryName").css({
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
<section class="container instructor-profile-block top">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center">View List of Question</h3>
			</div>
			<c:if test="${not empty succesmessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${succesmessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${userType eq 'admin'}">
				<aside class="bg-gray aback cover">
					<!-- course search form -->
					<form:form name="myForm" method="post"
						commandName="QuestionsObject" action="search-question"
						modelAttribute="QuestionsObject" class="course-search-form">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<%-- <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="question" path="question"
													placeholder="question" class="form-control element-block" />
											</label>
										</div>
									</div>
								</div> --%>
										<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="questionId" path="questionId"
													placeholder="questionId"
													class="form-control element-block" />
									</label>
								</div>
							</div>
						</div>
								<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="questionType" path="questionType"
													placeholder="questionType"
													class="form-control element-block" />
											</label>
										</div>
									</div>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="subcategory"
													path="subCategoryBO.subcategory" placeholder="subcategory"
													class="form-control element-block" />
											</label>
										</div>
									</div>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="SubcategoryBO.categoryBO.category"
													path="subCategoryBO.categoryBO.category"
													placeholder="categoryName"
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
			<c:if test="${!empty QuestionsLists.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>subCategory</th>
								<th>QuestionId</th>
								<th>QuestionType</th>
								<th>Category</th>
								<th>View</th>
								<th style="width: 71px;">Edit</th>
								<th style="width: 71px;">Delete</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${QuestionsLists.list}" var="QuestionsLists"
								varStatus="status">
								<tr>
									<td data-title="S:No"><c:out
											value="${QuestionsLists.s_No }"></c:out></td>

									<td data-title="subCategory"><c:out
											value="${QuestionsLists.subCategoryBO.subcategory}"></c:out></td>

									<td data-title="questionId"><c:out
											value="${QuestionsLists.questionId }"></c:out></td>

									<td data-title="questionType"><c:out
											value="${QuestionsLists.questionType}"></c:out></td>

									<td data-title="category"><c:out
											value="${QuestionsLists.subCategoryBO.categoryBO.category}"></c:out></td>

									<td data-title="View"><a
										href="view-Questions-details.html?questionId=${QuestionsLists.questionId}&&subcategoryId=${QuestionsLists.subCategoryBO.subcategoryId}&&categoryId=${QuestionsLists.subCategoryBO.categoryBO.categoryId}">
											<i class="fa fa-eye"
											style="margin-center: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a
										href="edit-Questions.html?questionId=${QuestionsLists.questionId}&&subcategoryId=${QuestionsLists.subCategoryBO.subcategoryId}&&categoryId=${QuestionsLists.subCategoryBO.categoryBO.categoryId}">
											<i class="fa fa-edit"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>
									<td data-title="Delet"><a
										href="delete-Questions.html?questionId=${QuestionsLists.questionId}">
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
						<c:if test="${QuestionsLists.currentPage gt 1}">
							<li><a
								href="view-addQuestions?page=1&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-addQuestions?page=${QuestionsLists.currentPage - 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${QuestionsLists.noOfPages}" var="i">
							<c:choose>
								<c:when test="${QuestionsLists.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="view-addQuestions?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if
							test="${QuestionsLists.currentPage lt QuestionsLists.totalPages}">
							<li><a
								href="view-addQuestions?page=${QuestionsLists.currentPage + 1}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-addQuestions?page=${QuestionsLists.lastRecordValue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</section>
</main>