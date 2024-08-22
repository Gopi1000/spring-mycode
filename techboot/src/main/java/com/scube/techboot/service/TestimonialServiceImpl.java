package com.scube.techboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.scube.techboot.bo.TestimonialBO;
import com.scube.techboot.dao.TestimonialDao;

import com.scube.techboot.vo.TestimonialVO;

@Transactional
@Service
public class TestimonialServiceImpl implements TestimonialService {
	
	@Autowired
	private TestimonialDao testimonialDao;

	@Override
	public TestimonialBO saveTestimonial(TestimonialBO testimonialBo,HttpSession session) {
		// TODO Auto-generated method stub
		TestimonialVO testimonialVo=new TestimonialVO();
		testimonialVo.setImageName(testimonialBo.getImageName());
		testimonialVo.setName(testimonialBo.getName());
		testimonialVo.setMessage(testimonialBo.getMessage());
		testimonialVo.setProfession(testimonialBo.getProfession());
		testimonialVo.setTitleName(testimonialBo.getTitleName());
		testimonialVo.setIsDelete(false);
		testimonialVo.setSending_status(true);
		if(null!=session.getAttribute("adminId")){
			long id=(long) session.getAttribute("adminId");
			testimonialVo.setCreatedBy(id);
			testimonialBo.setCreatedBy(id);
			
		}
		testimonialVo.setCreatedTime(new Date());
		testimonialBo.setCreatedTime(new Date());
		testimonialVo=testimonialDao.saveTestimonial(testimonialVo);
		if(null!=testimonialVo){
			testimonialBo.setTestimonialId(testimonialVo.getTestimonialId());
		}
		return testimonialBo;
	}

	@Override
	public long retriveOfTestimonial(TestimonialBO testimonialBo) {
		// TODO Auto-generated method stub
		TestimonialVO testimonialVo=new TestimonialVO();
		long count=testimonialDao.retriveOfTestimonial(testimonialVo);
		if(0!=count){
			return count;
		}
		return count;
	}

	@Override
	public List<TestimonialBO> viewListTestimonial(TestimonialBO testimonialBo) {
		// TODO Auto-generated method stub
		List<TestimonialBO> testingbo =new ArrayList<TestimonialBO>();
		List<TestimonialVO> testingVo =new ArrayList<TestimonialVO>();
		TestimonialVO testimonialVo=new TestimonialVO();
		testimonialVo.setIsDelete(false);
		testimonialVo.setSending_status(true);
		testimonialVo.setRecordIndex(testimonialBo.getRecordIndex());
		testimonialVo.setMaxRecord(testimonialBo.getMaxRecord());
		List<TestimonialVO> testing=testimonialDao.viewListTestimonial(testimonialVo);
		if(null!=testing && !testing.isEmpty() && testing.size()>0){
			int sNo=testimonialBo.getRecordIndex();
			for(TestimonialVO testimonial:testing){
				TestimonialVO testimonialVO=new TestimonialVO();
				testimonialVO.setTestimonialId(testimonial.getTestimonialId());
				testimonialVO.setName(testimonial.getName());
				testimonialVO.setProfession(testimonial.getProfession());
				testimonialVO.setTitleName(testimonial.getTitleName());
				testimonialVO.setS_No(++sNo);
				testimonialVO.setMessage(testimonial.getMessage());
				testimonialVO.setImageName(testimonial.getImageName());
				testingVo.add(testimonialVO);
		   }
			testingVo.forEach(testimonial->{
				TestimonialBO testimonialBO=new TestimonialBO();
				testimonialBO.setTestimonialId(testimonial.getTestimonialId());
				testimonialBO.setName(testimonial.getName());
				testimonialBO.setProfession(testimonial.getProfession());
				testimonialBO.setTitleName(testimonial.getTitleName());
				testimonialBO.setMessage(testimonial.getMessage());
				testimonialBO.setImageName(testimonial.getImageName());
				testimonialBO.setS_No(testimonial.getS_No());
				testingbo.add(testimonialBO);
			});
		}
		return testingbo;
	}

	@Override
	public TestimonialBO getEditTestimonial(TestimonialBO testimonialBo) {
		// TODO Auto-generated method stub
		TestimonialVO testimonialVo=new TestimonialVO();
		TestimonialBO testimonialBO=new TestimonialBO();
		testimonialVo.setTestimonialId(testimonialBo.getTestimonialId());
		testimonialVo.setIsDelete(false);
		testimonialVo=testimonialDao.getTestimonialObject(testimonialVo);
		if(null!=testimonialVo){
			testimonialBO.setName(testimonialVo.getName());
			testimonialBO.setProfession(testimonialVo.getProfession());
			testimonialBO.setTitleName(testimonialVo.getTitleName());
			testimonialBO.setMessage(testimonialVo.getMessage());
			testimonialBO.setImageName(testimonialVo.getImageName());
			testimonialBO.setTestimonialId(testimonialVo.getTestimonialId());
		}
		return testimonialBO;
	}

