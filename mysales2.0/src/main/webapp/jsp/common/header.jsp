<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	
</script>

<div class="wrapper">
	<header class="main-header">
		<!-- main navbar -->
		<nav
			class="navbar navbar-default navbar navbar-fixed-top main-navbar hidden-sm hidden-xs">
			<div class="container" style="height: 80px;">
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav  nav-bar-width">
						<a href="admin-home"><li class="header-tab"
							style="width: 130px; height: 35px; color: #fff; font-size: 20px; font-weight: 600; margin-top: 22px;"><span>
									<i class="fa fa-bar-chart" aria-hidden="true"
									style="font-size: 15px;"></i> MySales
							</span></li></a>
					</ul>
					<ul class="nav navbar-nav" style="float: right;">
						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN','ROLE_CAMPAIGN','ROLE_MARKETING','ROLE_SALES')">
							<li class="dropdown" id="fdJobId"><a href="admin-home"
								class="pd-tp-27"
								style="font-size: 13px; font-weight: 700; color: #FDA30E;"><span
									class="dropbtn">${userType.firstName}</span>(${userType.userType})</a></li>
						</sec:authorize>
						
						
						<!-- Roles -->
						
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Roles<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									
									<a href="create-role"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Role</a> 
										
									<a href="view-role"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Role</a>
								</div></li>
								
								
								<!-- Privileges -->
								
								<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Privileges<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-privilege"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Privilege</a> <a href="view-privilege"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Privilege</a>
								</div></li>
								
								
								<!-- Access -->
								
								<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Access<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-access"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Access</a> <a href="view-access"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Access</a>
										
								</div></li>
								
								<!-- role-privilege -->
								
								<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Roles Privilege<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									
									<a href="create-role-privilege"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create</a> 
										
									<a href="view-role-privilege"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View</a>
								</div></li>
								</sec:authorize>
								
								
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">GST<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-gst" style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create GST</a> <a href="view-gst"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View GST</a>
								</div></li>

							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Employees<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-employees"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Employees</a> <a href="view-employees"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Employees</a>
								</div></li>
						</sec:authorize>
						<!-- product menu -->
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Products<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-productservice"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Product</a> <a href="view-productservice"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Product</a>
								</div></li>
						</sec:authorize>

						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN','ROLE_CAMPAIGN','ROLE_MARKETING','ROLE_SALES')">
							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Campaigns <i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-campaign"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Campaign</a> <a href="view-campaign"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Campaign</a>
								</div></li>

							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Leads <i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-leads" style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Leads</a> <a href="view-leads"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Leads</a>
								</div></li>
						</sec:authorize>

						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN','ROLE_MARKETING','ROLE_SALES')">
							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Customers <i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-customers"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Customers</a> <a href="view-customers"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Customers</a>
										
										<!-- <a href="view-contact"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Contact</a> -->
								</div></li>

						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROLE_ADMIN')">

							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Sales Order <i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="create-sales-order"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Create Sales </a> <a href="view-sales-order"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;View Sales </a>
								</div></li>
						</sec:authorize>
						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN','ROLE_MARKETING','ROLE_SALES')">

							<li class="dropdown" id="fdJobId"><a href="#"
								class="pd-tp-27" style="font-size: 13px; font-weight: 700;"><span
									class="dropbtn">Reports<i class="fa fa-caret-down"
										aria-hidden="true" style="font-size: 15px; margin-left: 5px;"></i></span></a>

								<div class="dropdown-content dropdown-menu" role="menu"
									style="list-style-type: none;">
									<a href="view-leads-reports"
										style="height: 15px; margin-top: -17px;"><i
										class="fa fa-play" aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Leads Report </a> <a href="view-report?report=report"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Customers Report </a>
										<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
										<a href="#"
										style="height: 15px;"><i class="fa fa-play"
										aria-hidden="true" style="font-size: 8px;"></i>
										&nbsp;&nbsp;Sales  Report </a></sec:authorize>
								</div></li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<li class="link-btn hdr-icon-pd"><a href="logout"
								class="pd-tp-20"><span
									class="btn btn-theme  btn-pill btn-xs btn-line">Logout</span></a></li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<!--start mobile header -->
	<div class="container mobi" style="background-color: #2a3f54;">
		<nav class="mobile-nav hidden-md hidden-lg navbar navbar-fixed-top">
			<ul class="nav navbar-nav nav-block-left">
				<li class="header-tab"
					style="width: 130px; height: 30px; color: #fff; font-size: 20px; font-weight: 600; margin-top: 10px;"><span>
						<i class="fa fa-bar-chart" aria-hidden="true"
						style="font-size: 15px; margin-left: 5px;"></i>MySales
				</span></li>
			</ul>
			<a href="#" class="btn-nav-toogle first"> <span class="bars"></span>
				Menu
			</a>

			<div class="mobile-nav-block">

				<a href="#" class="btn-nav-toogle"> <span class="barsclose"></span>
					Close
				</a>
				<ul class="nav navbar-nav" style="margin-top: 10px;">
					<sec:authorize
						access="hasAnyRole('ROLE_ADMIN','ROLE_CAMPAIGN','ROLE_MARKETING','ROLE_SALES')">
						<li><a href="admin-home"><strong>Home</strong></a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="dropdown pd-lft"><a href="#"
							data-toggle="dropdown" role="button"><strong>
									Employees</strong><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="create-employees" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>Create
											Employees </strong></a></li>
								<li><a href="view-employees" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>View
											Employees</strong></a></li>
							</ul></li>
						<li class="dropdown pd-lft"><a href="#"
							data-toggle="dropdown" role="button"><strong> GST</strong><span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="create-gst" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>Create
											GST </strong></a></li>
								<li><a href="view-gst" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>View
											GST</strong></a></li>
							</ul></li>
						<li class="dropdown pd-lft"><a href="#"
							data-toggle="dropdown" role="button"><strong>
									Products</strong><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="create-productservice" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>Create
											Product </strong></a></li>
								<li><a href="view-productservice" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>View
											Products</strong></a></li>
							</ul></li>
					</sec:authorize>
					<sec:authorize
						access="hasAnyRole('ROLE_ADMIN','ROLE_CAMPAIGN','ROLE_MARKETING','ROLE_SALES')">
						<li class="dropdown pd-lft"><a href="#"
							data-toggle="dropdown" role="button"><strong><i
									aria-hidden="true"></i>Campaigns</strong><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="create-campaign" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>Create
											Campaign </strong></a></li>
								<li><a href="view-campaign" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>View
											Campaign</strong></a></li>

							</ul></li>
					</sec:authorize>
					<sec:authorize
						access="hasAnyRole('ROLE_ADMIN','ROLE_CAMPAIGN','ROLE_MARKETING','ROLE_SALES')">
						<li class="dropdown pd-lft"><a href="#"
							data-toggle="dropdown" role="button"><strong><i
									aria-hidden="true"></i>Leads</strong><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="create-leads" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>Create
											Leads </strong></a></li>
								<li><a href="view-leads" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>
											View Leads </strong></a></li>
							</ul></li>
					</sec:authorize>
					<sec:authorize
						access="hasAnyRole('ROLE_ADMIN','ROLE_MARKETING','ROLE_SALES')">
						<li class="dropdown pd-lft"><a href="#"
							data-toggle="dropdown" role="button"><strong><i
									aria-hidden="true"></i> Customers </strong><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="create-customers" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>Create
											Customers </strong></a></li>
								<li><a href="view-customers" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>View
											Customers</strong></a></li>
							</ul></li>
						<li class="dropdown pd-lft"><a href="#"
							data-toggle="dropdown" role="button"><strong><i
									aria-hidden="true"></i>Reports</strong><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>
											Leads </strong></a></li>
								<li><a href="view-report?report=report" class="fa fa-play"
									style="font-size: 12px; font-weight: 600;"><strong>
											Customer Reports </strong></a></li>
							</ul></li>

					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<i class="fa fa-sign-out"><a href="logout"> <strong>Logout</strong></a></i>
					</sec:authorize>
				</ul>
			</div>
		</nav>
	</div>
</div>


