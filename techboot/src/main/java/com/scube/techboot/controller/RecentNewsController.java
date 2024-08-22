package com.scube.techboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import com.scube.techboot.bo.RecentNewsBO;
import com.scube.techboot.service.RecentNewsService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.SaveImagesToFolder;
import com.scube.techboot.utils.TechbootResourceBundle;

@Controller
public class RecentNewsController {
	
	@Autowired
	private RecentNewsService recentNewsService;
	
	//URL
	private static final String CREATE_RECENTNEWS="/create-recentNews";
	private static final String VIEW_RECENTNEWS="/view-recentNews";
	private static final String LIST_VIEW_RECENTNEWS="/recentNews-list";
	private static final String EDIT_RECENTNEWS="/edit-recentNews";
	private static final String DELETE_RECENTNEWS="/delete-recentNews";
	private static final String VIEW_RECENTNEWS_DETAILS="/view-recentNews-details";
	private static final String SEARCH_RECENTNEWS="/search-news";
	
	@RequestMapping(value=CREATE_RECENTNEWS,method=RequestMethod.GET)
	public String getRecentNews(HttpServletRequest request,HttpSession session,Model model){
		
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		model.addAttribute("recentNewsBo",new RecentNewsBO());
		return "create-recentNews";
		
	}
	
	@RequestMapping(value=CREATE_RECENTNEWS,method=RequestMethod.POST)
	public String postRecentNews(@Valid @ModelAttribute("recentNewsBo") RecentNewsBO recentNewsBo,BindingResult bindingResult,@RequestParam("recentNewsLogos") MultipartFile recentNewsLogo,HttpServletRequest request,
			HttpSession session,HttpServletResponse response,Model model) throws RuntimeException, IOException{
		
		if(bindingResult.hasErrors()){
			return "create-recentNews";
		}
		
		RecentNewsBO recentNewsBO=new RecentNewsBO();
		String imageName=null;
		long lastnewsId=0;
		if(null!=recentNewsBo){
			long count=recentNewsService.retrieveNewsCount(recentNewsBo);
			if(0!=count)
				lastnewsId=count;
			long lastNewsSequenceId=lastnewsId+1;
			String imgContentType=recentNewsLogo.getContentType();
			String temp[] = imgContentType.split("/");
			imageName = lastNewsSequenceId + "." + temp[1];
			recentNewsBo.setImageName(imageName);
		}
		if(null!=recentNewsBo && null!=session.getAttribute("adminId")){
			recentNewsBO=recentNewsService.saveRecentNewsDetails(recentNewsBo,session);
		}
		if(null!=recentNewsBO && 0!=recentNewsBO.getNewsId()){
			String imgPathName = TechbootResourceBundle.getValue("RecentNews.Logo");
			SaveImagesToFolder.saveImageToFolder(imageName,recentNewsLogo,imgPathName);
		}
		model.addAttribute("successMessage", TechbootResourceBundle.getValue("create.RecentNews"));
		return "redirect:/view-recentNews";
		
	}
	
	@RequestMapping(value=VIEW_RECENTNEWS,method=RequestMethod.GET)
	public String getViewRecentNewsList(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		
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
		RecentNewsBO recentNewsBO=new RecentNewsBO();
		
		recentNewsPagination(recentNewsBO,model,paging,session);
		model.addAttribute("recentNewsBo" ,recentNewsBO);//my  addition
		return "view-recentNews";
		}
	
