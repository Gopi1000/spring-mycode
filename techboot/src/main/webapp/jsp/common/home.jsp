<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	$(document)
			.ready(
					function() {
						$('#btnSubmits')
								.click(
										function(e) {
											var email = $("#email").val();
											$("#messageId").html("");
											if (email == '') {
												$("#email").css({
													"border" : "1px solid red",
												});
											   }else {
												$("#email").css({
													"border" : "",
												});
												var mailformat = /(^\w+([\.-]?\w+)*@\w+([\.-]?\w+)(\.\w{2,3})$)|(^\w+([\.-]?\w+)*@\w+([\.-]?\w+)(\.\w{2,3})$)/;
												if (email.match(mailformat)) {
													$
															.ajax({
																type : "GET",
																url : 'ajax_news.html',
																data : 'email='
																		+ email,
																success : function(data) {
																	if (data == "Registration completed successfully.") {
																		$("#email").val("");
																	     }
																	if(data == "Your enquiry not posted successfully,please contact administrator."){
																		}
																	$("#messageId").html(data);
																},
															});
												} else {
													$("#messageId")
															.html(
																	"You have entered an invalid email address!");
												}
											}
										});
					});
</script>

<main id="main"> <!-- intro block -->
<section class="intro-block">
	<div class="slider fade-slider">
		<div>
			<!-- intro block slide -->
			<article class="intro-block-slide overlay bg-cover"
				style="background-image: url(resource/images/img01.jpg);">
				<div class="align-wrap container">
					<div class="align">
						<div class="anim">
							<h1 class="intro-block-heading">Career Development &amp;
								Training Organization</h1>
						</div>
						<div class="anim delay1">
							<p>We offer the most complete career development solutions ,
								for fresher, experienced professionals with latest Industry
								demanding technologies.</p>
						</div>
						<div class="anim delay2">
							<div class="btns-wrap">
								<a href="courses-list.html"
									class="btn btn-warning btn-theme text-uppercase">Our
									Courses</a> <a href="contact.html"
									class="btn btn-white text-uppercase">Contact us</a>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
		<div>
			<!-- intro block slide -->
			<article class="intro-block-slide overlay bg-cover"
				style="background-image: url(resource/images/img01.jpg);">
				<div class="align-wrap container">
					<div class="align">
						<div class="anim">
							<h1 class="intro-block-heading">Career Development
								Organization</h1>
						</div>
						<div class="anim delay1">
							<p>We offer the hands-on oriented training with one of the
								real time project, which helps the candidates to experience the
								technologies from an implementation perspective.</p>
						</div>
						<div class="anim delay2">
							<div class="btns-wrap">
								<a href="courses-list.html"
									class="btn btn-warning btn-theme text-uppercase">Our
									Courses</a> <a href="contact.html"
									class="btn btn-white text-uppercase">Contact us</a>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
		<div>
			<!-- intro block slide -->
			<article class="intro-block-slide overlay bg-cover"
				style="background-image: url(resource/images/img01.jpg);">
				<div class="align-wrap container">
					<div class="align">
						<div class="anim">
							<h1 class="intro-block-heading">Training Organization</h1>
						</div>
						<div class="anim delay1">
							<p>We offer the Industry demands and working professional
								takes the training with real time experience and example.</p>
						</div>
						<div class="anim delay2">
							<div class="btns-wrap">
								<a href="courses-list.html"
									class="btn btn-warning btn-theme text-uppercase">Our
									Courses</a> <a href="contact.html"
									class="btn btn-white text-uppercase">Contact us</a>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</div>
	<div class="container">
		<!-- features aside -->
		<aside class="features-aside">
			<a href="#" class="col"> <span
				class="icn-wrap text-center no-shrink"> <img
					src="resource/images/icon01.svg" width="44" height="43"
					alt="trophy">
			</span>
				<div class="description">
					<h2 class="features-aside-heading">Industry Best Instructors</h2>
					<span class="view-more element-block text-uppercase">view
						more</span>
				</div>
			</a> <a href="#" class="col"> <span
				class="icn-wrap text-center no-shrink"> <img
					src="resource/images/icon02.svg" width="43" height="39"
					alt="computer">
			</span>
				<div class="description">
					<h2 class="features-aside-heading">Learn Courses with hands-on</h2>
					<span class="view-more element-block text-uppercase">view
						more</span>
				</div>
			</a> <a href="#" class="col"> <span
				class="icn-wrap text-center no-shrink"> <img
					src="resource/images/icon03.svg" width="43" height="39"
					alt="computer">
			</span>
				<div class="description">
					<h2 class="features-aside-heading">Online Library &amp; Store</h2>
					<span class="view-more element-block text-uppercase">view
						more</span>
				</div>
			</a>
		</aside>
	</div>
</section>



