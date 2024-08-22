<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>

<script>
	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline', 'ol',
					'ul', 'strikeThrough', 'html' ]
		}).panelInstance('inputAddress');
	});
</script>

<!-- <script type="text/javascript">
	$(document).ready(function() {
		var select = '${sgst}';
		$('#sgst_Id').val(select);

	});
</script> -->

<script>

function staffValueId(staff_Id) {	
		$.ajax({
			type : "GET",
			url : 'getStaffDetails',
			data : 'staffId=' + $("#staff_Id").val(),
			success : function(ProductServiceBO) {
				$("#staffName").val(ProductServiceBO.fees);
						
			},		
	});
};
</script>





<script>

function quantityValueId(quantity_Id) {	
	
	
		$.ajax({
			type : "GET",
			url : 'getTotalDetails',
			data : 'quantityId=' + $("#quantity_Id").val(),
			success : function(SalesOrderBO) {
				$("#total_Id").val(SalesOrderBO.totalPrice);
						
			},		
	});
};
</script>



<script>

function grandtotalValue() {	
	
	
		$.ajax({
			type : "GET",
			url : 'getGrandTotalDetails',
			data : 'quantityId=' + $("#quantity_Id").val(),
			success : function(SalesOrderBO) {
				$("#grandtotalId").val(SalesOrderBO.grandTotal);
				$("#sgst_Id").val(SalesOrderBO.gstBO.sgst);
				$("#cgst_Id").val(SalesOrderBO.gstBO.cgst);
				$("#totalInvoiceId").val(SalesOrderBO.totalInvoice);		
			},		
	});
};
</script>

<script>

function addAgreementDetails(){
	var isValid = false;	
	var allowance = $("#staff_Id").val();
	if(allowance == ''){
		
		$("#staff_Id").css({
			"border":"1px solid red",
		});
		var isValid = false;
		}
	else{
		
		$("#staff_Id").css({
			"border":"",
			"background":""
		});
		isValid = true;
	}
	
	var amount = $("#staffName").val();
	if(amount == ''){
		
		$("#staffName").css({
			"border":"1px solid red",
		});
		var isValid = false;
	}
	else{
		 
		$("#staffName").css({
			"border":"",
			"background":""
		});
		isValid = true;
	}
	
	/* var taxable = $("#quantity_Id").val();
	if(taxable == ''){
		 
		$("#quantity_Id").css({
			"border":"1px solid red",
		});
		var isValid = false;
	}
	else{
		
		$("#quantity_Id").css({
			"border":"",
			"background":""
		});
		isValid = true;
	} */
	if(isValid==false)		
		e.preventDefault();	
	 var staffName = $("#staffName").val();
	 var staff_Id = $("#staff_Id").val();
	 var quantity_Id = $("#quantity_Id").val();	
	 var total_Id = $("#total_Id").val();			
			$ .ajax({				
				type:"GET",				
				url:'addAgreement',				
				data:'product='
				+staff_Id
				+'&price='
				+staffName
				+'&quantityId='
				+quantity_Id
				+'&totalId='
				+total_Id,					
				success:function(agreementList){			
					var len = agreementList.length;						
				 /* html = '<br/><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
						+'<div class="cs-shortcode-table">'			
						+'<table>'				
						+'<thead>'
						+'<tr>'									
						+ '<th >S.NO</th>'
						+ '<th>product</th>'
						+ '<th >Amount</th>'
						+ '<th>quantity_Id</th>'
						+ '<th>Total</th>'					
						+ '</tr></thead><tbody>'; */



						html = '<br/><div class="pi-responsive-table-sm">'
							+ '<table style="border: 1px solid; width:100%;" >'
							+ '<thead style="background-color: #2a3f54" >'
							+ '<tr>'
							+ '<th style="text-align: center; color: #fff;">S.NO</th>'
							+ '<th style="text-align: center; color: #fff;">Product</th>'
							+ '<th style="text-align: center; color: #fff;">Amount</th>'
							+ '<th style="text-align: center; color: #fff;">Quantity</th>'
							+ '<th style="text-align: center; color: #fff;">Total</th>'
							+ '</tr></thead><tbody>';
			
						for ( var i = 0; i < len; i++) {
							html = html
									+ '<tr>'
									+ '<td class="td-border list-capitalize; style="text-align: center;">'
									+ (i + 1)
									+ '</td>'
									+ '<td class="td-border list-capitalize">'
									+ agreementList[i].product.serviceName
									+ '</td>'
									+ '<td class="td-border list-capitalize">'
									+ agreementList[i].price
									+ '</td>'
									+ '<td class="td-border list-capitalize">'
									+ agreementList[i].quantity
									+ '</td>'
									+ '<td class="td-border list-capitalize">'
									+ agreementList[i].totalPrice
									+ '</tr>';
						}
				   html = html + '</tbody></table></div></div>';
				    $("#addAgreement").html(html);
					$("#staff_Id").val("");
					$("#staffName").val("");
					$("#quantity_Id").val("");	 	
					$("#total_Id").val("");
				} 
				
			});
		};
		


