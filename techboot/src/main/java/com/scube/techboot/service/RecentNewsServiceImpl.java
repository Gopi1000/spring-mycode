package com.scube.techboot.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.techboot.bo.RecentNewsBO;
import com.scube.techboot.dao.RecentNewsDao;
import com.scube.techboot.vo.RecentNewsVO;

@Transactional
@Service
public class RecentNewsServiceImpl implements RecentNewsService {
	
	@Autowired
	private RecentNewsDao recentNewsDao;

	@Override
	public long retrieveNewsCount(RecentNewsBO recentNewsBo) {
		// TODO Auto-generated method stub
		RecentNewsVO recentNewsVo=new RecentNewsVO();
		long count=recentNewsDao.retrieveNewsCount(recentNewsVo);
		if(0!=count){
			return count;
		}
		return count;
	}

	@Override
	public RecentNewsBO saveRecentNewsDetails(RecentNewsBO recentNewsBo, HttpSession session) {
		// TODO Auto-generated method stub
		RecentNewsVO recentNewsVo=new RecentNewsVO();
		recentNewsVo.setTitleName(recentNewsBo.getTitleName());
		recentNewsVo.setDescriptions(recentNewsBo.getDescriptions());
		recentNewsVo.setImageName(recentNewsBo.getImageName());
		recentNewsVo.setisDelete(false);
		recentNewsVo.setSending_status(true);
		recentNewsVo.setDate(recentNewsBo.getDate());
		recentNewsVo.setCreatedTime(new Date());
		recentNewsVo.setAuthorName(recentNewsBo.getAuthorName());
		if(null!=session.getAttribute("adminId")){
			long id=(long) session.getAttribute("adminId");
			recentNewsVo.setCreatedBy(id);
		}
		recentNewsVo=recentNewsDao.saveRecentNewsDetails(recentNewsVo);
		if(null!=recentNewsVo){
			recentNewsBo.setNewsId(recentNewsVo.getNewsId());
		}
		return recentNewsBo;
	}

	@Override
	public List<RecentNewsBO> getViewRecentNewsList(RecentNewsBO recentNewsBO) {
		// TODO Auto-generated method stub
		List<RecentNewsVO> recentNewslist=new ArrayList<RecentNewsVO>();
		List<RecentNewsBO> recentNewslistBo=new ArrayList<RecentNewsBO>();
		RecentNewsVO recentNewsVo=new RecentNewsVO();
		recentNewsVo.setisDelete(false);
		recentNewsVo.setSending_status(true);
		recentNewsVo.setRecordIndex(recentNewsBO.getRecordIndex());
		recentNewsVo.setMaxRecord(recentNewsBO.getMaxRecord());
		if(null!=recentNewsBO.getTitleName()){
			recentNewsVo.setTitleName(recentNewsBO.getTitleName());
		}
		if(null!=recentNewsBO.getAuthorName()){
			recentNewsVo.setAuthorName(recentNewsBO.getAuthorName());
		}
		if(null!=recentNewsBO.getDate()){
			recentNewsVo.setDate(recentNewsBO.getDate());
		}
		List<RecentNewsVO> newslist=recentNewsDao.getViewRecentNewsList(recentNewsVo);
		if(null!=newslist &&!newslist.isEmpty() && newslist.size()>0){
			int SNo=recentNewsBO.getRecordIndex();
			for(RecentNewsVO recentnews:newslist){
				RecentNewsVO recentNewsVO=new RecentNewsVO();
				recentNewsVO.setDate(recentnews.getDate());
				recentNewsVO.setTitleName(recentnews.getTitleName());
				recentNewsVO.setDescriptions(recentnews.getDescriptions());
				recentNewsVO.setImageName(recentnews.getImageName());
				recentNewsVO.setAuthorName(recentnews.getAuthorName());
				recentNewsVO.setS_No(++SNo);
				recentNewsVO.setNewsId(recentnews.getNewsId());
				recentNewslist.add(recentNewsVO);
			}
			recentNewslist.forEach(recentNews->{
				RecentNewsBO recentNewsBo=new RecentNewsBO();
				Date date1=recentNews.getDate();
				SimpleDateFormat format= new SimpleDateFormat("E dd MMM yyyy");
				String newsDate=format.format(date1);
				recentNewsBo.setTitleName(recentNews.getTitleName());
				recentNewsBo.setDescriptions(recentNews.getDescriptions());
				recentNewsBo.setImageName(recentNews.getImageName());
				recentNewsBo.setAuthorName(recentNews.getAuthorName());
				recentNewsBo.setBeginDate(newsDate);
				recentNewsBo.setNewsId(recentNews.getNewsId());
				recentNewsBo.setS_No(recentNews.getS_No());
				recentNewslistBo.add(recentNewsBo);
			});
		}
		return recentNewslistBo;
	}

