package com.scube.techboot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.bo.TestimonialBO;

public interface TestimonialService {
	
	TestimonialBO saveTestimonial(TestimonialBO testimonialBo, HttpSession session);

	long retriveOfTestimonial(TestimonialBO testimonialBo);
	
	List<TestimonialBO> viewListTestimonial(TestimonialBO testimonialBo);
	
	TestimonialBO getEditTestimonial(TestimonialBO testimonialBo);
	
	boolean postEditTestimonial(TestimonialBO testimonialBo);

	boolean deleteTestimonial(TestimonialBO testimonialBo);

	long retrieveTestimonialCount(TestimonialBO testimonialBo);

	List<TestimonialBO> viewTestimonial(TestimonialBO testimonialBo);

	int searchTestimonial(TestimonialBO testimonialBO);

	List<TestimonialBO> searchTestimonialData(TestimonialBO testimonialBO);


	

	

	

	

	

}
