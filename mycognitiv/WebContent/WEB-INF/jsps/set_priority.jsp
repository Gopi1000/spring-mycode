<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   
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
		<div id="page-wrapper" style="height: 550px;">
			<div class="container-fluid">
<div class="alert alert-success">
							
							<h4 align="center">${errorMessage}</h4>
								
						</div>
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header"><i class="fa fa-edit"></i>Set Priority</h1>
						
					</div>
				</div>

				<div class="row">
					
					<div class="col-lg-4"></div>
					<div class="col-lg-4 well">
						<form action="setPriority"
							method="post" >					
							<div class="form-group">
								<label>Set priority<font color="red"> *</font></label> <input
									class="form-control" name="priority" type="text" required="required"
									placeholder="priority" >
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

