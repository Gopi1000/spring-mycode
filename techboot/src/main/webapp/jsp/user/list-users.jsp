<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script>

function submit()
{
	var x=document.getElementById("name");
	var y=document.getElementById("email");
	var z=document.getElementById("mobile")
	if(null!=x && x=="" && null!=y && y=="" && null!=z && z=="")
		{
		x.css({"border" : "1px solid red",});
		}	
	}
</script>

<main id="main">
<section class="container instructor-profile-block top">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="contact-form">
				<h3 class="text-center">View List of Users</h3>
			</div>

			<c:if test="${not empty successmessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${successmessage}"></c:out>
				</div>
			</c:if>
			
			<c:if test="${not empty infoMessage}">
				<div class="alert alert-info">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>info:</strong>
					<c:out value="${infoMessage}"></c:out>
				</div>
			</c:if>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>

			</c:if>

			<aside class="bg-gray aback cover">
		<!-- course search form -->
		<form:form name="myForm" method="post" commandName="login"
			action="search-user" modelAttribute="login"
			class="course-search-form">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="form-holder">
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
								
									<label class="element-block fw-normal font-lato"> <form:input
											type="text" id="name" path="name"
											placeholder="Name" class="form-control element-block"/>
									</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
									<label class="element-block fw-normal font-lato"> <form:input
											type="text" id="email" path="emailAddress"
											placeholder="Email Id" class="form-control element-block"  />
									</label>
								</div>
							</div>
						</div>
						 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<div class="form-row">
								<div class="form-group">
								
									<label class="element-block fw-normal font-lato"> <form:input
											type="text" id="mobile" path="mobileNo"	placeholder="Mobile Number"
											 class="form-control element-block" />
									</label>
								</div>
							</div>
						</div>

						<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12">
							<div class="text-center">
								<button type="submit" id="submit" class=" bac btn-warning" >Search
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</aside>

			<c:if test="${!empty loginlist.list}">
				<div class="table-wrap">
					<!-- topics data table -->
					<table class="table topics-data-table tab-full-responsive"
						style="text-align: left;">
						<thead class="bg-theme hidden-xs" style="font-size: 10px">

							<tr>
								<th style="width: 120px;">S.No:</th>
								<th>Name</th>
								<th>User Type</th>
								<th>Email Id</th>
								<th>Password</th>
								<th>Mobile Number</th>
								
								<th style="width: 71px;">View</th>
								<th style="width: 71px;">Edit</th>
								<th style="width: 72px;">Delete</th>
							</tr>
						</thead>
						<tbody>
												
						<c:forEach items="${loginlist.list}" var="listUser"
								varStatus="status">
								
						 <c:choose>
						 <c:when test="${listUser.isDelete==true}">
								<tr style="background-color: red">
									<td data-title="S:No"><c:out value="${listUser.s_No }"></c:out></td>

									<td data-title="Name"><c:out
											value="${listUser.name }"></c:out></td>

									<td data-title="userType"><c:out value="${listUser.userType}"></c:out></td>

									<td data-title="emailId"><c:out
											value="${listUser.emailAddress }"></c:out></td>
									
									<td data-title="password"><c:out
											value="${listUser.password }"></c:out></td>		
									
									<td data-title="mobileNo"><c:out
											value="${listUser.mobileNo }"></c:out></td>
											<td></td>
											<td></td>
											<td></td>													
								</tr>
							
							 </c:when>
						  <c:when test="${listUser.isActive==false && listUser.isDelete==false}">
							
								<tr style="background-color: yellow">
									<td data-title="S:No"><c:out value="${listUser.s_No }"></c:out></td>

									<td data-title="Name"><c:out
											value="${listUser.name }"></c:out></td>

									<td data-title="userType"><c:out value="${listUser.userType}"></c:out></td>

									<td data-title="emailId"><c:out
											value="${listUser.emailAddress }"></c:out></td>
											
									<td data-title="password"><c:out
											value="${listUser.password }"></c:out></td>			
											
									<td data-title="mobileNo"><c:out
											value="${listUser.mobileNo }"></c:out></td>
											
									<td data-title="View"><a
										href="view-user.html?loginId=${listUser.loginId}">
											<i class="fa fa-eye"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>		
																		
									<td data-title="Edit"><a
										href="edit-user.html?loginId=${listUser.loginId}">
											<i class="fa fa-edit"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Delete"><a
										href="delete-user.html?loginId=${listUser.loginId}">
											<i class="fa fa-trash"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>
								</tr>
							
							 </c:when> 
							 
							 <c:otherwise>
							 	<tr>
									<td data-title="S:No"><c:out value="${listUser.s_No }"></c:out></td>

									<td data-title="Name"><c:out
											value="${listUser.name }"></c:out></td>

									<td data-title="userType"><c:out value="${listUser.userType}"></c:out></td>

									<td data-title="emailId"><c:out
											value="${listUser.emailAddress }"></c:out></td>
											
									<td data-title="password"><c:out
											value="${listUser.password }"></c:out></td>			
											
									<td data-title="mobileNo"><c:out
											value="${listUser.mobileNo }"></c:out></td>
											
									<td data-title="View"><a
										href="view-user.html?loginId=${listUser.loginId}">
											<i class="fa fa-eye"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>		

									<td data-title="Edit"><a
										href="edit-user.html?loginId=${listUser.loginId}">
											<i class="fa fa-edit"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>

									<td data-title="Delete"><a
										href="delete-user.html?loginId=${listUser.loginId}">
											<i class="fa fa-trash"
											style="margin-left: 10px; color: #ffc000;"></i>
									</a></td>
								</tr>
												 
							 </c:otherwise>
													 
							</c:choose>
							 </c:forEach>
						</tbody>
					</table>
				</div>

				<nav style="text-align: center;">
					<ul class="pagination pagination-theme  no-margin center"
						style="margin-left: 575px;">
						<c:if test="${loginlist.currentPage gt 1}">
							<li><a href="list-users?page=1"><span><i
										class="fa fa-angle-double-left" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="list-users?page=${loginlist.currentPage - 1}"><span><i
										class="fa fa-angle-left" aria-hidden="true"></i> </span></a></li>
						</c:if>
						<c:forEach items="${loginlist.noOfPages}" var="i">
							<c:choose>
								<c:when test="${loginlist.currentPage == i}">
									<li class="active"><a
										style="color: #fff; background-color: #34495e">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="list-users?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${loginlist.currentPage lt loginlist.totalPages}">
							<li><a
								href="list-users?page=${loginlist.currentPage + 1}"><span><i
										class="fa fa-angle-right" aria-hidden="true"></i> </span></a></li>
							<li><a
								href="list-users?page=${loginlist.lastRecordValue}"><span><i
										class="fa fa-angle-double-right" aria-hidden="true"></i> </span></a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
</section>
</main>