
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<main id="main">
 <h4 align="center"><span style="color: orange" >${ adminProfile.loginId }</span>
<div><span style="color: orange" >${ adminProfile.emailAddress }</span></div>
</h4>

			<!-- categories aside -->
			<aside class="container instructor-blkprofile checkout-form">
				<div class="add-job_container">
					<div class="job-box">
		<div id="wrapper">
		
 	<!-- categories list -->
			<ul class="list-unstyled categories-list">
				<div class="col-lg-12">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="panel panel-default"
								style="text-align: center; height: 130px;">
							<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
									<div class="panel-title">
									<label style="font-weight:600; color: #000;"><i
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
										<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
											<div class="panel-title">
												<label style="font-weight:600; color: #000;"><i
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
										<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
											<div class="panel-title">
												<label style="font-weight:600; color: #000;"><i
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
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
											<div class="panel-title">
												<label style="font-weight:600; color: #000;"><i
													class="fa fa-pencil-square-o" aria-hidden="true"></i> Courses </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												<a href="view-course.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty coursesCounts}">
														<c:out value="${coursesCounts}"></c:out> 
													</c:if> <c:if test="${empty coursesCounts}">0</c:if>
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
										<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
											<div class="panel-title">
												<label style="font-weight:600; color: #000;"><i
													class="fa fa-users" aria-hidden="true"></i> Course Registration </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												<a href="view-course-registration.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty courseRegistrationCount}">
														<c:out value="${courseRegistrationCount}"></c:out> 
													</c:if> <c:if test="${empty courseRegistrationCount}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
											<div class="panel-title">
												<label style="font-weight:600; color: #000;"><i
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
					      	<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="panel panel-default"
										style="text-align: center; height: 130px;">
										<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
											<div class="panel-title">
												<label style="font-weight:600; color: #000;"><i
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
										<div class="panel-heading" style="background-color:#fafafa;text-align: center;">
											<div class="panel-title">
												<label style="font-weight:600; color: #000;"><i
													class="fa fa-mobile-phone" aria-hidden="true"></i> Contact </label>
											</div>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style=" font-weight: 600;">
												 <a href="view-contact.html"
													style="text-decoration: underline; text-transform: capitalize; color:#333;">
													<c:if test="${!empty contactCounts}">
														<c:out value="${contactCounts}"></c:out> 
													</c:if> <c:if test="${empty contactCounts}">0</c:if>
												</a> 
											</div>
										</div>
									</div>
								</div>
						</div>
					</ul>
				</div>
			</div>
		</div>
	</aside>
<br>
<aside class="subscription-aside-block bg-theme text-white"></aside>
 
</main>