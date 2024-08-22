package com.scube.techboot.reporting;

import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.scube.techboot.bo.StudentCouresBO;




public class PDFBuilder extends  AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<StudentCouresBO> EnrollmentList = (List<StudentCouresBO>) model.get("EnrollmentList");
		document.add(new Paragraph("Student"));
         
	        PdfPTable table = new PdfPTable(3);
	        table.setWidthPercentage(100.0f);
	        table.setWidths(new float[] {1.0f, 2.0f,1.0f});
	        table.setSpacingBefore(10);
	         
	        // define font for table header row
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(BaseColor.WHITE);
	         
	        // define table header cell
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(BaseColor.BLUE);
	        cell.setPadding(3);
	         
	        // write table header
	        cell.setPhrase(new Phrase("Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("CourseName", font));
	        table.addCell(cell);
	 
	        cell.setPhrase(new Phrase("Fees", font));
	        table.addCell(cell);
	       
	         
	        // write table row data
	        for (StudentCouresBO list : EnrollmentList) {
	        	
	        	table.addCell(list.getStudentRegisterBO().getFirstName());
	            table.addCell(list.getCourseBO().getCourseName());
//float to String 	            
	            float f=(list.getCourseBO().getFees());
	            String s=String.valueOf(f);
	            table.addCell(s);
	           
	        }
	         
	       
	        document.add(table);
	         
	    }
		
	

}
