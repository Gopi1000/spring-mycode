<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
.hovereffect {
	width: 100%;
	height: 100%;
	float: left;
	overflow: hidden;
	position: relative;
	text-align: center;
	cursor: default;
	position: relative;
}

.titlehover:hover {
	color: red;
}

.hovereffect .overlay {
	position: absolute;
	overflow: hidden;
	width: 80%;
	height: 80%;
	left: 10%;
	top: 10%;
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.35s, transform 0.35s;
	-webkit-transform: scale(0, 1);
	-ms-transform: scale(0, 1);
	transform: scale(0, 1);
}

.hovereffect:hover .overlay {
	opacity: 4;
	filter: alpha(opacity =       
		                                                         
		           100);
	-webkit-transform: scale(1);
	-ms-transform: scale(1);
	transform: scale(1);
}

.hovereffect img {
	display: block;
	position: relative;
	-webkit-transition: all 0.55s;
	transition: all 0.35s;
}

.hovereffect:hover img {
	filter:
		url('data:image/svg+xml;charset=utf-8,<svg xmlns="http://www.w3.org/2000/svg"><filter id="filter"><feComponentTransfer color-interpolation-filters="sRGB"><feFuncR type="linear" slope="0.6" /><feFuncG type="linear" slope="0.6" /><feFuncB type="linear" slope="0.6" /></feComponentTransfer></filter></svg>#filter');
	filter: brightness(0.3);
	-webkit-filter: brightness(0.3);
}

.hovereffect h2 {
	text-transform: uppercase;
	text-align: center;
	position: relative;
	font-size: 7px;
	background-color: transparent;
	color: #FFF;
	padding: 1em 0;
	/* opacity: 0; */
	filter: alpha(opacity =        
		                                                         
		          0);
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.45s, transform 0.45s;
	-webkit-transform: translate3d(0, -100%, 0);
	transform: translate3d(0, -100%, 0);
}

.hovereffect a,hovereffect p {
	color: #FFF;
	padding: 1em 0;
	/* 	opacity: 0; */
	filter: alpha(opacity =        
		                                                         
		          0);
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.45s, transform 0.45s;
	-webkit-transform: translate3d(0, 100%, 0);
	transform: translate3d(0, 100%, 0);
}

.hovereffect:hover a,.hovereffect:hover p,.hovereffect:hover h2 {
	opacity: 1;
	filter: alpha(opacity =       
		                                                         
		           100);
	-webkit-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0);
}
</style>
<script type="text/javascript">
<!--
	function hideDiv() {
		$('#colseBTId').hide();
		$('#errorRecId').slideUp(500);
	}
	function hideIndDiv() {
		$('#colseBTIndId').hide();
		$('#errorIndId').slideUp(500);
	}
	function retrieveMethod(criteriaType, criteriaValue) {
		if (criteriaType == 'REC') {
			$('#errorRecId').slideUp(500);
			$
					.ajax({
						type : "POST",
						url : 'retrieve-top-recruiters',
						data : 'criteriaValue=' + criteriaValue,
						success : function(criteriaValueList) {
							var html = '';
							$
									.each(
											criteriaValueList,
											function(key, value) {
												html = html
														+ ''
														+ '<div class="col-sm-3 no-mr-tp"'
						+'style="padding-bottom: 0px; padding-top: 0px;">'
														+ '<i class="fa fa-square" aria-hidden="true"'
						+'style="color: #3d454c; margin-right: 5px; font-size: 8px;"></i>'
														+ '<a	href="find-jobs.html?companyId='
														+ key
														+ '&&companyjobs=true">'
														+ value + '</a></div>';

											});
							if (html != '') {
								$('#errorRecId').slideUp(500);
								$('#pageLoadRecId').hide();
								$('#scrRecId').html(html);
							} else {
								$('#pageLoadRecId').hide();
								$('#errorRecId').slideDown(500);
								$('#colseBTId').show();
								$('#errorRecIdVal').html(
										"There is no recruiters found for your criteris '"
												+ criteriaValue + "'");
							}

						},
					});
		} else {
			$('#errorIndId').slideUp(500);
			$
					.ajax({
						type : "POST",
						url : 'retrieve-top-industry',
						data : 'criteriaValue=' + criteriaValue,
						success : function(criteriaValueList) {
							var len = criteriaValueList.length;
							if (len > 0) {
								var html = '';
								for ( var i = 0; i < len; i++) {
									html = html
											+ '<div class="col-sm-3 no-mr-tp" style="padding-bottom: 0px; padding-top: 0px;">'
											+ '<i class="fa fa-square" aria-hidden="true"'
							+'style="color: #3d454c; margin-right: 5px; font-size: 8px;"></i>'
											+ '<a href="find-jobs.html?industryType='
											+ criteriaValueList[i]
											+ '&&companyjobs=true">'
											+ '<strong style="color: #34495e; font-weight: normal;">'
											+ criteriaValueList[i]
											+ '</strong></a></div>';
								}
								$('#errorIndId').slideUp(500);
								$('#pageLoadIndId').hide();
								$('#scriptIndId').html(html);
							} else {
								$('#pageLoadIndId').hide();
								$('#errorIndId').slideDown(500);
								$('#colseBTIndId').show();
								$('#errorIndIdVal').html(
										"There is no Industry found for your criteris '"
												+ criteriaValue + "'");
							}
						},
					});
		}
	}
