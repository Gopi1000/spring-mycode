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
<body>
	<div id="">
		<!-- Navigation -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<i class="fa fa-edit"></i>View Answer
						</h1>
					</div>
				</div>
				<c:if test="${!empty infoMesseage}">
					<div class="alert alert-success" style="font-size: 100px;">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<h4 align="center">${infoMesseage}</h4>
					</div>
				</c:if>
				<div class="row">
					<div class="col-lg-4 well">
						<form action="ViewTextAnswer" method="post">
							<div class="form-group">
								<label>Candidate<font color="red"> *</font></label><select
									name="userName" class="form-control" type="text"
									required="required">
									<option value="">Select Candidate</option>
									<c:forEach items="${userList}" var="user">
										<option value="${user.userId}">${user.userName}</option>
									</c:forEach>
								</select>
							</div>

							<button type="submit" class="btn btn-success">Search</button>
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
