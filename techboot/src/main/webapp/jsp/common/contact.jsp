<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<main id="main">
			<!-- heading banner -->
	<header class="heading-banner text-white bgCover" style="background-image: url(resource/images/img23.jpg);">
	<div class="container holder">
		<div class="align">
			<h1>Contact</h1>
				</div>
			</div>
			</header>
			<!-- breadcrumb nav -->
			<nav class="breadcrumb-nav">
				<div class="container">
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li><a href="home.html">Home</a></li>
						<li class="active">Contact</li>
					</ol>
				</div>
			</nav>
			<!-- contact block -->
			<section class="contact-block">
				<div class="container">
					<header class="seperator-head text-center">
						<h2>Contact Details</h2>
						<p>Welcome to our Website. We are glad to have you around.</p>
					</header>
			<div class="row">
			<div class="col-xs-12 col-sm-4">
				<!-- detail column -->
				<div class="detail-column">
					<span class="icn-wrap no-shrink element-block"> <img
						src="resource/images/icon11.png" alt="icon">
					</span>
					<div class="descr-wrap">
						<h3 class="text-uppercase">give us a call</h3>
						<p><a href="tel:+919790590476">+919790590476</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<!-- detail column -->
				<div class="detail-column">
					<span class="icn-wrap no-shrink element-block"> <img
						src="resource/images/icon12.png" alt="icon">
					</span>
					<div class="descr-wrap">
						<h3 class="text-uppercase">send us a message</h3>
						<p><a href="">enquiry@techboot.com</a></p>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<!-- detail column -->
				<div class="detail-column">
					<span class="icn-wrap no-shrink element-block"> <img
						src="resource/images/icon13.png" alt="icon">
					</span>
					<div class="descr-wrap">
						<h3 class="text-uppercase">visit our location</h3>
						<p>3/1419 Ground Floor, 5th Cross Street, Annai Velanganni
							Nagar Phase-I, Madhanandhapuram, Chennai - 600125</p>
					</div>
				</div>
			</div>
		</div>
					<hr class="sep-or element-block" data-text="or">
					<!-- contact form -->
					<form:form name="myForm" method="post" modelAttribute="contactBo"
			         commandName="contactBo" class="contact-form top">
            <h3 class="text-center">Drop Us a Message</h3>
			<c:if test="${not empty successMessage}">
			<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Success:</strong>
				<c:out value="${successMessage}"></c:out>
			</div>
		    </c:if>
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<div class="form-group">
							<form:input  type="text" path="yourName" class="form-control element-block"
							placeholder="Your Name"/>
							<form:errors path="yourName" cssColor="errors" style="color: red" ></form:errors>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4">
					<div class="form-group">
					<form:input type="text" path="emailAddress" class="form-control element-block"
							placeholder="Email Address"/>
						<form:errors path="emailAddress" cssColor="errors" style="color: red" ></form:errors>	
					</div>
				</div>
				<div class="col-xs-12 col-sm-4">
					<div class="form-group">
					<form:input type="text" path="contactNumber" class="form-control element-block"
							placeholder="Contact Number"/>
						<form:errors path="contactNumber" cssColor="errors" style="color: red" ></form:errors>	
					</div>
				</div>
				<div class="col-xs-12">
					<div class="form-group">
						<!-- <textarea class="form-control element-block" path="message"
							placeholder="Message"></textarea> -->
							<form:textarea type="text" path="message" class="form-control element-block"
							placeholder="Message" />
							<form:errors path="message" cssColor="errors" style="color: red" ></form:errors>
					</div>
				</div>
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-theme btn-warning text-uppercase font-lato fw-bold">send
					message</button>
			</div>
		</form:form>
		</div>
				<!-- mapHolder -->
				<div class="mapHolder">
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d18490.69215714457!2d80.13813466188542!3d13.023621283315055!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3a5260f0abb09795%3A0xed4592c211992bea!2sTechBoot!5e0!3m2!1sen!2sin!4v1583737860490!5m2!1sen!2sin" width="1500" height="400" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
					<!-- <iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d13607.729903367896!2d74.30893281977539!3d31.498539800000007!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2s!4v1530737870558" style="border:0" allowfullscreen="" width="100%" height="400" frameborder="0"></iframe> -->
					<span class="mapMarker">
											<img src="images/map-marker.png" alt="marker">
					</span>
				</div>
				<!-- btn aside block -->
				<aside class="btn-aside-block container">
					<div class="row">
						<div class="col-xs-12 col-sm-8 col">
							<h3>Have Any Questions?</h3>
							<p>Various versions years, sometimes by accident, sometimes on purpose</p>
						</div>
						<div class="col-xs-12 col-sm-4 text-right col">
							<a href="#" class="btn btn-warning btn-theme text-capitalize font-lato fw-normal">Ask Question Now</a>
						</div>
					</div>
				</aside>
			</section>
		</main>

	