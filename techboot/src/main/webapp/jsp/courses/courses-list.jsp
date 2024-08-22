<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:set var="req" value="${pageContext.request}"></c:set>
<c:set var="baseURL"
	value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"></c:set>
<script src="${baseURL}/resource/js/jquery.js"></script>
<script src="${baseURL}/resource/js/jquery.main.js"></script>
<script src="${baseURL}/resource/js/plugins.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script>
$(document).ready(function() {
    $(function() {
        $("#courseName").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "${baseURL}/getMachedKeywords",
                    type: "GET",
                    data: { term: request.term },

                    dataType: "json",

                    success: function(metalist) {
                    	response($.map(metalist, function(v,i){
                    	    return {
                    	                label: v.metaTitle,
                    	                value: v.metaTitle
                    	               };
                    	}));
                    }
               });              
            }   
        });
    });
});
</script>
<style>
* {
	box-sizing: border-box;
}

.course_search input[type=text] {
	padding: 5px;
	font-size: 17px;
	border: 1px solid grey;
	float: left;
	width: 85%;
	background: #f1f1f1;
}

.course_search button {
	float: left !important;
	width: 15% !important;
	padding: 5px !important;
	background: orange !important;
	color: white !important;
	font-size: 17px !important;
	border: 1px solid grey !important;
	border-left: none !important;
	cursor: pointer !important;
}

.course_search button:hover {
	background: #d99210 !important;
}
</style>



<main id="main"> <!-- heading banner --> <header
	class="heading-banner text-white bgCover"
	style="background-image: url(resource/images/img23.jpg);">
	<div class="container holder">
		<div class="align">
			<h1>Courses</h1>
		</div>
	</div>
</header> <!-- breadcrumb nav -->
<nav class="breadcrumb-nav">
	<div class="container">
		<!-- breadcrumb -->
		<ol class="breadcrumb">
			<li><a href="home.html">Home</a></li>
			<li class="active">Courses</li>
		</ol>
	</div>
</nav>
<!-- two columns --> <c:if test="${not empty successMessage}">
	<div class="alert alert-success">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Success:</strong>
		<c:out value="${successMessage}"></c:out>
	</div>
</c:if> <c:if test="${not empty errorMessage}">
	<div class="alert alert-success"
		style="margin-right: 25%; margin-left: 5%; margin-top: 5%;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Info:</strong>
		<c:out value="${errorMessage}"></c:out>
	</div>
