<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<main id="main">
<section class="container instructor-profile-block">

	<div class="contact-form">
		<h3 class="text-center">Question Details</h3>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
			style="border: 1px solid #e6e6e6;">
			<br>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-industry"> </i>&nbsp;&nbsp;
								Question</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${Questionlist.question}</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-user-circle-o">&nbsp;&nbsp; </i>QuestionType
							</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${Questionlist.questionType}</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-envelope"> </i>&nbsp;&nbsp;category
								</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${Questionlist.subCategoryBO.categoryBO.category}</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label><i class="fa fa-envelope"> </i>&nbsp;&nbsp;subCategory
								</label>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<span>:&nbsp;&nbsp;&nbsp;
								${Questionlist.subCategoryBO.subcategory}</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="text-right">
		<input type="button" value="Back"
			class="btn btn-theme btn-warning text-uppercase font-lato fw-bold"
			Style="min-width: 100px" onclick="history.go(-1)">
	</div>
</section>
</main>