	@Override
	public RecentNewsBO getRecentNewsObject(RecentNewsBO recentNewsBo) throws ParseException {
		// TODO Auto-generated method stub
		RecentNewsVO recentNewsVo=new RecentNewsVO();
		recentNewsVo.setisDelete(false);
		recentNewsVo.setSending_status(true);
		recentNewsVo.setNewsId(recentNewsBo.getNewsId());
		recentNewsVo=recentNewsDao.getRecentNewsObject(recentNewsVo);
		if(null!=recentNewsVo){
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			RecentNewsBO recentNewsBO=new RecentNewsBO();
			recentNewsBO.setNewsId(recentNewsVo.getNewsId());
			recentNewsBO.setAuthorName(recentNewsVo.getAuthorName());
			recentNewsBO.setDescriptions(recentNewsVo.getDescriptions());
			recentNewsBO.setTitleName(recentNewsVo.getTitleName());
			recentNewsBO.setDate(recentNewsVo.getDate());
			recentNewsBO.setImageName(recentNewsVo.getImageName());
			String Date=sim.format(recentNewsVo.getDate());
			recentNewsBO.setBeginDate(Date);
			return recentNewsBO;
			
		}
		return recentNewsBo;
	}

	@Override
	public boolean postEditRecentNews(RecentNewsBO recentNewsBo) {
		// TODO Auto-generated method stub
		RecentNewsVO recentNewsVo=new RecentNewsVO();
		recentNewsVo.setNewsId(recentNewsBo.getNewsId());
		recentNewsVo.setisDelete(false);
		recentNewsVo.setSending_status(true);
		recentNewsVo=recentNewsDao.getRecentNewsObject(recentNewsVo);
		if(null!=recentNewsVo){
			recentNewsVo.setCreatedBy(recentNewsVo.getCreatedBy());	
			recentNewsVo.setCreatedTime(recentNewsVo.getCreatedTime());
			recentNewsVo.setisDelete(recentNewsVo.getisDelete());
			recentNewsVo.setSending_status(recentNewsVo.getSending_status());
			recentNewsVo.setTitleName(recentNewsBo.getTitleName());
			recentNewsVo.setImageName(recentNewsBo.getImageName());
			recentNewsVo.setAuthorName(recentNewsBo.getAuthorName());
			recentNewsVo.setDescriptions(recentNewsBo.getDescriptions());
			recentNewsVo.setDate(recentNewsBo.getDate());
			recentNewsVo.setModifiedBy(recentNewsVo.getCreatedBy());
			recentNewsVo.setModifiedTime(new Date());
			recentNewsVo.setNewsId(recentNewsBo.getNewsId());
			}
		
		return recentNewsDao.postEditRecentNews(recentNewsVo);
	}

	@Override
	public boolean deleteRecentNews(RecentNewsBO recentNewsBo) {
		// TODO Auto-generated method stub
		RecentNewsVO recentNewsVo=new RecentNewsVO();
		recentNewsVo.setisDelete(true);
		recentNewsVo.setSending_status(false);
		recentNewsVo.setNewsId(recentNewsBo.getNewsId());
		return recentNewsDao.deleteRecentNews(recentNewsVo);
	}

	@Override
	public long getRetrieveNewsCount(RecentNewsBO recentNewsBO) {
		// TODO Auto-generated method stub
		RecentNewsVO recentNewsVo=new RecentNewsVO();
		recentNewsVo.setisDelete(false);
		recentNewsVo.setSending_status(true);
		if(null!=recentNewsBO.getTitleName() && !recentNewsBO.getTitleName().isEmpty()){
			recentNewsVo.setTitleName(recentNewsBO.getTitleName());
		}
		 if(null!=recentNewsBO.getAuthorName() && !recentNewsBO.getAuthorName().isEmpty()){
			recentNewsVo.setAuthorName(recentNewsBO.getAuthorName());
		}
		 if(null!=recentNewsBO.getDate()){
			recentNewsVo.setDate(recentNewsBO.getDate());
		}
		return recentNewsDao.getRetrieveNewsCount(recentNewsVo);
	}

}
