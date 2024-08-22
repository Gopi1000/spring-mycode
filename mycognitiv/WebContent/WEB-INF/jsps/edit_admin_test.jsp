<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="">
		<!-- Navigation -->
		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<i class="fa fa-edit"></i>Edit Test Category
						</h1>

					</div>
					<c:if test="${!empty infoMessage}">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<h4 align="center">${infoMessage}</h4>
					</c:if>
					<div class="col-lg-2"></div>
					<div class="col-lg-8"></div>

					<div class="col-lg-4"></div>
					<div class="col-lg-4 well">
						<form action="editTest" method="post">
							<div class="form-group">
								<input type="hidden" name="testId" value="{editTestObj.testId}">
								<label>Category<font color="red"> *</font></label><select
									name="category" class="form-control" type="text"
									required="required" id="categoryId">
									<option value="${editTestObj.categoryId}">${editTestObj.category}</option>
									<c:forEach items="${categoryList}" var="category">
										<option value="${category.categoryId}">${category.category}</option>
									</c:forEach>
								</select> <label>SubCategory<font color="red"> *</font></label><select
									name="subcategories" multiple="multiple" class="form-control"
									id="subcategoryId">
									<option value="${editTestObj.subcategoryId}" selected="true">${subCategoryRef}</option>
									<c:forEach items="${subcategoryList}" var="subcategory">
										<option value="${subcategory.subcategoryId}">${subcategory.subcategory}</option>
									</c:forEach>

								</select>
								<div class="form-group">
									<label>Self Rate<font color="red"> *</font></label> <select
										name="selfRate" class="form-control" type="text"
										required="required">
										<option value="${editTestObj.selfRate}">${editTestObj.selfRate}</option>
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
										<option value="${editTestObj.totalQuestion}">${editTestObj.totalQuestion}</option>
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
								<button type="submit" class="btn btn-success">Update</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>