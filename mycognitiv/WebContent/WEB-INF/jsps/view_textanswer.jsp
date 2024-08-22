<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Display tag Pagination and Sorting Example in JSP</title>
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<link rel="stylesheet" href="css/screen.css" type="text/css">
<link rel="stylesheet" href="css/site.css" type="text/css">



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
							<i class="fa fa-edit"></i>View Answer
						</h1>

					</div>
				</div>


				<div class="row">

					<div class="col-lg-4 well">
						<form action="ViewTextAnswer" method="post">
							<div class="pi-row pi-grid-small-margins">
								<div class="form-group">

									<div class="row clearfix">
										<div class="col-xs-4">
											<label for="branchName" class="font-color">Candidate:</label>
											<select class="form-control" name="userName" type="text"
												required placeholder="userName" id="testNameId">
												<option value="">Select Candidate</option>
												<c:forEach items="${userList}" var="user">
													<option value="${user.userId}">${user.userName}</option>

												</c:forEach>
											</select>
										</div>



										<br>
										<div class="col-xs-3">
											<button type="submit"
												class="btn btn-theme btn-success btn-block">Search</button>
										</div>
									</div>
								</div>

							</div>
						</form>
					</div>
					<c:if test="${!empty answerList}">

						<div class="col-lg-12 well">
							<display:table id="data" name="${answerList}"
								requestURI="/ViewTextAnswer" pagesize="1" export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
								<display:column property="question" title="Questions"
									sortable="true" />
								<br />
								<display:column property="description" title="Answer"
									sortable="true"></display:column>
							</display:table>
						</div>
				</div>
				</c:if>


			</div>


		</div>
	</div>
	<!-- /.row -->
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
