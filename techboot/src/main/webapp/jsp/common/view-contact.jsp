<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<main id="main">
<section class="container instructor-profile-block">
	<div class="row">
	<div class="col-sm-12 col-xs-12">
		<div class="contact-form">
				<h3 class="text-center">View List of Contact</h3>
			</div>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Success:</strong>
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${!empty contactlists}">
					<div class="table-wrap">
			<!-- topics data table -->
			<table class="table topics-data-table tab-full-responsive">
				<thead class="bg-theme  hidden-xs">
           
					<tr>
						<th>S.No</th>
						<th>Your Name</th>
						<th>Email Address</th>
						<th>Contact Number</th>
						<th>View</th>
						<th style="width: 72px;">Delete</th>
						<th>Edit</th>
					</tr>

				</thead>
				<tbody>
				  <c:set var="sNo" value="${0}"></c:set>
					<c:forEach items="${contactlists}" var="contactlist"
						varStatus="status">
						<tr>
                  <c:set var="sNo" value="${sNo+1}"></c:set>
							<td data-title="S.No"><c:out value="${sNo}"></c:out></td>

							<td data-title="Your Name"><c:out
									value="${contactlist.yourName }"></c:out></td>
							<td data-title="Email Address"><c:out
									value="${contactlist.emailAddress }"></c:out></td>

							<td data-title="Message"><c:out
									value="${contactlist.contactNumber }"></c:out></td>
									
							<td data-title="view"><a
							href="view-contact-details.html?contactId=${contactlist.contactId}">
							<i class="fa fa-eye" style="margin-left: 10px;color: #ffc000;"></i>
									</a></td>
									<td><a href="deletecontact?contactId=${contactlist.contactId}">delete</a></td>	
									<td><a href="editcontact?contactId=${contactlist.contactId}">edit</a></td>
	
                           </tr>
					</c:forEach>


				</tbody>
			</table>


		</div>
		      </c:if>
	      </div>
     </div>
</section>
</main>