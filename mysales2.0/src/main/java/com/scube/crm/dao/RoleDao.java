package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.bo.PrivilegeBO;
import com.scube.crm.bo.RoleBO;
import com.scube.crm.vo.PrivilegeVO;
//import com.scube.crm.vo.RolePrivilegeVO;
import com.scube.crm.vo.RoleVO;

public interface RoleDao {
	
	RoleVO insertrole(RoleVO rolevo);

	List<RoleVO> retrieverole();

	RoleVO getParticularrole(long editId);

	String updateRole(RoleVO rolevo);

	String deleteRole(RoleVO rolevo);
	
	List<RoleVO> listrole(RoleVO rolevo);

	List<RoleVO> listRole(RoleVO rOLEVO);

	List<PrivilegeVO> listPrivilege(PrivilegeVO privilegeVO);

	RoleVO insertroleprivilege(RoleVO rpvo);

	List<RoleVO> retrieveroleprivilege();

	RoleVO getParticularroleprivilege(long editId);

	String deleteRoleprivilege(RoleVO rolevo);

}
