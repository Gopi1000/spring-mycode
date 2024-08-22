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
<jsp:include page="professor_menu.jsp"></jsp:include>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#categoryId').change(function(event) {
	        var category = $("select#categoryId").val();
	        $.get('AjaxViewSubcategory', {
	        	category : category
	        }, function(response) {

	        var select = $('#subcategoryId');
	        select.find('option').remove();
	          $.each(response, function(subcategoryId, SubcategoryBO) {
	        	  $('<option>').val(SubcategoryBO.subcategoryId).text(SubcategoryBO.subcategory).appendTo(select);
	         
	      });
	        });
	        });
	
});


</script>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<i class="fa fa-edit"></i>Edit Questions
						</h1>

					</div>
				</div>


				<div class="row">

					<div class="col-lg-2"></div>
					<!-- <div class="col-lg-4 well"> -->
						<form action="ProfessorEditquestion" method="post">
						<div class="pi-row pi-grid-small-margins">
							<div class="form-group">
							
							<div class="row clearfix">
								<div class="col-xs-3">
								<label for="branchName" class="font-color">Category:</label>
									<select class="form-control" name="category"  id="categoryId">
									<option value="">Select Category</option>
									 <c:forEach items="${categoryList}" var="test">
										<option value="${test.categoryId}">
											${test.category}</option> 
											
									</c:forEach> 
								</select>
								</div>
								
								<div class="col-xs-3">
								<label for="branchName" class="font-color">SubCategory:</label>
									<select class="form-control" name="subcategory" id="subcategoryId">
									<option value="">Select SubCategory</option>
									 <%-- <c:forEach items="${subcategoryList}" var="data">
										<option value="${data.subcategoryId}">
											${data.subcategory}</option> 
											
									</c:forEach>  --%>
								</select>
								</div>

                                <br>
								<div class="col-xs-2">
									<button type="submit"
										class="btn btn-theme btn-success btn-block">Search</button>
								</div>
							</div>
						</div>
							
</div>
						</form>
                       
						<c:if test="${!empty questionList}">
						
						 <div class="col-lg-10 well"> 
						 
							<display:table id="data" name="${questionList}"
							requestURI="/ProfessorEditquestion" pagesize="5"
							export="false"
							class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders">
								<display:column property="quesId" title="Question No"
									sortable="true" />
								<display:column property="question" title="Questions"
									sortable="true" />
								<c:set var="indexVal" value="0"></c:set>
								
								 <display:column property="questionOptions[${indexVal}] " title="Options"
									sortable="true" />
									
								<display:column property="answer" title="Answers"
									sortable="true" /> 
										
									<display:column url="professor_editquestions" media="html"
								paramId="id" paramProperty="quesId" title="Edit">
								<a href="professor_editQuestion?quesId=${data.quesId}"> <i
									style="text-align: center;" class=" fa fa-edit" style="color:gray;"></i></a>
                          
							</display:column>
									
							</display:table>
							</div>
						</c:if>
						
					<!-- </div> -->
				</div>
				<%-- <c:if test="${not empty questionList}">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-money fa-fw"></i> Available Test Details
									</h3>
								</div>
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th>Ser.No</th>
													<th>Test Name</th>
													<th>Test Level</th>
													<th>Created Date</th>
												</tr>
											</thead>

											<c:forEach items="${questionList}" var="test">
												<tbody>
													<tr>
														<td>${test.quesId}</td>
														<td style="text-transform: capitalize;">${test.question}</td>
														<td>${test.questionOptions}</td>
														<td>${test.answer}</td>
													</tr>

												</tbody>
											</c:forEach>
										</table>

									</div>

								</div>
							</div>
						</div>
					</div>
				</c:if> --%>

			</div>
		</div>
		<!-- /.row -->
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
