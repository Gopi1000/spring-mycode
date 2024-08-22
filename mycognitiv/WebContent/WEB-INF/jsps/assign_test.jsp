<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="header.jsp"></jsp:include>

<body>
	<div id="">

		<!-- Navigation -->
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">

				<!-- Page Heading -->

				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<i class="fa fa-edit"></i>AssignTest
						</h1>

					</div>
				</div>
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 align="center">${errorMessage}</h4>

					</div>
				</c:if>

				<div class="row">

					<div class="col-lg-4"></div>
					<div class="col-lg-4 well">
						<form action="assignTest" method="post">
							<div class="form-group">
								<label>Select Test<font color="red"> *</font></label><select
									name="category" class="form-control" type="text"
									required="required">
									<option value="">Select Test</option>
									<c:forEach items="${categoryList}" var="category">
										<option value="${category.testId}">
											${category.testName}</option>
									</c:forEach>
								</select>
							</div>

							<button type="submit" class="btn btn-success">Save</button>
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