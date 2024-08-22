<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
</head>
<jsp:include page="header.jsp"></jsp:include>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#categoryId').change(function(event) {
	        var category = $("select#categoryId").val();
	        $.get('AjaxViewSubcategory', {
	        	category : category
	        }, function(response) {

	        var select = $('#subcategoryId');
	        select.find('option').remove();
	          $.each(response, function(subcategoryId, SubcategoryBO) {
	        	  $('<option>').val(SubcategoryBO.subcategoryId).text(SubcategoryBO.subcategory).appendTo(select);
	      });
	        });
	        });
	
});


</script>
<body>
	<div id="">
		<!-- Navigation -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header"><i class="fa fa-edit"></i>Questions</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 well">
						<form   action="fileUpload"
							method="post" enctype="multipart/form-data">					
							<div class="form-group">
								<label>Category<font color="red"> *</font></label>
								<select 
									name="category" class="form-control"  type="text"
									required="required" id="categoryId">
									<option  value="">Select Category</option>
									<c:forEach items="${categoryList}" var="category">
										<option value="${category.categoryId}">
											${category.category}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>SubCategory<font color="red"> *</font></label><select
									name="subcategory" class="form-control" type="text"
									required="required" id="subcategoryId">
									<option value="">Select SubCategory</option>
									<%-- <c:forEach items="${subcategoryList}" var="subcategory">
										<option value="${subcategory.subcategoryId}">
											${subcategory.subcategory}</option>
									</c:forEach> --%>
								</select>
							</div>
							
							



							
							<div class="form-group">
								<label>Select File</label> <input type="file" name="files" required="required">
							</div>
							<button type="submit" class="btn btn-success">Upload</button>
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
