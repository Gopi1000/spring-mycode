<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main id="main">
<section class="container instructor-profile-block checkout-form">
	<form:form name="myForm" id="subcategoryform" method="post" modelAttribute="subcategory"
		commandName="subcategory" class="contact-form top">
		<div class="col-xs-12 col-sm-12">
			<form:hidden path="categoryBO.categoryId" />
			<div class="contact-form">
				<h3 class="text-center">Edit Subcategory</h3>
			</div>
			
				<c:if test="${not empty errorMessage}">
				<div class="alert alert-success" style="margin-right: -1%;margin-left: -1%;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Error:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success" style="margin-right: -1%;margin-left: -1%;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>

			<c:if test="${userType eq 'admin'}">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="border: 1px solid #e6e6e6;padding: 50px;">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Category Name <span
											class="required"><font class="f-color">*</font></span></span> <form:select id="category"
									path="categoryBO.category" style="height: 42px;">
									<form:option value="Select">--Select--</form:option>
									<form:options itemLabel="category" items="${listcategory}"
										itemValue="categoryId"></form:options>
								</form:select>
											
									</label>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Subcategory Name <span
											class="required"><font class="f-color">*</font></span></span> <form:input
											type="text" id="subcategory" path="subcategory"
											placeholder="Subcategory Name" class="form-control element-block" />
									</label>
								</div>
							</div>
							
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <span
										class="element-block">Priority <span
											class="required"><font class="f-color">*</font></span></span> <form:input
											type="text" id="priority" path="priority"
											placeholder="priorityvalue" class="form-control element-block" />
									</label>
								</div>
							</div>
						</div></div></div></c:if>
						</div>
						<div class="text-right">
		<button type="submit" id="submit"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"Style ="min-width: 100px">Save
		</button>
	</div>
						</form:form></section></main>