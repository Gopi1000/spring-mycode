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
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header" align="left">
							Choose Test Category
						</h1>

					</div>
				</div>
<!--  $('<option>').appendTo(select) 
	        	  .append($('<option>').val(SubcategoryBO.subcategoryId))
	        			 .append($('<option>').text(SubcategoryBO.subcategory)); -->
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8"></div>

					<div class="col-lg-4"></div>
					<div class="col-lg-4 well">
						<form action="takeTestCategory" method="post">
							<div class="form-group">
								<label>Category<font color="red"> *</font></label><select
									name="category" class="form-control" type="text"
									required="required" id="categoryId">
									<option value="">Select Category</option>
									<c:forEach items="${categoryList}" var="category">
										<option value="${category.categoryId}">
											${category.category}</option>
									</c:forEach>
								</select>
							</div>
							<%-- <div class="form-group">
								<label>SubCategory</label><select
									name="subcategory" class="form-control" type="text">
									<option value="">Select SubCategory</option>
									<c:forEach items="${subcategoryList}" var="subcategory">
										<option value="${subcategory.subcategoryId}">
											${subcategory.subcategory}</option>
									</c:forEach>
								</select>
							</div> --%><div class="form-group">
								<label>SubCategory<font color="red"> *</font></label><select multiple
									name="subcategories" class="form-control"  id="subcategoryId">
									 <option value="">Select SubCategory</option> 
										
								</select>
							</div>
							<div class="form-group">
								<label>Self Rate<font color="red"> *</font></label> <select
									name="selfRate" class="form-control" type="text"
									required="required">
									<option value="">Select Self Rate</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
							</div>
							<div class="form-group">
								<label>Total Question<font color="red"> *</font></label> <select
									name="noOfQuestions" class="form-control" type="text"
									required="required">
									<option value="">Select No.of Questions</option>
									<option value="5">5</option>
									<option value="10">10</option>
									<option value="15">15</option>
									<option value="20">20</option>
									<option value="25">25</option>
									<option value="30">30</option>
									<option value="35">35</option>
									<option value="40">40</option>
									<option value="45">45</option>
									<option value="50">50</option>
									<!-- <option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option> -->
								</select>
							</div>
							<!-- <div class="form-group">
								<label>Test Type<font color="red"> *</font></label> <select
									name="selfRate" class="form-control" type="text"
									required="required">
									<option value="">Select Test Type</option>
									<option value="options">options</option>
									<option value="text">text</option>
									
								</select>
							</div> -->
							<button type="submit" class="btn btn-success">Submit</button>
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
