 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


   <main id="main">
<section class="container instructor-profile-block top">

	<div class="row">
		<div class="col-xs-12 col-sm-12">
		<div class="contact-form">
				<h3 class="text-center">View List of Subcategory</h3>
			</div>
			
			<c:if test="${not empty succesMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${succesMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			
							<aside class="bg-gray aback cover">
		
		<form:form name="myForm" method="post" commandName="subcategory"
						action="search-subcategory" modelAttribute="subcategory"
						class="course-search-form">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="form-holder">
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="subcategory" path="subcategory"
													placeholder="subcategoryname"
													class="form-control element-block" />
									</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="subcategoryId" path="subcategoryId"
													placeholder="subcategoryId"
													class="form-control element-block" />
									</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
													type="text" id="category" path="categoryBO.category"
													placeholder="categoryName"
													class="form-control element-block" />
									</label>
								</div>
							</div>
						</div>

						<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12">
							<div class="text-center">
								<button type="submit" id="submit" class=" bac btn-warning">Search
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</aside>
							
								
				<div class="table-wrap">
       <table class="table topics-data-table tab-full-responsive" style="text-align: left;">
						<thead class="bg-theme  hidden-xs">

							<tr>
								<!--  <th style="width: 120px;">S.No:</th>-->
								<th>SubCategoryId</th>
								<th>SubCategory</th>
								<th>Category</th>
							
								
								<th style="width: 71px;">Edit</th>
								
							</tr>
						</thead>
						
						<c:forEach items="${subcategorylists}" var="list">
								
								<tr>

									 <!--   <td><c:out value="${list.s_No}"></c:out></td>-->
									<td><c:out value="${list.subcategoryId}"></c:out></td>
									<td><c:out value="${list.subcategory}"></c:out></td>
									<td><c:out value="${list.categoryBO.category}"></c:out></td>
									
									<td data-title="Edit"><a
										href="edit-subcategory.html?subcategoryId=${list.subcategoryId}">
											<i class="fa fa-edit" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
									</tr>
									</c:forEach>
									
									
						
						
</table>
</div>

		
			
  </div>
</div>
</section>
</main>

</html> 



