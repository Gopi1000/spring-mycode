package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.vo.TestimonialVO;

public interface TestimonialDao {

	TestimonialVO saveTestimonial(TestimonialVO testimonialVo);

	long retriveOfTestimonial(TestimonialVO testimonialVo);

	List<TestimonialVO> viewListTestimonial(TestimonialVO testimonialVo);

	boolean postEditTestimonial(TestimonialVO testimonialVo);

	boolean deleteTestimonial(TestimonialVO testimonialVo);

	long retrieveTestimonialCount(TestimonialVO testimonialVo);

	TestimonialVO getTestimonialObject(TestimonialVO testimonialVo);

	List<TestimonialVO> viewTestimonial(TestimonialVO testimonialVo);

	List<TestimonialVO> searchTestimonialData(TestimonialVO testimonialVO);

	int searchTestimonial(TestimonialVO testimonialVO);


}
