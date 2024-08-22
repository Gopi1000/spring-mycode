package com.scube.techboot.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.bo.RecentNewsBO;

public interface RecentNewsService {

	long retrieveNewsCount(RecentNewsBO recentNewsBo);

	RecentNewsBO saveRecentNewsDetails(RecentNewsBO recentNewsBo, HttpSession session);

	List<RecentNewsBO> getViewRecentNewsList(RecentNewsBO recentNewsBO);

	RecentNewsBO getRecentNewsObject(RecentNewsBO recentNewsBo) throws ParseException;

	boolean postEditRecentNews(RecentNewsBO recentNewsBo);

	boolean deleteRecentNews(RecentNewsBO recentNewsBo);

	long getRetrieveNewsCount(RecentNewsBO recentNewsBO);

}
