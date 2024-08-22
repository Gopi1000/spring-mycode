package com.scube.crm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.dao.GstDAO;
import com.scube.crm.dao.ProductServiceDao;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.vo.GstVO;
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.User;

@Service
@Transactional
public class GstServiceImpl implements GstService{

	
	  @Autowired 
	  private GstDAO gstDao;
	 
	static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(LeadsServiceImpl.class);
	
	@Override
	public GstBO createGstValues(GstBO gstBO) {
		User user = new User();
		GstVO gstVO=new GstVO();
		gstVO.setSgst(gstBO.getSgst());
		gstVO.setCgst(gstBO.getCgst());
		gstVO.setStartDate(gstBO.getStartDate());
		gstVO.setIsDelete(false);
		gstVO.setIsActive(true);
		gstVO.setCreatedBy(gstBO.getCreatedBy());
		if (null != gstBO.getAdminLoginBO()) {
			user.setId(gstBO.getAdminLoginBO().getId());
			gstVO.setUserVO(user);
		}
		return  gstDao.createGstValues(gstVO);
	}

	@Override
	public List<GstBO> getListGst(GstBO gstBO) {
		GstServiceImpl.LOGGER.entry();
		int count = 1;
		GstVO gstVO=new GstVO();
		List<GstVO> gstListVO=new ArrayList<GstVO>();
		List<GstBO> gstListBO=new ArrayList<GstBO>();
		try {
		gstVO.setIsDelete(false);
		gstVO.setIsActive(true);
		gstVO.setCgst(gstBO.getCgst());
		gstVO.setSgst(gstBO.getSgst());
		if(null!=gstBO.getStartDate()) {
			gstVO.setStartDate(gstBO.getStartDate());
		}
		gstListVO=gstDao.getListGst(gstVO);
		
		if(null!=gstListVO && gstListVO.size()>0 && !gstListVO.isEmpty()){
			int data;
				for (GstVO gstVo : gstListVO) {
				data = count++;
				GstBO gstBo=new GstBO();
				gstBo.setGstId(gstVo.getGstId());
				gstBo.setsNo(data);
				gstBo.setCgst(gstVo.getCgst());
				gstBo.setSgst(gstVo.getSgst());
				SimpleDateFormat sim1=new SimpleDateFormat("yyyy-MM-dd");
				String startDate1=sim1.format(gstVo.getStartDate());
				gstBo.setBeginDate(startDate1);
				gstListBO.add(gstBo);
			}
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		GstServiceImpl.LOGGER.exit();
		return gstListBO;
	}

	@Override
	public GstBO getGstValues(GstBO gstBO) {
		GstVO gstVO=new GstVO();
		gstVO.setGstId(gstBO.getGstId());
		gstVO= gstDao.getGstValues(gstVO);
		if(null!=gstVO){
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			String startDate=sim.format(gstVO.getStartDate());
			gstBO.setBeginDate(startDate);
			gstBO.setGstId(gstVO.getGstId());
			gstBO.setCgst(gstVO.getCgst());
			gstBO.setSgst(gstVO.getSgst());
		}
		
		return gstBO;
	}

	@Override
	public boolean gstValueUpdate(GstBO gstBO) {
		GstVO gstVO=new GstVO();
		GstVO gstVo=new GstVO();
		User user=new User();
		try {
			if(null!=gstBO && 0<gstBO.getGstId())
		gstVO.setSgst(gstBO.getSgst());
		gstVO.setCgst(gstBO.getCgst());
		gstVO.setGstId(gstBO.getGstId());
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=sim.parse(gstBO.getBeginDate());
		//Date fromDate = sim.parse(startDate);
		gstVO.setStartDate(startDate);
		//gstVO.setStartDate(gstBO.getStartDate());
		gstVO.setIsDelete(false);
		gstVO.setIsActive(true);
		gstVO.setModifiedBy(gstBO.getAdminLoginBO().getId());
		if (null != gstBO.getAdminLoginBO()) {
			user.setId(gstBO.getAdminLoginBO().getId());
			gstVO.setUserVO(user);
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return gstDao.gstUpdateValues(gstVO);
	}

	@Override
	public Boolean deleteGstValues(GstBO gstBO) {
		GstVO gstVO=new GstVO();
		gstVO.setGstId(gstBO.getGstId());
		gstVO.setIsActive(false);
		gstVO.setIsDelete(true);
		return gstDao.deleteGstValues(gstVO);

	}

	
}
