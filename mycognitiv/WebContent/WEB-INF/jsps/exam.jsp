
<%@ page language="java" import="com.scube.mycognitiv.utils.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

 

<!-- <script src="ckeditor/ckeditor.js"></script> -->
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript">
	//<![CDATA[
	/* bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	}); */

	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'bold', 'italic', 'underline', 'ol', 'ul',
					'strikeThrough', 'html', 'image' ]
		}).panelInstance('keySkillsInput');
		new nicEditor({
			buttonList : [ 'bold', 'italic', 'underline', 'ol', 'ul',
					'strikeThrough', 'html', 'image' ]
		}).panelInstance('descriptionInput');
	});
	//]]>
</script>


<script>
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;

			var nicInstance = nicEditors.findEditor('keySkillsInput');
			var keyskill = nicInstance.getContent();
			var keyskillsValue = keyskill.replace('<br>', ' ');
			if (keyskillsValue == ' ') {
				isValid = false;
				$("#errorSkill").html("Please enter the KeySkill field");
			} else {
				$("#errorSkill").hide();
			}
		});
	});
</script>
<script language="javascript">
	var tim;
	var min = '${sessionScope.min}';
	var sec = '${sessionScope.sec}';

	function customSubmit(someValue) {
		document.questionForm.minute.value = min;
		document.questionForm.second.value = sec;
		document.questionForm.submit();
	}

	function examTimer() {
		if (parseInt(sec) > 0) {

			document.getElementById("showtime").innerHTML = "Time Remaining :"
					+ min + " Minute ," + sec + " Seconds";
			sec = parseInt(sec) - 1;
			tim = setTimeout("examTimer()", 1000);
		} else {

			if (parseInt(min) == 0 && parseInt(sec) == 0) {
				document.getElementById("showtime").innerHTML = "Time Remaining :"
						+ min + " Minute ," + sec + " Seconds";
				alert("Time Up");
				document.questionForm.minute.value = 0;
				document.questionForm.second.value = 0;
				document.questionForm.submit();

			}

			if (parseInt(sec) == 0) {
				document.getElementById("showtime").innerHTML = "Time Remaining :"
						+ min + " Minute ," + sec + " Seconds";
				min = parseInt(min) - 1;
				sec = 59;
				tim = setTimeout("examTimer()", 1000);
			}

		}
	}
</script>

</head>
<jsp:include page="header.jsp"></jsp:include>

<body onload="examTimer()">

	<div id="">
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Questions</h1>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<div>
								<%
									int currentQuestion = ((Exam) request.getSession(false).getAttribute("currentExam")).getCurrentQuestion();
									// System.out.println("Question Number "+currentQuestion+ " retrieved ");
								%>
								Current Question ${sessionScope.quest.questionNumber} /
								${sessionScope.totalNumberOfQuizQuestions}
							</div>
							<div id="showtime"></div>
							<div>
								<span style="color: Block; font-weight: bold">${sessionScope.quest.question}</span>
								<br /> <input type="hidden" name="answer${questionList.quesId}" />

								<form action="exam" method="post" name="questionForm">
									<c:if test="${sessionScope.questiontype eq 'text' }">
										<div class="form-group adminbox">

											<label>Type Answer</label> <font style="color: red;">*
											</font>

											<textarea class="form-control rtext" rows="4" cols="50"
												name="answer" id="keySkillsInput"
												placeholder="Enter Your Answer"></textarea>
											<div id="errorSkill" style="color: red;"></div>
										</div>
									</c:if>
									<c:forEach var="choice"
										items="${sessionScope.quest.questionOptions}"
										varStatus="counter">
										<c:set var="tempChoice" value="${fn:split(choice, '@`') }"></c:set>
										<c:choose>

											<c:when
												test="${sessionScope.quest.questionType eq 'options'}">
												<div>
													<input type="radio" name="answer" value="${tempChoice[0]}">
													<label>${tempChoice[1]}</label>
												</div>
											</c:when>

											<c:otherwise>

												<div>
													<input type="checkbox" name="answer"
														value="${tempChoice[0]}"> <label>
														${tempChoice[1]}</label>
												</div>


											</c:otherwise>
										</c:choose>
									</c:forEach>
									<br />
									<c:if test="${sessionScope.quest.questionNumber > 1}">
										<input type="submit" class="btn btn-primary" name="action"
											value="Previous" onclick="customSubmit()" />
									</c:if>
									<c:if
										test="${sessionScope.quest.questionNumber <= sessionScope.totalNumberOfQuizQuestions-1}">
										<input type="submit" class="btn btn-success" name="action"
											value="Next" onclick="customSubmit()" />
									</c:if>
									<input type="submit" class="btn btn-warning" name="action"
										value="Finish Exam" onclick="customSubmit()" /> <input
										type="hidden" name="minute" /><br /> <input type="hidden"
										name="second" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>