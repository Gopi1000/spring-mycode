<%@page import="com.scube.mycognitiv.bo.CategoryBO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
<link href="styles/displaytag.css" rel="stylesheet" type="text/css" />
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
							<i class="fa fa-edit"></i>Category List
						</h1>

					</div>
				</div>

				<c:if test="${!empty requestScope.errorMessage}">
					<div class="alert alert-success" style="font-size: 100px;">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<h4 align="center">${requestScope.errorMessage}</h4>

					</div>
				</c:if>

				<c:if test="${!empty infoMesseage}">
					<div class="alert alert-success" style="font-size: 100px;">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<h4 align="center">${infoMesseage}</h4>
					</div>
				</c:if>
				<div class="row">
					<c:if test="${!empty categoryList}">
						<div
							class="pi-section-w pi-section-white piTooltips pi-responsive-table-sm">
							<div class="col-lg-12 well">
								<display:table id="data" name="${categoryList}"
									requestURI="/view_category" pagesize="${maxRecordRef}"
									partialList="true" size="${categoryCount}" export="false"
									class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
									<%-- <display:column property="categoryId" title="Category Id" /> --%>
									<display:column property="category" title="Category" />
									<display:column media="html" paramId="id"
										paramProperty="subcategoryId" title="Edit" maxLength="250">
										<a
											href="${pageContext.request.contextPath}/EditCategory?categoryId=${data.categoryId}">
											<i style="text-align: center;" class=" fa fa-edit"></i>
										</a>
									</display:column>
									<display:column media="html" paramId="id"
										paramProperty="subcategoryId" title="Edit Priority"
										maxLength="250">
										<a
											href="${pageContext.request.contextPath}/edit_priority?categoryId=${data.categoryId}">
											<i style="text-align: center;" class=" fa fa-edit"></i>
										</a>
									</display:column>
									<display:column media="html" paramId="id"
										paramProperty="subcategoryId" title="Delete" maxLength="250">
										<a
											href="${pageContext.request.contextPath}/DeleteCategory?categoryId=${data.categoryId}">
											<i style="text-align: center;" class=" fa fa-trash"></i>
										</a>
									</display:column>
								</display:table>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
