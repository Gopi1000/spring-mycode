<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>



<body>
    <div align="center">
        <h1>Student Reporting</h1>
        <h3><a href="downloadEexcel.html?type=excel" var="xlsURL">Download Excel Document</a></h3> 
        <h3><a href="downloadPDF.html">Download PDF Document</a></h3> 
<h3><a href="viewcompany" var="CompanyList">company enrollement</a></h3>
<h3><a href="viewcampaign" var="campaignList">campiegn enrollement</a></h3>
<h3><a href="vieww" var="EnrollmentList">view enrollement</a></h3>

    </div>
</body>
</html>