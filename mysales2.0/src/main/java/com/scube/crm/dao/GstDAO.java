package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.bo.GstBO;
import com.scube.crm.vo.GstVO;

public interface GstDAO {

	GstBO createGstValues(GstVO gstVO);

	List<GstVO> getListGst(GstVO gstVO);

	GstVO getGstValues(GstVO gstVO);

	boolean gstUpdateValues(GstVO gstVO);

	Boolean deleteGstValues(GstVO gstVO);

}
