package com.scube.techboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.scube.techboot.bo.TestimonialBO;
import com.scube.techboot.service.TestimonialService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.SaveImagesToFolder;
import com.scube.techboot.utils.TechbootResourceBundle;

@Controller
public class TestimonialController {

	@Autowired
	private TestimonialService testimonialService;
	//URL
	private static final String CREATE_TESTIMONIAL="/create-testimonial";
	private static final String EDIT_TESTIMONIAL="/edit-testimonial";
	private static final String DELETE_TESTIMONIAL="/delete-testimonial";
	private static final String VIEW_TESTIMONIAL="/view-testimonial";
	private static final String VIEW_TESTIMONIAL_DETAILS="/view-testimonial-details";
	private static final String SEARCH_TESTIMONIAL="/search-testimonial";

	@RequestMapping(value=CREATE_TESTIMONIAL, method = RequestMethod.GET)
	public String testimonial(Model model, HttpServletRequest request,HttpSession session){
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		if(null!=session.getAttribute("adminId")){
			model.addAttribute("testimonialBo", new TestimonialBO());
		}
		return "create-testimonial";
	}

	@RequestMapping(value=CREATE_TESTIMONIAL, method = RequestMethod.POST)
	public String testimonial(@Valid @ModelAttribute("testimonialBo") TestimonialBO testimonialBo,BindingResult bindingresult,@RequestParam("testimonialLogos") MultipartFile testimonialLogo, HttpServletRequest request,
			HttpServletResponse response,Model model,HttpSession session) throws SerialException, SQLException, IOException{

		if(bindingresult.hasErrors()){
			return "create-testimonial";
		}
		TestimonialBO testimonialBO=new TestimonialBO();
		String imageName=null;
		long lasttestId=0;
		//logo
		if(null!=testimonialBo){
			long count=(long) testimonialService.retriveOfTestimonial(testimonialBo);
			if(0!=count)
				lasttestId= count;
			long lastTestSequenceId=lasttestId+1;
			String imgContentType=testimonialLogo.getContentType();
			String temp[] = imgContentType.split("/");
			imageName = lastTestSequenceId + "." + temp[1];
			testimonialBo.setImageName(imageName);

		}
		if(null!=testimonialBo && null!=session.getAttribute("adminId")){
			testimonialBO=testimonialService.saveTestimonial(testimonialBo,session);
		}
		//save local
		if(null!=testimonialBO && 0!=testimonialBO.getTestimonialId()){
			String imgPathName = TechbootResourceBundle.getValue("testimonial.Logo");
			SaveImagesToFolder.saveImageToFolder(imageName,testimonialLogo,imgPathName);
		}
		model.addAttribute("successMessage", TechbootResourceBundle.getValue("create.testimonial"));
		return "redirect:/view-testimonial";
	}

	@RequestMapping(value=VIEW_TESTIMONIAL, method=RequestMethod.GET)
	public String viewTestimonial(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}

