<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="req" value="${pageContext.request}"></c:set>
<c:set var="baseURL"
	value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"></c:set>

<!-- footer area container -->
<div class="footer-area bg-dark text-white">
	<!-- aside -->
	<aside class="aside container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-3 col">
				<div class="logo">
					<a href="${baseURL}/home.html"><img
						src="${baseURL}/resource/images/logo-white.png" alt="studyLMS"></a>
				</div>
				<br>
				<aside>
					<p style="margin-top: -23px;">We have over 20 years experience in
						providing technology training for various level of technology aspirants. We support fresh graduates and working professionals whom wants to upgrade their career.</p>
				</aside>
				<br> <br> <a href="${baseURL}/about-us" style="margin-top: -64px"
					; class="btn btn-default text-uppercase">Start Leaning Now</a>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3 col hidden-xs">
				<h3>Popular Courses</h3>
				<!-- widget cources list -->
				<ul class="widget-cources-list list-unstyled"
					style="margin-top: -29px;">
					<li>
					<li><c:if test="${not empty ind0 }">
							<a href="${baseURL}/course-details/${ind0.courseName}">
								<div class="alignleft ">
								<div class="img-container">
									<img class="footer-block-size image-shadows"
										src="${baseURL}/read_images.html?imgName=${ind0.imageName}&&image=courseLogo"
										alt="image description">
									 </div> 
								</div>
								<div class="description-wrap">
									<h4 style="padding-top:5px;">
										<c:out value="${ind0.courseName}"></c:out>
									</h4>
								</div>
							</a>
						</c:if></li>
					<li><c:if test="${not empty ind1 }">
							<a href="${baseURL}/course-details/${ind1.courseName}">
								<div class="alignleft">
								<div class="img-container">
									<img class="footer-block-size"
										src="${baseURL}/read_images.html?imgName=${ind1.imageName}&&image=courseLogo"
										alt="image description">
										</div> 
								</div>
								<div class="description-wrap">
									<h4 style="padding-top:5px;">
										<c:out value="${ind1.courseName}"></c:out>
									</h4>
								</div>
							</a>
						</c:if></li>
					<li><c:if test="${not empty ind2 }">
							<a href="${baseURL}/course-details/${ind2.courseName}">
								<div class="alignleft">
								<div class="img-container">
									<img class="footer-block-size"
										src="${baseURL}/read_images.html?imgName=${ind2.imageName}&&image=courseLogo"
										alt="image description">
										</div> 
								</div>
								<div class="description-wrap">
									<h4 style="padding-top:5px;">
										<c:out value="${ind2.courseName}"></c:out>
									</h4>
								</div>
							</a>
						</c:if></li>
					</li>


				</ul>
			</div>
			<nav class="col-xs-12 col-sm-6 col-md-3 col">
				<h3>Quick Links</h3>
				<!-- fooer navigation -->
				<ul class="fooer-navigation list-unstyled">
					<li><a href="${baseURL}/courses-list.html">All Courses</a></li>
					<%-- <li><a href="${baseURL}/#">Privacy Policy</a></li>
					<li><a href="${baseURL}/#">Terms of Use</a></li> --%>
					<li><a href="${baseURL}/contact.html">Contact Us</a></li>
					<li><a href="${baseURL}/about-us.html">About Us</a></li>
					
				</ul>
			</nav>
			<div class="col-xs-12 col-sm-6 col-md-3 col">
				<h3>contact us</h3>
				<p>We are available to help you on Monday to Friday during 10AM-6PM.</p>
				<!-- ft address -->
				<address class="ft-address">
					<dl>
						<dt>
							<span class="fas fa-map-marker"><span class="sr-only">marker</span></span>
						</dt>
						<dd>3/1418 First Floor, 5th Street, Annai Velanganni
							Nagar Phase-I, Madhanandhapuram, Chennai - 600125</dd>
						<dt>
							<span class="fas fa-phone-square"><span class="sr-only">phone</span></span>
						</dt>
						<dd>
							Call: <a href="tel:+919790590476">+ 919790590476</a>
						</dd>
						<dt>
							<i class="fa fa-whatsapp" aria-hidden="true"></i>

						</dt>
						<dd>
							Whatsapp: <a href="tel:+919790590476">+ 919790590476</a>
						</dd>
						<dt>
							<span class="fas fa-envelope-square"><span class="sr-only">email</span></span>
						</dt>
						<dd>
							Email: <a href="mailto:info@Techboot.com">enquiry@techbootsolutions.com</a>
						</dd>
					</dl>
				</address>
			</div>
		
			
		</div>
	</aside>
	<!-- page footer -->
	<footer id="page-footer" class="font-lato">
		<div class="container">
			<div class="row holder">
				<div class="col-xs-12 col-sm-push-6 col-sm-6">
					<ul class="socail-networks list-unstyled">
						<li><a href="${baseURL}/#"><span class="fab fa-facebook"></span></a></li>
						<li><a href="${baseURL}/#"><span class="fab fa-twitter"></span></a></li>
						<li><a href="${baseURL}/#"><span class="fab fa-instagram"></span></a></li>
						<li><a href="${baseURL}/#"><span class="fab fa-linkedin"></span></a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-pull-6 col-sm-6">
					<p>
						<a href="${baseURL}/#">TechBoot Solutions</a> | &copy; 2018-2021 <a
							href="${baseURL}/#">DesignFalls</a>, All rights reserved
					</p>
				</div>
			</div>
		</div>
	</footer>
</div>
<!-- back top of the page -->
<span id="back-top" class="text-center fa fa-caret-up"></span>
<!-- loader of the page -->
<div id="loader" class="loader-holder">
	<div class="block">
		<img src="${baseURL}/resource/images/svg/hearts.svg" width="100"
			alt="loader">
	</div>
</div>

