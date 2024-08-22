<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<title>myCognitiv</title>
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<link rel="stylesheet" href="css/screen.css" type="text/css">
<link rel="stylesheet" href="css/site.css" type="text/css">



</head>
<jsp:include page="header.jsp"></jsp:include>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#categoryId').change(
						function(event) {
							var category = $("select#categoryId").val();
							$.get('AjaxViewSubcategory', {
								category : category
							}, function(response) {

								var select = $('#subcategoryId');
								select.find('option').remove();
								$.each(response, function(subcategoryId,
										SubcategoryBO) {
									$('<option>').val(
											SubcategoryBO.subcategoryId).text(
											SubcategoryBO.subcategory)
											.appendTo(select);

								});
							});
						});

			});
</script>
<body>
	<div id="">
		<!-- Navigation -->
		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<i class="fa fa-edit"></i>Search Questions
						</h1>
					</div>
				</div>


				<div class="row">
					<div class="col-lg-2"></div>
					<!-- <div class="col-lg-4 well"> -->
					<form action="EditQuestions" method="post">
						<div class="pi-row pi-grid-small-margins">
							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<label for="branchName" class="font-color">Category:</label> <select
											class="form-control" name="category" type="text"
											id="categoryId">
											<option value="">Select Category</option>
											<c:forEach items="${categoryList}" var="test">
												<option value="${test.categoryId}">
													${test.category}</option>

											</c:forEach>
										</select>
									</div>
								
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<label for="branchName" class="font-color">SubCategory:</label>
										<select class="form-control" name="subcategory" type="text"
											id="subcategoryId">
											<option value="">Select SubCategory</option>									
									</select>
									</div>

									
									<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="submit"
											class="btn btn-theme btn-success btn-block margintop">Search</button>
									</div>
								</div>
							</div>

						</div>
					</form>

					<c:if test="${!empty questionList}">

						<div
							class="pi-section-w pi-section-white piTooltips pi-responsive-table-sm">
							<div class="col-lg-12 well">
								<%
									if (null != session.getAttribute("questionCount")) {
											int count = (int) session.getAttribute("questionCount");
											session.setAttribute("quesCount", count);
										}
								%>
								<%  
								
								   if(null!=session.getAttribute("maxRecordRef")){
									   int maxRecordRef=(int)session.getAttribute("maxRecordRef");
								   }		
								%>

								<display:table id="data" name="${questionList}"
									requestURI="/EditQuestions" pagesize="${maxRecordRef}"
									partialList="true" size="${questionCount}" export="false"
									class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
									<display:column property="quesId" title="Question No" />
									<display:column property="question" title="Questions" />
									<display:column media="html" paramId="id"
										paramProperty="quesId" title="Edit">
										<a href="editQuestion?quesId=${data.quesId}"> <i
											style="text-align: center;" class=" fa fa-edit"></i></a>

									</display:column>

								</display:table>
							</div>
						</div>
					</c:if>

					<!-- </div> -->
				</div>
				
			</div>
		</div>
		<!-- /.row -->
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>