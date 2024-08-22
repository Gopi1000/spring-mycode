package com.scube.techboot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.bo.CampaignBO;
import com.scube.techboot.bo.ProductServiceBO;

public interface CampaignService {

	CampaignBO saveCompaign(CampaignBO campaignBO,HttpSession session);

	long getListOfCampaign(CampaignBO campaignBO);

	CampaignBO getCampaignObject(CampaignBO campaignBo);

	boolean updateCampaign(CampaignBO campaignBo,HttpSession session);

	boolean deleteCampaign(CampaignBO campaignBo);

	boolean checkCampaign(ProductServiceBO service);

	long getListOfCompanyCampaign(CampaignBO campaignBO);

	List<CampaignBO> listOfCampaign(CampaignBO campaignBO);

	List<CampaignBO> searchCampaign(CampaignBO campaignBO);

	long listOfCompanyCampaign(CampaignBO campaignBO);

	long listOfCampaignCount(CampaignBO campaignBO);

	long lsatObject();

	//boolean getSpecification(CampaignBO campaignBO);
	

}