<!-- popular posts block -->
<section class="popular-posts-block container">
	<div class="home-overlay">
		<header class="popular-posts-head">
			<h2 class="popular-head-heading">Most Popular Courses</h2>

		</header>

		<div class="row">
			<!-- popular posts slider -->


			<div class="slider popular-posts-slider">
				<c:forEach items="${courselist}" var="courselists" begin="0" end="3">
					<div>
						<div class="col-xs-12">
							<!-- 	popular post -->
							<article class="popular-post alligator-turtle">
								<div style="background: rgba(0, 0, 0, 0.9);">

									<div class="aligncenter">
										<div class="img-container">
											<img class="block-size alligator-turtles"
												src="read_images.html?imgName=${courselists.imageName}&&image=courseLogo"
												style="background-color: #000000;" alt="image description">
										</div>

									</div>
								</div>
								<%-- <div>
								<strong
									class="bg-primary text-white font-lato text-uppercase price-tag">
									<c:out
										value="${courselists.companyBO.companyName}"></c:out>
								</strong>
							</div>  --%>
								<%-- <div>
								<strong
									class="bg-primary text-white font-lato text-uppercase price-tag">
									<i class="fa fa-inr" aria-hidden="true"></i> <c:out
										value="${courselists.rupees}"></c:out>
								</strong>
							</div> --%>
								<h3 class="post-heading"  style="min-height: 50px">
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
										<li><span class="fas fa-star"><span
												class="sr-only">star</span></span></li>
										<li><span class="fas fa-star"><span
												class="sr-only">star</span></span></li>
										<li><span class="fas fa-star"><span
												class="sr-only">star</span></span></li>
										<li><span class="fas fa-star"><span
												class="sr-only">star</span></span></li>
										<li><span class="fas fa-star"><span
												class="sr-only">star</span></span></li>
									</ul>
								</footer>
							</article>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>
		<a href="courses-list.html"
			class="btn btn-default text-uppercase pull-right">View More</a>
	</div>
</section>


<!-- counter aside -->
<aside class="bg-cover counter-aside"
	style="background-image: url(resource/images/img10.jpg);">
	<div class="container align-wrap">
		<div class="align">
			<div class="row">
				<div class="col-xs-12 col-sm-3 col">
					<h2 class="counter-aside-heading">
						<strong class="countdown element-block">50+</strong> <strong
							class="text element-block">Colleges Supported</strong>
					</h2>
				</div>
				<div class="col-xs-12 col-sm-3 col">
					<h2 class="counter-aside-heading">
						<strong class="countdown element-block">100+</strong> <strong
							class="text element-block">Employers </strong>
					</h2>
				</div>
				<div class="col-xs-12 col-sm-3 col">
					<h2 class="counter-aside-heading">
						<strong class="countdown element-block">750+</strong> <strong
							class="text element-block">Candidates Trained</strong>
					</h2>
				</div>
				<div class="col-xs-12 col-sm-3 col">
					<h2 class="counter-aside-heading">
						<strong class="countdown element-block">50+</strong> <strong
							class="text element-block">Training Professionals</strong>
					</h2>
				</div>
			</div>
		</div>
	</div>
</aside>
<!-- upcoming events block -->
<section class="upcoming-events-block container">
	<c:if test="${not empty successMessage}">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Success:</strong>
			<c:out value="${successMessage}"></c:out>

		</div>
	</c:if>
	<header class="block-header">
		<div class="pull-left">
			<h2 class="block-header-heading">Upcoming Events</h2>
			<p style="color: #777777">Recent and upcoming Training events
				listed here</p>
		</div>

	</header>
	<!-- upcoming events list -->
	<ul class="list-unstyled upcoming-events-list">
		<c:forEach items="${eventlists}" var="eventlist" begin="0" end="2">
			<li>
				<div class="alignright event-block-size">
					<div class="img-container">
						<img class="event-block-size"
							src="read_images.html?imgName=${eventlist.imageName}&&image=eventLogo"
							alt="image description">
					</div>
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
					<%-- <h3 class="list-heading">
						<c:out value="${eventlist.companyBO.companyName}"></c:out>
					</h3> --%>
					<h3 class="list-heading">
						<a
							href="event-register/${eventlist.titleName}?eventId=${eventlist.eventId}">
							<c:out value="${eventlist.titleName}"></c:out> <img
							src="resource/images/img94.png">
						</a>
					</h3>

					<address style="color: #777777">
						<c:out value="${eventlist.timing}"></c:out>
						|
						<c:out value="${eventlist.address}"></c:out>
					</address>
					<a
						href="event-register/${eventlist.titleName}?eventId=${eventlist.eventId}"
						class="btn btn-default text-uppercase" style="color: #777777">register</a>
				</div>
			</li>
		</c:forEach>
	</ul>
	<a href="events-list.html"
		class="btn btn-default text-uppercase pull-right">View More</a>
