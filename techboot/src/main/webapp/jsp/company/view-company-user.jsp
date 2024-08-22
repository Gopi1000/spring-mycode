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
				<h3 class="text-center">View List of Company</h3>
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
			<c:if test="${!empty companylists}">
				<div class="table-wrap">
       <table class="table topics-data-table tab-full-responsive" style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Company Name</th>
								<th>Email Address</th>
								<th>Mobile Number</th>
								<th style="width: 93px;">View</th>
								<th style="width: 71px;">Edit</th>
								
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${companylists}" var="CompanyList"
								varStatus="status">
								<tr>
									<td data-title="S:No"><c:out value="${CompanyList.s_No}"></c:out></td>

									<td data-title="Company Name"><c:out
											value="${CompanyList.companyName}"></c:out></td>

									<td data-title="emailAddress"><c:out
											value="${CompanyList.emailAddress}"></c:out></td>

									<td data-title="contectNumber"><c:out
											value="${CompanyList.contectNumber}"></c:out></td>

									<td data-title="view"><a
										href="view-companydetails.html?company_Id=${CompanyList.companyId}">
											<i class="fa fa-eye"style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a href="edit_company_user.html?company_Id=${companyId}">
											<i class="fa fa-edit" style="margin-left: 10px;color: #ffc000;"></i>
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

</html>