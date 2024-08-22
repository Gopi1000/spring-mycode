package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.vo.CampaignVO;


public interface CampaignDao {

	CampaignVO saveCompaignDao(CampaignVO campaignVO);

	long getListCampaignDao(CampaignVO campaignVO);

	CampaignVO getCampaignObject(CampaignVO campaignVo);

	boolean updateCampaign(CampaignVO campaignVo);

	boolean deleteCampaign(CampaignVO campaignVo);

	boolean checkCampaign(CampaignVO campaignVo);

	long getListOfCompanyCampaign(CampaignVO campaignVO);

	List<CampaignVO> listOfCampaign(CampaignVO campaignVo);

	List<CampaignVO> searchCampaign(CampaignVO campaignVo);

	long listOfCampaignCount(CampaignVO campaignVO);

	long listOfCompanyCampaign(CampaignVO campaignVO);

	long lastObject();

	//boolean saveManageSms(ManageSms manageSms);

}
