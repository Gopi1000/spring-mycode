package com.scube.techboot.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CampaignBO;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.dao.CampaignDao;
import com.scube.techboot.dao.CustomerDao;
import com.scube.techboot.vo.CampaignVO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.ProductServiceVO;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService{

	@Autowired
	private CampaignDao campaignDao;


	@Autowired
	private CustomerDao customerDao;


	@Override
	public CampaignBO saveCompaign(CampaignBO campaignBO, HttpSession session) {
		// TODO Auto-generated method stub

		CampaignVO campaignVO=new CampaignVO();
		CompanyVO companyVO=new CompanyVO();
		campaignVO.setCampaignName(campaignBO.getCampaignName());
		campaignVO.setMessage(campaignBO.getMessage());
		campaignVO.setStartedTime(campaignBO.getStartedTime());
		campaignVO.setEndTime(campaignBO.getEndTime());
		campaignVO.setCategory(campaignBO.getCategory());
		campaignVO.setIsDelete(false);
		campaignVO.setCreatedTime(new Date());
		campaignVO.setSending_status(true);
		//service

		if(campaignBO.getProductService().getServiceId()!=0){

			ProductServiceVO productServiceVo=new ProductServiceVO();
			productServiceVo.setServiceId(campaignBO.getProductService().getServiceId());
			campaignVO.setProductService(productServiceVo);
		}

		if(campaignBO.getCourse().getCourseId()!=0)
		{
			CourseVO courseVo=new CourseVO();
			courseVo.setCourseId(campaignBO.getCourse().getCourseId());
			campaignVO.setCourse(courseVo);
		}

		//company
		int companyId=campaignBO.getCompanyBO().getCompanyId();
		companyVO.setCompanyId(companyId);
		campaignVO.setCompanyVO(companyVO);

		if(null!=session.getAttribute("adminId")){
			long id=(long) session.getAttribute("adminId");
			campaignVO.setCreatedBy(id);
		}
		CampaignVO campaign=campaignDao.saveCompaignDao(campaignVO);
		if(null!=campaign){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			//ProductServiceBO productService=new ProductServiceBO();
			CompanyBO company=new CompanyBO();
			ProductServiceBO productServiceBO=new ProductServiceBO();
			CourseBO courseBO=new CourseBO();

			int id=campaign.getCompanyVO().getCompanyId();
			company.setCompanyName(campaign.getCompanyVO().getCompanyName());
			company.setCompanyId(id);
			campaignBO.setCompanyBO(company);
			campaignBO.setMessage(campaign.getMessage());
			String startDate=format.format(campaign.getStartedTime());
			campaignBO.setStDate(startDate);
			String endDate=format.format(campaign.getEndTime());
			campaignBO.setEdDate(endDate);
			campaignBO.setStartedTime(campaign.getStartedTime());
			campaignBO.setEndTime(campaign.getEndTime());
			campaignBO.setCampaignId(campaign.getCampaignId());
			campaignBO.setCampaignName(campaign.getCampaignName());
			campaignBO.setCategory(campaign.getCategory());

			if(null!=campaign.getProductService()){

				productServiceBO.setServiceId(campaign.getProductService().getServiceId());
				productServiceBO.setServiceName(campaign.getProductService().getServiceName());
				productServiceBO.setServiceSpecification(campaign.getProductService().getServiceSpecification());
				campaignBO.setProductService(productServiceBO);

			}

			if(null!=campaign.getCourse())
			{
				courseBO.setCourseId(campaign.getCourse().getCourseId());
				courseBO.setCourseName(campaign.getCourse().getCourseName());
				courseBO.setAuthorName(campaign.getCourse().getAuthorName());
				campaignBO.setCourse(courseBO);

			}

			campaignBO.setCreatedTime(campaign.getCreatedTime());
			campaignBO.setCreatedBy(campaign.getCreatedBy());
		}

		return campaignBO;
	}


	@Override
	public long getListOfCampaign(CampaignBO campaignBO) {
		// TODO Auto-generated method stub
		CampaignVO campaignVO=new CampaignVO();
		campaignVO.setIsDelete(campaignBO.getIsDelete());
		campaignVO.setSending_status(campaignBO.getSending_status());
		return campaignDao.getListCampaignDao(campaignVO);
	}


	@Override
	public CampaignBO getCampaignObject(CampaignBO campaignBo) {
		CampaignVO campaignVo=new CampaignVO();
		campaignVo.setCampaignId(campaignBo.getCampaignId());
		campaignVo=campaignDao.getCampaignObject(campaignVo);
		if(null!=campaignVo){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			ProductServiceBO productService=new ProductServiceBO();
			CourseBO courseBO=new CourseBO();
			CompanyBO companyBO=new CompanyBO();
			int id=campaignVo.getCompanyVO().getCompanyId();
			companyBO.setCompanyName(campaignVo.getCompanyVO().getCompanyName());
			companyBO.setCompanyId(id);
			campaignBo.setCompanyBO(companyBO);
			campaignBo.setMessage(campaignVo.getMessage());
			String startDate=format.format(campaignVo.getStartedTime());
			campaignBo.setStDate(startDate);
			String endDate=format.format(campaignVo.getEndTime());
			campaignBo.setEdDate(endDate);
			campaignBo.setStartedTime(campaignVo.getStartedTime());
			campaignBo.setEndTime(campaignVo.getEndTime());
			campaignBo.setCampaignId(campaignVo.getCampaignId());
			campaignBo.setCampaignName(campaignVo.getCampaignName());
			campaignBo.setCategory(campaignVo.getCategory());
			if(null!=campaignVo.getProductService()){

				productService.setServiceId(campaignVo.getProductService().getServiceId());
				productService.setServiceName(campaignVo.getProductService().getServiceName());
				/*productService.setServiceSpecification(campaignVo.getProductService().getServiceSpecification());
				productService.setDuration(campaignVo.getProductService().getDuration());
				productService.setFees(campaignVo.getProductService().getFees());
				String date_start=format.format(campaignVo.getProductService().getStartDate());
				String date_end=format.format(campaignVo.getProductService().getEndDate());
				productService.setBeginDate(date_start);
				productService.setLastDate(date_end);*/
				campaignBo.setProductService(productService);

			}

			if(null!=campaignVo.getCourse())
			{
				courseBO.setCourseId(campaignVo.getCourse().getCourseId());
				courseBO.setCourseName(campaignVo.getCourse().getCourseName());
				campaignBo.setCourse(courseBO);
			}			
			campaignBo.setCreatedTime(campaignVo.getCreatedTime());
			campaignBo.setCreatedBy(campaignVo.getCreatedBy());
		}
		return campaignBo;
	}


	@Override
	public boolean updateCampaign(CampaignBO campaignBo,HttpSession session) {

		ProductServiceVO productServiceVo=new ProductServiceVO();
		CampaignVO campaignVo=new CampaignVO();
		campaignVo.setCampaignId(campaignBo.getCampaignId());
		campaignVo.setCampaignName(campaignBo.getCampaignName());
		campaignVo.setCategory(campaignBo.getCategory());
		/*productServiceVo.setServiceId(campaignBo.getProductService().getServiceId());
		campaignVo.setProductService(productServiceVo);*/
		campaignVo.setSending_status(true);
		campaignVo.setIsDelete(false);
		campaignVo.setModifiedTime(new Date());
		campaignVo.setMessage(campaignBo.getMessage());
		campaignVo.setEndTime(campaignBo.getEndTime());
		campaignVo.setStartedTime(campaignBo.getStartedTime());
		if(null!=session.getAttribute("edit-CompanyId")){
			int id=(int) session.getAttribute("edit-CompanyId");
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(id);
			campaignVo.setCompanyVO(companyVO);
		}
		if(campaignBo.getProductService().getServiceId()!=0){
			productServiceVo.setServiceId(campaignBo.getProductService().getServiceId());
			productServiceVo.setServiceName(campaignBo.getProductService().getServiceName());
			campaignVo.setProductService(productServiceVo);
		}
		if(campaignBo.getCourse().getCourseId()!=0)
		{
			CourseVO courseVo=new CourseVO();
			courseVo.setCourseId(campaignBo.getCourse().getCourseId());
			courseVo.setCourseName(campaignBo.getCourse().getCourseName());
			campaignVo.setCourse(courseVo);;
		}
		if(null!=session.getAttribute("campaignCT") && null!=session.getAttribute("campaignCB")){
			long id=(long) session.getAttribute("campaignCB");
			Date date=(Date) session.getAttribute("campaignCT");
			campaignVo.setCreatedBy(id);
			campaignVo.setCreatedTime(date);
		}
		return campaignDao.updateCampaign(campaignVo);
	}


	@Override
	public boolean deleteCampaign(CampaignBO campaignBo) {
		CampaignVO campaignVo=new CampaignVO();
		campaignVo.setCampaignId(campaignBo.getCampaignId());
		campaignVo.setIsDelete(true);
		campaignVo.setSending_status(false);
		return campaignDao.deleteCampaign(campaignVo);
	}


	@Override
	public boolean checkCampaign(ProductServiceBO service) {
		CampaignVO campaignVo=new CampaignVO();
		ProductServiceVO serviceVo=new ProductServiceVO();
		campaignVo.setIsDelete(false);
		serviceVo.setServiceId(service.getServiceId());
		campaignVo.setProductService(serviceVo);
		return campaignDao.checkCampaign(campaignVo);
	}
	@Override
	public long getListOfCompanyCampaign(CampaignBO campaignBO) {
		// TODO Auto-generated method stub

		CampaignVO campaignVO=new CampaignVO();
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(campaignBO.getCompanyBO().getCompanyId());
		campaignVO.setCompanyVO(companyVO);
		campaignVO.setIsDelete(campaignBO.getIsDelete());
		campaignVO.setSending_status(campaignBO.getSending_status());
		return campaignDao.getListOfCompanyCampaign(campaignVO);
	}

	@Override
	public List<CampaignBO> listOfCampaign(CampaignBO campaignBO) {
		// TODO Auto-generated method stub
		CampaignVO campaignVo=new CampaignVO();
		List<CampaignVO> listVo=new ArrayList<CampaignVO>();
		List<CampaignBO> listBo=new ArrayList<CampaignBO>();
		if(null!=campaignBO.getCompanyBO()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(campaignBO.getCompanyBO().getCompanyId());
			campaignVo.setCompanyVO(companyVo);
		}
		campaignVo.setIsDelete(campaignBO.getIsDelete());
		campaignVo.setSending_status(campaignBO.getSending_status());
		campaignVo.setRecordIndex(campaignBO.getRecordIndex());
		campaignVo.setMaxRecord(campaignBO.getMaxRecord());
		List<CampaignVO> listVO=campaignDao.listOfCampaign(campaignVo);
		if(null!=listVO && !listVO.isEmpty() && listVO.size()>0){
			int sNo=campaignBO.getRecordIndex();
			for(CampaignVO campaign:listVO){
				CampaignVO campaignVO=new CampaignVO();
				ProductServiceVO productServiceVO=new ProductServiceVO();
				CourseVO courseVO=new CourseVO();
				campaignVO.setCampaignId(campaign.getCampaignId());
				campaignVO.setCampaignName(campaign.getCampaignName());
				campaignVO.setCategory(campaign.getCategory());
				campaignVO.setMessage(campaign.getMessage());
				campaignVO.setIsDelete(campaign.getIsDelete());
				campaignVO.setSending_status(campaign.getSending_status());

				if(null!=campaign.getProductService()){

					productServiceVO.setServiceId(campaign.getProductService().getServiceId());
					productServiceVO.setServiceName(campaign.getProductService().getServiceName());
					productServiceVO.setServiceSpecification(campaign.getProductService().getServiceSpecification());
					campaignVO.setProductService(productServiceVO);

				}

				if(null!=campaign.getCourse())
				{
					courseVO.setCourseId(campaign.getCourse().getCourseId());
					courseVO.setCourseName(campaign.getCourse().getCourseName());
					courseVO.setAuthorName(campaign.getCourse().getAuthorName());
					campaignVO.setCourse(courseVO);
				}

				campaignVO.setS_No(++sNo);
				listVo.add(campaignVO);
			}
			listVo.forEach(campaign->{
				CampaignBO campaignBo=new CampaignBO();
				ProductServiceBO productServiceBO=new ProductServiceBO();
				CourseBO courseBO=new CourseBO();
				campaignBo.setCampaignId(campaign.getCampaignId());
				campaignBo.setCampaignName(campaign.getCampaignName());
				campaignBo.setCategory(campaign.getCategory());
				campaignBo.setMessage(campaign.getMessage());
				campaignBo.setIsDelete(campaign.getIsDelete());
				campaignBo.setSending_status(campaign.getSending_status());

				if(null!=campaign.getProductService()){

					productServiceBO.setServiceId(campaign.getProductService().getServiceId());
					productServiceBO.setServiceName(campaign.getProductService().getServiceName());
					productServiceBO.setServiceSpecification(campaign.getProductService().getServiceSpecification());
					campaignBo.setProductService(productServiceBO);

				}

				if(null!=campaign.getCourse())
				{
					courseBO.setCourseId(campaign.getCourse().getCourseId());
					courseBO.setCourseName(campaign.getCourse().getCourseName());
					courseBO.setAuthorName(campaign.getCourse().getAuthorName());
					campaignBo.setCourse(courseBO);

				}

				campaignBo.setS_No(campaign.getS_No());
				listBo.add(campaignBo);

			});
		}

		return listBo;
	}


	@Override
	public List<CampaignBO> searchCampaign(CampaignBO campaignBO) {
		// TODO Auto-generated method stub
		CampaignVO campaignVo=new CampaignVO();
		List<CampaignVO> listVo=new ArrayList<CampaignVO>();
		List<CampaignBO> listBo=new ArrayList<CampaignBO>();
		if(null!=campaignBO.getCompanyBO()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(campaignBO.getCompanyBO().getCompanyId());
			campaignVo.setCompanyVO(companyVo);
		}
		/*ProductServiceVO productService=new ProductServiceVO();
		productService.setServiceName(campaignBO.getProductService().getServiceName());
		campaignVo.setProductService(productService);	*/
		campaignVo.setCategory(campaignBO.getCategory());
		campaignVo.setIsDelete(campaignBO.getIsDelete());
		campaignVo.setSending_status(campaignBO.getSending_status());
		campaignVo.setRecordIndex(campaignBO.getRecordIndex());
		campaignVo.setMaxRecord(campaignBO.getMaxRecord());
		campaignVo.setCampaignName(campaignBO.getCampaignName());
		List<CampaignVO> listVO=campaignDao.searchCampaign(campaignVo);
		if(null!=listVO && !listVO.isEmpty() && listVO.size()>0){
			int sNo=campaignBO.getRecordIndex();
			for(CampaignVO campaign:listVO){
				CampaignVO campaignVO=new CampaignVO();
				CourseVO courseVO=new CourseVO();
				ProductServiceVO productServiceVo=new ProductServiceVO();
				campaignVO.setCampaignId(campaign.getCampaignId());
				campaignVO.setCampaignName(campaign.getCampaignName());
				campaignVO.setCategory(campaign.getCategory());
				campaignVO.setMessage(campaign.getMessage());
				campaignVO.setIsDelete(campaign.getIsDelete());
				campaignVO.setSending_status(campaign.getSending_status());
	/*			productServiceVo.setServiceId(campaign.getProductService().getServiceId());
				productServiceVo.setServiceName(campaign.getProductService().getServiceName());*/
				if(null!=campaign.getProductService()){

					productServiceVo.setServiceId(campaign.getProductService().getServiceId());
					productServiceVo.setServiceName(campaign.getProductService().getServiceName());
					productServiceVo.setServiceSpecification(campaign.getProductService().getServiceSpecification());
					campaignVO.setProductService(productServiceVo);

				}

				if(null!=campaign.getCourse())
				{
					courseVO.setCourseId(campaign.getCourse().getCourseId());
					courseVO.setCourseName(campaign.getCourse().getCourseName());
					courseVO.setAuthorName(campaign.getCourse().getAuthorName());
					campaignVO.setCourse(courseVO);
				}
				campaignVO.setS_No(++sNo);
				campaignVO.setProductService(productServiceVo);
				listVo.add(campaignVO);
			}
			listVo.forEach(campaign->{
				CampaignBO campaignBo=new CampaignBO();
				ProductServiceBO productServiceBo=new ProductServiceBO();
				CourseBO courseBO=new CourseBO();
				campaignBo.setCampaignId(campaign.getCampaignId());
				campaignBo.setCampaignName(campaign.getCampaignName());
				campaignBo.setCategory(campaign.getCategory());
				campaignBo.setMessage(campaign.getMessage());
				campaignBo.setIsDelete(campaign.getIsDelete());
				campaignBo.setSending_status(campaign.getSending_status());
				productServiceBo.setServiceId(campaign.getProductService().getServiceId());
				productServiceBo.setServiceName(campaign.getProductService().getServiceName());
				campaignBo.setProductService(productServiceBo);
				if(null!=campaign.getProductService()){

					productServiceBo.setServiceId(campaign.getProductService().getServiceId());
					productServiceBo.setServiceName(campaign.getProductService().getServiceName());
					productServiceBo.setServiceSpecification(campaign.getProductService().getServiceSpecification());
					campaignBo.setProductService(productServiceBo);

				}

				if(null!=campaign.getCourse())
				{
					courseBO.setCourseId(campaign.getCourse().getCourseId());
					courseBO.setCourseName(campaign.getCourse().getCourseName());
					courseBO.setAuthorName(campaign.getCourse().getAuthorName());
					campaignBo.setCourse(courseBO);

				}
				campaignBo.setS_No(campaign.getS_No());
				listBo.add(campaignBo);

			});
		}

		return listBo;
	}


	@Override
	public long listOfCompanyCampaign(CampaignBO campaignBO) {
		// TODO Auto-generated method stub
		CampaignVO campaignVO=new CampaignVO();	
		CompanyVO companyVo=new CompanyVO();
		companyVo.setCompanyId(campaignBO.getCompanyBO().getCompanyId());
		campaignVO.setCampaignName(campaignBO.getCampaignName());
		campaignVO.setIsDelete(campaignBO.getIsDelete());
		campaignVO.setSending_status(campaignBO.getSending_status());
		campaignVO.setCategory(campaignBO.getCategory());
		campaignVO.setCompanyVO(companyVo);
		return campaignDao.listOfCompanyCampaign(campaignVO);
	}


	@Override
	public long listOfCampaignCount(CampaignBO campaignBO) {
		// TODO Auto-generated method stub
		CampaignVO campaignVO=new CampaignVO();		
		campaignVO.setCampaignName(campaignBO.getCampaignName());
		campaignVO.setIsDelete(campaignBO.getIsDelete());
		campaignVO.setSending_status(campaignBO.getSending_status());
		campaignVO.setCategory(campaignBO.getCategory());
		/*ProductServiceVO productService=new ProductServiceVO();
		productService.setServiceName(campaignBO.getProductService().getServiceName());
		campaignVO.setProductService(productService);*/
		return campaignDao.listOfCampaignCount(campaignVO);
	}


	@Override
	public long lsatObject() {
		// TODO Auto-generated method stub
		return campaignDao.lastObject();

	}


	/*	@Override
	public boolean getSpecification(CampaignBO campaignBO) {
		// TODO Auto-generated method stub
		boolean statusValue=true;
		List<CustomerVO> listVO=new ArrayList<CustomerVO>();
		CustomerVO customerVO=new CustomerVO();
		customerVO.setSpecialization(campaignBO.getProductService().getServiceSpecification());
		customerVO.setIsDelete(false);
		listVO=customerDao.getListSpecialization(customerVO);
		if(null!=listVO && !listVO.isEmpty() && listVO.size()>0){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			listVO.forEach(customer->{
				ManageSms manageSms=new ManageSms();
				long number=customer.getMobileNumber();
				String name=customer.getFirstName();
				String companyName=campaignBO.getCompanyBO().getCompanyName();
				String serviceName=campaignBO.getProductService().getServiceName();
				String duration=campaignBO.getProductService().getDuration();
				String bdate=campaignBO.getProductService().getBeginDate();
				String ldate=campaignBO.getProductService().getLastDate();
				double fees=campaignBO.getProductService().getFees();
				String feess=String.valueOf(fees);
				String message=campaignBO.getMessage();
				String finalmessage="Dear ".concat(name)+","+("Company Name:").concat(companyName)+","+("Service Name:").concat(serviceName)+","+("Message:")
						.concat(message)+","+("Duration:").concat(duration)+","+("Fees:").concat(feess)+","+("Start Date:").concat(bdate)
						+","+("End Date:").concat(ldate);
				System.out.println(finalmessage);
				manageSms.setMessage(finalmessage);
				manageSms.setMobileNumber(number);
				manageSms.setSending_status(false);
				manageSms.setCreatedTime(new Date());
				boolean status=campaignDao.saveManageSms(manageSms);

			});

		}
		return statusValue;

	}*/
}