	private void recentNewsPagination(RecentNewsBO recentNewsBO, Model model, String paging, HttpSession session) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		long count=0;
		long totalrecentNewsCount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		maxRecord=Integer.parseInt(Record);
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		if(null!=session.getAttribute("adminId")){
			count=recentNewsService.getRetrieveNewsCount(recentNewsBO);
		}
		if(0!=count){
			totalrecentNewsCount=count;
		}
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		recentNewsBO.setRecordIndex(StartingRecordIndex);
		recentNewsBO.setMaxRecord(maxRecord);
		recentNewsBO.setPagination("pagination");
		if(null!=session.getAttribute("adminId")){
			List<RecentNewsBO> newslist=recentNewsService.getViewRecentNewsList(recentNewsBO);
			if(null!=newslist && !newslist.isEmpty() && newslist.size()>0){
				model.addAttribute("newslists",Pagination.paginationLimitedRecords(page, newslist, maxRecord, totalrecentNewsCount));
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

	@RequestMapping(value=LIST_VIEW_RECENTNEWS,method=RequestMethod.GET)
	public String listOfRecentNews(Model model,HttpServletRequest request,HttpSession session){
		
			RecentNewsBO recentNewsBO=new RecentNewsBO();
			List<RecentNewsBO> newslist=new ArrayList<RecentNewsBO>();
			if(null!=request.getParameter("successMessage")){
				model.addAttribute("successMessage",request.getParameter("successMessage"));
			}
			int page=1;
			int recordTotal=0;
			int maxRecord=3;
			
		   //Second page
			if(null!=request.getParameter("page")){
			page=Integer.parseInt(request.getParameter("page"));
		   }
			recentNewsBO.setIsDelete(true);
			newslist=recentNewsService.getViewRecentNewsList(recentNewsBO);
			if(null!=newslist && !newslist.isEmpty() && newslist.size()>0) {
				 recordTotal=newslist.size();
			 }
			
			int StartingRecordIndex=paginationPageValues(page, maxRecord);
			recentNewsBO.setMaxRecord(maxRecord);
			recentNewsBO.setRecordIndex(StartingRecordIndex);
			recentNewsBO.setIsDelete(false);
			newslist=recentNewsService.getViewRecentNewsList(recentNewsBO);
			if(null!=newslist && !newslist.isEmpty() && newslist.size()>0){
				model.addAttribute("newslists",Pagination.paginationLimitedRecords(page, newslist, maxRecord, recordTotal));
				return "recentNews-list";
			}
		return "recentNews-list";
		
	}
	
	
	@RequestMapping(value=SEARCH_RECENTNEWS, method=RequestMethod.POST)
	public String searchServices(@ModelAttribute("recentNewsBo") RecentNewsBO recentNewsBo,
			Model model,HttpServletRequest request,HttpServletResponse response,
			HttpSession session) throws FileNotFoundException{
		List<RecentNewsBO> newslist=new ArrayList<RecentNewsBO>();
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		String paging=null;
		
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}
		
		long count=0;
		long totalrecentNewsCount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		maxRecord=Integer.parseInt(Record);
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		if(null!=session.getAttribute("adminId")){
			count=recentNewsService.getRetrieveNewsCount(recentNewsBo);
		}
		if(0!=count){
			totalrecentNewsCount=count;
		}
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		recentNewsBo.setRecordIndex(StartingRecordIndex);
		recentNewsBo.setMaxRecord(maxRecord);
		recentNewsBo.setPagination("pagination");
		if(null!=session.getAttribute("adminId")){
			newslist=recentNewsService.getViewRecentNewsList(recentNewsBo);
			if(null!=newslist && !newslist.isEmpty() && newslist.size()>0){
				model.addAttribute("newslists",Pagination.paginationLimitedRecords(page, newslist, maxRecord, totalrecentNewsCount));
			}
		}
		return "view-recentNews";
		
	
	}
	
	@RequestMapping(value=EDIT_RECENTNEWS,method=RequestMethod.GET)
	public String getEditRecentNews(Model model,HttpServletRequest request,HttpSession session) throws ParseException{
		
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}
		
		RecentNewsBO recentNewsBo=new RecentNewsBO();
		if(null!=request.getParameter("newsId")){
			String value=request.getParameter("newsId");
			int newsId=Integer.parseInt(value);
			recentNewsBo.setNewsId(newsId);
		}
		recentNewsBo=recentNewsService.getRecentNewsObject(recentNewsBo);
		if(null!=recentNewsBo){
			 model.addAttribute("recentNewsBo",recentNewsBo);
			 return "edit-recentNews";
			}
		return "create-recentNews";
		
	}
	
	@RequestMapping(value=EDIT_RECENTNEWS,method=RequestMethod.POST)
	public String postEditRecentNews(@Valid @ModelAttribute("recentNewsBo") RecentNewsBO recentNewsBo,BindingResult bindingResult,@RequestParam("recentNewsLogos") MultipartFile recentNewsLogo,HttpServletRequest request,
			HttpSession session,HttpServletResponse response,Model model) throws RuntimeException, IOException{
		
		if(bindingResult.hasErrors()){
			return "edit-recentNews";
		}
		boolean status;
		String imageName=recentNewsBo.getImageName();
	   if(null!=request.getParameter("newsId")){
			String value=request.getParameter("newsId");
			int newsId=Integer.parseInt(value);
			recentNewsBo.setNewsId(newsId);
		}
		if(status=recentNewsService.postEditRecentNews(recentNewsBo)){
			if(null!=recentNewsLogo && !recentNewsLogo.getOriginalFilename().isEmpty()){
				String imgPathName = TechbootResourceBundle.getValue("RecentNews.Logo");
				SaveImagesToFolder.saveImageToFolder(imageName,recentNewsLogo,imgPathName);
			}
			
		}
      model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.RecentNews"));
		return "redirect:/view-recentNews";
	}
	
	@RequestMapping(value=DELETE_RECENTNEWS,method=RequestMethod.GET)
	public String deleteRecentNews(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		boolean status;
		RecentNewsBO recentNewsBo=new RecentNewsBO();
		if(null!=request.getParameter("newsId")){
			String values=request.getParameter("newsId");
			int newsId=Integer.parseInt(values);
			recentNewsBo.setNewsId(newsId);
			}
		if(status=recentNewsService.deleteRecentNews(recentNewsBo)){
			 model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.RecentNews"));
				return "redirect:/view-recentNews";
		}
		return "redirect:/view-recentNews";
		
	}
	
	@RequestMapping(value=VIEW_RECENTNEWS_DETAILS,method=RequestMethod.GET)
	public String viewRecentNewsDetails(Model model,HttpServletRequest request,HttpSession session) throws ParseException{
		
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		RecentNewsBO recentNewsBo=new RecentNewsBO();
		if(null!=request.getParameter("newsId")){
			String values=request.getParameter("newsId");
			int newsId=Integer.parseInt(values);
			recentNewsBo.setNewsId(newsId);
			}
		recentNewsBo=recentNewsService.getRecentNewsObject(recentNewsBo);
		if(null!=recentNewsBo){
			 model.addAttribute("recentNewsBo",recentNewsBo);
			 return "view-recentNews-details";
		}
		return "redirect:/view-recentNews";
		
	}
}
