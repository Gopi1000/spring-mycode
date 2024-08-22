<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
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
		<div id="page-wrapper" style="height: 770px;">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<i class="fa fa-edit"></i> ViewTest
						</h1>

					</div>
				</div>
				<c:if test="${!empty requestScope.errorMessage}">
					<div class="alert alert-success" style="font-size: 100px;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<h4 align="center">${requestScope.errorMessage}</h4>
					</div>
				</c:if>
				<c:if test="${!empty infoMessage}">
				<div class="alert alert-success" style="font-size: 100px;">
					 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a> 
					 <h4 align="center">${infoMessage}</h4>
					 </div>
				</c:if>
				<c:if test="${empty infoMessage}">
					<c:if test="${!empty adminAssignedTestList}">
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">
											<i class="fa fa-list fa-fw"></i> View Tests
										</h3>
									</div>

									<display:table id="data" name="${adminAssignedTestList}"
										requestURI="/view_admin_test" pagesize="${maxRecordRef}"
										partialList="true" size="${testCount}" export="false"
										class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
										<%-- <display:column property="serialNo" title="Test Id" maxLength="5" /> --%>
										<display:column property="sNo" title="S.NO" maxLength="250" />
										<display:column property="category" title="Category"
											maxLength="250" />
										<display:column property="subcategory" title="SubCategory"
											maxLength="250" />
										<c:if test="${userType eq 'Admin'}">
											<display:column media="html" paramId="id"
												paramProperty="testId" title="Edit" maxLength="250">
												<a href="editTest?testId=${data.testId}"> <i
													style="text-align: center;" class=" fa fa-pencil"></i></a>
											</display:column>
										</c:if>
										<c:if test="${!empty userId }">
											<display:column media="html" paramId="id"
												paramProperty="testId" title="Launch" maxLength="250">
												<a
													href='${pageContext.request.contextPath}/takeTestCategory?testId=${data.testId}'><i
													class="fa fa-rocket"></i></a>
											</display:column>
										</c:if>
										<c:if test="${userType eq 'Admin'}">
											<display:column media="html" paramId="id"
												paramProperty="testId" title="Delete" maxLength="250">
												<a href="deleteTest?testId=${data.testId}"> <i
													style="text-align: center;" class=" fa fa-trash"></i></a>
											</display:column>
										</c:if>
									</display:table>

								</div>
							</div>
						</div>
					</c:if>
				</c:if>
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
