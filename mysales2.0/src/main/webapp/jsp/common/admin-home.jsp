<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<html>
<div class="box-list">
	<div class="item" style="border: 0px solid #ffff;">
		<br />
		<div class="text-center underline">
			<h3>My Sales DashBoard</h3>
		</div>
		<br /> <br />
		<div class="row">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="col-sm-3">
					<div class="form-group">
						<div
							style="width: 258px; height: 156px; border: 10px solid #e6e6e6; box-sizing: border-box; border-radius: 20px">

							<h3 style="text-align: center;">Employees</h3>
							<a href="view-employees"
								style="text-decoration: underline; text-transform: capitalize; color: #333;">
								<h1 style="text-align: center;">${adminDashboardCount.employeeCount }</h1>
							</a>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<div
							style="width: 258px; height: 156px; border: 10px solid #e6e6e6; box-sizing: border-box; border-radius: 20px">

							<h3 style="text-align: center;">Products</h3>
							<a href="view-productservice"
								style="text-decoration: underline; text-transform: capitalize; color: #333;">
								<h1 style="text-align: center;">${adminDashboardCount.productCount }</h1>
							</a>
						</div>
					</div>
				</div>
			</sec:authorize>
			<sec:authorize
				access="hasAnyRole('ROLE_ADMIN','ROLE_CAMPAIGN','ROLE_MARKETING','ROLE_SALES')">
				<div class="col-sm-3">
					<div class="form-group">
						<div
							style="width: 258px; height: 156px; border: 10px solid #e6e6e6; box-sizing: border-box; border-radius: 20px"">
							<h3 style="text-align: center;">Campaigns</h3>
							<a href="view-campaign"
								style="text-decoration: underline; text-transform: capitalize; color: #333;">
								<h1 style="text-align: center;">${adminDashboardCount.campaignCount }</h1>
							</a>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">
						<div
							style="width: 258px; height: 156px; border: 10px solid #e6e6e6; box-sizing: border-box; border-radius: 20px"">
							<h3 style="text-align: center;">Leads</h3>
							<a href="view-leads"
								style="text-decoration: underline; text-transform: capitalize; color: #333;">
								<h1 style="text-align: center;">${adminDashboardCount.leadsCount }</h1>
							</a>
						</div>
					</div>
				</div>
			</sec:authorize>
			<sec:authorize
				access="hasAnyRole('ROLE_ADMIN','ROLE_MARKETING','ROLE_SALES')">
				<div class="col-sm-3">
					<div class="form-group">
						<div
							style="width: 258px; height: 156px; border: 10px solid #e6e6e6; box-sizing: border-box; border-radius: 20px"">
							<h3 style="text-align: center;">Customers</h3>
							<a href="view-customers"
								style="text-decoration: underline; text-transform: capitalize; color: #333;">
								<h1 style="text-align: center;">${adminDashboardCount.customerCount }</h1>
							</a>
						</div>
					</div>
				</div>
			</sec:authorize>
		</div>
	</div>
</div>
</html>


