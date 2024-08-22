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
				<h3 class="text-center">View List Of Campaign</h3>
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
					<strong>error:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			
	<%-- 		
			<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

<script>
	$(function() {
		$("#datepickers").datepicker();
	});
</script>

<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">Started
								Date<font class="f-color">*</font>
							</label>
							<form:input type="text" id="datepicker" path="startedTime"
								placeholder="Started Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:MM/DD/YYYY</label>
							<form:errors path="startedTime" cssClass="errors" />
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="element-block fw-normal font-lato">End Date<font
								class="f-color">*</font></label>
							<form:input type="text" id="datepickers" path="endTime"
								placeholder="End Date" class="form-control element-block" />
							<label class="element-block fw-normal font-lato"
								style="font-size: 10px">EX:MM/DD/YYYY</label>
							<form:errors path="endTime" cssClass="errors" />
						</div>
					</div>
				</div> --%>
			<aside class="bg-gray aback cover">
				<!-- course search form -->
				<form:form name="myForm" method="post" commandName="campaignBO"
					action="search-Campaign" modelAttribute="campaignBO"
					class="container course-search-form">
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
								<div class="form-row">
									<div class="form-group">
										<label class="element-block fw-normal font-lato"> <form:input
												type="text" id="companyName" path="campaignName"
												placeholder="Campaign Name"
												class="form-control element-block"  autofocus="true"/>
										</label>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
								<div class="form-row">
									<div class="form-group">
									<label class="element-block fw-normal font-lato" style="line-height: 0.857;">
									<form:select path="category"
									id="categoryId"  style="height: 42px;">
									<form:option value="Select">-- Category --   </form:option>
									<form:option value="Sms">SMS   </form:option>
									<form:option value="WhatsApp">WHATSAPP </form:option>
									<form:option value="Email">EMAIL  </form:option>
								</form:select>
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
				</form:form>
			</aside>
			
			<c:if test="${!empty campaignLists.list}">
			<c:choose>
			<c:when test="${userType eq 'admin'}">
			<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Campaign Name</th>
								<th>Category</th>
								<th style="width: 93px;">View</th>
								<th style="width: 93px;">Edit</th>
								<th style="width: 93px;">Delete</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach items="${campaignLists.list}" var="campaignList"
								varStatus="status">
								<tr>

									<td data-title="S:No"><c:out value="${campaignList.s_No }"></c:out></td>

									<td data-title="Campaign Name"><c:out
											value="${campaignList.campaignName }"></c:out></td>
									
									<td data-title="Category"><c:out
											value="${campaignList.category }"></c:out></td>

									<td data-title="View"><a
										href="view-campaign-details.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-eye"style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a
										href="edit-campaign.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-edit" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
									<td data-title="Delete"><a
										href="delete-campaign.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-trash" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			
			</c:when>
			
			<c:otherwise>
			  <c:if test="${companyType eq 'Service' }">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Campaign Name</th>
								<th>Service Name</th>
								<th>Category</th>
								<th style="width: 93px;">View</th>
								<th style="width: 93px;">Edit</th>
								<th style="width: 93px;">Delete</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach items="${campaignLists.list}" var="campaignList"
								varStatus="status">
								<tr>

									<td data-title="S:No"><c:out value="${campaignList.s_No }"></c:out></td>

									<td data-title="Campaign Name"><c:out
											value="${campaignList.campaignName }"></c:out></td>
									<td data-title="Service Name"><c:out
											value="${campaignList.productService.serviceName}"></c:out></td>

									<td data-title="Category"><c:out
											value="${campaignList.category }"></c:out></td>

									<td data-title="View"><a
										href="view-campaign-details.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-eye"style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a
										href="edit-campaign.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-edit" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
									<td data-title="Delete"><a
										href="delete-campaign.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-trash" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</c:if>
				<c:if test="${companyType eq 'Training' }">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Campaign Name</th>
								<th>Course Name</th>
								<th>Category</th>
								<th style="width: 93px;">View</th>
								<th style="width: 93px;">Edit</th>
								<th style="width: 93px;">Delete</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach items="${campaignLists.list}" var="campaignList"
								varStatus="status">
								<tr>

									<td data-title="S:No"><c:out value="${campaignList.s_No }"></c:out></td>

									<td data-title="Campaign Name"><c:out
											value="${campaignList.campaignName }"></c:out></td>
									<td data-title="Course Name"><c:out
											value="${campaignList.course.courseName}"></c:out></td>

									<td data-title="Category"><c:out
											value="${campaignList.category }"></c:out></td>

									<td data-title="View"><a
										href="view-campaign-details.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-eye"style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>

									<td data-title="Edit"><a
										href="edit-campaign.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-edit" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
									<td data-title="Delete"><a
										href="delete-campaign.html?campaignId=${campaignList.campaignId}">
											<i class="fa fa-trash" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</c:if>
				</c:otherwise>
			</c:choose>
				
				<nav style="text-align: center;">
					<ul class="pagination pagination-theme  no-margin center"
						style="margin-left: 575px;">
						<c:if test="${campaignLists.currentPage gt 1}">
							<li><a href="view-campaign?page=1&&search=${searchvalue}"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-campaign?page=${campaignLists.currentPage - 1}&&search=${searchvalue}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${campaignLists.noOfPages}" var="i">
							<c:choose>
								<c:when test="${campaignLists.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="view-campaign?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if
							test="${campaignLists.currentPage lt campaignLists.totalPages}">
							<li><a
								href="view-campaign?page=${campaignLists.currentPage + 1}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="view-campaign?page=${campaignLists.lastRecordValue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</section>
</main>



