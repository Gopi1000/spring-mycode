package com.scube.techboot.reporting;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.scube.techboot.bo.StudentCouresBO;


public class ExcelReporView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition","attachement; filename=\"users.xls\"");
		List<StudentCouresBO> EnrollmentList=(List<StudentCouresBO>) model.get("EnrollmentList");
		Sheet sheet= workbook.createSheet("EnrollmentList");
		Row headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Course Name");
		headerRow.createCell(2).setCellValue("Fees");
		int row=1;
		for(StudentCouresBO studentEnrollmentBO:EnrollmentList) {
			Row dataRow=sheet.createRow(row++);
			dataRow.createCell(0).setCellValue(studentEnrollmentBO.getStudentRegisterBO().getFirstName());
			dataRow.createCell(1).setCellValue(studentEnrollmentBO.getCourseBO().getCourseName());
			dataRow.createCell(2).setCellValue(studentEnrollmentBO.getCourseBO().getFees());
		}
		
		
	}

}