//-->
</script>
<div id="loader">
	<i class="fa fa-cog fa-4x fa-spin"></i>
</div>
<div class="wrapper">
	<header class="main-header">
		<!-- ============ SLIDES START ============ -->
		<div id="slider" class="sl-slider-wrapper">

			<div class="sl-slider">
				<div class="sl-slide" data-orientation="horizontal"
					data-slice1-rotation="-25" data-slice2-rotation="-25"
					data-slice1-scale="2" data-slice2-scale="2">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-1"></div>
						<div class="tint"></div>
						<div class="slide-content">
							<h2>Looking for a job?</h2>
							<p>
								<a href="find-jobs.html" class="btn btn-lg btn-success">Find
									Jobs</a>
							</p>

						</div>
					</div>
				</div>
				<div class="sl-slide" data-orientation="horizontal"
					data-slice1-rotation="-25" data-slice2-rotation="-25"
					data-slice1-scale="2" data-slice2-scale="2">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-2"></div>
						<div class="tint"></div>
						<div class="slide-content">
							<h2>Looking for candidates?</h2>
							<p>
								<a href="find-resumes.html" class="btn btn-lg btn-danger">Find
									Candidates</a>
							</p>
						</div>
					</div>
				</div>
				<div class="sl-slide" data-orientation="horizontal"
					data-slice1-rotation="-25" data-slice2-rotation="-25"
					data-slice1-scale="2" data-slice2-scale="2">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-3"></div>
						<div class="tint"></div>
						<div class="slide-content">
							<h2>Do you want to advertise your job post?</h2>
							<p>
								<a href="job-openings.html" class="btn btn-lg btn-warning">Post
									a job</a>
							</p>
						</div>
					</div>
				</div>
				<div class="sl-slide" data-orientation="horizontal"
					data-slice1-rotation="-25" data-slice2-rotation="-25"
					data-slice1-scale="2" data-slice2-scale="2">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-4"></div>
						<div class="tint"></div>
						<div class="slide-content">
							<h2>Are you consulting company?</h2>
							<p>
								<a href="contactus.html" class="btn btn-lg btn-warning"
									style="color: #fff; background-color: #12A5F4; border-color: #12A5F4;">Contact
									Us</a>
							</p>
						</div>

					</div>
				</div>

				<nav id="nav-dots" class="nav-dots">
					<!-- <span class="nav-dot-current"></span> <span></span> <span></span> <span></span> -->
					<div class="hm-search-padding">
						<form:form id="myForm" method="post" action="find-jobs.html"
							commandName="searchjob">
							<c:if test="${not empty message}">
								<div class="message red">${message}</div>
							</c:if>
							<div class="row home-search" style="border: 1px solid #fff;">
								<div class=" col-sm-12 hm-search-tp-padding">

									<div
										class=" col-md-4 fs-search fsl-mobile-search fs-mobile-skills">
										<div class="form-group" style="margin-bottom: 7px;">
											<label class="hidden-xs">&nbsp;</label>
											<form:input type="text" class="form-control" path="keywords"
												placeholder=" Skills/Job Title  " escapeXml="false"
												style="height: 45px;font-weight: 700;"></form:input>
										</div>

									</div>

									<div class=" col-md-4 fs-mobile-search" style="padding: 0px">
										<div class="form-group" style="margin-bottom: 7px;">
											<label class="hidden-xs">&nbsp;</label>
											<form:input type="text" class="form-control"
												path="searchElement" placeholder=" Job Location"
												style="height: 45px;font-weight: 700;"></form:input>
										</div>

									</div>
									<div class=" col-md-3 fs-mobile-search" style="padding: 0px">
										<div class="form-group">
											<label class="hidden-xs">&nbsp;</label>
											<form:input type="text" class="form-control" path="minExp"
												placeholder=" Experience"
												style="height: 45px;font-weight: 700;"></form:input>
										</div>
									</div>
									<input type="hidden" value="true" name="search" />
									<div
										class=" col-md-1 fsl-search fsl-mobile-search fs-mobile-btnsearch"
										style="padding-bottom: 0px;">
										<div class="form-group">
											<label class="hidden-xs">&nbsp;</label>
											<button class="btn btn-theme btn-success btn-block"
												style="padding: 11px 10px; background-color: #7cb228; border-color: #7cb228;">
												<span
													style="font-size: 100%; font-family: 'Segoe UI', 'wf_segoe-ui_normal', 'Arial', sans-serif;"><i
													class="fa fa-search" aria-hidden="true"
													style="font-size: 20px;"></i></span>
											</button>
										</div>
									</div>
									<div class="col-sm-12">
										<span class="hm-adv-seach"><a href="#modal-advanced"
											data-toggle="modal"
											style="color: white; font-family: Montserrat, Helvetica, Arial, sans-serif; font-size: 12px;"
											id="adsea"><i class="fa fa-search-plus"></i> Advanced
												Search</a></span>

									</div>
								</div>
							</div>
							<!-- form search -->
						</form:form>
					</div>
				</nav>
			</div>
		</div>
	</header>