	@Override
	public boolean postEditTestimonial(TestimonialBO testimonialBo) {
		// TODO Auto-generated method stub
		TestimonialVO testimonialVo=new TestimonialVO();
		testimonialVo.setTestimonialId(testimonialBo.getTestimonialId());
		testimonialVo.setIsDelete(false);
		testimonialVo=testimonialDao.getTestimonialObject(testimonialVo);
		if(null!=testimonialVo){
		testimonialVo.setImageName(testimonialBo.getImageName());
		testimonialVo.setName(testimonialBo.getName());
		testimonialVo.setProfession(testimonialBo.getProfession());
		testimonialVo.setMessage(testimonialBo.getMessage());
		testimonialVo.setTitleName(testimonialBo.getTitleName());
		testimonialVo.setIsDelete(false);
		testimonialVo.setSending_status(true);
		testimonialVo.setCreatedBy(testimonialVo.getCreatedBy());
		testimonialVo.setCreatedTime(testimonialVo.getCreatedTime());
		testimonialVo.setModifiedTime(new Date());
		testimonialVo.setModifiedBy(testimonialVo.getCreatedBy());
		}
		return testimonialDao.postEditTestimonial(testimonialVo);
	}

	@Override
	public boolean deleteTestimonial(TestimonialBO testimonialBo) {
		// TODO Auto-generated method stub
		TestimonialVO testimonialVo=new TestimonialVO();
		testimonialVo.setTestimonialId(testimonialBo.getTestimonialId());
		testimonialVo.setIsDelete(true);
		testimonialVo.setSending_status(false);
		return testimonialDao.deleteTestimonial(testimonialVo);
	}

	@Override
	public long retrieveTestimonialCount(TestimonialBO testimonialBo) {
		// TODO Auto-generated method stub
		TestimonialVO testimonialVo=new TestimonialVO();
		testimonialVo.setIsDelete(false);
		testimonialVo.setSending_status(true);
		return testimonialDao.retrieveTestimonialCount(testimonialVo);
	}

	@Override
	public List<TestimonialBO> viewTestimonial(TestimonialBO testimonialBo) {
		// TODO Auto-generated method stub
		List<TestimonialBO> testinglist=new ArrayList<TestimonialBO>();
		TestimonialVO testimonialVo=new TestimonialVO();
		testimonialVo.setIsDelete(false);
		testimonialVo.setSending_status(true);
		List<TestimonialVO> testimonial =testimonialDao.viewTestimonial(testimonialVo);
		if(null!=testimonial && !testimonial.isEmpty() && testimonial.size()>0){
			for(TestimonialVO testimonialVO:testimonial){
				TestimonialBO testimonialBO=new TestimonialBO();
				testimonialBO.setTitleName(testimonialVO.getTitleName());
				testimonialBO.setName(testimonialVO.getName());
				testimonialBO.setProfession(testimonialVO.getProfession());
				testimonialBO.setMessage(testimonialVO.getMessage());
				testimonialBO.setImageName(testimonialVO.getImageName());
				testinglist.add(testimonialBO);
		}

	}
		return testinglist;
  }

	@Override
	public int searchTestimonial(TestimonialBO testimonialBO) {
		// TODO Auto-generated method stub
		TestimonialVO testimonialVO=new TestimonialVO ();
		testimonialVO.setName(testimonialBO.getName());
		testimonialVO.setIsDelete(false);
		int totalTestimonial=testimonialDao.searchTestimonial(testimonialVO);
		return totalTestimonial;
		
	}

	@Override
	public List<TestimonialBO> searchTestimonialData(TestimonialBO testimonialBO) {
		// TODO Auto-generated method stub
		List<TestimonialVO> testimonialList=new ArrayList<TestimonialVO>();
		List<TestimonialBO> testimonialListBO=new ArrayList<TestimonialBO>();
		List<TestimonialBO> listTestimonialBO=new ArrayList<TestimonialBO>();
		TestimonialVO testimonialVO=new TestimonialVO();
		testimonialVO.setRecordIndex(testimonialBO.getRecordIndex());
		testimonialVO.setMaxRecord(testimonialBO.getMaxRecord());
		testimonialVO.setName(testimonialBO.getName());
		testimonialVO.setIsDelete(false);
		testimonialList=testimonialDao.searchTestimonialData(testimonialVO);
		if(null!=testimonialList && testimonialList.size()>0 && !testimonialList.isEmpty()){
			int sNo=testimonialBO.getRecordIndex();
			for(TestimonialVO testimonial:testimonialList){
				
				TestimonialBO testimonials=new TestimonialBO();
				
				testimonials.setTestimonialId(testimonial.getTestimonialId());
				testimonials.setName(testimonial.getName());
				testimonials.setImageName(testimonial.getImageName());
				testimonials.setMessage(testimonial.getMessage());
				testimonials.setProfession(testimonial.getProfession());
				testimonials.setTitleName(testimonial.getTitleName());
				testimonials.setS_No(++sNo);
				testimonialListBO.add(testimonials);
			}
			/*testimonialListBO.forEach(testimonial->{
				
				TestimonialBO testimonialbo=new TestimonialBO();
				
				testimonialbo.setTestimonialId(testimonial.getTestimonialId());
				testimonialbo.setName(testimonial.getName());
				testimonialbo.setImageName(testimonial.getImageName());
				testimonialbo.setMessage(testimonial.getMessage());
				testimonialbo.setProfession(testimonial.getProfession());
				testimonialbo.setTitleName(testimonial.getTitleName());
				testimonialbo.setS_No(testimonial.getS_No());
				listTestimonialBO.add(testimonialbo);
			});*/
		}
		return testimonialListBO;
		
	}
}
