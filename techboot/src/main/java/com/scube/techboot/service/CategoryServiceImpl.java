package com.scube.techboot.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.dao.CategoryDao;
import com.scube.techboot.vo.CategoryVO;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	
	@Override
	public int saveCategory(CategoryBO categoryBo) throws Exception {
		CategoryVO categoryVo =new CategoryVO();
		if (null!=categoryBo){
			categoryBo.setCreatedTime(new Date());
			categoryBo.setIsDelete(false);
			categoryBo.setSending_status(true);
		
			PropertyUtils.copyProperties(categoryVo, categoryBo);
		}
		return  categoryDao.saveCategory(categoryVo) ;
	}


	@Override
	public boolean categoryNameValidations(CategoryBO categoryBO) {
		CategoryVO categoryVO=new CategoryVO();
		categoryVO.setCategory(categoryBO.getCategory());
		categoryVO.setIsDelete(false);
		boolean status = categoryDao.isExixstsCategory(categoryVO);
		if(status){
			return true;
		}
		return false;
	}


	@Override
	public boolean deleteCategory(CategoryBO categoryBo) throws Exception {
		CategoryVO categoryVo =new CategoryVO();
		if (null!=categoryBo){
		PropertyUtils.copyProperties(categoryVo, categoryBo); 
		}
		return categoryDao.deleteCategory(categoryVo);
	}


	@Override
	public CategoryBO retrieveCategory(CategoryBO categoryBo) throws Exception {
		CategoryVO categoryVo =new CategoryVO();
		PropertyUtils.copyProperties(categoryVo, categoryBo);
		categoryVo=categoryDao.updateCategory(categoryVo);
		if(null!=categoryVo){
			PropertyUtils.copyProperties(categoryBo, categoryVo);
		}
		return categoryBo;
	}


	@Override
	public boolean updateSaveStudent(CategoryBO categoryBo) throws Exception {
		CategoryVO categoryVo =new CategoryVO();
		if (null!=categoryBo){
			categoryBo.setModifiedTime(new Date());
			PropertyUtils.copyProperties(categoryVo, categoryBo);
		}
		return categoryDao.updateSaveCategory(categoryVo);
		
	}


	@Override
	public long retrieveCategoryCount(CategoryBO categoryBO) {
		CategoryVO categoryVO=new CategoryVO();
		categoryVO.setIsDelete(false);
		return categoryDao.retrievecategoryCount(categoryVO);

	}


	@Override
	public List<CategoryBO> viewCatogery(CategoryBO categoryBO) {
		List<CategoryBO> categoryListBO=new ArrayList<CategoryBO>();
		List<CategoryVO> categoryListVO=new ArrayList<CategoryVO>();
		CategoryVO categoryVO=new CategoryVO();
		categoryVO.setCategoryId(categoryBO.getCategoryId());
		categoryVO.setRecordIndex(categoryBO.getRecordIndex());
		categoryVO.setMaxRecord(categoryBO.getMaxRecord());
		categoryVO.setIsDelete(false);
		categoryListVO=categoryDao.viewCatogery(categoryVO);
		if(null!=categoryListVO&& !categoryListVO.isEmpty() && categoryListVO.size()>0){
			int sNo=categoryBO.getRecordIndex();
			for(CategoryVO categoryvo:categoryListVO){
				CategoryBO categorybo=new CategoryBO();
				categorybo.setCategoryId(categoryvo.getCategoryId());
				categorybo.setCategory(categoryvo.getCategory());
				categorybo.setCreatedTime(categoryvo.getCreatedTime());
				categoryListBO.add(categorybo);
			}
		}
		return categoryListBO;
	}


	@Override
	public int searchCategory(CategoryBO categoryBo) {
		CategoryVO categoryVO=new CategoryVO();
		categoryVO.setCategory(categoryBo.getCategory());
		categoryVO.setIsDelete(false);
		return categoryDao.searchCategory(categoryVO);
	}


	@Override
	public List<CategoryBO> searchPageCategory(CategoryBO categoryBo) {
		List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
		List<CategoryBO> categoryListBO=new ArrayList<CategoryBO>();
		List<CategoryBO> ListcategoryBO=new ArrayList<CategoryBO>();
		CategoryVO categoryVO=new CategoryVO();
		categoryVO.setRecordIndex(categoryBo.getRecordIndex());
		categoryVO.setMaxRecord(categoryBo.getMaxRecord());
		categoryVO.setCategory(categoryBo.getCategory());
		categoryVO.setIsDelete(false);
		categoryList=categoryDao.searchPageCategory(categoryVO);
		if(null!=categoryList && categoryList.size()>0 && !categoryList.isEmpty()){
			int sNo=categoryBo.getRecordIndex();
			int s_No=1;
			for(CategoryVO category:categoryList){
				categoryBo.setCategoryId(category.getCategoryId());
				categoryBo.setCategory(category.getCategory());
				categoryBo.setS_No(s_No++);
				categoryListBO.add(categoryBo);
			}
			categoryListBO.forEach(category->{
				categoryBo.setCategoryId(category.getCategoryId());
				categoryBo.setCategory(category.getCategory());
				categoryBo.setS_No(category.getS_No());
				ListcategoryBO.add(categoryBo);
				
			});
		}
		return ListcategoryBO;
	}


	@Override
	public List<CategoryBO> viewcat() {
		// TODO Auto-generated method stub
		List< CategoryBO> list=categoryDao.viewcat();
		return list;
		
	}
}