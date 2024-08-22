package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.PrivilegeBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.RoleBO;
//import com.scube.crm.bo.RolePrivilegeBO;

public interface RoleService {
	
	RoleBO insertrole(RoleBO rolebo);

	List<RoleBO> retrieverole(List<RoleBO> rolebo);

	RoleBO getParticularrole(long editId);

	String updateRole(RoleBO rolebo);

	String deleteRole(long deleteId);
	
	List<RoleBO> listrole(RoleBO rolebo1);

	List<RoleBO> listRole(RoleBO bO);

	List<PrivilegeBO> listPrivilege(PrivilegeBO bo1);

	RoleBO insertroleprivilege(RoleBO rpbo);

	List<RoleBO> retrieveroleprivilege(List<RoleBO> rolebo);

	RoleBO getParticularroleprivilege(long editId);

	String deleteRoleprivilege(long deleteId);

	

	

	

	/*List<RoleBO> listofrole(RoleBO rolebo1);*/

	

}
