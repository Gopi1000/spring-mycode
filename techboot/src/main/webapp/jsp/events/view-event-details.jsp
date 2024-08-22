
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<main id="main">
<section class="container instructor-profile-block">
	<div class="contact-form">
		<h3 class="text-center">Event Details</h3>
	</div>
	<div class="row">
		<div class="col-sm-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
				style="border: 1px solid #e6e6e6;">
				<section class="text-center bg-gray">
					<div class="container">
						<div class="col-xs-12 col-sm-10 col-sm-offset-1">

							<blockquote class="testimonial-quote">
								<span class="avatar rounded-circle element-block"> <img
									class="rounded-circle"
									src="read_images.html?imgName=${viewevent.imageName}&&image=eventlogo">
								</span>

							</blockquote>

						</div>
					</div>
				</section>
				<br>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-industry"> </i><span
									class="fafa_line_space">Title Name</span></label>
							</div>

							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${viewevent.titleName}</span></span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-user-circle-o"></i><span
									class="fafa_line_space">Date</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${viewevent.eventDate}</span></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-industry"> </i><span
									class="fafa_line_space">Timing</span></label>
							</div>

							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${viewevent.timing}</span></span>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<label><i class="fa fa-calendar"></i><span
									class="fafa_line_space">Address</span></label>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<span>:<span class="fafa_line_space1">${viewevent.address}</span></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="text-right">
		<input type="button" value="Back"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
			onclick="history.go(-1)">
	</div>
</section>
</main>