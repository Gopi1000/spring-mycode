package com.scube.techboot.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.EventRegisterVO;
import com.scube.techboot.vo.EventVO;

@Repository
public class EventDaoImpl implements EventDao{
	private static final Logger LOGGER=Logger.getLogger(EventDaoImpl.class);
	@Autowired
	private SessionFactory SessionFactory;


	@Override
	public long retriveOfEvent(EventVO eventsVO) {
		// TODO Auto-generated method stub
		long value=0;
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventVO.class);
			criteria.addOrder(Order.desc("eventId"));
			List<EventVO> listVo=criteria.list();
			if(null!=listVo && !listVo.isEmpty() && listVo.size()>0){
				eventsVO=listVo.get(0);
				long count=(eventsVO.getEventId());	
				return count; 
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return value;
	}

	@Override
	public EventVO saveEvent(EventVO eventVO) {
		// TODO Auto-generated method stub
		try{
			Session session= SessionFactory.getCurrentSession();
			long eventId=(long) session.save(eventVO);
			if(0!=eventId) {
				eventVO.setEventId(eventId);
				return eventVO;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return eventVO;
	}


	@Override
	public long retrieveOfEventsCount(EventVO eventVO) {
		// TODO Auto-generated method stub
		try{
			Session session= SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventVO.class);
			criteria.add(Restrictions.eq("isDelete",eventVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", eventVO.getSending_status()));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
				return count;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return 0;
	}

	@Override
	public long retrieveOfEventsCompanyCount(EventVO eventVO) {
		// TODO Auto-generated method stub
		try{
			Session session= SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventVO.class);
			criteria.add(Restrictions.eq("companyVO.companyId", eventVO.getCompanyVO().getCompanyId()));
			criteria.add(Restrictions.eq("isDelete",eventVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", eventVO.getSending_status()));
			List<EventVO> eventlist=criteria.list();
			if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0){
				long count=eventlist.size();
				return count;
			   }
			}catch(Exception e){
				if(LOGGER.isInfoEnabled()) {
					LOGGER.info("FROM INFO: Exception \t"+e);
				}
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("FROM DEBUG : Exception \t"+e);
				}
			}
		return 0;
	}
	
	@Override
	public EventVO getEventObjects(EventVO eventVO) {
		// TODO Auto-generated method stub
		EventVO eventVo=new EventVO();
		try {
			Session sessio=SessionFactory.getCurrentSession();
			Criteria criteria=sessio.createCriteria(EventVO.class);
			criteria.add(Restrictions.eq("eventId",eventVO.getEventId()));
			criteria.add(Restrictions.eq("isDelete", eventVO.getIsDelete()));
			eventVo=(EventVO) criteria.uniqueResult();

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return eventVo;
	}

	@Override
	public boolean postEditEvent(EventVO eventVO) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			if(null!=eventVO){
				session.saveOrUpdate(eventVO);
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return false;
	}

	@Override
	public boolean deleteEvent(EventVO eventVo) {
		// TODO Auto-generated method stub

		try{
			String deleteQuery="UPDATE EventVO T set T.isDelete = :isDelete,T.sending_status = :sending_status WHERE T.eventId = :eventId";
			final Query query = SessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", eventVo.getIsDelete());
			query.setParameter("sending_status", eventVo.getSending_status());
			query.setParameter("eventId", eventVo.getEventId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public EventVO viewEventDetails(EventVO eventVo) {
		// TODO Auto-generated method stub
		EventVO eventvo=new EventVO();

		try{
			if(eventVo.getEventId()!=0){
				Session session=SessionFactory.getCurrentSession();
				Criteria cr=session.createCriteria(EventVO.class);
				cr.add(Restrictions.eq("eventId", eventVo.getEventId()));
				eventvo=(EventVO) cr.uniqueResult();
			}		
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
       return eventvo;

	}

	@Override
	public EventRegisterVO saveEventRegister(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegister=new EventRegisterVO();

		try{
			Session session= SessionFactory.getCurrentSession();
			int eventId=(int) session.save(eventRegisterVO);
			if(0!=eventId) {
				eventRegister.setEventRegisterId(eventId);

				return eventRegister;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return eventRegister;
	}


	@Override
	public List<EventRegisterVO> viewEventRegistration(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		List<EventRegisterVO> registerlist=new ArrayList<EventRegisterVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventRegisterVO.class);
			if(null!=eventRegisterVO.getCompanyVO() && 0!=eventRegisterVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",eventRegisterVO.getCompanyVO().getCompanyId()));	
			}
			criteria.add(Restrictions.eq("isDelete", eventRegisterVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", eventRegisterVO.getSending_status()));
			criteria.setFirstResult(eventRegisterVO.getRecordIndex());
			criteria.setMaxResults(eventRegisterVO.getMaxRecord());
			criteria.addOrder(Order.desc("eventRegisterId"));
			registerlist=criteria.list();
			if(null!=registerlist && !registerlist.isEmpty() && registerlist.size()>0){
				return registerlist;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return registerlist;
	}

	@Override
	public EventRegisterVO emailValidations(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVo=new EventRegisterVO();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventRegisterVO.class);
			criteria.add(Restrictions.eq("emailAddress", eventRegisterVO.getEmailAddress()));
			criteria.add(Restrictions.eq("eventVo.eventId", eventRegisterVO.getEventVo().getEventId()));
			criteria.add(Restrictions.eq("isDelete", eventRegisterVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", eventRegisterVO.getSending_status()));
			eventRegisterVo =(EventRegisterVO) criteria.uniqueResult();
			if(null!=eventRegisterVo){
				return eventRegisterVo;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return eventRegisterVo;
	}
	

	@Override
	public EventRegisterVO mobileNumberValidations(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVo=new EventRegisterVO();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventRegisterVO.class);
			criteria.add(Restrictions.eq("mobileNumber", eventRegisterVO.getMobileNumber()));
			criteria.add(Restrictions.eq("eventVo.eventId", eventRegisterVO.getEventVo().getEventId()));
			criteria.add(Restrictions.eq("isDelete", eventRegisterVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", eventRegisterVO.getSending_status()));
			eventRegisterVo =(EventRegisterVO) criteria.uniqueResult();
			if(null!=eventRegisterVo){
				return eventRegisterVo;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return eventRegisterVo;
	}
	
	@Override
	public long retrieveOfEventsRegistrationCount(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventRegisterVO.class);
			if(null!=eventRegisterVO.getCompanyVO() && 0!=eventRegisterVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",eventRegisterVO.getCompanyVO().getCompanyId()));	
			}
			criteria.add(Restrictions.eq("isDelete", eventRegisterVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", eventRegisterVO.getSending_status()));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
				return count;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return 0;
	}

	@Override
	public EventRegisterVO ViewEventRegistrationDetails(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVo=new EventRegisterVO();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventRegisterVO.class);
			criteria.add(Restrictions.eq("eventRegisterId", eventRegisterVO.getEventRegisterId()));
			criteria.add(Restrictions.eq("isDelete", eventRegisterVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", eventRegisterVO.getSending_status()));
			eventRegisterVo =(EventRegisterVO) criteria.uniqueResult();
			if(null!=eventRegisterVo){
				return eventRegisterVo;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return eventRegisterVo;
	}

	@Override
	public List<EventVO> retrieveCurrentEvents(EventVO eventVO) {
		// TODO Auto-generated method stub
		List<EventVO> eventslistVo=new ArrayList<EventVO>();
		try {
			Session sessions=SessionFactory.getCurrentSession();
			Criteria criteria=sessions.createCriteria(EventVO.class);
			if(null!=eventVO.getCompanyVO() && 0!=eventVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",eventVO.getCompanyVO().getCompanyId()));	
			}
			criteria.add(Restrictions.ge("date",eventVO.getDate()));
			criteria.add(Restrictions.eq("isDelete", eventVO.getIsDelete()));
			criteria.addOrder(Order.desc("eventId"));
			eventslistVo=criteria.list();
			if(null!=eventslistVo && !eventslistVo.isEmpty() && eventslistVo.size()>0){
				return eventslistVo;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return eventslistVo;
	}

	@Override
	public List<EventVO> retrievePastEvents(EventVO eventVO) {
		// TODO Auto-generated method stub
		List<EventVO> eventslistVo=new ArrayList<EventVO>();
		try {
			Session sessions=SessionFactory.getCurrentSession();
			Criteria criteria=sessions.createCriteria(EventVO.class);
			if(null!=eventVO.getCompanyVO() && 0!=eventVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",eventVO.getCompanyVO().getCompanyId()));	
			}
			criteria.add(Restrictions.lt("date",eventVO.getDate()));
			criteria.add(Restrictions.eq("isDelete", eventVO.getIsDelete()));
			criteria.addOrder(Order.desc("eventId"));
			eventslistVo=criteria.list();
			if(null!=eventslistVo && !eventslistVo.isEmpty() && eventslistVo.size()>0){
				return eventslistVo;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return eventslistVo;
	}

	@Override
	public List<EventRegisterVO> searchEvents(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		List<EventRegisterVO> registerlist=new ArrayList<EventRegisterVO>();
		try {
			Session sessions=SessionFactory.getCurrentSession();
			Criteria criteria=sessions.createCriteria(EventRegisterVO.class);
			if(null!=eventRegisterVO.getCompanyVO() && 0!=eventRegisterVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",eventRegisterVO.getCompanyVO().getCompanyId()));	
			}
			criteria.add(Restrictions.eq("eventVo.eventId",eventRegisterVO.getEventVo().getEventId()));
			criteria.add(Restrictions.eq("isDelete", eventRegisterVO.getIsDelete()));
			criteria.setFirstResult(eventRegisterVO.getRecordIndex());
			criteria.setMaxResults(eventRegisterVO.getMaxRecord());
			registerlist=criteria.list();
			if(null!=registerlist && !registerlist.isEmpty() && registerlist.size()>0){
				return registerlist;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return registerlist;
	}



	@Override
	public List<EventVO> viewListEvent(EventVO eventVO,HttpSession session) {
		// TODO Auto-generated method stub
		List<EventVO> eventsVO=new ArrayList<EventVO>();
		try {

			Session sessions=SessionFactory.getCurrentSession();
			Criteria criteria=sessions.createCriteria(EventVO.class);
			if(null==session.getAttribute("adminId") && null==eventVO.getCompanyVO()){
				criteria.add(Restrictions.ge("date",eventVO.getDate()));
			}else if(null!=eventVO.getCompanyVO() && 0!=eventVO.getCompanyVO().getCompanyId()){
				 criteria.add(Restrictions.eq("companyVO.companyId", eventVO.getCompanyVO().getCompanyId()));	
			}
			/*criteria.setFirstResult(eventVO.getRecordIndex());
			criteria.setMaxResults(eventVO.getMaxRecord());*/
			if(eventVO.getIsDelete()==true){
				eventVO.setIsDelete(false);
			}
			if(null!=eventVO && eventVO.getIsDelete()==false) {
				criteria.setFirstResult(eventVO.getRecordIndex());
				criteria.setMaxResults(eventVO.getMaxRecord());
				}
			criteria.add(Restrictions.eq("isDelete",eventVO.getIsDelete()));
            criteria.add(Restrictions.eq("sending_status", eventVO.getSending_status()));
			criteria.addOrder(Order.desc("eventId"));
			eventsVO=criteria.list();
			if(null!=eventsVO && !eventsVO.isEmpty() && eventsVO.size()>0) {
				return eventsVO;

			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
    return eventsVO;
	}

	@Override
	public List<EventRegisterVO> totalPastEventsCount(EventRegisterVO eventRegisterVO) {
		// TODO Auto-generated method stub
		List<EventRegisterVO> registerlist=new ArrayList<EventRegisterVO>();
		try {
			Session sessions=SessionFactory.getCurrentSession();
			Criteria criteria=sessions.createCriteria(EventRegisterVO.class);
			if(null!=eventRegisterVO.getCompanyVO() && 0!=eventRegisterVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",eventRegisterVO.getCompanyVO().getCompanyId()));	
			}
			criteria.add(Restrictions.eq("eventVo.eventId",eventRegisterVO.getEventVo().getEventId()));
			criteria.add(Restrictions.eq("isDelete", eventRegisterVO.getIsDelete()));
			registerlist=criteria.list();
			if(null!=registerlist && !registerlist.isEmpty() && registerlist.size()>0){
				return registerlist;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return registerlist;
	}

	@Override
	public int searchTitleName(EventVO eventVO) {
		List<EventVO> eventList=new ArrayList<EventVO>();
		int totalEvent=0;
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventVO.class);
			criteria.add(Restrictions.ilike("titleName",eventVO.getTitleName(),MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("isDelete",eventVO.getIsDelete()));
			eventList=criteria.list();
			if(null!=eventList && eventList.size()>0 && !eventList.isEmpty()){
				totalEvent=eventList.size();
			}
		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return totalEvent;
	}

	@Override
	public List<EventVO> searchPageEvent(EventVO eventVO) {
		List<EventVO> eventList=new ArrayList<EventVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(EventVO.class);
			criteria.add(Restrictions.ilike("titleName",eventVO.getTitleName(),MatchMode.ANYWHERE));
			criteria.setFirstResult(eventVO.getRecordIndex());
			criteria.setMaxResults(eventVO.getMaxRecord());
			criteria.add(Restrictions.eq("isDelete",eventVO.getIsDelete()));
			eventList=criteria.list();

		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return eventList;
	}
}

