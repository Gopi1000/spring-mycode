<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="professor_menu.jsp"></jsp:include>
<script type="text/javascript">
	var qType1 = '';
	var qId1 = '';
	var tempId1 = '';
	function editFun(qType, qId, tempId) {
		qType1 = qType;
		qId1 = qId;
		tempId1 = tempId;
		var appBanners = document.getElementsByClassName('edit'), i;

		for (var i = 0; i < appBanners.length; i ++) {
		    appBanners[i].style.display = 'none';
		}
		if (qType == 'question') {
			var html = '<div class="col-lg-10"><input class="form-control" name="input" type="text" id="inputId"/></div><div class="col-lg-2"><input type="button" onclick="editFun2();" value="Update"/></div>';
			$("#qInputId").html(html);
			$("#qInputId").show();
		} else if (qType == 'option') {
			var html = '<div class="col-lg-10"><input class="form-control" name="input" type="text" id="inputId"/></div><div class="col-lg-2"><input type="button" onclick="editFun2();" value="Update"/></div>';
			$("#oInputId" + tempId).html(html);
			$("#oInputId" + tempId).show();
		} else {
			var html = '<div class="col-lg-10"><input class="form-control" name="input" type="text" id="inputId"/></div><div class="col-lg-2"><input type="button" onclick="editFun2();" value="Update"/></div>';
			$("#aInputId").html(html);
			$("#aInputId").show();
		}
	}
	function editFun2() {
		var inputValue = $("#inputId").val();
		$.post('updateQuestionAjax', {
			qType : qType1,
			inputValue : inputValue,
			qId : qId1,
			tempId : tempId1
		}, function(response) {
			if (response == 'Success') {
				window.location.replace("professor_editQuestion?quesId="+qId1);
			} else {
				alert("Some thing went wrong. Contact admin.");
			}
		});
	}
</script>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">
                <c:if test="${!empty errorMessage}">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<h4 align="center">${errorMessage}</h4>
				</c:if>
				<c:if test="${!empty quizQuestion}">



					<div class="row">
						<div class="col-lg-12">
							<h1 class="page-header">
								<i class="fa fa-edit"></i>Edit Questions
							</h1>

						</div>
						
						<div class="col-lg-12"></div>
						<div class="col-lg-10 well">
							<form name="login-form" class="login-form"
								action="updateQuestion" method="post">

								<input type='hidden' name='quesId'
									value="${ quizQuestion.getQuesId()}" />
								<div class="form-group">
									<label>Question :</label>${ quizQuestion.getQuestion()} <a
										href="javascript:editFun('question',${ quizQuestion.quesId},0);">
										<i class=" fa fa-edit edit"></i>
									</a>
									<div class="col-lg-12" id="qInputId"></div>
								</div>
							 	<c:set var="indexVal" value="0"></c:set>

								<c:forEach var="options"
									items="${quizQuestion.getQuestionOptions() }">
									<c:set var="optTemp" value="${fn:split(options, '%*')}"></c:set>
									<div class="form-group">
										<label>Option ${indexVal} :</label>
										<%-- questionOption[${indexVal}] --%>
										${optTemp[1]}<a
											href="javascript:editFun('option',${ quizQuestion.quesId}, ${optTemp[0]});">
											<i class=" fa fa-edit edit"></i>
										</a>
										<div class="col-lg-12" id="oInputId${optTemp[0]}"></div>
									</div>
									<c:set var="indexVal" value="${indexVal+1}"></c:set>
								</c:forEach>
								<div class="form-group">
								<c:set var="ans" value="${fn:split(quizQuestion.getAnswer(), '%*')}"></c:set>
									<label>Answer :</label> ${ans[1]}<a
										href="javascript:editFun('answer',${ quizQuestion.quesId},0);">
										<i class=" fa fa-edit edit"></i>
									</a>
									<div class="col-lg-12" id="aInputId"></div>
								</div> 
								
								<!-- <div class="col-lg-5">
								<label>Question Weightage<font color="red"> *</font></label>
								<input class="form-control" name="input" type="text"/>
								</div> -->
								 <div class="form-group">
								<label>Question Weightage:<font color="red"> *</font></label> <input
									class="form-control" name="complexity" type="text" 
									placeholder="Complexity">
							</div> 
								
                              <div class="col-xs-2" style="float: right;">
									<button type="submit"
										class="btn btn-theme btn-success btn-block">Add</button>
								</div>




							</form>
						</div>

					</div>

					<!--  <table>  
       <tr><td></td><td><input type="hidden" name="id" value="quizQuestion.getQuesId()"/></td></tr> 
       <tr><td>Question:</td><td><input type="text" name="question" value="quizQuestion.getQuestion()"/></td></tr>  
       <tr><td>QuestionOption:</td><td><input type="text" name="questionOptions" value="quizQuestion.getQuestionOption()"/>  
                </td></tr>  
        <tr><td>Answer:</td><td><input type="text" name="answer" value="quizQuestion.getAnswer()"/></td></tr> 
        
        <tr><td colspan="2"><input type="submit" value="Edit & Save "/></td></tr>  
        </table> 
        </form>   -->

				</c:if>
			</div>
		</div>
	</div>

</body>

</html>
<%-- <jsp:include page="footer.jsp"></jsp:include> --%>