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
			
		<c:if test="${userType eq 'admin' }">
		<aside class="bg-gray aback cover">
					<!-- course search form -->
					<form:form name="myForm" method="post"
						action="search" commandName="searchCourse"
						modelAttribute="searchCourse" class="course-search-form">
						<div class="col-lg-11 col-md-11 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12"></div>
								<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
									<div class="form-row">
										<div class="form-group">
											<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="companyName" path="CourseBO.courseName"
													placeholder="courseName" class="form-control element-block" />
											</label>
											
											<%-- <label class="element-block fw-normal font-lato"> <form:input
													type="text" id="companyName" path="studentRegisterBO.firstName"
													placeholder="StudentName" class="form-control element-block"   autofocus="true"/>
											</label> --%>
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
							  	
			<c:if test="${!empty EnrollmentList}">
	 
	
				<div class="col-sm-12" style="margin-top: -27px">
					<div class="pi-responsive-table-sm">
						<div class="pi-section-w pi-section-white piTooltips">
							<display:table id="data" name="${EnrollmentList}"
								requestURI="/vieww" pagesize="10" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">

								<display:column property="s_No" title="S No" />
								<td style="color: #0000FF;"><display:column
										property="courseBO.courseName" title="Course Name" /></td>
								<display:column property="studentRegisterBO.firstName"
									title="Student Name" />
								</td>
								<display:column property="courseBO.fees" title="Fees" />

								
								 
							</display:table>
						</div>
					</div>
				</div>
			 </c:if> 
			 </c:if>
			</div>
	</div>
</section>
</main>