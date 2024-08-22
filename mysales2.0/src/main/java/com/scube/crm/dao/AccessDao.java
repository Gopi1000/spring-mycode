package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.vo.AccessVO;

public interface AccessDao {

	AccessVO insertaccess(AccessVO vo);

	List<AccessVO> retrieveaccess();

	List<AccessVO> listaccess(AccessVO accessvo);

	AccessVO getParticularaccess(long editId);

	String updateaccess(AccessVO accessvo);

	String deleteaccess(AccessVO accessvo);

}
