<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>
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
						<h1 class="page-header" align="left""> <i class="fa fa-user"></i> Companies</h1>
					</div>
				</div>
				<c:if test="${!empty errorMessage}">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<h4 align="center">${errorMessage}</h4>
				</c:if>
				<c:if test="${!empty companyBOList}">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-list fa-fw"></i> View Companies
									</h3>
								</div>

								<display:table id="data" name="${companyBOList}"
									requestURI="/view_companies" pagesize="${maxRecordRef}"
									partialList="true" size="${companyCount}" export="false"
									class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
									<display:column property="serialNo" title="S.No" maxLength="5" />
									<display:column property="companyName" title="Company Name"
										maxLength="250" />
									<display:column property="companyEmail" title="Email" maxLength="250" />
									<display:column property="companyContactNo" title="Phone No"
										maxLength="250" />
									<display:column property="companyLocation" title="Location" maxLength="250" />	
									<%-- <display:column media="html" paramId="id"
										paramProperty="userId" title="Edit" maxLength="250">
										<a href="admin_edit_candidate?userId=${data.userId}"> <i
											style="text-align: center;" class=" fa fa-edit"></i>
										</a>
									</display:column>--%>
									<display:column media="html" paramId="id"
										paramProperty="companyRegId" title="Delete" maxLength="250">
										<a
											href="${pageContext.request.contextPath}/delete_company?companyId=${data.companyRegId}&email=${data.companyEmail}">
											<i style="text-align: center;" class="fa fa-trash-o"></i>
										</a>
									</display:column> 

								</display:table>

							</div>
						</div>
					</div>
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
