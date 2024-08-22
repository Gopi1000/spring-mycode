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
<%-- <jsp:include page="menu.jsp"></jsp:include> --%>
<body>
	<div id="wrapper">
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-8">
						<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<h3>
								Congratulation ${sessionScope.user} your have successfully completed your skill Assessment, You will receive your certification details via email!.
								
							</h3>
						</div>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->
		</div>

	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
