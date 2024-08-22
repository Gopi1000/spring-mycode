
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<aside class="container instructor-profile-block checkout-form company-dashboard">
	<div class="add-job_container">
	   <div class="job-box">
		<div id="wrapper">
			<c:if test="${not empty infoMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>info:</strong>
					<c:out value="${infoMessage}"></c:out>
				</div>
			</c:if>
	    <div class="row bg-title">
		   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<h3 class="text-left mobile-title" style="text-decoration: underline;">
			
			</h3>
		  </div>
		</div>
		<c:if test="${companyType eq 'Service' }">
		<!-- categories list -->
			<ul class="list-unstyled categories-list">
				<div class="col-lg-12">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="panel panel-default"
								style="text-align: center; height: 130px;">
							<div class="panel-heading home-dash">
									<div class="panel-title">
									<label style="font-weight: normal; color: #000;"><i
											class=" fa fa-qrcode" aria-hidden="true"></i> Service </label>
											</div>
										</div>
									<div id="collapseOne" class="panel-collapse collapse in">
										<div class="panel-body"
											style=" font-weight: 600;">
												<a href="view-service.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty serviceCounts}">
														<c:out value="${serviceCounts}"></c:out> 
													</c:if> <c:if test="${empty serviceCounts}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading home-dash">
											<div class="panel-title">
												<label style="font-weight: normal; color: #000;"><i
													class="fa fa-pencil-square-o" aria-hidden="true"></i> Campaign </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
											 <a href="view-campaign.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty campaignCounts}">
														<c:out value="${campaignCounts}"></c:out> 
													</c:if> <c:if test="${empty campaignCounts}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
						 	<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading home-dash">
											<div class="panel-title">
												<label style="font-weight: normal; color: #000;"><i
													class="fa fa-user" aria-hidden="true"></i> Customers </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												<a href="view-customer.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty customerCounts}">
														<c:out value="${customerCounts}"></c:out> 
													</c:if> <c:if test="${empty customerCounts}">0</c:if>
												</a>
											</div>
										</div>
									</div>
								</div>								
						   </div>		
					</ul>
					</c:if>
					<c:if test="${companyType eq 'Training' }">
		<!-- categories list -->
			<ul class="list-unstyled categories-list">
				<div class="col-lg-12">					
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading home-dash">
											<div class="panel-title">
												<label style="font-weight: normal; color: #000;"><i
													class="fa fa-pencil-square-o" aria-hidden="true"></i> Campaign </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
											 <a href="view-campaign.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty campaignCounts}">
														<c:out value="${campaignCounts}"></c:out> 
													</c:if> <c:if test="${empty campaignCounts}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
						 	
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading home-dash">
											<div class="panel-title">
												<label style="font-weight: normal; color: #000;"><i
													class="fa fa-pencil-square-o" aria-hidden="true"></i> Courses </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												<a href="view-course.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty courseCounts}">
														<c:out value="${courseCounts}"></c:out> 
													</c:if> <c:if test="${empty courseCounts}">0</c:if>
												</a>
											</div>
										</div>
									</div>
								</div>
						   
						
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading home-dash">
											<div class="panel-title">
												<label style="font-weight: normal; color: #000;"><i
													class="fa fa-user-circle-o" aria-hidden="true"></i> Events </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												<a href="view-event.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty eventsCounts}">
														<c:out value="${eventsCounts}"></c:out> 
													</c:if> <c:if test="${empty eventsCounts}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
								</div>
								<div class="col-lg-12">
					      	<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading home-dash">
											<div class="panel-title">
												<label style="font-weight: normal; color: #000;"><i
													class="fa fa-users" aria-hidden="true"></i> Events Registration</label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												<a href="view-events-registration.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty eventRegistrationCounts}">
														<c:out value="${eventRegistrationCounts}"></c:out> 
													</c:if> <c:if test="${empty eventRegistrationCounts}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading home-dash">
											<div class="panel-title">
												<label style="font-weight: normal; color: #000;"><i
													class="fa fa-users" aria-hidden="true"></i> Course Registrations</label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												<a href="view-course-registration.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty courseRegistrationCounts}">
														<c:out value="${courseRegistrationCounts}"></c:out> 
													</c:if> <c:if test="${empty courseRegistrationCounts}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
						</div>
					</ul>
					</c:if>
				</div>
			</div>
		</div>
	</aside>
<br>
<aside class="subscription-aside-block bg-theme text-white"></aside>