</div>

<div class="modal fade" id="modal-advanced">
	<div class="modal-dialog ">
		<div class="modal-content">
			<form:form id="myForm" method="post" class="job_filters"
				action="find-jobs.html" commandName="searchjob">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">Advanced Job Search</h3>
				</div>
				<div class="modal-body">
					<h5>Find Jobs</h5>
					<div class="row">
						<div class="col-md-6" style="padding-bottom: 0px;">
							<div class="form-group">
								<label>Company Name</label> <input type="text"
									class="form-control " name="companyName"
									placeholder="Company Name" autofocus>

							</div>
						</div>
						<div class="col-md-6" style="padding-bottom: 0px;">
							<div class="form-group">
								<label>Location</label>
								<form:input type="text" class="form-control  "
									path="searchElement" placeholder=" Job Location"></form:input>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12" style="padding-bottom: 0px;">
							<div class="form-group">
								<label>Experience</label> <label class="color-white">Experience</label>
								<form:input type="text" class="form-control  " path="minExp"
									placeholder=" Experience"></form:input>
							</div>
						</div>
					</div>


					<div class="white-space-10"></div>
					<div class="row">
						<div class="col-md-6" style="padding-bottom: 0px;">
							<div class="form-group">
								<label>Job Title </label>
								<form:input type="text" class="form-control " path="jobTitle"
									placeholder=" Job Title"></form:input>
							</div>
						</div>
						<div class="col-md-6" style="padding-bottom: 0px;">
							<div class="form-group">
								<label>Key Skills </label>
								<form:input type="text" class="form-control  " path="keywords"
									placeholder=" Key Skills"></form:input>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6" style="padding-bottom: 0px;">
							<div class="form-group">
								<label> Salary </label>
								<form:input type="text" class="form-control  " path="maxSalary"
									placeholder=" Salary"></form:input>
							</div>
						</div>
						<div class="col-md-6" style="padding-bottom: 0px;">
							<div class="form-group">
								<label>Job Type</label>
								<form:select type="text" id="jobTypeInput" name="jobType"
									path="jobType" class="form-control">
									<form:option value="">Select Job Type </form:option>
									<form:option value="Permanent">Permanent</form:option>
									<form:option value="Contract">Contract</form:option>
									<form:option value="PartTime">PartTime</form:option>
								</form:select>
							</div>
						</div>
						<input type="hidden" value="advancedsearch" name="aid" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-theme"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-success btn-theme">Find
						Jobs</button>
				</div>
			</form:form>
		</div>
	</div>
</div>


<!-- ============ COMPANIES START ============ -->

