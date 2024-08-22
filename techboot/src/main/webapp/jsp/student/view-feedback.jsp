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
				<h3 class="text-center">View Feedback</h3>
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

		
			<c:if test="${!empty feedbackbolist}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Course Name</th>
								<th> Star Rating</th>
								<th> Command </th>
								<th style="width: 71px;">Edit</th>
								<th style="width: 72px;">Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${feedbackbolist}" var="feedback" varStatus="status">
								
								<tr>
									<td data-title="S:No"><c:out value="${status.count}"></c:out></td>

									<td data-title="Course Name"><c:out
											value="${feedback.courseBO.courseName}"></c:out></td>
											
											<td data-title="Star Rating"><c:out
											value="${feedback.starRating}"></c:out></td>
											
											<td data-title="Star Rating"><c:out
											value="${feedback.command}"></c:out></td>

									<td data-title="Edit"><a
										href="edit-feedback?feedbackId=${feedback.feedbackId}">
											<i class="fa fa-edit" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
									
									<td data-title="Delete"><a
										href="delete-feedback?feedbackId=${feedback.feedbackId}">
											<i class="fa fa-trash" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</c:if>
		</div>
	</div>
</section>
</main>