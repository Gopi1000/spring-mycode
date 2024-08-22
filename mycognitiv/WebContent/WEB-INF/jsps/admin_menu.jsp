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
<ul class="nav navbar-nav side-nav" style="background-color: gray;">
	<div class="block-section">
		<div class="row">
			<div class="panel-group no-margin" id="accordion">
				<!-- profile picture section -->


				<div class="panel panel-default" style="margin-top: -1px;">
					<div class="panel-heading" style="background-color: grey;">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseOne"
								aria-controls="collapseOne">
								<h4 class=" no-margin with-ic" style="margin-top: 0px; margin-bottom: 0px;">
									<i class="fa fa-calendar-o" style="color:#FFFFFF"
										style="font-size: 16px; margin-top: 0px; margin-bottom: 0px;"></i>
									<label style="color:#FFFFFF">Category</label>
								</h4>
								<div class="collapse-toogle"></div>
							</a>
						</div>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in" style="background-color: grey;">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">
								<li ><a
									href='${pageContext.request.contextPath}/create_category' style="color: #fff;">
										<i class="fa fa-pencil fa-fw"
										style="font-size: 16px;"></i>&nbsp;Create Category
								</a></li>
								<li ><a
									href='${pageContext.request.contextPath}/view_category' style="color: #fff;"> <i class="fa fa-list fa-fw"
										style="font-size: 16px;"></i>&nbsp;View Category
								</a></li>



							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="margin-top: -1px;">
					<div class="panel-heading" style="background-color: grey;">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseFour"
								aria-controls="collapseFour">
								<h4 class=" no-margin with-ic" style="margin-top: 0px; margin-bottom: 0px;">
									<i class="fa fa-calendar-o" style="color:#FFFFFF"
										style="font-size: 16px;"></i>
									<label style="color:#FFFFFF">Sub Category</label>
								</h4>
								<div class="collapse-toogle"></div>

							</a>
						</div>
					</div>
					<div id="collapseFour" class="panel-collapse collapse in" style="background-color: grey;">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">

								<li style="color: #fff;"><a
									href='${pageContext.request.contextPath}/create_subcategory' style="color: #fff;"><i class="fa fa-pencil fa-fw"
										style="font-size: 16px;"></i>&nbsp;Create SubCategory</a></li>
								<li style="color: #fff;" ><a
									href='${pageContext.request.contextPath}/view_subcategory' style="color: #fff;"><i class="fa fa-list fa-fw"
										style="font-size: 16px;"></i>&nbsp;View SubCategory</a></li>
								<li style="color: #fff;"><a
									href='${pageContext.request.contextPath}/edit_priority' style="color: #fff;"><i class="fa fa-edit fa-fw"
										style="font-size: 16px;"></i>&nbsp;Edit Priority</a></li>

							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="margin-top: -1px;">
					<div class="panel-heading" style="background-color: grey;">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseFour"
								aria-controls="collapseFour">
								<h4 class=" no-margin with-ic" style="margin-top: 0px; margin-bottom: 0px;">
									<i class="fa fa-file-text-o" style="color:#FFFFFF"
										style="font-size: 16px;"></i>
									<label style="color:#FFFFFF">Question</label>
								</h4>
								<div class="collapse-toogle"></div>

							</a>
						</div>
					</div>
					<div id="collapseFour" class="panel-collapse collapse in" style="background-color: grey;">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">

								<li ><a
									href='${pageContext.request.contextPath}/upload_questions' style="color: #fff;"><i class="fa fa-upload fa-fw"
										style="font-size: 16px;"></i>&nbsp;Upload Question</a></li>
								<li ><a
									href='${pageContext.request.contextPath}/edit_questions' style="color: #fff;"><i class="fa fa-edit fa-fw"
										style="font-size: 16px;"></i>&nbsp;Edit Question</a></li>

							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="margin-top: -1px;">
					<div class="panel-heading" style="background-color: grey;">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseFour"
								aria-controls="collapseFour">
								<h4 class=" no-margin with-ic" style="margin-top: 0px; margin-bottom: 0px;">
									<i class="fa fa-stack-overflow" style="color:#FFFFFF"
										style="font-size: 20px;"></i><label style="color:#FFFFFF">Answer</label>
								</h4>
								<div class="collapse-toogle"></div>

							</a>
						</div>
					</div>
					<div id="collapseFour" class="panel-collapse collapse in" style="background-color: grey;">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">

								<li><a
									href='${pageContext.request.contextPath}/view_textanswer'  style="color: #fff;"><i class="fa fa-list fa-fw"
										style="font-size: 16px;"></i>&nbsp;view Answer</a></li>
							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="margin-top: -1px;">
					<div class="panel-heading" style="background-color: grey;">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseFour"
								aria-controls="collapseFour">
								<h4 class=" no-margin with-ic" style="margin-top: 0px; margin-bottom: 0px;">
									<i class="fa fa-users fa-1x" style="color:#FFFFFF"
										style="font-size: 20px;"></i><label style="color:#FFFFFF">Candidate</label>
								</h4>
								<div class="collapse-toogle"></div>

							</a>
						</div>
					</div>
					<div id="collapseFour" class="panel-collapse collapse in" style="background-color: grey;">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">
							
							<li ><a
									href='${pageContext.request.contextPath}/create_candidate' style="color: #fff;">
										<i class="fa fa-pencil fa-fw"
										style="font-size: 16px;"></i>&nbsp;create Candidate
								</a></li>

								<li><a
									href='${pageContext.request.contextPath}/view_candidates'  style="color: #fff;"><i class="fa fa-list fa-fw"
										style="font-size: 16px;"></i>&nbsp; view Candidates</a></li>
							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="margin-top: -1px;">
					<div class="panel-heading" style="background-color: grey;">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseFour"
								aria-controls="collapseFour">
								<h4 class=" no-margin with-ic" style="margin-top: 0px; margin-bottom: 0px;">
									<i class="fa fa-pencil fa-1x" style="color:#FFFFFF"
										style="font-size: 20px;"></i><label style="color:#FFFFFF">Test</label>
								</h4>
								<div class="collapse-toogle"></div>

							</a>
						</div>
					</div>
					<div id="collapseFour" class="panel-collapse collapse in" style="background-color: grey;">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">
							
							<li ><a
									href='${pageContext.request.contextPath}/admin_create_test' style="color: #fff;">
										<i class="fa fa-pencil fa-fw"
										style="font-size: 16px;"></i>&nbsp;create Test
								</a></li>

								<li><a
									href='${pageContext.request.contextPath}/view_admin_test'  style="color: #fff;"><i class="fa fa-list fa-fw"
										style="font-size: 16px;"></i>&nbsp; view Test</a></li>
							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</ul>
<div class="gradient"></div>

</html>