</script>



<div class="row scrollspy-sidenav pb-20 body-mt-15">
	<script>
		$(document).ready(function() {
			$('#btnsubmit').click(function(e) {
				var isValid = true;
				$('input[type="text"].required').each(function() {
					if ($.trim($(this).val()) == '') {
						isValid = false;
						$(this).css({
							"border" : "1px solid red",
						});
					} else {
						$(this).css({
							"border" : "",
							"background" : ""
						});
					}

					if (isValid == false)
						e.preventDefault();

				});
			});
		});
	</script>

	<script>
		$(document).ready(function() {
			$('#btnsubmit').click(function(e) {

				var isValid = true;
				var firstname = $("#productNameInput").val();
				if (firstname == '') {

					$("#productNameInput").css({
						"border" : "1px solid red",
					});
					isValid = false;
				} else if (!/^[a-zA-Z\s]*$/g.test(firstname)) {
					$("#productNameErr").show();
					$("#productNameErr")("Please Enter Character Only");
					isValid = false;
				} else {
					$("#productNameErr").hide();
					$("#productNameInput").css({
						"border" : "",
						"background" : ""
					});
				}
				if (isValid1 == false)
					e.preventDefault();
			});
		});
	</script>








	<div class="warning">

		<c:if test="${not empty successMessage}">
			<div class="alert alert-info" role="alert"
				style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>success!</strong>
				<c:out value="${successMessage}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-info" role="alert"
				style="font-size: 12px; padding: 8px 9px 5px 10px; margin-top: 15px;">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Info!</strong>
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
	</div>

	<div class="contact-form-wrapper">

		<div class="box-list">
			<div class="item">
				<div class="row ">
					<div class="text-center underline">
						<h3>Create Sales Order</h3>

					</div>
					<br>
					<%-- <c:if test="${!empty listClients}"> --%> 
             <div class="row ">
				<form:form id="myForm" method="post" class="login-form clearfix"
					action="search-customer" commandName="salesOrderBO">
					<div class="row"
					style="border: 4px solid #e6e6e6; margin:15px 15px 15px 15px; background-color: #e1e1e1">
												<input type="hidden" value="true" name="search" />
                                              <div class="col-sm-4">
								<div class="form-group">
									<label>Customer Name<span class="font10 text-danger">*</span></label>
									<form:select type="text" path="clientBO.firstName"
										class="form-control required">
									<form:option value="${firstname}">${firstname}</form:option>
										<form:options items="${listClients}"  itemLabel="firstName" 
											itemValue="id" /> 
									</form:select>
								</div>
							</div> 

						

						<div class=" col-md-1" style="padding-bottom: 0px;">
							<div class="form-group home-right">
								<label class="hidden-xs">&nbsp;</label>
								<button class="btn btn-theme btn-success btn-block"
									style="padding: 6px 5px; background-color: #7cb228; border-color: #7cb228;">
									<small><i class="fa fa-search" aria-hidden="true"
										style="font-size: 20px;"></i></small>
								</button>
							</div>
						</div>

					</div>
				</form:form>
				</div>
			<%-- 	</c:if> --%>
				
				
				
					<form:form method="POST" id="addForm" action="create-sales-order"
						modelAttribute="salesOrderBO">
						<div class="row">
						<div class="col-sm-3">
								<div class="form-group">
									<label> Sales Order No<span class="font10 text-danger">*</span></label>
									<form:input id="productNameInputt" type="text"
										path="salesOrderNo" class="form-control required"
								placeholder="Sales Order No" maxlength="150" value="${salesno}" readonly="true"/>
									<%-- <form:errors path="salesOrderNo cssClass="error" /> --%>
									<div id="productNameErrr" style="color: red;"></div>
								</div>
							</div>
							</div>
						<div class="row">
							<div class="col-sm-3">
								<div class="form-group">
									<label> Customer Name <span class="font10 text-danger">*</span></label>
									<form:input id="productNameInput" type="text" readonly="true"
										path="clientBO.firstName" class="form-control required"
										placeholder="First Name" maxlength="150" />
									<form:errors path="clientBO.firstName" cssClass="error" />
									<div id="productNameErr" style="color: red;"></div>
								</div>
							</div>
							
