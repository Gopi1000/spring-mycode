
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
								<div class="panel-heading"
									style="background-color: #fafafa; text-align: center;">
									<div class="panel-title">
										<label style="font-weight: normal; color: #000;"><i
											class=" fa fa-qrcode" aria-hidden="true"></i> Enrollment </label>
									</div>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in">
									<div class="panel-body" style="font-weight: 600;">
										<a href="view-course-enrollment.html"
											style="text-decoration: underline; text-transform: capitalize; color: #333;">
											<c:if test="${!empty enrollmentCounts}">
												<c:out value="${enrollmentCounts}"></c:out>
											</c:if> <c:if test="${empty enrollmentCounts}">0</c:if>
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

