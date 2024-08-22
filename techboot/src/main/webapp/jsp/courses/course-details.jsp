<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="req" value="${pageContext.request}"></c:set>
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" ></c:set>
<style>
.urlhref:hover {
  color: #34c7dc !important;
}

.urlhref {
 color:#000 !important;
 text-transform: uppercase;
}
</style>
<main id="main"> <!-- heading banner --> <header
	class="heading-banner text-white bgCover"
	style="background-image: url(${baseURL}/resource/images/img23.jpg);">
<div class="container holder">
	<div class="align">
		<h1>Course Details</h1>
	</div>
</div>
</header> <!-- breadcrumb nav --> <nav class="breadcrumb-nav">
<div class="container">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li><a href="${baseURL}/home.html">Home</a></li>
		<li><a href="${baseURL}/courses-list.html">Course</a></li>
		<li class="active"><c:out
				value="${courseDetailsBO.curseBO.courseName}"></c:out></li>
	</ol>
</div>
</nav> <!-- two columns -->
<div id="two-columns" class="container">
	<div class="row">
		<!-- content -->
		<br>
		
		<article id="content" class="col-xs-12 col-md-9"> <!-- content h1 -->
		<h1 class="content-h1 fw-semi">
			<c:out value="${courseDetailsBO.curseBO.courseName}"></c:out>
			</a>
		</h1>
		
		<header class="view-header row">
		<div class="col-xs-12 col-sm-9 d-flex">
			<div class="d-col">
				<!-- post author -->
				<div class="post-author">
					<div class="alignleft no-shrink rounded-circle">
						<a href="#"><img src="${baseURL}/resource/images/img95.jpg"
							class="rounded-circle" alt="image description"></a>
					</div>
					<div class="description-wrap">
						<h2 class="author-heading">
							<a href="#">Instructor</a>
						</h2>
						<h3 class="author-heading-subtitle text-uppercase">lospher
							cooke</h3>
					</div>
				</div>
			</div>
			<div class="d-col">
				<!-- post author -->
				<div class="post-author">
					<div class="alignleft no-shrink icn-wrap">
						<i class="far fa-bookmark"></i>
					</div>
					<div class="description-wrap">
						<h2 class="author-heading">
							<a href="#">Category</a>
						</h2>
						<h3 class="author-heading-subtitle text-uppercase">Programing
							language</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-3">
			<div class="rating-holder">
				<ul class="star-rating list-unstyled justify-end">
					<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
					<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
					<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
					<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
					<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
				</ul>
				<strong class="element-block text-right subtitle fw-normal">(2
					Reviews)</strong>
			</div>
		</div>
		</header>
		<div class="aligncenter content-aligncenter">
		<div class="img-container">
			<img src="${baseURL}/read_images.html?imgName=${courseDetailsBO.curseBO.imageName}&&image=courseLogo"
						alt="image description">
		</div>
		</div>
		<div>
		<h3 class="content-h3">Course Description</h3>
		<c:out value="${courseDetailsBO.description}"
		escapeXml="false"></c:out>
        </div>
		<h3 class="content-h3">Design</h3>
		<c:out value="${courseDetailsBO.designed}"
		escapeXml="false"></c:out> <!-- <p>Leverage agile frameworks to provide a robust synopsis for high level overviews. Iterative approaches to corporate strategy foster collaborative thinking to further the overall value proposition. Organically grow the holistic world view of disruptive innovation via workplace diversity and empowerment.</p> -->
		 <ul class="listDefault list-unstyled">
							<!-- <li></li>
							<li></li>
							<li></li> -->
						</ul>

		<h2>Curriculam</h2>
		<!-- sectionRow --> <section class="sectionRow">
		<h2 class="h6 text-uppercase fw-semi rowHeading">Section-1:
			</h2>
		<!-- sectionRowPanelGroup -->
		<div class="panel-group sectionRowPanelGroup" id="accordion"
			role="tablist" aria-multiselectable="true">
			<!-- panel -->
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h3 class="panel-title fw-normal">
                          <h2 class="h6 text-uppercase fw-semi rowHeading">	Introduction :</h2>
						</a>
					</h3>
				</div>
			<div class="panel-body">
					<c:out value="${courseDetailsBO.curriculum}"
					escapeXml="false"></c:out>
				</div>
				<!-- </div> -->
			</div>
			</section> 
		</article>
		<!-- sidebar -->
		<%-- <div class="text-center contact-form">
		<a href="${baseURL}/register-course?courseId=${courseDetailsBO.curseBO.courseId}"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold">Enrolled Now</a>
	</div> --%>
		<aside class="col-xs-12 col-md-3" id="sidebar"> <!-- widget course select -->
		<section class="widget widget_box widget_course_select"> 
		 <header class="widgetHead text-center bg-theme">
		<h3>
		<a class="urlhref" href="${baseURL}/register-course?courseId=${courseDetailsBO.curseBO.courseId}">Enroll Now</a></h3>
		</header> <strong class="price element-block font-lato" data-label="price:"><c:out
				value="${courseDetailsBO.rupees}"></c:out></strong>
		 <ul class="list-unstyled font-lato">
			<!-- <li><i class="far fa-user icn no-shrink"></i> 199 Students</li> -->
			<li><i class="far fa-clock icn no-shrink"></i> Duration: 30 days</li>
			<li><i class="fas fa-bullhorn icn no-shrink"></i> Lectures: 10</li>
			<!-- <li><i class="far fa-play-circle icn no-shrink"></i> Video: 12
				hours</li> -->
			<li><i class="far fa-address-card icn no-shrink"></i>
				Certificate of Completion</li>
		</ul>
		</section> 
						<!-- widget popular posts -->
						<section class="widget widget_popular_posts">
							<h3>Popular Courses</h3>
							<!-- widget cources list -->
							<ul class="widget-cources-list list-unstyled">
							<li>
							<c:if test="${not empty ind0}">
								<a href="${baseURL}/course-details/${ind0.courseName}">
									<div class="alignleft">
									<div class="img-container">
										<img class="course-block-size" src="${baseURL}/read_images.html?imgName=${ind0.imageName}&&image=courseLogo" alt="image description">
										</div>
									</div>
									<div class="description-wrap">
										<h4 style="padding-top:5px;font-weight: 700"><c:out 
										value="${ind0.courseName}"></c:out></h4>
									</div>
								</a>
								</c:if>
							</li>
								<li>
								<c:if test="${not empty ind1}">
								<a href="${baseURL}/course-details/${ind1.courseName}">
									<div class="alignleft">
									<div class="img-container">
										<img class="course-block-size" src="${baseURL}/read_images.html?imgName=${ind1.imageName}&&image=courseLogo" alt="image description">
										</div>
									</div>
									<div class="description-wrap ">
										<h4 style="padding-top:5px;font-weight: 700"><c:out 
										value="${ind1.courseName}"></c:out></h4>
									</div>
								</a>
								</c:if>
							</li>
							<li>
							<c:if test="${not empty ind2}">
								<a href="${baseURL}/course-details/${ind2.courseName}">
									<div class="alignleft ">
									<div class="img-container">
										<img class="course-block-size" src="${baseURL}/read_images.html?imgName=${ind2.imageName}&&image=courseLogo" alt="image description">
										</div>
									</div>
									<div class="description-wrap" >
										<h4 style="padding-top:5px;font-weight: 700"><c:out 
										value="${ind2.courseName}"></c:out></h4>
									</div>
								</a>
								</c:if>
							</li>
							</ul>
						</section>
						<!-- widget tags -->
						<nav class="widget widget_tags">
							<h3>Tags</h3>
							<!-- tag clouds -->
							<ul class="list-unstyled tag-clouds font-lato">
							<c:forEach items="${courseCategoryList}" var="courseCategorylists">
							 <li><a href="${baseURL}/courses-list.html?courseCategoryId=${courseCategorylists.courseCategoryId}"><c:out
										value="${courseCategorylists.courseCategoryName}"></c:out></a></li>
								</c:forEach>
							</ul>
						</nav>
		</aside>
	</div>
</div>
</main>