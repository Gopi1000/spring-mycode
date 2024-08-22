package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.vo.PrivilegeVO;

public interface PrivilegeDao {
	
	PrivilegeVO insertprivilege(PrivilegeVO privilegevo);

	List<PrivilegeVO> retrievePrivilege();

	List<PrivilegeVO> listprivilege(PrivilegeVO vO);

	PrivilegeVO getParticularprivilege(long editId);

	String updateprivilege(PrivilegeVO privilegevo);

	String deleteprivilege(PrivilegeVO vo);

}
