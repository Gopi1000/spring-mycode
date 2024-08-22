package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.vo.RecentNewsVO;

public interface RecentNewsDao {

	long retrieveNewsCount(RecentNewsVO recentNewsVo);

	RecentNewsVO saveRecentNewsDetails(RecentNewsVO recentNewsVo);

	List<RecentNewsVO> getViewRecentNewsList(RecentNewsVO recentNewsVo);

	RecentNewsVO getRecentNewsObject(RecentNewsVO recentNewsVo);

	boolean postEditRecentNews(RecentNewsVO recentNewsVo);

	boolean deleteRecentNews(RecentNewsVO recentNewsVo);

	long getRetrieveNewsCount(RecentNewsVO recentNewsVo);

}
