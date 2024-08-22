
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/plugins/nicEdit-latest.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	bkLib.onDomLoaded(nicEditors.allTextAreas);
</script>


<script type="text/javascript">
	$(document).ready(
			function() {
				$('#categoryId').change(
						function(event) {
							var category = $("select#categoryId").val();
							$.get('AjaxViewSubcategory', {
								category : category
							}, function(response) {

								var select = $('#subcategoryId');
								select.find('option').remove();
								$.each(response, function(subcategoryId,
										SubcategoryBO) {
									$('<option>').val(
											SubcategoryBO.subcategoryId).text(
											SubcategoryBO.subcategory)
											.appendTo(select);
								});
							});
						});

			});
</script>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


</head>
<jsp:include page="header.jsp"></jsp:include>

<body>
	<div id="wrapper">
		<!-- Navigation -->
		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<c:if test="${!empty errorMessage}">
						<div class="alert alert-success" style="font-size: 100px;">
							<h4 align="center">${errorMessage}</h4>
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						</div>
					</c:if>
					<h1 class="page-header">Questions</h1>


					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-edit fa-fw"></i> Create Questions
									</h3>
								</div>
								<div class="panel-body">
									<div class="row">

										<div class="col-lg-12">
											<form name="login-form" class="login-form"
												action="CreateQuestion" method="post">

												<div class="form-group">
													<label>Category<font color="red"> *</font></label> <select
														name="category" class="form-control" type="text"
														required="required" id="categoryId">
														<option value="">Select Category</option>
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
														<c:forEach items="${subcategoryList}" var="subcategory">
															<option value="${subcategory.subcategoryId}">
																${subcategory.subcategory}</option>
														</c:forEach>
													</select>
												</div>

												<h4>
													QuestionType<font color="red">*</font>
												</h4>
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group">
															<select class="form-control" name="questionType"
																type="text" required="required">
																<option>Select QuestionType</option>
																<option>options</option>
																<!-- <option>Fill In The Blanks</option>
																<option>True Or False</option> -->
																<option>Multiple Select</option>
															</select>
														</div>
													</div>
												</div>


												<h4>
													Question<font color="red"> *</font>
												</h4>
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group">
															<textarea id="ck" name="question" rows="4" cols="212"></textarea>
														</div>
													</div>
												</div>

												<h4>Answers :</h4>

												<div class="row">
													<div class="col-lg-6 ">
														<div class="form-group">

															<label>Option1<font color="red"> *</font></label>
															<textarea id="ck" name="answer1" rows="4" cols="100"></textarea>
														</div>
													</div>
													<div class="col-lg-6 ">
														<div class="form-group">
															<label>Option2<font color="red"> *</font></label>
															<textarea id="ck" name="answer2" rows="4" cols="100"></textarea>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-lg-6 ">
														<div class="form-group">
															<label>Option3<font color="red"> *</font></label>
															<textarea id="ck" name="answer3" rows="4" cols="100"></textarea>
														</div>
													</div>
													<div class="col-lg-6 ">
														<div class="form-group">
															<label>Option4<font color="red"> *</font></label>
															<textarea id="ck" name="answer4" rows="4" cols="100"></textarea>
														</div>
													</div>
												</div>

												<h4>
													CorrectAnswer<font color="red"> *</font>
												</h4>
												<div class="row">
													<div class="form-group">
														<div class="col-lg-12">
															<textarea id="correctAnswer" name="correctAnswer"
																rows="4" cols="212"></textarea>
														</div>
													</div>
												</div>

												<h4>
													Complexity<font color="red">*</font>
												</h4>
												<div class="row">
													<div class="col-lg-12">
														<div class="form-group">
															<select class="form-control" name="complexity"
																type="text" required="required">
																<option>Select Complexity</option>
																<option>1</option>
																<option>2</option>
																<option>3</option>
																<option>4</option>
																<option>5</option>
																<option>6</option>
																<option>7</option>
																<option>8</option>
																<option>9</option>
																<option>10</option>
															</select>
														</div>
													</div>
												</div>

												<button type="submit" class="btn btn-success">Add</button>
												<a href='${pageContext.request.contextPath}/admin_home'
													class="btn btn-danger">Cancel</a>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>


<jsp:include page="footer.jsp"></jsp:include>
</html>



