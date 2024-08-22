package com.scube.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.PrivilegeBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.RoleBO;
//import com.scube.crm.bo.RolePrivilegeBO;
import com.scube.crm.dao.RoleDao;
import com.scube.crm.vo.PrivilegeVO;
//import com.scube.crm.vo.RolePrivilegeVO;
import com.scube.crm.vo.RoleVO;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roledao;

	public RoleBO insertrole(RoleBO rolebo) {

		RoleVO rolevo = new RoleVO();

		rolevo.setRoleid(rolebo.getRoleid());
		rolevo.setRolename(rolebo.getRolename());

		rolevo = roledao.insertrole(rolevo);

		return rolebo;
	}

	public List<RoleBO> retrieverole(List<RoleBO> rolebo) {

		List<RoleBO> rolebo1 = new ArrayList<RoleBO>();
		List<RoleVO> rolevo = new ArrayList<RoleVO>();

		rolevo = roledao.retrieverole();

		int sno = 1;
		for (RoleVO rolevo1 : rolevo) {

			RoleBO bo = new RoleBO();

			bo.setRoleid(rolevo1.getRoleid());
			bo.setRolename(rolevo1.getRolename());
			bo.setSno(sno++);

			rolebo1.add(bo);
		}

		return rolebo1;
	}

	public RoleBO getParticularrole(long editId) {
		RoleVO rolevo = new RoleVO();
		RoleBO roleBo = new RoleBO();

		rolevo = roledao.getParticularrole(editId);

		if (null != rolevo) {

			roleBo.setRoleid(rolevo.getRoleid());
			roleBo.setRolename(rolevo.getRolename());

		}

		return roleBo;
	}

	public String updateRole(RoleBO rolebo) {
		RoleVO rolevo = new RoleVO();

		rolevo.setRoleid(rolebo.getRoleid());
		rolevo.setRolename(rolebo.getRolename());

		String respnse = roledao.updateRole(rolevo);
		rolebo.setRoleid(rolevo.getRoleid());

		return respnse;
	}

	public String deleteRole(long deleteId) {
		RoleVO rolevo = new RoleVO();

		rolevo.setRoleid(deleteId);
		String role = roledao.deleteRole(rolevo);

		return role;
	}

	public List<RoleBO> listrole(RoleBO rolebo1) {

		int sno = 1;
		RoleVO rolevo = new RoleVO();
		List<RoleVO> List = new ArrayList<RoleVO>();
		List<RoleBO> ListBO = new ArrayList<RoleBO>();

		rolevo.setRolename(rolebo1.getRolename());
		List = roledao.listrole(rolevo);

		if (List != null && List.size() > 0 && !List.isEmpty()) {

			for (RoleVO vo : List) {

				RoleBO roleBO = new RoleBO();

				roleBO.setRoleid(vo.getRoleid());
				roleBO.setRolename(vo.getRolename());
				roleBO.setSno(sno++);
				
				

				ListBO.add(roleBO);
			}
		}

		return ListBO;
	}
	
	
	
	/*public List<RoleBO> listofrole(RoleBO rolebo1) {

		int sno = 1;
		RoleVO rolevo = new RoleVO();
		List<RoleVO> List = new ArrayList<RoleVO>();
		List<RoleBO> ListBO = new ArrayList<RoleBO>();

		rolevo.setRolename(rolebo1.getRolename());
		List = roledao.listrole(rolevo);

		if (List != null && List.size() > 0 && !List.isEmpty()) {

			for (RoleVO vo : List) {

				RoleBO roleBO = new RoleBO();

				roleBO.setRoleid(vo.getRoleid());
				roleBO.setRolename(vo.getRolename());
				roleBO.setSno(sno++);
				
				List<PrivilegeBO> privilegebolist = new ArrayList<PrivilegeBO>();
				
				for(PrivilegeVO vo1 : vo.getPrivilegelist()){
					PrivilegeBO bo1=new PrivilegeBO();
					bo1.setPrivilegeid(vo1.getPrivilegeid());
					bo1.setPrivilegename(vo1.getPrivilegename());
					privilegebolist.add(bo1);
				}
				roleBO.setPrivilegelist(privilegebolist);
				ListBO.add(roleBO);
				}
		}
			
			return ListBO;
		}*/
	
	
	public List<RoleBO> listRole(RoleBO bO) {
		
		int sno = 1;
		RoleVO ROLEVO=new RoleVO();
		List<RoleVO> ListVO=new ArrayList<RoleVO>();
		List<RoleBO> ListBO=new ArrayList<RoleBO>();
		//ROLEVO.setRolename(bO.getRolename());
		
		ListVO=roledao.listRole(ROLEVO);
		
		if (ListVO != null && ListVO.size() > 0 && !ListVO.isEmpty()) {

			for (RoleVO vo : ListVO) {

				RoleBO roleBO = new RoleBO();

				roleBO.setRoleid(vo.getRoleid());
				roleBO.setRolename(vo.getRolename());
				roleBO.setSno(sno++);

				ListBO.add(roleBO);
			}
		}
		
		return ListBO;
	}

	
	public List<PrivilegeBO> listPrivilege(PrivilegeBO bo1) {
		int sno = 1;
		PrivilegeVO privilegeVO=new PrivilegeVO();
		List<PrivilegeVO> ListVO=new ArrayList<PrivilegeVO>();
		List<PrivilegeBO> ListBO=new ArrayList<PrivilegeBO>();
		privilegeVO.setPrivilegename(bo1.getPrivilegename());
		
		ListVO=roledao.listPrivilege(privilegeVO);
		
		if (ListVO != null && ListVO.size() > 0 && !ListVO.isEmpty()) {

			for (PrivilegeVO vo : ListVO) {

				PrivilegeBO privilegeBO = new PrivilegeBO();

				privilegeBO.setPrivilegeid(vo.getPrivilegeid());
				privilegeBO.setPrivilegename(vo.getPrivilegename());
				privilegeBO.setSno(sno++);

				ListBO.add(privilegeBO);
			}
		}
		
		return ListBO;
	}
	
	
	public RoleBO insertroleprivilege(RoleBO rpbo) {
		
		RoleVO rpvo=new RoleVO();
	    rpvo.setRoleid(rpbo.getRoleid());
	    rpvo.setRolename(rpbo.getRolename());
	    List<PrivilegeVO> listvo=new ArrayList<PrivilegeVO>();
	    for(PrivilegeBO bo :rpbo.getPrivilegelist()){
	    	PrivilegeVO vo=new PrivilegeVO();
	    	vo.setPrivilegeid(bo.getPrivilegeid());
	    	vo.setPrivilegename(bo.getPrivilegename());
	    	listvo.add(vo);
	    }
	
		rpvo.setPrivilegelist(listvo);
		
		rpvo = roledao.insertroleprivilege(rpvo);
		

		return rpbo;
}
	
	
	public List<RoleBO> retrieveroleprivilege(List<RoleBO> rolebo) {

		//List<RoleBO> rolebo1 = new ArrayList<RoleBO>();
		List<RoleVO> rolevo = new ArrayList<RoleVO>();
		
		//List<PrivilegeVO> listvo = new ArrayList<PrivilegeVO>();
		//List<PrivilegeBO> listvo1 = new ArrayList<PrivilegeBO>();

		//PrivilegeVO bo = new PrivilegeVO();
		rolevo = roledao.retrieveroleprivilege();
		
		//RoleBO rpbo=new RoleBO();

		int sno = 1;
		for (RoleVO vo : rolevo) {

			//for(PrivilegeVO vo1 : vo.getPrivilegelist()){
			RoleBO roleBO = new RoleBO();
			
			roleBO.setRoleid(vo.getRoleid());
			roleBO.setRolename(vo.getRolename());
			roleBO.setSno(sno++);
			
			List<PrivilegeBO> privilegebolist = new ArrayList<PrivilegeBO>();
			
			for(PrivilegeVO vo1 : vo.getPrivilegelist()){
				PrivilegeBO bo1=new PrivilegeBO();
				bo1.setPrivilegeid(vo1.getPrivilegeid());
				bo1.setPrivilegename(vo1.getPrivilegename());
				privilegebolist.add(bo1);
			}
			roleBO.setPrivilegelist(privilegebolist);
			rolebo.add(roleBO);
			}
		
		return rolebo;
	}
	
	
	public RoleBO getParticularroleprivilege(long editId) {
		RoleVO rolevo = new RoleVO();
		RoleBO roleBo = new RoleBO();

		rolevo = roledao.getParticularroleprivilege(editId);
		
		if (null != rolevo) {

			roleBo.setRoleid(rolevo.getRoleid());
			roleBo.setRolename(rolevo.getRolename());
			
            List<PrivilegeBO> privilegebolist = new ArrayList<PrivilegeBO>();
			
			for(PrivilegeVO vo1 : rolevo.getPrivilegelist()){
				PrivilegeBO bo1=new PrivilegeBO();
				bo1.setPrivilegeid(vo1.getPrivilegeid());
				bo1.setPrivilegename(vo1.getPrivilegename());
				privilegebolist.add(bo1);
			}
			roleBo.setPrivilegelist(privilegebolist);

		}
		return roleBo;
		

	}
	
	
	public String deleteRoleprivilege(long deleteId) {
		RoleVO rolevo = new RoleVO();

		rolevo.setRoleid(deleteId);
		String role = roledao.deleteRoleprivilege(rolevo);

		return role;
	}

}
