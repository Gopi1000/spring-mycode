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

		
			<c:if test="${!empty EnrollmentList}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th>Name</th>
								<th>Course Name</th>
								<th> Fees</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${EnrollmentList}" var="EnrollmentList"
								varStatus="status">
								<tr>
								<td>${EnrollmentList.firstName }
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
					 <!-- <h3><a href="downloadPDF.html?type=excel" var="xlsURL">Download PDF Document</a></h3> -->
					<a href="${xlsURL}">Download Excel Report</a>
				</div>

				
			</c:if>
		</div>
	</div>
</section>
</main>