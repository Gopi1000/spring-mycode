package com.scube.techboot.service;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.BaseBo;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.EventRegisterBO;
import com.scube.techboot.bo.EventsBO;
import com.scube.techboot.dao.EventDao;
import com.scube.techboot.utils.TechbootResourceBundle;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.EventRegisterVO;
import com.scube.techboot.vo.EventVO;

@Service
@Transactional
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDao eventDao;
	@Override
	public long retriveOfEvent(EventsBO eventsBo) {
		// TODO Auto-generated method stub
		EventVO eventsVO=new EventVO();
		long count=eventDao.retriveOfEvent(eventsVO);
		if(0!=count) {

			return count;
		}
		return count;
	}

	@Override
	public EventsBO saveEvent(EventsBO eventsBo) {
		// TODO Auto-generated method stub
		EventVO eventVO=new EventVO();
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(eventsBo.getCompanyBO().getCompanyId());
		eventVO.setCompanyVO(companyVO);
		
		eventVO.setEventId(eventsBo.getEventId());
		eventVO.setImageName(eventsBo.getImageName());
		eventVO.setTitleName(eventsBo.getTitleName());
		eventVO.setTiming(eventsBo.getTiming());
		eventVO.setAddress(eventsBo.getAddress());
		eventVO.setDate(eventsBo.getDate());
		eventVO.setIsDelete(false);
		eventVO.setSending_status(true);
		eventVO.setCreatedTime(new Date());
		eventVO=eventDao.saveEvent(eventVO);
		if(null!=eventVO) {
			eventsBo.setEventId(eventVO.getEventId());
		}	

		return eventsBo;
	}

	@Override
	public long retrieveOfEventsCount(EventsBO eventBO) {
		// TODO Auto-generated method stub
		EventVO eventVO=new EventVO();
		eventVO.setIsDelete(false);
		eventVO.setSending_status(true);
		return eventDao.retrieveOfEventsCount(eventVO);
	}


	@Override
	public List<EventsBO> viewListEvent(EventsBO eventBO,HttpSession session) throws FileNotFoundException, ParseException {
		// TODO Auto-generated method stub
		List<EventsBO> listEventBo =new ArrayList<EventsBO>();
		List<EventVO> listEventVo =new ArrayList<EventVO>();
		EventVO eventVO=new EventVO();
		eventVO.setDate(new Date());
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		String startDate=format.format(eventVO.getDate());
		Date date=format.parse(startDate);
		eventVO.setDate(date);
		/*eventVO.setIsDelete(false);*/
		eventVO.setSending_status(true);
		if(eventBO.getIsDelete()==true) {
			eventVO.setMaxRecord(eventBO.getMaxRecord());
			eventVO.setRecordIndex(eventBO.getRecordIndex());
			eventVO.setIsDelete(false);
		}
		eventVO.setIsDelete(false);
		eventVO.setRecordIndex(eventBO.getRecordIndex());
		eventVO.setMaxRecord(eventBO.getMaxRecord());
		if(null!=eventBO.getCompanyBO() && 0!=eventBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(eventBO.getCompanyBO().getCompanyId());
			eventVO.setCompanyVO(companyVo);
		}
		if(eventBO.getIsDelete()==true){
			eventBO.setIsDelete(true);
		}
		List<EventVO> eventlist=eventDao.viewListEvent(eventVO,session);
		if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0 ) {
			int SNo=eventBO.getRecordIndex();
			for(EventVO event:eventlist) {
				EventVO events=new EventVO();
				CompanyVO company=new CompanyVO();
				company.setCompanyName(event.getCompanyVO().getCompanyName());
				events.setCompanyVO(company);
				events.setEventId(event.getEventId());
				events.setAddress(event.getAddress());
				events.setDate(event.getDate());
				events.setTiming(event.getTiming());
				events.setTitleName(event.getTitleName());
				events.setS_No(++SNo);
				events.setImageName(event.getImageName());


				listEventVo.add(events);
			}
			listEventVo.forEach(UpcomingEvent->{
				EventsBO eventsBo=new EventsBO();
				Date vals=UpcomingEvent.getDate();
				SimpleDateFormat	 DateFor = new SimpleDateFormat("E dd MMM yyyy");
				String eventDate = DateFor.format(vals);
				if(null!=eventDate) {
					String[] val=eventDate.split(" ");
					String day = null;
					String month = null;
					try {
						day = TechbootResourceBundle.getValue(val[0]);
						month =TechbootResourceBundle.getValue(val[2]);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					eventsBo.setDay(day);
					eventsBo.setMonth(month);
					eventsBo.setDayDate(val[1]);
				}
				eventsBo.setEventId(UpcomingEvent.getEventId());
				eventsBo.setAddress(UpcomingEvent.getAddress());
				eventsBo.setEventDate(eventDate);
				eventsBo.setDate(UpcomingEvent.getDate());
				eventsBo.setTiming(UpcomingEvent.getTiming());
				eventsBo.setTitleName(UpcomingEvent.getTitleName());
				eventsBo.setS_No(UpcomingEvent.getS_No());
				eventsBo.setImageName(UpcomingEvent.getImageName());
				CompanyBO company=new CompanyBO();
				company.setCompanyName(UpcomingEvent.getCompanyVO().getCompanyName());
				eventsBo.setCompanyBO(company);
				listEventBo.add(eventsBo);

			});
		}
		return listEventBo;
	}

	@Override
	public EventsBO getEventObjects(EventsBO eventBO) throws ParseException {
		// TODO Auto-generated method stub
		EventsBO eventsBO=new EventsBO();
		EventVO eventVO=new EventVO();
		eventVO.setEventId(eventBO.getEventId());
		eventVO.setIsDelete(false);
		eventVO=eventDao.getEventObjects(eventVO);
		if(null!=eventVO) {
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			CompanyBO companyBo=new CompanyBO();
			companyBo.setCompanyId(eventVO.getCompanyVO().getCompanyId());
			eventsBO.setCompanyBO(companyBo);
			eventsBO.setEventId(eventVO.getEventId());
			eventsBO.setTitleName(eventVO.getTitleName());
			eventsBO.setDate(eventVO.getDate()); 
			eventsBO.setTiming(eventVO.getTiming());
			eventsBO.setImageName(eventVO.getImageName());
			String eventsDate=sim.format(eventVO.getDate());
			eventsBO.setEventDate(eventsDate);
			eventsBO.setAddress(eventVO.getAddress());

		}
		return eventsBO;
	}

	@Override
	public boolean postEditEvent(EventsBO eventBO) {
		// TODO Auto-generated method stub
		EventVO eventVO=new EventVO();
		eventVO.setEventId(eventBO.getEventId());
		eventVO.setTitleName(eventBO.getTitleName());
		eventVO.setDate(eventBO.getDate());
		eventVO.setTiming(eventBO.getTiming());
		eventVO.setImageName(eventBO.getImageName());
		eventVO.setAddress(eventBO.getAddress());
		eventVO.setIsDelete(false);
		eventVO.setSending_status(true);
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(eventBO.getCompanyBO().getCompanyId());
		eventVO.setCompanyVO(companyVO);
		//eventVO.setModifiedTime(new Date());
		return eventDao.postEditEvent(eventVO);
	}

	@Override
	public boolean deleteEvent(EventsBO eventBo) {
		// TODO Auto-generated method stub
		EventVO eventVo=new EventVO(); 
		eventVo.setEventId(eventBo.getEventId());
		eventVo.setIsDelete(true);
		eventVo.setSending_status(false);
		return eventDao.deleteEvent(eventVo);

	}

	@Override
	public EventRegisterBO saveEventRegister(EventRegisterBO eventRegisterBO) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		EventVO eventVO=new EventVO();
		CompanyVO companyVo=new CompanyVO();
		companyVo.setCompanyId(eventRegisterBO.getCompanyBO().getCompanyId());
		eventRegisterVO.setCompanyVO(companyVo);
		eventRegisterVO.setEventRegisterId(eventRegisterBO.getEventRegisterId());
		eventRegisterVO.setCandidateName(eventRegisterBO.getCandidateName());
		eventRegisterVO.setEmailAddress(eventRegisterBO.getEmailAddress());
		eventRegisterVO.setMobileNumber(eventRegisterBO.getMobileNumber());
		eventRegisterVO.setProfessional(eventRegisterBO.getProfessional());
		eventRegisterVO.setQualification(eventRegisterBO.getQualification());
		eventRegisterVO.setCreatedTime(new Date());
		eventRegisterVO.setIsDelete(false);
		eventRegisterVO.setSending_status(true);
		eventVO.setEventId(eventRegisterBO.getEventBo().getEventId());
		eventRegisterVO.setEventVo(eventVO);
		eventRegisterVO=eventDao.saveEventRegister(eventRegisterVO);
		if(null!=eventRegisterVO){
			eventRegisterBO.setEventRegisterId(eventRegisterVO.getEventRegisterId());	
		}
		return eventRegisterBO ;
	}

	@Override
	public List<EventRegisterBO> viewEventRegistration(EventRegisterBO eventRegisterBo) {
		// TODO Auto-generated method stub
		List<EventRegisterBO> registerlistBo=new ArrayList<EventRegisterBO>();
		List<EventRegisterVO> registerlistVo=new ArrayList<EventRegisterVO>();
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		eventRegisterVO.setSending_status(true);
		eventRegisterVO.setIsDelete(false);
		eventRegisterVO.setRecordIndex(eventRegisterBo.getRecordIndex());
		eventRegisterVO.setMaxRecord(eventRegisterBo.getMaxRecord());
		if(null!=eventRegisterBo.getCompanyBO() && 0!=eventRegisterBo.getCompanyBO().getCompanyId()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(eventRegisterBo.getCompanyBO().getCompanyId());
			eventRegisterVO.setCompanyVO(companyVo);
		}
		List<EventRegisterVO> registerlist=eventDao.viewEventRegistration(eventRegisterVO);
		if(null!=registerlist && !registerlist.isEmpty() && registerlist.size()>0){
			int sNo=eventRegisterBo.getRecordIndex();
			for(EventRegisterVO eventRegister:registerlist){
				EventVO eventsVO=new EventVO();
				EventRegisterVO eventRegisterVo=new EventRegisterVO();
				eventRegisterVo.setCandidateName(eventRegister.getCandidateName());
				eventRegisterVo.setEmailAddress(eventRegister.getEmailAddress());
				eventsVO.setTitleName(eventRegister.getEventVo().getTitleName());
				eventRegisterVo.setEventVo(eventsVO);
				eventRegisterVo.setS_No(++sNo);
				eventRegisterVo.setEventRegisterId(eventRegister.getEventRegisterId());
				registerlistVo.add(eventRegisterVo);
			}
			registerlistVo.forEach(registerEvent->{
				EventRegisterBO eventRegisterBO=new EventRegisterBO();
				EventsBO eventsBO=new EventsBO();
				eventRegisterBO.setCandidateName(registerEvent.getCandidateName());
				eventRegisterBO.setEmailAddress(registerEvent.getEmailAddress());
				eventRegisterBO.setEventRegisterId(registerEvent.getEventRegisterId());
				eventsBO.setTitleName(registerEvent.getEventVo().getTitleName());
				eventRegisterBO.setEventBo(eventsBO);
				eventRegisterBO.setS_No(registerEvent.getS_No());
				registerlistBo.add(eventRegisterBO);
			});
		}
		return registerlistBo;
	}

	@Override
	public EventRegisterBO emailValidations(EventRegisterBO eventRegisterBo) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		EventVO eventVO=new EventVO();
		eventRegisterVO.setEmailAddress(eventRegisterBo.getEmailAddress());
		eventVO.setEventId(eventRegisterBo.getEventBo().getEventId());
		eventRegisterVO.setEventVo(eventVO);
		eventRegisterVO.setSending_status(true);
		eventRegisterVO.setIsDelete(false);
		EventRegisterVO eventRegisterVo=eventDao.emailValidations(eventRegisterVO);
		if(null!=eventRegisterVo){
			EventRegisterBO eventRegister=new EventRegisterBO();
			eventRegister.setEmailAddress(eventRegisterVo.getEmailAddress());
			return eventRegister;
		}
		return null; 
	}

	@Override
	public EventRegisterBO mobileNumberValidations(EventRegisterBO eventRegisterBo) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		EventVO eventVO=new EventVO();
		eventVO.setEventId(eventRegisterBo.getEventBo().getEventId());
		eventRegisterVO.setEventVo(eventVO);
		eventRegisterVO.setMobileNumber(eventRegisterBo.getMobileNumber());
		eventRegisterVO.setSending_status(true);
		eventRegisterVO.setIsDelete(false);
		EventRegisterVO eventRegisterVo=eventDao.mobileNumberValidations(eventRegisterVO);
		if(null!=eventRegisterVo){
			EventRegisterBO eventRegister=new EventRegisterBO();
			eventRegister.setMobileNumber(eventRegisterVo.getMobileNumber());
			return eventRegister;
		}
		return null;
	}

	@Override
	public long retrieveOfEventsRegistrationCount(EventRegisterBO eventRegisterBo) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		if(null!=eventRegisterBo.getCompanyBO() && 0!=eventRegisterBo.getCompanyBO().getCompanyId()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(eventRegisterBo.getCompanyBO().getCompanyId());
			eventRegisterVO.setCompanyVO(companyVo);	
		}
		eventRegisterVO.setSending_status(true);
		eventRegisterVO.setIsDelete(false);
		return eventDao.retrieveOfEventsRegistrationCount(eventRegisterVO);
	}

	@Override
	public EventRegisterBO ViewEventRegistrationDetails(EventRegisterBO eventRegisterBo) {
		// TODO Auto-generated method stub
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		eventRegisterVO.setEventRegisterId(eventRegisterBo.getEventRegisterId());
		eventRegisterVO.setSending_status(true);
		eventRegisterVO.setIsDelete(false);
		eventRegisterVO=eventDao.ViewEventRegistrationDetails(eventRegisterVO);
		if(null!=eventRegisterVO){
			EventRegisterBO eventRegisterBO=new EventRegisterBO();
			EventsBO eventsBO=new EventsBO();
			eventRegisterBO.setCandidateName(eventRegisterVO.getCandidateName());
			eventRegisterBO.setEmailAddress(eventRegisterVO.getEmailAddress());
			eventRegisterBO.setMobileNumber(eventRegisterVO.getMobileNumber());
			eventRegisterBO.setProfessional(eventRegisterVO.getProfessional());
			eventRegisterBO.setQualification(eventRegisterVO.getQualification());
			eventsBO.setEventId(eventRegisterVO.getEventVo().getEventId());
			eventsBO.setTitleName(eventRegisterVO.getEventVo().getTitleName());
			eventRegisterBO.setEventBo(eventsBO);
			return eventRegisterBO;
		}
		return eventRegisterBo;
	}

	@Override
	public List<EventsBO> retrieveCurrentEvents(EventsBO eventBO) throws ParseException {
		// TODO Auto-generated method stub
		EventVO eventVO=new EventVO();
		if(null!=eventBO.getCompanyBO() && 0!=eventBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(eventBO.getCompanyBO().getCompanyId());
			eventVO.setCompanyVO(companyVo);
		}
		List<EventsBO> eventlistBo=new ArrayList<EventsBO>();
		eventVO.setDate(new Date());
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		String startDate=format.format(eventVO.getDate());
		Date date=format.parse(startDate);
		eventVO.setDate(date);
		eventVO.setIsDelete(false);
		List<EventVO> eventslists=eventDao.retrieveCurrentEvents(eventVO);
		if(null!=eventslists && !eventslists.isEmpty() && eventslists.size()>0){
			for(EventVO event:eventslists){
				EventsBO eventBo=new EventsBO();
				eventBo.setEventId(event.getEventId());
				eventBo.setEventsName(event.getTitleName());
				eventlistBo.add(eventBo);
			}
		}
		return eventlistBo;
	}

	@Override
	public List<EventsBO> retrievePastEvents(EventsBO eventBO) throws ParseException {
		// TODO Auto-generated method stub
		EventVO eventVO=new EventVO();
		if(null!=eventBO.getCompanyBO() && 0!=eventBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(eventBO.getCompanyBO().getCompanyId());
			eventVO.setCompanyVO(companyVo);
		}
		List<EventsBO> eventlistBo=new ArrayList<EventsBO>();
		eventVO.setDate(new Date());
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		String startDate=format.format(eventVO.getDate());
		Date date=format.parse(startDate);
		eventVO.setDate(date);
		eventVO.setIsDelete(false);
		List<EventVO> eventlist=eventDao.retrievePastEvents(eventVO);
		if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0){
			for(EventVO event:eventlist){
				EventsBO eventBo=new EventsBO();
				eventBo.setEventId(event.getEventId());
				eventBo.setTitleName(event.getTitleName());
				eventlistBo.add(eventBo);
			}
		}
		return eventlistBo;
	}

	@Override
	public List<EventRegisterBO> searchEvents(EventRegisterBO eventRegisterBO) {
		// TODO Auto-generated method stub
		EventVO eventVO=new EventVO();
		List<EventRegisterBO> registerlistBo=new ArrayList<EventRegisterBO>();
		List<EventRegisterVO> registerlistVo=new ArrayList<EventRegisterVO>();
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		eventVO.setEventId(eventRegisterBO.getEventBo().getEventId());
		eventRegisterVO.setIsDelete(false);
		if(null!=eventRegisterBO.getCompanyBO() && 0!=eventRegisterBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(eventRegisterBO.getCompanyBO().getCompanyId());
			eventRegisterVO.setCompanyVO(companyVo);
		}
		eventRegisterVO.setRecordIndex(eventRegisterBO.getRecordIndex());
		eventRegisterVO.setMaxRecord(eventRegisterBO.getMaxRecord());
		eventRegisterVO.setEventVo(eventVO);
		List<EventRegisterVO> registerlist=eventDao.searchEvents(eventRegisterVO);
		if(null!=registerlist && !registerlist.isEmpty() && registerlist.size()>0){
			int sNo=eventRegisterVO.getRecordIndex();
			for(EventRegisterVO events:registerlist){
				EventVO eventsVO=new EventVO();
				EventRegisterVO eventRegisterVo=new EventRegisterVO();
				eventRegisterVo.setEventRegisterId(events.getEventRegisterId());
				eventRegisterVo.setEmailAddress(events.getEmailAddress());
				eventRegisterVo.setCandidateName(events.getCandidateName());
				eventsVO.setTitleName(events.getEventVo().getTitleName());
				eventRegisterVo.setS_No(++sNo);
				eventRegisterVo.setEventVo(eventsVO);
				registerlistVo.add(eventRegisterVo);
			}
			registerlistVo.forEach(registerEventlist->{
				EventsBO eventsBO=new EventsBO();
				EventRegisterBO eventRegisterBo=new EventRegisterBO();
				eventRegisterBo.setEventRegisterId(registerEventlist.getEventRegisterId());
				eventRegisterBo.setEmailAddress(registerEventlist.getEmailAddress());
				eventRegisterBo.setCandidateName(registerEventlist.getCandidateName());
				eventsBO.setTitleName(registerEventlist.getEventVo().getTitleName());
				eventRegisterBo.setS_No(registerEventlist.getS_No());
				eventRegisterBo.setEventBo(eventsBO);
				registerlistBo.add(eventRegisterBo);
			});
		}
		return registerlistBo;
	}

	@Override
	public List<EventRegisterBO> totalEventsCount(EventRegisterBO eventRegisterBO) {
		// TODO Auto-generated method stub
		List<EventRegisterBO> registerlistBo=new ArrayList<EventRegisterBO>();
		EventRegisterVO eventRegisterVO=new EventRegisterVO();
		if(null!=eventRegisterBO.getCompanyBO() && 0!=eventRegisterBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(eventRegisterBO.getCompanyBO().getCompanyId());
			eventRegisterVO.setCompanyVO(companyVo);
		}
		EventVO eventVO=new EventVO();
		eventVO.setEventId(eventRegisterBO.getEventBo().getEventId());
		eventRegisterVO.setEventVo(eventVO);
		eventRegisterVO.setIsDelete(false);
		List<EventRegisterVO> registerlistVo=eventDao.totalPastEventsCount(eventRegisterVO);
		if(null!=registerlistVo && !registerlistVo.isEmpty() && registerlistVo.size()>0){
			for(EventRegisterVO registerVo:registerlistVo){
				EventRegisterBO eventRegisterBo=new EventRegisterBO();
				eventRegisterBo.setCandidateName(registerVo.getCandidateName());
				eventRegisterBo.setEmailAddress(registerVo.getEmailAddress());
				registerlistBo.add(eventRegisterBo);
			}
		}
		return registerlistBo;
	}

	@Override
	public long retrieveOfEventsCompanyCount(EventsBO eventBO) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(eventBO.getCompanyBO().getCompanyId());
		EventVO eventVO=new EventVO();
		eventVO.setCompanyVO(companyVO);
		eventVO.setIsDelete(false);
		eventVO.setSending_status(true);
		return eventDao.retrieveOfEventsCompanyCount(eventVO);
	}

	@Override
	public List<EventsBO> viewEventsDetails(EventsBO eventsBO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int searchEventName(EventsBO eventsBO) {
		EventVO eventVO=new EventVO();
		eventVO.setTitleName(eventsBO.getTitleName());
		eventVO.setIsDelete(false);
		int totalEvent=eventDao.searchTitleName(eventVO);
		return totalEvent;
	}

	@Override
	public List<EventsBO> searchPageEvent(EventsBO eventsBO) {
		List<EventVO> eventList=new ArrayList<EventVO>();
		List<EventsBO> eventListBO=new ArrayList<EventsBO>();
		List<EventsBO> listEvenBO=new ArrayList<EventsBO>();
		EventVO eventVO=new EventVO();
		eventVO.setRecordIndex(eventsBO.getRecordIndex());
		eventVO.setMaxRecord(eventsBO.getMaxRecord());
		eventVO.setTitleName(eventsBO.getTitleName());
		eventVO.setIsDelete(false);
		eventVO.setEventId(eventsBO.getEventId());
		eventList=eventDao.searchPageEvent(eventVO);
		if(null!=eventList && eventList.size()>0 && !eventList.isEmpty()){
			int sNo=eventVO.getRecordIndex();
			int s_No=1;
			for(EventVO eventVo:eventList){
				EventsBO eventsBO1=new EventsBO();
				eventsBO1.setEventId(eventVo.getEventId());
				eventsBO1.setAddress(eventVo.getAddress());
				eventsBO1.setTitleName(eventVo.getTitleName());
				eventsBO1.setTiming(eventVo.getTiming());
				eventsBO1.setDate(eventVo.getDate());
				eventsBO1.setS_No(++sNo);
				eventListBO.add(eventsBO1);
			}
			eventListBO.forEach(eventVo->{
				EventsBO Eventsbo=new EventsBO();
				Eventsbo.setAddress(eventVo.getAddress());
				Eventsbo.setEventId(eventVo.getEventId());
				Eventsbo.setTitleName(eventVo.getTitleName());
				Eventsbo.setTiming(eventVo.getTiming());
				Eventsbo.setDate(eventVo.getDate());
				Eventsbo.setS_No(eventVo.getS_No());
				listEvenBO.add(Eventsbo);
			});
		}
		return listEvenBO;
	}
}