</section>

<!-- categories aside -->
<aside class="bg-cover categories-aside text-center"
	style="background-image: url(resource/images/img14.jpg);">
	<div class="container holder">
		<!-- categories list -->
		<ul class="list-unstyled categories-list">
		<li><a href="#">
				<div class="align">
					<span class="icn-wrap">
					<i class='fab fa-java fa-5x'></i>
					 <!-- <img
						src="resource/images/icon04.svg" width="43" height="43"
						alt="image description"> -->
					</span> <strong class="h h5 element-block text-uppercase">Core Java</strong>
				</div>
		</a></li>
		<li><a href="#">
				<div class="align">
					<span class="icn-wrap">
					<i class='fab fa-java fa-5x'></i> <!-- <img
						src="resource/images/icon05.svg" width="44" height="48"
						alt="image description"> -->
					</span> <strong class="h h5 element-block text-uppercase">Java Servlets</strong>
				</div>
		</a></li>
		<li><a href="#">
				<div class="align">
					<span class="icn-wrap">
					<i class='fab fa-java fa-5x'></i> <!-- <img
						src="resource/images/icon05.svg" width="44" height="48"
						alt="image description"> -->
					</span> <strong class="h h5 element-block text-uppercase">JSP</strong>
				</div>
		</a></li>
		<li><a href="#">
				<div class="align">
					<span class="icn-wrap"> 
						<i class='fab fa-java fa-5x'></i> 
					</span> <strong class="h h5 element-block text-uppercase">Spring</strong>
				</div>
		</a></li>
		<li><a href="#">
				<div class="align">
					<span class="icn-wrap"> <i class='fab fa-java fa-5x'></i> 
					
					</span> <strong class="h h5 element-block text-uppercase">Spring Boot</strong>
				</div>
		</a></li>
		<li><a href="#">
				<div class="align">
					<span class="icn-wrap"> 
						<i class='fab fa-java fa-5x'></i> 
					</span> <strong class="h h5 element-block text-uppercase">Angular</strong>
				</div>
		</a></li>
	</ul>
	</div>
</aside>
<section class="testimonials-block text-center bg-gray"
	style="background-image: url(resource/images/bg-pattern01.png);">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<!-- testimonail slider -->
				<div class="slick-slider slider testimonail-slider">
					<c:forEach items="${testinglists}" var="testimonial">
						<div>
							<blockquote class="testimonial-quote font-roboto">
								<h2>
									<c:out value="${testimonial.titleName}"></c:out>
								</h2>
								<p>
									<span class="text-black"><c:out
											value="${testimonial.message}"></c:out></span>
								</p>
								<cite class="element-block font-lato"> <span
					
									class="avatar rounded-circle element-block"> <img
										class="rounded-circle"
										src="read_images.html?imgName=${testimonial.imageName}&&image=testimonialLogo">

								</span> <strong class="element-block h5 h"><c:out
											value="${testimonial.name}-"></c:out><span class="text-black"><c:out
												value="${testimonial.profession}"></c:out></span></strong>
								</cite>
							</blockquote>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- news block -->
<section class="news-block container">
	<header class="seperator-head text-center">
		<h2>Recent News</h2>
		<p>Share your work to collaboratve with our vibrant design
			element.</p>
	</header>

	<div class="row">
		<c:forEach items="${newslists}" var="newslist" begin="0" end="2">
			<div class="col-xs-12 col-sm-6 col-md-4">
				<!-- news post -->
				<article class="news-post">

					<div class="aligncenter">
						
							<a href="#"><div class="img-container"><img 
								src="read_images.html?imgName=${newslist.imageName}&&image=newsLogo"
								alt="image desciption"></div></a>
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
	<a href="recentNews-list"
		class="btn btn-default text-uppercase pull-right">View More</a>
</section>
<!-- subscription aside block -->
<aside class="subscription-aside-block bg-theme text-white">
	<!-- newsletter sub form -->
	<form id="NewsLetterId" class="container newsletter-sub-form">
		<div class="row form-holder">
			<div class="col-xs-12 col-sm-6 col">
				<div class="text-wrap">
					<span class="element-block icn no-shrink rounded-circle"><i
						class="far fa-envelope-open"><span class="sr-only">icn</span></i></span>
					<div class="inner-wrap">
						<label for="email">Signup for Newsletter</label>
						<p>Subscribe now and receive weekly newsletter with new
							updates.</p>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col">
				<div class="input-group">
					<input type="email" id="email" name="email" class="form-control"
						placeholder="Enter your email&hellip;"> <span
						class="input-group-btn">
						<button type="button" class="btn btn-dark text-uppercase"
						id="btnSubmits" >Submit</button>
					</span>
				</div>
			</div>
			<div id="messageId" style="color: #fff;margin-left: 65%"> </div>
		</div>
	</form>
</aside>
</main>