</c:if>
<div id="two-columns" class="container">

	<div class="row">
		<!-- content -->
		<article id="content" class="col-xs-12 col-md-9">
			<!-- show head -->

			<div class="row flex-wrap">

				<c:forEach items="${courselist.list}" var="courselists">


					<div class="col-xs-12 col-sm-6 col-lg-4">
						<!-- popular post -->
						<article class="popular-post">


							<div class="aligncenter">
								<div class="img-container">
									<img class="block-size alligator-turtles"
										src="read_images.html?imgName=${courselists.imageName}&&image=courseLogo"
										alt="image description">
								</div>
							</div>
							<h3 class="post-heading">
								<a href="course-details/${courselists.courseName}"><c:out
										value="${courselists.courseName}"></c:out></a>

							</h3>
							<div class="post-author">
								<div class="alignleft rounded-circle no-shrink">
									<a href="#"><img src="resource/images/img95.jpg"
										class="rounded-circle" alt="image description"></a>
								</div>
								<h4 class="author-heading">
									<a href="#"><c:out value="${courselists.authorName}"></c:out></a>
								</h4>
							</div>
							<footer class="post-foot gutter-reset">
								<ul class="list-unstyled post-statuses-list">
									<li><a href="#"> <span
											class="fas icn fa-users no-shrink"><span
												class="sr-only">users</span></span> <strong class="text fw-normal">98</strong>
									</a></li>
									<li><a href="#"> <span
											class="fas icn no-shrink fa-comments"><span
												class="sr-only">comments</span></span> <strong
											class="text fw-normal">10</strong>
									</a></li>
								</ul>
								<ul class="star-rating list-unstyled">
									<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
									<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
									<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
									<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
									<li><span class="fas fa-star"><span class="sr-only">star</span></span></li>
								</ul>
							</footer>
						</article>
					</div>

				</c:forEach>
			</div>

			<nav style="text-align: center;">
				<ul class="pagination pagination-theme  no-margin center"
					style="margin-left: 575px;">
					<c:if test="${courselist.currentPage gt 1}">
						<li><a href="courses-list?page=1&&search=${searcgValue}"><span><i
									class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
						<li><a
							href="courses-list?page=${courselist.currentPage - 1}&&search=${searcgValue}"><span><i
									class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
					</c:if>
					<c:forEach items="${courselist.noOfPages}" var="i">
						<c:choose>
							<c:when test="${courselist.currentPage == i}">
								<li class="active"><a
									style="color: #fff; background-color: #34495e">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="courses-list?page=${i}&&search=${searcgValue}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${courselist.currentPage lt courselist.totalPages}">
						<li><a
							href="courses-list?page=${courselist.currentPage + 1}&&search=${courselist}"><span><i
									class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
						<li><a
							href="courses-list?page=${courselist.lastRecordValue}&&search=${searcgValue}"><span><i
									class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
					</c:if>
				</ul>
			</nav>
		</article>

		<!-- sidebar -->
		<aside class="col-xs-12 col-md-3" id="sidebar">
			<!-- widget search -->
			<section class="widget course_search">
				<h3>Course Search</h3>
				<!-- search form -->
				<form:form name="myForm" method="post" commandName="courseObject"
					action="search-course-list" modelAttribute="courseObject"
					class="course_search">
					<fieldset>
						<form:input type="text" id="courseName" path="courseName"
							placeholder="Course Name" />
						<div id="ui-widget"></div>
						<button type="submit">
							<i class="fa fa-search"></i>
						</button>

					</fieldset>
				</form:form>

			</section>
			<!-- widget popular posts -->
			<section class="widget widget_popular_posts">
				<h3>Popular Courses</h3>
				<!-- widget cources list -->
				<ul class="widget-cources-list list-unstyled">
					<li><a href="course-details/${ind0.courseName}">
							<div class="alignleft large ">
								<div class="img-container">
									<img class="course-list-block"
										src="${baseURL}/read_images.html?imgName=${ind0.imageName}&&image=courseLogo"
										alt="image description">
								</div>
							</div>
							<div class="description-wrap">
								<h4 style="padding-top: 5px; font-weight: 700">
									<c:out value="${ind0.courseName}"></c:out>
								</h4>
								<%-- <strong class="price text-primary element-block font-lato text-uppercase"><i class="fa fa-inr" aria-hidden="true"></i><c:out
										value="${ind2.rupees}"></c:out></strong> --%>
							</div>
					</a></li>
					<li><a href="course-details/${ind1.courseName}">
							<div class="alignleft large">
								<div class="img-container">
									<img class="course-list-block"
										src="${baseURL}/read_images.html?imgName=${ind1.imageName}&&image=courseLogo"
										alt="image description">
								</div>
							</div>
							<div class="description-wrap">
								<h4 style="padding-top: 5px; font-weight: 700">
									<c:out value="${ind1.courseName}"></c:out>
								</h4>
								<%-- <strong class="price text-primary element-block font-lato text-uppercase"><i class="fa fa-inr" aria-hidden="true"></i><c:out
										value="${ind2.rupees}"></c:out></strong> --%>
							</div>
					</a></li>
					<li><a href="course-details/${ind2.courseName}">
							<div class="alignleft large">
								<div class="img-container">
									<img class="course-list-block"
										src="${baseURL}/read_images.html?imgName=${ind2.imageName}&&image=courseLogo"
										alt="image description">
								</div>
							</div>
							<div class="description-wrap">
								<h4 style="padding-top: 5px; font-weight: 700">
									<c:out value="${ind2.courseName}"></c:out>
								</h4>
							</div>
					</a></li>
				</ul>
			</section>
			<nav class="widget widget_tags">
				<h3>Tags</h3>
				<!-- tag clouds -->
				<ul class="list-unstyled tag-clouds font-lato">
					<c:forEach items="${courseCategoryList}" var="courseCategorylists">
						<li><a
							href="courses-list.html?courseCategoryId=${courseCategorylists.courseCategoryId}"><c:out
									value="${courseCategorylists.courseCategoryName}"></c:out></a></li>
					</c:forEach>
				</ul>
			</nav>
		</aside>
	</div>
</div>



</main>
</div>