<section class="section_align">
	<div class="container bg-clr2">
		<div class="col-md-12">
			<div style="text-align: center;">
				<h3 class="font-bold mobile-topOpenings">Top Openings</h3>
			</div>
		</div>
		<div class="row">
			<div class="row">
				<div class="col-sm-12">
					<div class="box-lists" style="border: 1px solid #e1e1e1;">
						<div class="row hm-top-emp-align">
							<c:forEach items="${topOpeningsList}" var="companyList">
								<div class="wid ">
									<div class=" hovereffect">
										<a
											href="find-jobs.html?companyId=${companyList.key}&&search=true"><img
											src="show-company-image.html?cmpyId=${companyList.key}"
											alt="" class="img-responsive">
										<!-- </a> -->
											<div class="overlay">
												<Span
													style="font-weight: bold; font-size: 10px; text-transform: capitalize;">${companyList.value}</Span>
											</div> </a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div style="text-align: center;">
					<h3 class="font-bold" style="margin-top: 8px;">Top Recruiters</h3>
				</div>
				<div id="errorRecId" style="display: none;">
					<div style="text-align: center;">
						<div class="alert alert-success" role="alert">
							<button type="button" class="close" aria-label="Close"
								id="colseBTId" onclick="hideDiv();">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>OOPS</strong> <span id="errorRecIdVal"></span>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-12" style="padding-right: 0px; padding-left: 0px;">
				<div class="block-section">
					<div class="container">
						<div
							style="background-color: #efefef; overflow: hidden; cursor: pointer;">
							<a class="allSector-padding activet hm-alpha-ltrs"
								style="color: #333;" onclick="retrieveMethod('REC','ALL');">All
								Sector</a> <a class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','A');">A</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','B');">B</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','C');">C</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','D');">D</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','E');">E</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','F');">F</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','G');">G</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','H');">H</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','I');">I</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','J');">J</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','K');">K</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','L');">L</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','M');">M</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','N');">N</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','O');">O</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','P');">P</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','Q');">Q</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','R');">R</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','S');">S</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','T');">T</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','U');">U</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','V');">V</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','W');">W</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','X');">X</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','Y');">Y</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('REC','Z');">Z</a>
						</div>
						<div id="myTabContent"
							class="tab-content flat-tab-content bg-clr1">
							<div class="tab-pane fade in active">
								<div class="row">

									<c:if test="${not empty errorMessage}">
										<div style="text-align: center;">
											<div class="col-sm-6 no-mr-tp">
												<div class="alert alert-success" role="alert">
													<button type="button" class="close" data-dismiss="alert"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<strong>OOPS</strong>
													<c:out value="${errorMessage}"></c:out>
												</div>
											</div>
										</div>
									</c:if>
									<div id="pageLoadRecId">
										<c:if test="${not empty topOpeningsList }">
											<c:forEach items="${topOpeningsList}" var="company">
												<div class="col-sm-3 no-mr-tp"
													style="padding-bottom: 0px; padding-top: 0px;">
													<i class="fa fa-square" aria-hidden="true"
														style="color: #3d454c; margin-right: 5px; font-size: 8px;"></i>
													<a
														href="find-jobs.html?companyId=${company.key}&&companyjobs=true">
														<span style="text-transform: capitalize"><c:out
																value="${company.value}" /></span>
													</a>
												</div>
											</c:forEach>
										</c:if>
									</div>
									<div id="scrRecId"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div style="text-align: center;">
					<h3 class="font-bold" style="margin-top: 8px;">Top Industry</h3>
				</div>
				<div id="errorIndId" style="display: none;">
					<div style="text-align: center;">
						<div class="alert alert-success" role="alert">
							<button type="button" class="close" aria-label="Close"
								id="colseBTIndId" onclick="hideIndDiv();">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>OOPS</strong> <span id="errorIndIdVal"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12" style="padding-right: 0px; padding-left: 0px;">
				<div class="block-section">
					<div class="container">
						<div
							style="background-color: #efefef; overflow: hidden; cursor: pointer;">
							<a class="allSector-padding activet hm-alpha-ltrs"
								style="color: #333;" onclick="retrieveMethod('IND','ALL');">All
								Sector</a> <a class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','A');">A</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','B');">B</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','C');">C</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','D');">D</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','E');">E</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','F');">F</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','G');">G</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','H');">H</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','I');">I</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','J');">J</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','K');">K</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','L');">L</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','M');">M</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','N');">N</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','O');">O</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','P');">P</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','Q');">Q</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','R');">R</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','S');">S</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','T');">T</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','U');">U</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','V');">V</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','W');">W</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','X');">X</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','Y');">Y</a> <a
								class="allSector-padding activet hm-alpha-ltrs"
								onclick="retrieveMethod('IND','Z');">Z</a>
						</div>
						<div id="myTabContent"
							class="tab-content flat-tab-content bg-clr1">
							<div class="tab-pane fade in active" id="tab0">
								<div class="row" id="modCompany">
									<div id="pageLoadIndId">
										<c:forEach items="${topIndustryList}" var="companyList">
											<div class="col-sm-3 no-mr-tp"
												style="padding-bottom: 0px; padding-top: 0px;">
												<i class="fa fa-square" aria-hidden="true"
													style="color: #3d454c; margin-right: 5px; font-size: 8px;"></i>
												<a
													href="find-jobs.html?industryType=${companyList}&&companyjobs=true"><strong
													style="color: #34495e; font-weight: normal;"><c:out
															value="${companyList}" /></strong></a>
											</div>
										</c:forEach>
									</div>
									<div id="scriptIndId"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row" style="margin-bottom: 25px;">
				<div class="col-md-12">
					<div style="text-align: center;">
						<h3 class="font-bold" style="margin-top: 8px;">Connect us</h3>
					</div>
				</div>
			</div>
			<div class="row" style="margin-bottom: 25px;">
				<div class="col-sm-12">
					<div class="col-sm-3 hm-connent-us">
						<div>
							<a href="walkin-job-view.html?walking=search"
								class="btn btn-theme btn-success btn-block"
								style="color: #fff; background-color: #34495e; border-color: #34495e;"><strong>Current
									Walk-in's</strong></a>
							<div class="hm-cm-last">
								<p>
									<i class="fa fa-check-square" aria-hidden="true"></i> Find the
									job walk-in from our clients and get your career.
								</p>
								<a href="walkin-job-view.html?walking=search"
									class="btn btn-sm btn-pill btn-theme btn-line dark">Go</a>
							</div>
						</div>
					</div>

					<div class="col-sm-3 div-pad hm-connent-us">
						<div>
							<a href="contactus.html"
								class="btn btn-theme btn-success btn-block"
								style="color: #fff; background-color: #d58512; border-color: #d58512;"><strong>Join
									as a recruitment Partner</strong></a>
							<div class="hm-cm-last">
								<p>
									<i class="fa fa-check-square" aria-hidden="true"></i> We
									welcome recruiters to myjobkart, our recuritment platform shall
									help you to find the right candidates and fulfill your customer
									needs.</br> <i class="fa fa-mobile" aria-hidden="true"></i>
									044-42824663
								</p>
								<a href="contactus.html"
									class="btn btn-sm btn-pill btn-theme btn-line dark">Go</a>
							</div>
						</div>
					</div>

					<div class="col-sm-3 div-pad hm-connent-us">
						<div>
							<a href="follower-details"
								class="btn btn-theme btn-success btn-block"
								style="color: #fff; background-color: #449d44; border-color: #449d44;"><strong>Follow
									an Employer</strong></a>
							<div class="hm-cm-last">
								<p>
									<i class="fa fa-check-square" aria-hidden="true"></i> Our
									platform is not just marketing the jobpost and jobseekers. We
									offers "One place recruitment program" which enable the
									recuriters to have interactive recruitment process with
									candidates.
								</p>
								<a href="follower-details"
									class="btn btn-sm btn-pill btn-theme btn-line dark"
									target="_blank">Go</a>
							</div>
						</div>
					</div>
					<div class="col-sm-3 div-pad hm-connent-us">
						<div>
							<a href="create-job-alert.html"
								class="btn btn-theme btn-success btn-block"
								style="color: #fff; background-color: #12A5F4; border-color: #12A5F4;"><strong>Myjobkart
									Referal Program</strong></a>
							<div class="hm-cm-last">
								<p>
									<i class="fa fa-check-square" aria-hidden="true"></i> Get best
									matched jobs on your email. No registration needed
								</p>
								<a href="create-job-alert.html"
									class="btn btn-sm btn-pill btn-theme btn-line dark">Go</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
