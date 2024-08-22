
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
				<h3 class="text-center">View List of Customer</h3>
			</div>
		</div>
	</div>
	<c:if test="${not empty sucessMessage}">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Success:</strong>
			<c:out value="${sucessMessage}"></c:out>
		</div>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Error:</strong>
			<c:out value="${errorMessage}"></c:out>
		</div>
	</c:if>

	<aside class="bg-gray aback cover">
		<!-- course search form -->
		<form:form name="myForm" method="post" commandName="customerBo"
			action="search-Customer" modelAttribute="customerBo"
			class="course-search-form">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="form-holder">
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
											type="text" id="companyName" path="firstName"
											placeholder="First Name" class="form-control element-block" autofocus="true"/>
									</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
											type="text" id="companyName" path="emailId"
											placeholder="Email Id" class="form-control element-block"  />
									</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
											type="text" id="companyName" path="mobileNumber"
											placeholder="Mobile Number"
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
			</div>
		</form:form>
	</aside>

	<c:if test="${!empty customerLists.list}">

		<div class="table-wrap">
			<!-- topics data table -->
			<table class="table topics-data-table tab-full-responsive"
				style="text-align: left;">
				<thead class="bg-theme  hidden-xs">

					<tr>
						<th>S.No:</th>
						<th>First Name</th>
						<th>Email Address</th>
						<th>Mobile Number</th>
						<th style="width: 93px;">View</th>
						<th style="width: 71px;">Edit</th>
						<th style="width: 72px;">Delete</th>
					</tr>

				</thead>
				<tbody>
					<c:forEach items="${customerLists.list}" var="customerList"
						varStatus="status">
						<tr>

							<td data-title="S:No"><c:out value="${customerList.s_No }"></c:out></td>

							<td data-title="First Name"><c:out
									value="${customerList.firstName }"></c:out></td>


							<td data-title="EmailId"><c:out
									value="${customerList.emailId }"></c:out></td>

							<td data-title="Mobile Number"><c:out
									value="${customerList.mobileNumber }"></c:out></td>

							<td data-title="view"><a
								href="customer-viewdetails.html?viewcustomer=${customerList.customerId}">
									<i class="fa fa-eye" style="margin-left: 10px; color: #ffc000;"></i>
							</a></td>

							<td data-title="Edit"><a
								href="edit-customer.html?customerid=${customerList.customerId}">
									<i class="fa fa-edit"
									style="margin-left: 10px; color: #ffc000;"></i>
							</a></td>

							<td data-title="Delete"><a
								href="delete-customer.html?customerid=${customerList.customerId}">
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
				style="margin-left: 291px;">
				<c:if test="${customerLists.currentPage gt 1}">
					<li><a href="view-customer?page=1&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="view-customer?page=${customerLists.currentPage - 1}&&search=${searcgValue}"><span><i
								class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
				</c:if>
				<c:forEach items="${customerLists.noOfPages}" var="i">
					<c:choose>
						<c:when test="${customerLists.currentPage == i}">
							<li class="active"><a
								style="color: #fff; background-color: #34495e">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="view-customer?page=${i}&&search=${searcgValue}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if
					test="${customerLists.currentPage lt customerLists.totalPages}">
					<li><a
						href="view-customer?page=${customerLists.currentPage + 1}&&search=${searcgValue}"><span><i
								class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="view-customer?page=${customerLists.lastRecordValue}&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
				</c:if>
			</ul>
		</nav>
	</c:if>


</section>
</main>