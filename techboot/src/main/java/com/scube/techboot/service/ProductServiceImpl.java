package com.scube.techboot.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.dao.ProductServiceDao;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.ProductServiceVO;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductServiceDao productServiceDao;

	@Override
	public ProductServiceBO createServices(ProductServiceBO productServiceBO) {
		ProductServiceVO productServiceVO=new ProductServiceVO();
		CompanyVO companyVO=new CompanyVO();
		productServiceVO.setServiceName(productServiceBO.getServiceName());
		productServiceVO.setFees(productServiceBO.getFees());
		productServiceVO.setDuration(productServiceBO.getDuration());
		//productServiceVO.setStartDate(productServiceBO.getStartDate());
		//productServiceVO.setEndDate(productServiceBO.getEndDate());
		productServiceVO.setServiceSpecification(productServiceBO.getServiceSpecification());
		productServiceVO.setIsDelete(false);
		productServiceVO.setSending_status(true);
		companyVO.setCompanyId(productServiceBO.getCompanyBO().getCompanyId());
		productServiceVO.setCompanyVO(companyVO);
		productServiceVO.setCreatedTime(new Date());
		productServiceVO.setCreatedBy(productServiceBO.getCreatedBy());
		return  productServiceDao.createServices(productServiceVO);

	}
	@Override
	public List<ProductServiceBO> listservice(ProductServiceBO serviceBO) {
		// TODO Auto-generated method stub
		ProductServiceVO productServiceVO=new ProductServiceVO();
		List<ProductServiceVO> serviceList=new ArrayList<ProductServiceVO>();
		List<ProductServiceBO> serviceListBO=new ArrayList<ProductServiceBO>();
		//comapny
		if(null!=serviceBO.getCompanyBO()){
			CompanyVO companyVO=new CompanyVO(); 
			serviceBO.getCompanyBO().getCompanyId();
			companyVO.setCompanyId(serviceBO.getCompanyBO().getCompanyId());
			productServiceVO.setCompanyVO(companyVO);
		}
		productServiceVO.setIsDelete(false);
		serviceList=productServiceDao.listViewService(productServiceVO);
		if(serviceList!=null && serviceList.size()>0 && !serviceList.isEmpty()){
			serviceList.forEach(ProductService->{
				ProductServiceBO productServiceBO=new ProductServiceBO();
				CompanyBO company=new CompanyBO();
				productServiceBO.setServiceId(ProductService.getServiceId());
				productServiceBO.setServiceName(ProductService.getServiceName());
				productServiceBO.setServiceSpecification(ProductService.getServiceSpecification());
				//double to int 		
				double rs=ProductService.getFees();
				int rupees=(int) rs;
				productServiceBO.setRupees(rupees);
				productServiceBO.setDuration(ProductService.getDuration());
				company.setCompanyId(ProductService.getCompanyVO().getCompanyId());
				company.setCompanyName(ProductService.getCompanyVO().getCompanyName());
				company.setCompanyType(ProductService.getCompanyVO().getCompanyType());
				company.setCompanyPersonName(ProductService.getCompanyVO().getCompanyPersonName());
				company.setemailAddress(ProductService.getCompanyVO().getemailAddress());
				company.setPassword(ProductService.getCompanyVO().getPassword());
				productServiceBO.setCompanyBO(company);
				serviceListBO.add(productServiceBO);
			});
		}
		return serviceListBO;
	}
	/*@Override
	public ProductServiceBO getServiceObject(ProductServiceBO productServiceBO) {
		// TODO Auto-generated method stub
		ProductServiceVO productServiceVO=new ProductServiceVO();
		if(null!=productServiceBO && null!=productServiceBO.getCompanyBO() && 0<productServiceBO.getCompanyBO().getCompanyId()) {
		int id=productServiceBO.getCompanyBO().getCompanyId();
		CompanyVO ref=new CompanyVO();
		ref.setCompanyId(id);
		productServiceVO.setCompanyVO(ref);
		}else {
		productServiceVO.setServiceId(productServiceBO.getServiceId());}
		productServiceVO= productServiceDao.getServiceObject(productServiceVO);
		if(null!=productServiceVO){
			CompanyBO companyBO=new CompanyBO();
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			companyBO.setCompanyId(productServiceVO.getCompanyVO().getCompanyId());
			companyBO.setCompanyName(productServiceVO.getCompanyVO().getCompanyName());
			companyBO.setCompanyPersonName(productServiceVO.getCompanyVO().getCompanyPersonName());
			companyBO.setCompanyType(productServiceVO.getCompanyVO().getCompanyType());
			companyBO.setCompanyWebSite(productServiceVO.getCompanyVO().getCompanyWebSite());
			companyBO.setConformEmailAddress(productServiceVO.getCompanyVO().getConformEmailAddress());
			companyBO.setContectNumber(productServiceVO.getCompanyVO().getContectNumber());
			companyBO.setImageName(productServiceVO.getCompanyVO().getImageName());
			productServiceBO.setCompanyBO(companyBO);
			 String startDate=sim.format(productServiceVO.getStartDate());
			 productServiceBO.setBeginDate(startDate); 
			 String enddate=sim.format(productServiceVO.getEndDate());
			 productServiceBO.setLastDate(enddate);
			 productServiceBO.setServiceName(productServiceVO.getServiceName());
			 productServiceBO.setServiceId(productServiceVO.getServiceId());
			 productServiceBO.setFees(productServiceVO.getFees());
			 productServiceBO.setDuration(productServiceVO.getDuration());
			 productServiceBO.setStartDate(productServiceVO.getStartDate());
			 productServiceBO.setEndDate(productServiceVO.getEndDate());
			 productServiceBO.setServiceSpecification(productServiceVO.getServiceSpecification());
		}
		return productServiceBO;
	}*/
	@Override
	public Boolean serviceUpdate(ProductServiceBO productServiceBo) {
		// TODO Auto-generated method stub
		ProductServiceVO productServiceVO=new ProductServiceVO();
		ProductServiceVO serviceVO=new ProductServiceVO();
		CompanyVO companyVo=new CompanyVO();
		if(0!=productServiceBo.getServiceId()){
			productServiceVO.setServiceId(productServiceBo.getServiceId());
			serviceVO=productServiceDao.retrieveService(productServiceVO);
		}
		productServiceVO.setServiceName(productServiceBo.getServiceName());
		productServiceVO.setServiceSpecification(productServiceBo.getServiceSpecification());
		productServiceVO.setServiceId(productServiceBo.getServiceId());
		productServiceVO.setFees(productServiceBo.getFees());
		productServiceVO.setDuration(productServiceBo.getDuration());
		//productServiceVO.setStartDate(productServiceBo.getStartDate());
		//productServiceVO.setEndDate(productServiceBo.getEndDate());
		productServiceVO.setIsDelete(serviceVO.getIsDelete());
		productServiceVO.setSending_status(serviceVO.getSending_status());
		companyVo.setCompanyId(productServiceBo.getCompanyBO().getCompanyId());
		productServiceVO.setCompanyVO(companyVo);
		productServiceVO.setCreatedTime(serviceVO.getCreatedTime());
		productServiceVO.setCreatedBy(serviceVO.getCreatedBy());
		productServiceVO.setModifiedTime(new Date());

		return productServiceDao.serviceUpdateDao(productServiceVO);
	}
	@Override
	public Boolean deleteService(ProductServiceBO service) {
		ProductServiceVO productServiceVo=new ProductServiceVO();
		productServiceVo.setServiceId(service.getServiceId());
		productServiceVo.setSending_status(false);
		productServiceVo.setIsDelete(true);
		return productServiceDao.deleteService(productServiceVo);

	}
	@Override
	public List<ProductServiceBO> listofpageservice(ProductServiceBO serviceBO) {
		// TODO Auto-generated method stub
		List<ProductServiceVO> serviceList=new ArrayList<ProductServiceVO>();
		List<ProductServiceBO> servicelist=new ArrayList<ProductServiceBO>();
		ProductServiceVO productServiceVO=new ProductServiceVO();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		int s_No=serviceBO.getRecordIndex();
		//comapny
		if(null!=serviceBO.getCompanyBO()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(serviceBO.getCompanyBO().getCompanyId());
			productServiceVO.setCompanyVO(companyVO);
			productServiceVO.setServiceName(serviceBO.getServiceName());
		}//search
		else if(null!=serviceBO.getServiceName()){
			productServiceVO.setServiceName(serviceBO.getServiceName());
		}
		productServiceVO.setMaxRecord(serviceBO.getMaxRecord());
		productServiceVO.setRecordIndex(serviceBO.getRecordIndex());
		productServiceVO.setIsDelete(false);
		serviceList=productServiceDao.listofpageservice(productServiceVO);
		if(serviceList!=null && serviceList.size()>0 && !serviceList.isEmpty()){
			List<ProductServiceBO> serviceListBO=new ArrayList<ProductServiceBO>();
			for(ProductServiceVO service:serviceList){
				ProductServiceBO productServiceBO=new ProductServiceBO();
				CompanyBO company=new CompanyBO();
				productServiceBO.setServiceId(service.getServiceId());
				productServiceBO.setServiceName(service.getServiceName());
				//double to int	
				double rs=service.getFees();
				int rupees=(int) rs;
				productServiceBO.setRupees(rupees);
				productServiceBO.setDuration(service.getDuration());
				productServiceBO.setServiceSpecification(service.getServiceSpecification());
				/*String startDate=sim.format(service.getStartDate());
				productServiceBO.setBeginDate(startDate);
				String enddate=sim.format(service.getEndDate());
				productServiceBO.setLastDate(enddate);*/
				productServiceBO.setS_No(++s_No);
				company.setCompanyId(service.getCompanyVO().getCompanyId());
				company.setCompanyName(service.getCompanyVO().getCompanyName());
				productServiceBO.setCompanyBO(company);
				serviceListBO.add(productServiceBO);
			}
			serviceListBO.forEach(service->{
				ProductServiceBO productServiceBO=new ProductServiceBO();
				CompanyBO company=new CompanyBO();
				productServiceBO.setServiceId(service.getServiceId());
				productServiceBO.setServiceName(service.getServiceName());	
				productServiceBO.setRupees(service.getRupees());
				productServiceBO.setDuration(service.getDuration());
				productServiceBO.setServiceSpecification(service.getServiceSpecification());
				//String startDate=sim.format(services.getStartDate());
				//productServiceBO.setBeginDate(service.getBeginDate());
				//String enddate=sim.format(services.getEndDate());
				//productServiceBO.setLastDate(service.getLastDate());
				productServiceBO.setS_No(service.getS_No());
				company.setCompanyId(service.getCompanyBO().getCompanyId());
				company.setCompanyName(service.getCompanyBO().getCompanyName());
				productServiceBO.setCompanyBO(company);
				servicelist.add(productServiceBO);

			});
		}

		return servicelist;
	}

	@Override
	public int searchPageService(ProductServiceBO productServiceBO) {
		// TODO Auto-generated method stub
		int totalService;
		ProductServiceVO productServiceVO=new ProductServiceVO();
		if(null!=productServiceBO.getCompanyBO() && 0!=productServiceBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(productServiceBO.getCompanyBO().getCompanyId());
			productServiceVO.setCompanyVO(companyVO);
			productServiceVO.setIsDelete(false);
			productServiceVO.setServiceName(productServiceBO.getServiceName());
		}
		return totalService=productServiceDao.searchPageService(productServiceVO);

	}
	@Override
	public boolean isValidServiceName(ProductServiceBO productServiceBO) {
		// TODO Auto-generated method stub
		ProductServiceVO productServiceVO=new ProductServiceVO();
		CompanyVO companyVo=new CompanyVO();
		productServiceVO.setServiceName(productServiceBO.getServiceName());
		companyVo.setCompanyId(productServiceBO.getCompanyBO().getCompanyId());
		productServiceVO.setCompanyVO(companyVo);
		productServiceVO.setIsDelete(false);
		return productServiceDao.isValidServiceName(productServiceVO);
	}
	
	@Override
	public ProductServiceBO getServiceObject(ProductServiceBO productServiceBo) {
		// TODO Auto-generated method stub
		
		ProductServiceVO productServiceVO=new ProductServiceVO();
		if(null!=productServiceBo&& null!=productServiceBo.getCompanyBO() && 0<productServiceBo.getCompanyBO().getCompanyId()) {
		int id=productServiceBo.getCompanyBO().getCompanyId();
		CompanyVO ref=new CompanyVO();
		ref.setCompanyId(id);
		productServiceVO.setCompanyVO(ref);
		}else {
		productServiceVO.setServiceId(productServiceBo.getServiceId());}
		productServiceVO= productServiceDao.getserviceObject(productServiceVO);
		if(null!=productServiceVO){
			CompanyBO companyBO=new CompanyBO();
			//SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			companyBO.setCompanyId(productServiceVO.getCompanyVO().getCompanyId());
			companyBO.setCompanyName(productServiceVO.getCompanyVO().getCompanyName());
			//companyBO.setCompanyPersonName(productServiceVO.getCompanyVO().getCompanyPersonName());
			//companyBO.setCompanyType(productServiceVO.getCompanyVO().getCompanyType());
			//companyBO.setCompanyWebSite(productServiceVO.getCompanyVO().getCompanyWebSite());
			//companyBO.setConformEmailAddress(productServiceVO.getCompanyVO().getConformEmailAddress());
		//	companyBO.setContectNumber(productServiceVO.getCompanyVO().getContectNumber());
		  companyBO.setImageName(productServiceVO.getCompanyVO().getImageName());
			productServiceBo.setCompanyBO(companyBO);
			/* String startDate=sim.format(productServiceVO.getStartDate());
			 productServiceBO.setBeginDate(startDate); 
			 String enddate=sim.format(productServiceVO.getEndDate());
			 productServiceBO.setLastDate(enddate);*/
			 productServiceBo.setServiceName(productServiceVO.getServiceName());
			 productServiceBo.setServiceId(productServiceVO.getServiceId());
			 productServiceBo.setFees(productServiceVO.getFees());
			 productServiceBo.setDuration(productServiceVO.getDuration());
			// productServiceBo.setStartDate(productServiceVO.getStartDate());
			// productServiceBo.setEndDate(productServiceVO.getEndDate());
			 productServiceBo.setServiceSpecification(productServiceVO.getServiceSpecification());
		}
		return productServiceBo;
	}
			



}