<div class="col-sm-3">
								<div class="form-group">
									<label> Company Name<span class="font10 text-danger">*</span></label>
									<form:input id="productNameInput" type="text" readonly="true"
										path="clientBO.companyName" class="form-control required"
										placeholder="Company Name" maxlength="150" />
									<form:errors path="clientBO.companyName" cssClass="error" />
									<div id="productNameErr" style="color: red;"></div>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<label>Industry Type <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="clientBO.industryType" readonly="true"
										class="form-control required" placeholder="Industry Type" />
									<form:errors path="clientBO.industryType" class="input_error" />
								</div>
							</div>

							
							


							<div class="col-sm-3">
								<div class="form-group">
									<label>Mobile No<span class="font10 text-danger">*</span></label>
									<form:input type="text" path="clientBO.mobileNo" readonly="true"
										class="form-control required" placeholder="Mobile No" />
									<form:errors path="clientBO.mobileNo" class="input_error" />
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-3">
								<div class="form-group">
									<label>Status <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="clientBO.status" readonly="true"
										class="form-control required" placeholder="Status" />
									<form:errors path="clientBO.status" class="input_error" />
								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<label>Email <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="clientBO.emailAddress" readonly="true"
										class="form-control required" placeholder="Email" />
									<form:errors path="clientBO.emailAddress" class="input_error" />
								</div>
							</div>



							<div class="col-sm-3">
								<div class="form-group">
									<label>Website <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="clientBO.website" readonly="true"
										class="form-control required" placeholder="Website" />
									<form:errors path="clientBO.website" class="input_error" />
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Address <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="clientBO.address" readonly="true"
										class="form-control required" placeholder="Address" />
									<form:errors path="clientBO.address" class="input_error" />
								</div>
							</div>
						</div>
						<div class="row">
							
							<div class="col-sm-3">
								<div class="form-group">
									<label>Product<span class="font10 text-danger">*</span></label>
									<form:select type="text" path="product.serviceName" id="staff_Id"
															onchange="staffValueId(staff_Id)"
										class="form-control required">
										<form:option value="Select">-- Select --</form:option>
										<form:options items="${productBOList}" itemLabel="serviceName" 
											itemValue="serviceId" />

									</form:select>
								</div>
							</div> 

							<div class="col-sm-3">
								<div class="form-group">
									<label>Price <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="price" id="staffName"
										class="form-control required" placeholder="Price" />
									<form:errors path="price" class="input_error" />
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label>Quantity <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="quantity" id="quantity_Id" onchange="quantityValueId(quantity_Id)"
										class="form-control required" placeholder="Quantity" />
									<form:errors path="quantity" class="input_error" />
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label>Total <span class="font10 text-danger">*</span></label>
									<form:input type="text" path="totalPrice" id="total_Id" 
										class="form-control required" placeholder="Total" />
									<form:errors path="totalPrice" class="input_error" />
								</div>
							</div>
						</div>
						</br>
				
										<div class="row">
							 <div class="col-sm-1" style="float: right;">
										
								<input type="button"; style="background:#20354a ;color:#ffffff ;border: 1px solid transparent;"onclick="addAgreementDetails()"
									value="Add"/>
							
							</div>
							</div>
										</br>
										<div id="addAgreement"></div>
										
										</br>
						<div class="row">
							
							<div class="col-sm-1" style="float: right;">
							<form:input type="text" path="grandTotal" id="grandtotalId" onmousemove="grandtotalValue()"
										class="form-control required" placeholder="Grand Total" />
									<form:errors path="grandTotal" class="input_error" />
						</div>


                               <div class="col-sm-3" style="float: right;">
								<div class="form-group" style="float: right;">
									<label>Grand Total <span class="font10 text-danger">*</span></label>
									
								</div>
							</div>
							</div>
						<div class="row">
							
								
								
								 <div class="col-sm-1" style="float: right;">
								<div class="form-group">
									<form:input type="text" path="gstBO.sgst" id="sgst_Id"
										class="form-control required" placeholder="SGst" />
									<form:errors path="gstBO.sgst" class="input_error" />
								</div>
								</div>

							<div class="col-sm-3" style="float: right;">
								<div class="form-group" style="float: right;">
									<label>SGST<span class="font10 text-danger">*</span>${cgst}</label>
								</div>
							</div>
						</div>
						<div class="row">
						
							<div class="col-sm-1" style="float: right;">
								<div class="form-group">
									<form:input type="text" path="gstBO.cgst" id="cgst_Id" 
										class="form-control required" placeholder="CGst" />
									<form:errors path="gstBO.cgst" class="input_error"/>
								</div>
								</div>
							<div class="col-sm-1" style="float: right;">
							
								<div class="form-group" style="float: right;">
									<label>CGST<span class="font10 text-danger">*</span>${cgst}</label>
								</div>
							</div>
						</div>
						<div class="row">
						<div class="" style="float: right;">
								
								</div>
							<div class="col-sm-1" style="float: right;">
								<div class="form-group">
									<form:input type="text" path="totalInvoice"
										class="form-control required" placeholder="TotalInvoice" id="totalInvoiceId"/>
									<form:errors path="totalInvoice" class="input_error" />
								</div>
							</div>

							<div class="col-sm-3" style="float: right;">
								<div class="form-group" style="float: right;">
									<label>Total Invoice<span class="font10 text-danger">*</span></label>
								</div>
							</div>
						</div>

						<br />
						<div class="row">

							<div class="col-sm-1" style="float: right;">
								<a href=view-campaign?page=1"><span
									class="btn btn-t-primary btn-theme lebal_align">Cancel</span></a>
							</div>
							<div class="col-sm-1" style="float: right;">
								<button type="submit" 
									class="btn btn-t-primary btn-theme lebal_align">Submit</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<br>

