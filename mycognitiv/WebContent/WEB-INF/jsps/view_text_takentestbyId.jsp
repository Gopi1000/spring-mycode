
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="../css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<jsp:include page="header.jsp"></jsp:include>
<script type="text/javascript">
	
	function addFun(qId1,uId1) {
		
		$.post('AddMarkedQuestionAjax', {			
			qId : qId1,
			uId : uId1			
		}, function(response) {
			if (response != "Failed") {
				alert(response);
				} else {
				alert("Some thing went wrong. Contact admin.");
			}
		});
	}
	</script>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Quiz Results</h1>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12 well">

						<c:forEach var="q" items="${sessionScope.reviewQuestions}"
							varStatus="counter">
							<span style="float: right;" class="btn btn-success btn-sm"
								onclick="addFun(${q.quesId},${q.userId})">Add </span>
								${counter.count}. ${q.question}
							<br>
      							 ${q.answer}<br />
							<hr>
						</c:forEach>
						<br />
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../js/bootstrap.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="../js/plugins/morris/raphael.min.js"></script>
	<script src="../js/plugins/morris/morris.min.js"></script>
	<script src="../js/plugins/morris/morris-data.js"></script>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>