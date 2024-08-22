package com.scube.techboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.bo.SubcategoryBO;
import com.scube.techboot.dao.SubcategoryDao;
import com.scube.techboot.vo.CategoryVO;
import com.scube.techboot.vo.ProductServiceVO;
import com.scube.techboot.vo.SubcategoryVO;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService{
	
@Autowired
	private SubcategoryDao subcategoryDao;
	@Override
	public SubcategoryBO savesubcategory(SubcategoryBO subcategoryBO, HttpSession session) {
		// TODO Auto-generated method stub
		SubcategoryVO subcategoryVO=new SubcategoryVO();
		
		subcategoryVO.setSubcategoryId(subcategoryBO.getSubcategoryId());
		subcategoryVO.setSubcategory(subcategoryBO.getSubcategory());
		subcategoryVO.setCreatedTime(new Date());
		subcategoryVO.setPriority(subcategoryBO.getPriority());
		subcategoryVO.setSending_status(true);
		subcategoryVO.setIsDelete(false);
		
		
			CategoryVO categoryVO=new CategoryVO();
			categoryVO.setCategoryId(Integer.parseInt(subcategoryBO.getCategoryBO().getCategory()));
			subcategoryVO.setCategoryVO(categoryVO);
			
		
		if(null!=session.getAttribute("adminId")){
			long id= (long) session.getAttribute("adminId");
			subcategoryVO.setCreatedBy(id);
		}
		
		SubcategoryBO subcategory=subcategoryDao.savesubcategory(subcategoryVO);
		if(0!=subcategory.getSubcategoryId()){
		return subcategory;
	}
return subcategory;
		
	}
	@Override
	public List<SubcategoryBO> getSubcategoryObject() {
		// TODO Auto-generated method stub
		List<SubcategoryBO> subcategorylist=new ArrayList<SubcategoryBO>();
		subcategorylist= subcategoryDao.getSubcategoryobject();
		
		return subcategorylist;
		
		
	}
	@Override
	public List<SubcategoryBO> searchSubcategory(String subcategory) {
		// TODO Auto-generated method stub
		List<SubcategoryBO> list=new ArrayList<SubcategoryBO>();
		List<SubcategoryVO> listvo=subcategoryDao.search(subcategory);
	 for(SubcategoryVO subvo:listvo)
	 {
		 SubcategoryBO bo=new SubcategoryBO();
		 CategoryBO categorybo=new CategoryBO();
			CategoryVO categoryvo=new CategoryVO();
			categorybo.setCategoryId(subvo.getCategoryVO().getCategoryId());
			categorybo.setCategory(subvo.getCategoryVO().getCategory());
		 bo.setCategoryBO(categorybo);
		 bo.setSubcategoryId(subvo.getSubcategoryId());
		 bo.setSubcategory(subvo.getSubcategory());
		 
		 list.add(bo);
		 
		 
	 }
		
		return list;

	}
	@Override
	public SubcategoryBO editSubcategoryObject(SubcategoryBO subcategoryBO) {
		// TODO Auto-generated method stub
		SubcategoryVO subcategoryVO=new SubcategoryVO();
		subcategoryVO.setSubcategoryId(subcategoryBO.getSubcategoryId());
		subcategoryVO=subcategoryDao.editsubcategory(subcategoryVO);
		if(null!=subcategoryVO)
		{
			CategoryBO categorybo=new CategoryBO();
			//categorybo.setCategoryId(subcategoryVO.getCategoryVO().getCategoryId());
			categorybo.setCategoryId(subcategoryVO.getCategoryVO().getCategoryId());
			categorybo.setCategory(subcategoryVO.getCategoryVO().getCategory());
			subcategoryBO.setCategoryBO(categorybo);
			subcategoryBO.setSubcategoryId(subcategoryVO.getSubcategoryId());
			subcategoryBO.setSubcategory(subcategoryVO.getSubcategory());
			subcategoryBO.setPriority(subcategoryVO.getPriority());
		}
		return subcategoryBO;
	}
	@Override
	public Boolean subcategoryUpdate(SubcategoryBO subcategoryBO) {
		// TODO Auto-generated method stub
		
		
		
		SubcategoryVO subcategoryVO=new SubcategoryVO();
		SubcategoryVO subcategoryvo=new SubcategoryVO();
		CategoryVO categoryvo=new CategoryVO();
		if(0!=subcategoryBO.getSubcategoryId()){
		subcategoryVO.setSubcategoryId(subcategoryBO.getSubcategoryId());
		subcategoryvo=subcategoryDao.retrievesubcategory(subcategoryVO);
		}
		subcategoryVO.setSubcategory(subcategoryBO.getSubcategory());
		subcategoryVO.setSubcategoryId(subcategoryBO.getSubcategoryId());
		categoryvo.setCategoryId(Integer.parseInt(subcategoryBO.getCategoryBO().getCategory()));
		subcategoryVO.setCategoryVO(categoryvo);
		subcategoryVO.setModifiedTime(new Date());
		subcategoryVO.setModifiedBy(subcategoryBO.getModifiedBy());
		subcategoryVO.setPriority(subcategoryBO.getPriority());
		
		//subcategoryDao.retrievesubcategory(subcategoryVO);
		//SubcategoryVO subcategoryvo=subcategoryDao.retrievesubcategory(subcategoryVO);
		subcategoryVO.setCreatedBy(subcategoryvo.getCreatedBy());
		subcategoryVO.setCreatedTime(subcategoryvo.getCreatedTime());
		subcategoryVO.setIsDelete(subcategoryvo.getIsDelete());
		subcategoryVO.setSending_status(subcategoryvo.getSending_status());
		return subcategoryDao.subcategoryUpdate(subcategoryVO);
	}
	@Override
	public List<SubcategoryBO> searchSubcategory(SubcategoryBO subcategorybo) {
		// TODO Auto-generated method stub
		SubcategoryVO subcategoryVO=new SubcategoryVO();
		CategoryVO categoryvo=new CategoryVO();
		List<SubcategoryVO> listsubvo=new ArrayList<SubcategoryVO>();
		List<SubcategoryBO> listsubbo=new ArrayList<SubcategoryBO>();
		subcategoryVO.setIsDelete(false);
		if(null!=subcategorybo.getSubcategory()&& !subcategorybo.getSubcategory().isEmpty())
		{
			
			subcategoryVO.setSubcategory(subcategorybo.getSubcategory());
		}
		
		else if(0!=subcategorybo.getSubcategoryId())
		{
			
			subcategoryVO.setSubcategoryId(subcategorybo.getSubcategoryId());
		}
		else{
			
			
			categoryvo.setCategory(subcategorybo.getCategoryBO().getCategory());
			subcategoryVO.setCategoryVO(categoryvo);
		}
		
		
		listsubvo=subcategoryDao.search(subcategoryVO);
		if(null!=listsubvo && !listsubvo.isEmpty() && listsubvo.size()>0){
			 for(SubcategoryVO subvo:listsubvo)
			 {
				 SubcategoryBO bo=new SubcategoryBO();
				 CategoryBO categorybo=new CategoryBO();
					//CategoryVO categoryvo=new CategoryVO();
					categorybo.setCategoryId(subvo.getCategoryVO().getCategoryId());
					categorybo.setCategory(subvo.getCategoryVO().getCategory());
				 bo.setCategoryBO(categorybo);
				 bo.setSubcategoryId(subvo.getSubcategoryId());
				 bo.setSubcategory(subvo.getSubcategory());
				 
				 listsubbo.add(bo);
				 
				 
			 }
				
				
	}
		return listsubbo;

}
	@Override
	public boolean subcategoryNameValidations(SubcategoryBO subcategoryBO) {
		// TODO Auto-generated method stub
		SubcategoryVO subcategoryVO=new SubcategoryVO();
		subcategoryVO.setSubcategory(subcategoryBO.getSubcategory());
		subcategoryVO.setIsDelete(false);
		boolean status = subcategoryDao.isExixstsCategory(subcategoryVO);
		if(status){
			return true;
		}
		return false;
	}
	@Override
	public long retrieveSubcategoryCount(SubcategoryBO subcategoryBO) {
		// TODO Auto-generated method stub
		SubcategoryVO subcategoryVO=new SubcategoryVO();
		subcategoryVO.setIsDelete(false);
		return subcategoryDao.retrievesubcategoryCount(subcategoryVO);

	}
	@Override
	public List<SubcategoryBO> viewSubcategory(SubcategoryBO subcategoryBO) {
		// TODO Auto-generated method stub
		List<SubcategoryBO> subcategoryListBO=new ArrayList<SubcategoryBO>();
		List<SubcategoryVO> subcategoryListVO=new ArrayList<SubcategoryVO>();
		SubcategoryVO subcategoryVO=new SubcategoryVO();
		subcategoryVO.setSubcategoryId(subcategoryBO.getSubcategoryId());
		subcategoryVO.setRecordIndex(subcategoryBO.getRecordIndex());
		subcategoryVO.setMaxRecord(subcategoryBO.getMaxRecord());
		subcategoryVO.setIsDelete(false);
		subcategoryListVO=subcategoryDao.viewsubcategory(subcategoryVO);
		if(null!=subcategoryListVO&& !subcategoryListVO.isEmpty() && subcategoryListVO.size()>0){
			int sNo=subcategoryBO.getRecordIndex();
			for(SubcategoryVO subcategoryvo:subcategoryListVO){
				SubcategoryBO subcategorybo=new SubcategoryBO();
				subcategorybo.setSubcategoryId(subcategoryvo.getSubcategoryId());
				subcategorybo.setSubcategory(subcategoryvo.getSubcategory());
				subcategorybo.setCreatedTime(subcategoryvo.getCreatedTime());
				CategoryBO categorybo=new CategoryBO();
				categorybo.setCategory(subcategoryvo.getCategoryVO().getCategory());
				subcategorybo.setCategoryBO(categorybo);
				
				subcategoryListBO.add(subcategorybo);
			}
		}
		return subcategoryListBO;
		
	}
		
	}
