package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.GstBO;

public interface GstService {

	GstBO createGstValues(GstBO gstBO);

	List<GstBO> getListGst(GstBO gstBO);

	GstBO getGstValues(GstBO gstBO);

	boolean gstValueUpdate(GstBO gstBO);

	Boolean deleteGstValues(GstBO gstBO);

	
}
