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
				<h3 class="text-center">View List Of Message</h3>
			</div>

			<c:if test="${not empty Updatemessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${Updatemessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty message}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${message}"></c:out>
				</div>

			</c:if>
			<c:if test="${not empty deletemessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${deletemessage}"></c:out>
				</div>

			</c:if>
		</div>
		
		<c:if test="${!empty messageBOlist}">
			<div class="table-wrap">
				<!-- topics data table -->
				<table class="table topics-data-table tab-full-responsive">
					<thead class="bg-theme  hidden-xs">
						<tr>
							<th>Message</th>
							<th>Date</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${messageBOlist}" var="messageBOlist">
							<tr>
								<td data-title="Customer Message"><c:out
										value="${messageBOlist.message }"></c:out></td>
								<td data-title="Date"><c:out value="${messageBOlist.date }"></c:out></td>
								<td data-title="Edit"><a
									href="edit-message.html?id=${messageBOlist.messageId}"> <i
										class="fa fa-edit" style="margin-left: 10px"></i>
								</a></td>
								<td data-title="Delete"><a
									href="delete-message.html?id=${messageBOlist.messageId}"> <i
										class="fa fa-trash" style="margin-left: 10px"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
</section>
</main>