		String paging=null;
		if(null!=request.getParameter("successMessage")){
			model.addAttribute("successMessage",request.getParameter("successMessage"));
		}
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}
		TestimonialBO testimonialBo=new TestimonialBO();
		model.addAttribute("testimonial", testimonialBo);

		testimonialPagination(testimonialBo,paging,model,session);
		return "view-testimonial";

	}

	private void testimonialPagination(TestimonialBO testimonialBo, String paging, Model model, HttpSession session) throws FileNotFoundException {
		// TODO Auto-generated method stub
		long count=0;
		long totalTestimonialCount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		maxRecord=Integer.parseInt(Record);
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		if(null!=session.getAttribute("adminId")){
			count=testimonialService.retrieveTestimonialCount(testimonialBo);
		}
		if(0!=count){
			totalTestimonialCount=count;
		}
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		testimonialBo.setRecordIndex(StartingRecordIndex);
		testimonialBo.setMaxRecord(maxRecord);
		testimonialBo.setPagination("pagination");
		if(null!=session.getAttribute("adminId")){
			List<TestimonialBO> testinglist=testimonialService.viewListTestimonial(testimonialBo);
			if(null!=testinglist && !testinglist.isEmpty() && testinglist.size()>0){
				model.addAttribute("testimoniallist",Pagination.paginationLimitedRecords(page, testinglist, maxRecord, totalTestimonialCount));
			}
		}
	}

	private int paginationPageValues(int pageid, int recordPerPage) {
		// TODO Auto-generated method stub
		int pageRecords = 0;
		if (pageid == 1) {
			pageRecords = 0;
		} else {
			pageRecords = (pageid - 1) * recordPerPage + 1;
			pageRecords = pageRecords - 1;
		}
		return pageRecords;
	}

	@RequestMapping(value=EDIT_TESTIMONIAL, method=RequestMethod.GET)
	public String getEditTestimonial(Model model,HttpServletRequest request,HttpSession session){
		//checking login
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}

		TestimonialBO testimonialBo=new TestimonialBO();
		if(null!=request.getParameter("testimonialId")){
			String value=request.getParameter("testimonialId");
			long id=Long.parseLong(value);
			testimonialBo.setTestimonialId(id);
		}
		testimonialBo=testimonialService.getEditTestimonial(testimonialBo);
		if(null!=testimonialBo){
			model.addAttribute("testimonialBo",testimonialBo);
			return "edit-testimonial";
		}
		return "create-testimonial";

	}

	@RequestMapping(value=EDIT_TESTIMONIAL,  method=RequestMethod.POST)
	public String postEditTestimonial(@Valid @ModelAttribute("testimonialBo") TestimonialBO testimonialBo,BindingResult bindingresult,
			@RequestParam("testimonialLogos") MultipartFile testimonialLogo, HttpServletRequest request,
			HttpServletResponse response,Model model) throws RuntimeException, IOException{
		
		if(bindingresult.hasErrors()){
			return "edit-testimonial";	
		}
		boolean status;
		String imageName=testimonialBo.getImageName();
		if(null!=request.getParameter("testimonialId")){
			String value=request.getParameter("testimonialId");
			long id=Long.parseLong(value);
			testimonialBo.setTestimonialId(id);
		}
		if(null!=testimonialBo){
			if(status=testimonialService.postEditTestimonial(testimonialBo)){
				if(null!=testimonialLogo && !testimonialLogo.getOriginalFilename().isEmpty()){
					String imgPathName = TechbootResourceBundle.getValue("testimonial.Logo");
					SaveImagesToFolder.saveImageToFolder(imageName,testimonialLogo,imgPathName);
				}
			}
		}
		model.addAttribute("successMessage",TechbootResourceBundle.getValue("update.testimonial"));
		return "redirect:/view-testimonial";
	}

	@RequestMapping(value=DELETE_TESTIMONIAL, method=RequestMethod.GET)
	public String deleteTestimonial(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		//checking login
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}

		boolean status;
		TestimonialBO testimonialBo=new TestimonialBO();
		if(null!=request.getParameter("testimonialId")){
			String value=request.getParameter("testimonialId");
			long id=Long.parseLong(value);
			testimonialBo.setTestimonialId(id);
		}
		if(null!=testimonialBo){
			if(status=testimonialService.deleteTestimonial(testimonialBo)){
				model.addAttribute("successMessage",TechbootResourceBundle.getValue("delete.testimonial"));
				return "redirect:/view-testimonial";
			}
		}
		return "redirect:/view-testimonial";

	}

	@RequestMapping(value=VIEW_TESTIMONIAL_DETAILS,method=RequestMethod.GET)
	public String viewTestimonialDetails(Model model,HttpServletRequest request){

		TestimonialBO testimonialBo=new TestimonialBO();
		if(null!=request.getParameter("testimonialId")){
			String value=request.getParameter("testimonialId");
			long testId=Long.parseLong(value);
			testimonialBo.setTestimonialId(testId);
		}
		testimonialBo=testimonialService.getEditTestimonial(testimonialBo);
		if(null!=testimonialBo){
			model.addAttribute("testimonialBo",testimonialBo);
			return "view-testimonial-details";
		}

		return "redirect:/view-testimonial";
	}
	
	 @RequestMapping(value = SEARCH_TESTIMONIAL, method = RequestMethod.POST)
		public String searchTestimonial(@ModelAttribute("testimonial") TestimonialBO testimonialBO ,Model model, HttpServletRequest request,
				HttpSession session) throws Exception{
			//using
			model.addAttribute("testimonial",new TestimonialBO());
			List<TestimonialBO> testimonialList=new ArrayList<TestimonialBO>();
			long totalCount=0;
			int page=1;
			int maxRecord=0;
			String Record=TechbootResourceBundle.getValue("pagination.count");
			if(null!=Record){
				maxRecord=Integer.parseInt(Record);
			}
			if(null!=testimonialBO){
				int totalTestimonial=testimonialService.searchTestimonial(testimonialBO);
				if(0!= totalTestimonial){
					totalCount=totalTestimonial;
				}
				else{
					model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
					return  "redirect:/view-company";
				}
				//search pagination
				int startingRecordIndex = paginationPageValues(page, maxRecord);
				testimonialBO.setRecordIndex(startingRecordIndex);
				testimonialBO.setMaxRecord(maxRecord);
				testimonialBO.setPagination("pagination");
				//Limit Recored 1-10
				testimonialList=testimonialService.searchTestimonialData(testimonialBO);
				if(null!=testimonialList && !testimonialList.isEmpty() && testimonialList.size()>0){
					model.addAttribute("testimoniallist",
							Pagination.paginationLimitedRecords(page, testimonialList, maxRecord, totalCount));
				}
			}
			return "view-testimonial";
		}
}
