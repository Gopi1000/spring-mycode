<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="" style="padding-top: 7%;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				<a class="collapsed" data-toggle="expand" data-parent="#accordion"
					href="#collapseTwo" aria-controls="collapseTwo">
					<h4 class=" no-margin with-ic">
						<i class="fa fa-users menu-fa-icon"></i> Job Seeker
					</h4>
				</a>
			</div>
		</div>
		<div id="collapseTwo"
			class="panel-collapse collapse in no-mr-tp no-pd">
			<div class="panel-body  panel-body-lg">
				<ul class="list-unstyled" style="text-align: left">
					<li><a href="create-jobseeker"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Create JobSeeker</a></li>
					<li><a href="admin-jobseekers"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View JobSeekers</a></li>
							
					<li><a href="jobseekers-update-profile-alert"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;JobSeekers Update Profile Alert</a></li>		
				</ul>
				<div class="white-space-20"></div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				<a class="collapsed" data-toggle="expand" data-parent="#accordion"
					href="#collapseThree" aria-controls="collapseThree">
					<h4 class=" no-margin with-ic">
						<i class="fa fa-users menu-fa-icon"></i> Employer
					</h4>
				</a>
			</div>
		</div>
		<div id="collapseThree"
			class="panel-collapse collapse in no-mr-tp no-pd">
			<div class="panel-body  panel-body-lg">
				<ul class="list-unstyled" style="text-align: left">
					<li><a href="create-employer"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Create Employer</a></li>
					<li><a href="employer-details"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View Employers</a></li>


					<li><a href="admin-employer-history"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Employer History</a></li>
					<li><a href="employer_Invitation"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Employer Invitation</a></li>

					<li><a href="walkin-job-view"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Walkin Jobs</a></li>

					<li><a href="employer-report-company"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Company Report</a></li>



				</ul>
				<div class="white-space-20"></div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				<a class="collapsed" data-toggle="expand" data-parent="#accordion"
					href="#collapseFour" aria-controls="collapseFour">
					<h4 class=" no-margin with-ic">
						<i class="fa fa-qrcode menu-fa-icon"></i> Product Management
					</h4>
				</a>
			</div>
		</div>
		<div id="collapseFour"
			class="panel-collapse collapse in no-mr-tp no-pd">
			<div class="panel-body  panel-body-lg">
				<ul class="list-unstyled" style="text-align: left">

					<li><a href="create-product"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Create Product</a></li>
					<li><a href="view-product"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View Product</a></li>

					<li><a href="view-contact"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View Contact</a></li>
					<li><a href="view-feedback"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View FeedBack</a></li>
				</ul>
				<div class="white-space-20"></div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				<a class="collapsed" data-toggle="expand" data-parent="#accordion"
					href="#collapseFour" aria-controls="collapseFour">
					<h4 class=" no-margin with-ic">
						<i class="fa fa-cog menu-fa-icon"></i> Settings
					</h4>
				</a>
			</div>
		</div>
		<div id="collapseFour"
			class="panel-collapse collapse in no-mr-tp no-pd">
			<div class="panel-body  panel-body-lg">
				<ul class="list-unstyled" style="text-align: left">
					<li><a href="user-access-log"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Access Log</a></li>

					<!-- <li><a href="meta-information"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Add MetaInformation</a></li>

					<li><a href="meta-information-view"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View MetaInformation</a></li> -->

					<li><a href="upload-files"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Upload Company</a></li>

					<li><a href="admin-employer-upload"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Upload Employer</a></li>


					<li><a href="admin-job-opening-upload"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Upload JobPost</a></li>

					<li><a href="admin-jobseeker-upload"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Upload Job seeker</a></li>
					<li><a href="admin-company-view"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View Company Entity</a></li>

					<li><a href="admin-industry-view"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View Industry Entity</a></li>

					<li><a href="view-login-status"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;View User Online</a></li>

					<li><a href="refresh-context-values"><img
							src="resources/theme/images/menu_arrow.png"
							class="side-menu-icon">&nbsp;Refresh Industry And Company</a></li>

				</ul>
			</div>
		</div>
	</div>
</div> --%>