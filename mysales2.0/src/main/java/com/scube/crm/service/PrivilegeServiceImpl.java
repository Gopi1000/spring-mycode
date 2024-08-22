package com.scube.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.PrivilegeBO;
import com.scube.crm.dao.PrivilegeDao;
import com.scube.crm.vo.PrivilegeVO;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService{
	
	@Autowired
	PrivilegeDao privilegedao;
	
	
public PrivilegeBO insertprivilege(PrivilegeBO privilegebo) {
		
		PrivilegeVO privilegevo = new PrivilegeVO();

		privilegevo.setPrivilegeid(privilegebo.getPrivilegeid());
		privilegevo.setPrivilegename(privilegebo.getPrivilegename());

		privilegevo = privilegedao.insertprivilege(privilegevo);

		return privilegebo;
	}
	
	
	public List<PrivilegeBO> retrievePrivilege(List<PrivilegeBO> privilegebo) {
		List<PrivilegeBO> privilegebo1 = new ArrayList<PrivilegeBO>();
		List<PrivilegeVO> privilegevo = new ArrayList<PrivilegeVO>();

		privilegevo = privilegedao.retrievePrivilege();

		int sno = 1;
		for (PrivilegeVO privilegevo1 : privilegevo) {

			PrivilegeBO bo = new PrivilegeBO();

			bo.setPrivilegeid(privilegevo1.getPrivilegeid());
			bo.setPrivilegename(privilegevo1.getPrivilegename());
			bo.setSno(sno++);

			privilegebo1.add(bo);
		}

		return privilegebo1;
	}
	
	
	public List<PrivilegeBO> listprivilege(PrivilegeBO privilegebo1) {
		int sno = 1;
		PrivilegeVO VO = new PrivilegeVO();
		List<PrivilegeVO> List = new ArrayList<PrivilegeVO>();
		List<PrivilegeBO> ListBO = new ArrayList<PrivilegeBO>();

		VO.setPrivilegename(privilegebo1.getPrivilegename());
		List = privilegedao.listprivilege(VO);

		if (List != null && List.size() > 0 && !List.isEmpty()) {

			for (PrivilegeVO vo : List) {

				PrivilegeBO BO = new PrivilegeBO();

				BO.setPrivilegeid(vo.getPrivilegeid());
				BO.setPrivilegename(vo.getPrivilegename());
				BO.setSno(sno++);

				ListBO.add(BO);
			}
		}

		return ListBO;
	}
	
	public PrivilegeBO getParticularprivilege(long editId) {
	
		PrivilegeVO vo = new PrivilegeVO();
		PrivilegeBO Bo = new PrivilegeBO();

		vo = privilegedao.getParticularprivilege(editId);

		if (null != vo) {

			Bo.setPrivilegeid(vo.getPrivilegeid());
		    Bo.setPrivilegename(vo.getPrivilegename());

		}

		return Bo;
	}
	
	
	public String updateprivilege(PrivilegeBO privilegebo) {
		PrivilegeVO privilegevo = new PrivilegeVO();

		privilegevo.setPrivilegeid(privilegebo.getPrivilegeid());
		privilegevo.setPrivilegename(privilegebo.getPrivilegename());

		String respnse = privilegedao.updateprivilege(privilegevo);
		privilegebo.setPrivilegeid(privilegevo.getPrivilegeid());

		return respnse;
	}
	
	public String deleteprivilege(long deleteId) {
		PrivilegeVO vo = new PrivilegeVO();

		vo.setPrivilegeid(deleteId);
		String privilege = privilegedao.deleteprivilege(vo);

		return privilege;
	}

}
