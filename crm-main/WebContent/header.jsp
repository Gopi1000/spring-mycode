<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$('#fnCmpId').mouseover(function() {
			$('#fdJobId').removeClass("open");
		});
		$('#fdJobId').mouseover(function() {
			$('#fnCmpId').removeClass("open");
		});
	});
//-->
</script>

<div class="wrapper">
	<header class="main-header">
		<!-- main navbar -->
		<nav
			class="navbar navbar-default navbar navbar-fixed-top main-navbar hidden-sm hidden-xs">
			<div class="container" style="height: 80px;">
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav  nav-bar-width">
						<li class="header-tab"><a href="profile-view.html"><img
								src="resources/theme/images/logo MYCLIENT.png"
								alt="www.myjobkart.com"  style="width: 130px; height: 35px;" /></a></li>
					</ul>
					<ul class="nav navbar-nav" style="float: right;">

						<c:if test="${(not empty userType)}">
							<li class="dropdown pd-lft"><a class="pd-tp-27"
								href="profile-view.html"
								style="font-size: 14px; font-weight: 700;"><strong>My
										Home</strong> </a></li>
						</c:if>
						<c:if test="${(userType eq 'admin')}">

							<li class="pd-lft"><a class="pd-tp-27"
								href="create-user.html"
								style="font-size: 14px; font-weight: 700;"><strong>
										Create User</strong></a></li>
										
										<li class="pd-lft"><a class="pd-tp-27"
								href="create-customer.html"
								style="font-size: 14px; font-weight: 700;"><strong>
										Create Customer</strong></a></li>

							<li class="pd-lft"><a class="pd-tp-27"
								href="create-employer.html"
								style="font-size: 14px; font-weight: 700;"><strong>
										Create Client </strong></a></li>

							<li class="pd-lft"><a class="pd-tp-27"
								href="profile-view.html?report=report"
								style="font-size: 14px; font-weight: 700;"><strong>
										Reports</strong></a></li>
							<li class="link-btn hdr-icon-pd"><a href="logout.html"
								class="pd-tp-27"><span
									class="btn btn-theme  btn-pill btn-xs btn-line">Logout</span></a></li>


						</c:if>

						<c:if test="${(userType eq 'user')}">
							<li class="pd-lft"><a class="pd-tp-27"
								href="create-employer.html"
								style="font-size: 14px; font-weight: 700;"><strong>
										Create Client</strong></a></li>
							<li class="pd-lft"><a class="pd-tp-27"
								href="profile-view.html?report=report"
								style="font-size: 14px; font-weight: 700;"><strong>
										Reports</strong></a></li>


							<li class="link-btn hdr-icon-pd"><a href="logout.html"
								class="pd-tp-27"><span
									class="btn btn-theme  btn-pill btn-xs btn-line">Logout</span></a></li>
						</c:if>

					</ul>
				</div>
			</div>
		</nav>
	</header>
</div>


