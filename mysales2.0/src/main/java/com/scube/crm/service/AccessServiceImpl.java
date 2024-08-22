package com.scube.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.AccessBO;
import com.scube.crm.dao.AccessDao;
import com.scube.crm.vo.AccessVO;

@Service
@Transactional
public class AccessServiceImpl implements AccessService {
	
	@Autowired
	AccessDao accessdao;
	
	public AccessBO insertAccess(AccessBO accessbo) {
		AccessVO vo = new AccessVO();

		vo.setId(accessbo.getId());
		vo.setAccessname(accessbo.getAccessname());

		vo = accessdao.insertaccess(vo);

		return accessbo;
	}
	
	
	public List<AccessBO> retrieveaccess(List<AccessBO> accessbo) {
		
		List<AccessBO> accessbo1 = new ArrayList<AccessBO>();
		List<AccessVO> accessvo = new ArrayList<AccessVO>();

		accessvo = accessdao.retrieveaccess();

		int sno = 1;
		for (AccessVO rolevo1 : accessvo) {

			AccessBO bo = new AccessBO();

			bo.setId(rolevo1.getId());
			bo.setAccessname(rolevo1.getAccessname());
			bo.setSno(sno++);

			accessbo1.add(bo);
		}

		return accessbo1;
	}
	
	public List<AccessBO> listaccess(AccessBO bo) {
		int sno = 1;
		AccessVO accessvo = new AccessVO();
		List<AccessVO> List = new ArrayList<AccessVO>();
		List<AccessBO> ListBO = new ArrayList<AccessBO>();

		accessvo.setAccessname(bo.getAccessname());
		List = accessdao.listaccess(accessvo);

		if (List != null && List.size() > 0 && !List.isEmpty()) {

			for (AccessVO vo : List) {

				AccessBO BO = new AccessBO();

				BO.setId(vo.getId());
				BO.setAccessname(vo.getAccessname());
				BO.setSno(sno++);

				ListBO.add(BO);
			}
		}

		return ListBO;
	}
	
	public AccessBO getParticularaccess(long editId) {
		AccessVO vo = new AccessVO();
		AccessBO Bo = new AccessBO();

		vo = accessdao.getParticularaccess(editId);

		if (null != vo) {

			Bo.setId(vo.getId());
			Bo.setAccessname(vo.getAccessname());

		}

		return Bo;
	}
	
	public String updateaccess(AccessBO accessbo) {
		AccessVO accessvo = new AccessVO();

	    accessvo.setId(accessbo.getId());
		accessvo.setAccessname(accessbo.getAccessname());

		String respnse = accessdao.updateaccess(accessvo);
		accessbo.setId(accessvo.getId());

		return respnse;
	}
	
	
	public String deleteaccess(long deleteId) {
		AccessVO accessvo = new AccessVO();

		accessvo.setId(deleteId);
		String access = accessdao.deleteaccess(accessvo);

		return access;
	}

}
