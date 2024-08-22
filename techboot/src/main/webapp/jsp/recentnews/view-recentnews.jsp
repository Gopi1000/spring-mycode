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
				<h3 class="text-center">View List of Recent News</h3>
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
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${!empty newslists.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">
							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Title Name</th>
								<th>Date</th>
								<th>Author Name</th>
								<th style="width: 93px;">View</th>
								<th style="width: 71px;">Edit</th>
								<th style="width: 72px;">Delete</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${newslists.list}" var="newslist"
								varStatus="status">
								<tr>

									<td data-title="S.No"><c:out value="${newslist.s_No }"></c:out></td>

									<td data-title="Title Name"><c:out
											value="${newslist.titleName }"></c:out></td>
									<td data-title="Date"><c:out
											value="${newslist.beginDate }"></c:out></td>

									<td data-title="Author Name"><c:out
											value="${newslist.authorName }"></c:out></td>

									<td data-title="view"><a
										href="view-recentNews-details.html?newsId=${newslist.newsId}">
											<i class="fa fa-eye"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a
										href="edit-recentNews.html?newsId=${newslist.newsId}"> <i
											class="fa fa-pencil"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Delete"><a
										href="delete-recentNews.html?newsId=${newslist.newsId}"> <i
											class="fa fa-trash"
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
						<c:if test="${newslists.currentPage gt 1}">
							<li><a href="view-recentNews?page=1&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-recentNews?page=${newslists.currentPage - 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${newslists.noOfPages}" var="i">
							<c:choose>
								<c:when test="${newslists.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="view-recentNews?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${newslists.currentPage lt newslists.totalPages}">
							<li><a
								href="view-recentNews?page=${newslists.currentPage + 1}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-recentNews?page=${newslists.lastRecordValue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</section>
</main>

