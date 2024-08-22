<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

<main id="main">
<section class="container instructor-profile-block top">

	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center">SmsTracking</h3>
			</div>

			<c:if test="${userType eq 'admin'}">
				<aside class="bg-gray aback cover">
					<!-- course search form -->
					<form:form name="myForm" method="post"
						commandName="campaignSmsTrakingBO" action="search-sms-tracking"
						modelAttribute="campaignSmsTrakingBO" class="course-search-form">
						<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">

											<!-- <label class="element-block fw-normal font-lato">
										Current Date<font class="f-color">*</font>
									</label> -->
											<form:input type="text" id="datepicker" path="trackingDate"
												placeholder="Tracking Date"
												class="form-control element-block" />
											<label class="element-block fw-normal font-lato"
												style="font-size: 10px">EX:MM/DD/YYYY</label>
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
			<c:if test="${!empty campaignSmsTrackingList.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
                               <th style="width: 120px;">S.No</th> 
								<th>Tracking Date</th>
								<th>Mobile Number</th>
							     <th>Customer Name</th> 
								<th>View</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${campaignSmsTrackingList.list}"
								var="smstrackinglist" varStatus="status">
								<tr>
                                   <td data-title="S.No"><c:out value="${status.count }"></c:out></td> 
                                   
									<td data-title="Tracking Date"><c:out
											value="${smstrackinglist.trackingDate }"></c:out></td>


									<td data-title="Mobile Number"><c:out
											value="${smstrackinglist.mobileNumber }"></c:out></td>

	                                <td data-title="Customer Name"><c:out
											value="${smstrackinglist.customerName }"></c:out></td>
									
											
									  <td data-title="View"><a
										href="view-smstarcking-details.html?trackinId=${smstrackinglist.trackinId}">
											<i class="fa fa-eye"style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>

											

									

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				 <nav style="text-align: center;">
			<ul class="pagination pagination-theme  no-margin center"
				style="margin-left: 575px;">
				<c:if test="${campaignSmsTrackingList.currentPage gt 1}">
					<li><a href="courses-list?page=1&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="courses-list?page=${campaignSmsTrackingList.currentPage - 1}&&search=${searcgValue}"><span><i
								class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
				</c:if>
				<c:forEach items="${campaignSmsTrackingList.noOfPages}" var="i">
					<c:choose>
						<c:when test="${campaignSmsTrackingList.currentPage == i}">
							<li class="active"><a
								style="color: #fff; background-color: #34495e">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="courses-list?page=${i}&&search=${searcgValue}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if
					test="${campaignSmsTrackingList.currentPage lt campaignSmsTrackingList.totalPages}">
					<li><a
						href="courses-list?page=${campaignSmsTrackingList.currentPage + 1}&&search=${courselist}"><span><i
								class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="courses-list?page=${campaignSmsTrackingList.lastRecordValue}&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
				</c:if>
			</ul>
		</nav>
				
			
			</c:if>
		</div>
	</div>
</section>
</main>