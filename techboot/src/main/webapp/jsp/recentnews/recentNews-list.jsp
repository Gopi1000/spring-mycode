<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- news block -->
<main id="main"> <header
	class="heading-banner text-white bgCover"
	style="background-image: url(resource/images/img23.jpg);">
	<div class="container holder">
		<div class="align">
			<h1>Recent News</h1>
		</div>
	</div>
</header>
<section class="news-block container" style="padding-top:9px !important;">
	<!-- breadcrumb nav -->
	<nav class="breadcrumb-nav">
		<div class="container">
			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<li><a href="home.html">Home</a></li>
				<li class="active">Recent News</li>
			</ol>
		</div>
	</nav>
		<div class="row">
		<c:forEach items="${newslists.list}" var="newslist">
			<div class="col-xs-12 col-sm-6 col-md-4"><br>
				<!-- news post -->
				<article class="news-post">
					<div class="aligncenter">
						<a href="#"><img class="news-block-size"
							src="read_images.html?imgName=${newslist.imageName}&&image=newsLogo"
							alt="image desciption"></a>
					</div>
					<h3>
						<a href="#"><c:out value="${newslist.titleName}"></c:out></a>
					</h3>
					<p>
						<span class="text-gray"><c:out
								value="${newslist.descriptions}"></c:out></span>&hellip;
					</p>
					<time datetime="2011-01-12" class="time text-uppercase text-gray">
						<c:out value="${newslist.beginDate}"></c:out>
						by <a href="#"><c:out value="${newslist.authorName}"></c:out></a>
					</time>
				</article>
			</div>
		</c:forEach>


	</div>
<nav style="text-align: center;">
			<ul class="pagination pagination-theme  no-margin center"
				style="margin-left: 575px;">
				<c:if test="${newslists.currentPage gt 1}">
					<li><a href="recentNews-list?page=1&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="recentNews-list?page=${newslists.currentPage - 1}&&search=${searcgValue}"><span><i
								class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
				</c:if>
				<c:forEach items="${newslists.noOfPages}" var="i">
					<c:choose>
						<c:when test="${newslists.currentPage == i}">
							<li class="active"><a
								style="color: #fff; background-color: #34495e">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="recentNews-list?page=${i}&&search=${searcgValue}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if
					test="${newslists.currentPage lt newslists.totalPages}">
					<li><a
						href="recentNews-list?page=${newslists.currentPage + 1}&&search=${newslists}"><span><i
								class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="recentNews-list?page=${newslists.lastRecordValue}&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
				</c:if>
			</ul>
		</nav>
	<div class="text-right">
		<div>
			<a href="home.html"> <input type="button" value="Back"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold  btn-default  pull-right" />
			</a>
		</div>
	</div>
	<br>
	<br>


</section>
</main>