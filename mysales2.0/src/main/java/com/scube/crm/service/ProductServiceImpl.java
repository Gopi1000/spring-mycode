package com.scube.crm.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.dao.ProductServiceDao;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.User;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductServiceDao productServiceDao;
	static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(LeadsServiceImpl.class);
	
	@Override
	public ProductServiceBO createServices(ProductServiceBO productServiceBO) {
		
		User user = new User();
		ProductServiceVO productServiceVO=new ProductServiceVO();
		productServiceVO.setServiceName(productServiceBO.getServiceName());
		productServiceVO.setFees(productServiceBO.getFees());
		productServiceVO.setDuration(productServiceBO.getDuration());
		productServiceVO.setStartDate(productServiceBO.getStartDate());
		productServiceVO.setEndDate(productServiceBO.getEndDate());
		productServiceVO.setServiceSpecification(productServiceBO.getServiceSpecification());
		productServiceVO.setIsDelete(false);
		productServiceVO.setIsActive(true);
		productServiceVO.setCreatedBy(productServiceBO.getCreatedBy());
		if (null != productServiceBO.getAdminLoginBO()) {
			user.setId(productServiceBO.getAdminLoginBO().getId());
			productServiceVO.setUserVO(user);
		}
		return  productServiceDao.createServices(productServiceVO);

	}
	@Override
	public List<ProductServiceBO> listservice(ProductServiceBO serviceBO) {
		// TODO Auto-generated method stub
		ProductServiceImpl.LOGGER.entry();
		int count = 1;
		ProductServiceVO productServiceVO=new ProductServiceVO();
		List<ProductServiceVO> serviceList=new ArrayList<ProductServiceVO>();
		List<ProductServiceBO> serviceListBO=new ArrayList<ProductServiceBO>();
		productServiceVO.setIsDelete(false);
		productServiceVO.setIsActive(true);
		productServiceVO.setServiceName(serviceBO.getServiceName());
		serviceList=productServiceDao.listViewService(productServiceVO);
		
		if(serviceList!=null && serviceList.size()>0 && !serviceList.isEmpty()){
			int data;
			/*serviceList.forEach(ProductService->{*/
				for (ProductServiceVO ProductService : serviceList) {
				data = count++;
				ProductServiceBO productServiceBO=new ProductServiceBO();
				productServiceBO.setServiceId(ProductService.getServiceId());
				productServiceBO.setsNo(data);
				productServiceBO.setServiceName(ProductService.getServiceName());
				productServiceBO.setServiceSpecification(ProductService.getServiceSpecification());
				//double to int 		
				double rs=ProductService.getFees();
				int rupees=(int) rs;
				productServiceBO.setRupees(rupees);
				productServiceBO.setDuration(ProductService.getDuration());
				SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
				String startDate=sim.format(ProductService.getStartDate());
				productServiceBO.setBeginDate(startDate);
				String enddate=sim.format(ProductService.getEndDate());
				productServiceBO.setLastDate(enddate);
				serviceListBO.add(productServiceBO);
			}
		}
		ProductServiceImpl.LOGGER.exit();
		return serviceListBO;
	}
	@Override
	public ProductServiceBO getServiceObject(ProductServiceBO productServiceBO) {
		// TODO Auto-generated method stub
		ProductServiceVO productServiceVO=new ProductServiceVO();
		productServiceVO.setServiceId(productServiceBO.getServiceId());
		productServiceVO= productServiceDao.getServiceObject(productServiceVO);
		if(null!=productServiceVO){
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
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
	}
	@Override
	public Boolean serviceUpdate(ProductServiceBO productServiceBo) {
		// TODO Auto-generated method stub
		ProductServiceVO productServiceVO=new ProductServiceVO();
		ProductServiceVO serviceVO=new ProductServiceVO();
		User user = new User();
		if(0!=productServiceBo.getServiceId()){
			productServiceVO.setServiceId(productServiceBo.getServiceId());
			serviceVO=productServiceDao.retrieveService(productServiceVO);
		}
		productServiceVO.setServiceName(productServiceBo.getServiceName());
		productServiceVO.setServiceSpecification(productServiceBo.getServiceSpecification());
		productServiceVO.setServiceId(productServiceBo.getServiceId());
		productServiceVO.setFees(productServiceBo.getFees());
		productServiceVO.setDuration(productServiceBo.getDuration());
		productServiceVO.setStartDate(productServiceBo.getStartDate());
		productServiceVO.setEndDate(productServiceBo.getEndDate());
		productServiceVO.setIsDelete(serviceVO.getIsDelete());
		productServiceVO.setIsActive(serviceVO.getIsActive());
		productServiceVO.setCreatedBy(serviceVO.getCreatedBy());
		if (null != productServiceBo.getAdminLoginBO()) {
			user.setId(productServiceBo.getAdminLoginBO().getId());
			productServiceVO.setUserVO(user);
		}

		return productServiceDao.serviceUpdateDao(productServiceVO);
	}
	@Override
	public Boolean deleteService(ProductServiceBO service) {
		ProductServiceVO productServiceVo=new ProductServiceVO();
		productServiceVo.setServiceId(service.getServiceId());
		//productServiceVo.setIsActive(false);
		productServiceVo.setIsDelete(true);
		return productServiceDao.deleteService(productServiceVo);

	}

	@Override
	public boolean isValidServiceName(ProductServiceBO productServiceBO) {
		// TODO Auto-generated method stub
		ProductServiceVO productServiceVO=new ProductServiceVO();
		
		productServiceVO.setServiceName(productServiceBO.getServiceName());
		productServiceVO.setIsDelete(false);
		return productServiceDao.isValidServiceName(productServiceVO);
	}
}
