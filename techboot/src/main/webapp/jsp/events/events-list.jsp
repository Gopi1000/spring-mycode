<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<title>Event List</title>

<body>

	<main id="main"> <!-- heading banner --> <header
		class="heading-banner text-white bgCover"
		style="background-image: url(resource/images/img23.jpg);">
		<div class="container holder">
			<div class="align">
				<h1>Events</h1>
			</div>
		</div>
	</header> <!-- breadcrumb nav -->
	<nav class="breadcrumb-nav">
		<div class="container">
			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li class="active">Events</li>
			</ol>
		</div>
	</nav>
	<section class="upcoming-events-block container">
		<header class="block-header">
			<div class="pull-left">
				<h2 class="block-header-heading">Upcoming Events</h2>
				<p style="color: #777777">Recent and upcoming Training events
					listed here</p>
			</div>
			<!--  <a href="events-list" class="btn btn-default text-uppercase pull-right">View 
		More</a> -->
		</header>
		<!-- upcoming events list -->

		<ul class="list-unstyled upcoming-events-list">
			<c:forEach items="${eventlists.list}" var="eventlist">
				<li>
					<div class="alignright">
						<img class="event-block-size"
							src="read_images.html?imgName=${eventlist.imageName}&&image=eventLogo"
							alt="image description">
					</div>
					<div class="alignleft">
						<time datetime="2011-01-12" class="time text-uppercase">
							<strong class="date fw-normal"><c:out
									value="${eventlist.dayDate}"></c:out></strong> <strong
								class="month fw-light font-lato" style="color: #777777"><c:out
									value="${eventlist.month}"></c:out></strong> <strong
								class="day fw-light font-lato" style="color: #777777"><c:out
									value="${eventlist.day}"></c:out></strong>
						</time>
					</div>
					<div class="description-wrap">
						<h3 class="list-heading">
							<c:out value="${eventlist.companyBO.companyName}"></c:out>
						</h3>
						<h3 class="list-heading" style="color:#303030a3 ">
						  <a href ="event-register/${eventlist.titleName}?eventId=${eventlist.eventId}">
					    <c:out value="${eventlist.titleName}"></c:out>
					    <img src="resource/images/img92.png"></a>
					   </h3>
						<address style="color: #777777">
							<c:out value="${eventlist.timing}"></c:out>
							|
							<c:out value="${eventlist.address}"></c:out>
						</address>
						<a href="event-register/${eventlist.titleName}?eventId=${eventlist.eventId}"
						class="btn btn-default text-uppercase" style="color: #777777">register</a>
					</div>
				</li>
			</c:forEach>
		</ul>
		<nav style="text-align: center;">
			<ul class="pagination pagination-theme  no-margin center"
				style="margin-left: 575px;">
				<c:if test="${eventlists.currentPage gt 1}">
					<li><a href="events-list?page=1&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="events-list?page=${eventlists.currentPage - 1}&&search=${searcgValue}"><span><i
								class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
				</c:if>
				<c:forEach items="${eventlists.noOfPages}" var="i">
					<c:choose>
						<c:when test="${eventlists.currentPage == i}">
							<li class="active"><a
								style="color: #fff; background-color: #34495e">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="events-list?page=${i}&&search=${searcgValue}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${eventlists.currentPage lt eventlists.totalPages}">
					<li><a
						href="events-list?page=${eventlists.currentPage + 1}&&search=${eventlists}"><span><i
								class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
					<li><a
						href="events-list?page=${eventlists.lastRecordValue}&&search=${searcgValue}"><span><i
								class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
				</c:if>
			</ul>
		</nav>
		<div class="text-right">
		<div>
			<a href="home.html"> <input type="button" value="Back"
				class="btn btn-theme btn-warning text-uppercase font-lato fw-bold" />
			</a>
		</div>
	</div>
	</section>
	</main>