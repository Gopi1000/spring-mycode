package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.AccessBO;

public interface AccessService {

	AccessBO insertAccess(AccessBO accessbo);

	List<AccessBO> retrieveaccess(List<AccessBO> accessbo);

	List<AccessBO> listaccess(AccessBO bo);

	AccessBO getParticularaccess(long editId);

	String updateaccess(AccessBO accessbo);

	String deleteaccess(long deleteId);

	

	

	

}
