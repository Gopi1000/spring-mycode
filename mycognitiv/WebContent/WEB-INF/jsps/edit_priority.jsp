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
		<div id="page-wrapper" style="height: 770px;">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header"><i class="fa fa-edit fa-fw"></i>Edit Priority</h1>
						
					</div>
				</div>
				<c:if test="${!empty errorMessage}">
				<div class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<h4 align="center">${errorMessage}</h4>
				</div>
				</c:if>
				<c:if test="${!empty subcategoryList}">
				<div class="row">
					<div class="col-lg-10">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-edit fa-fw"></i> Edit Priority
								</h3>
							</div>
							
							<div class="panel-body">
								<div class="table-responsive">
								<form action="EditPriority" method="post">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr>
											   <!-- <th>Sr.No</th> -->
												<!-- <th>Category</th> -->
												<th>SubCategory</th>
												<th>Priority</th>
											</tr>
										</thead>
										 <c:set var="indexval" value="0"></c:set> 
										<c:forEach items="${subcategoryList}" var="subcategory">
										 <c:set var="indexval" value="${indexval+1}"></c:set>
										<tbody>
											<tr>	
											<%-- <td>${subcategory.subcategoryId}</td> --%>
											<%-- <td>${subcategory.category}</td> --%>
											<td><input type="text" name="subcategory${indexval}" value="${subcategory.subcategory}"/></td>	
												<td> 
												<input type="text" name="priority${indexval}" value="${ subcategory.priority}"/>  
                                                </td>
											</tr>
										</tbody>
										</c:forEach>
										<input type="hidden" name="listsize" value="${indexval}"/>
									</table>
									<div class="col-xs-3" style="float: right;">
									 <button type="submit" 
										class="btn btn-theme btn-success btn-block" >Update</button>
										
								</div>
								
								</form>
								<a href='${pageContext.request.contextPath}/view_category'
								class="btn btn-danger">Cancel</a>
									<!-- <button onclick="history.back(-1)" value="back" style="float: left;">Back</button> -->
								</div>
							</div>
							
						</div>
					</div>
				</div></c:if>
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
