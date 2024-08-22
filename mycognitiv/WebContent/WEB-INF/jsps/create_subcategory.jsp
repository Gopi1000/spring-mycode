<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>myCognitiv</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>
	<div id="">

		<!-- Navigation -->
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-4">
						<h1 class="page-header">
							<i class="fa fa-edit"></i>
							<c:if test="${not empty editSubcategory.subcategoryId}">
							Update SubCategory						</c:if>
							<c:if test="${ empty editSubcategory.subcategoryId}">
							Create SubCategory
						</c:if>
						</h1>
					</div>
				</div>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-success" style="font-size: 100px;">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<h4 align="center">${errorMessage}</h4>

					</div>
				</c:if>

				<div class="row">

					<div class="col-lg-4 well">
						<form action="CreateSubCategory" method="post">

							<input type="hidden" name="subcategoryId"
								value="${editSubcategory.subcategoryId}" />
							<div class="form-group">
								<label>Category<font color="red"> *</font></label><select
									name="category" class="form-control" type="text"
									required="required">
									<c:if test="${!empty editSubcategory.category}">
										<option value="${editSubcategory.category}">
											${editSubcategory.category}</option>
									</c:if>
									<option value="">Select Category</option>
									<c:forEach items="${categoryList}" var="category">
										<option value="${category.category}">
											${category.category}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>SubCategory<font color="red"> *</font></label> <input
									class="form-control" name="subcategory" type="text"
									required="required" placeholder="subcategory"
									value="${editSubcategory.subcategory}">
							</div>
							<button type="submit" class="btn btn-success">Save</button>
							<a href='${pageContext.request.contextPath}/admin_home'
								class="btn btn-danger">Cancel</a>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>