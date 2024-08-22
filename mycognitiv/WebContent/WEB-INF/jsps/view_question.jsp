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
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<h4 align="center">${errorMessage}</h4>
					<div class="col-lg-12">
						<h1 class="page-header">Edit Questions</h1>

					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-edit fa-fw"></i> Edit Questions
								</h3>
							</div>
							<div class="panel-body">
								<div class="row">

									<div class="col-lg-12">
										<form name="login-form" class="login-form"
											action="updateQuestion" method="post">

											<input type="hidden" name="id"
												value="quizQuestion.categoryId" /> <input type="hidden"
												name="id" value="quizQuestion.subcategoryId" /> <input
												type='hidden' name='quesId'
												value="${ quizQuestion.getQuesId()}" />


											<div class="form-group">
												<label>Category<font color="red"> *</font></label> <input
													class="form-control" type="text" name="category"
													readonly="true" value="${quizQuestion.category}"
													required="required" />
											</div>



											<div class="form-group">
												<label>SubCategory<font color="red"> *</font></label> <input
													class="form-control" type="text" name="subcategory"
													readonly="true" value="${quizQuestion.subcategory}" />
											</div>

											<div class="form-group">
												<label>QuestionType<font color="red">*</font></label> <select
													class="form-control" name="questionType" type="text"
													required="required">
													<option>${quizQuestion.questionType}</option>
													<option>options</option>
													<!-- <option>Fill In The Blanks</option>
													<option>True Or False</option> -->
													<option>Multiple Select</option>
												</select>
											</div>



											<h4>Question :</h4>
											<div class="row">
												<div class="col-lg-6">
													<div class="form-group">
														<label>Question<font color="red"> *</font></label>

														<textarea id="question" name="question" rows="4"
															cols="168">${quizQuestion.question}</textarea>
													</div>
												</div>
											</div>

											<h4>Answers :</h4>

											<div class="row">
												<div class="col-lg-6">
													<div class="form-group">

														<label>Option1<font color="red"> *</font></label>
														<textarea id="answer1" name="answer1" rows="4" cols="75">${quizQuestion.questionOptions[0]}</textarea>
													</div>
												</div>
												<div class="col-lg-6">
													<div class="form-group">
														<label>Option2<font color="red"> *</font></label>
														<textarea id="answer2" name="answer2" rows="4" cols="75">${quizQuestion.questionOptions[1]}</textarea>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="col-lg-6">
													<div class="form-group">
														<label>Option3<font color="red"> *</font></label>
														<textarea id="answer3" name="answer3" rows="4" cols="75">${quizQuestion.questionOptions[2]}</textarea>
													</div>
												</div>
												<div class="col-lg-6">
													<div class="form-group">
														<label>Option4<font color="red"> *</font></label>
														<textarea id="answer4" name="answer4" rows="4" cols="75">${quizQuestion.questionOptions[3]}</textarea>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group">
													<div class="col-lg-6">

														<label>CorrectAnswer<font color="red"> *</font></label>
														<textarea id="correctAnswer" name="correctAnswer" rows="4"
															cols="168">${quizQuestion.answer}</textarea>
													</div>
												</div>
											</div>

											<div class="form-group">
												<label>Complexity<font color="red">*</font></label> <select
													class="form-control" name="complexity" type="text"
													required="required">
													<option>${quizQuestion.complexity}</option>
													<option>options</option>
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


											<button type="submit" class="btn btn-success">Update</button>
											<a href='${pageContext.request.contextPath}/edit_questions'
												class="btn btn-danger">Cancel</a>
										</form>
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



