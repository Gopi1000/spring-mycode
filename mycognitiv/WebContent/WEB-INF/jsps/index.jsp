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
	
		<!-- Navigation -->
		<div id="page-wrapper" style="padding: 5.9%;">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i>  Test Your Skill Online
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<!-- /.row -->

				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Java</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=java&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Java Script</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=javascript&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">SQL</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=sql&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Python</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=python&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">CSS</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=css&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">PHP</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=php&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">Linux</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=linux&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-book fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge" style="font-size: 30px;">MongoDB</div>
										<div>Quiz!</div>
									</div>
								</div>
							</div>
							<a href="takeExam?test=mongodb&inviteId=0">
								<div class="panel-footer">
									<span class="pull-left">Take Exam</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>
				<!-- /.row -->


				<!-- /.row -->

				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	
	<!-- /#wrapper -->
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
