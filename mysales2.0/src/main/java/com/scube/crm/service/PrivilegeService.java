package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.PrivilegeBO;

public interface PrivilegeService {
	
	PrivilegeBO insertprivilege(PrivilegeBO privilegebo);

	List<PrivilegeBO> retrievePrivilege(List<PrivilegeBO> privilegebo);

	List<PrivilegeBO> listprivilege(PrivilegeBO privilegebo1);

	PrivilegeBO getParticularprivilege(long editId);

	String updateprivilege(PrivilegeBO privilegebo);

	String deleteprivilege(long deleteId);

}
