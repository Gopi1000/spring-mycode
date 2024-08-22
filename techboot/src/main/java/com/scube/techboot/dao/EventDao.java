package com.scube.techboot.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.vo.EventRegisterVO;
import com.scube.techboot.vo.EventVO;

public interface EventDao {

	long retriveOfEvent(EventVO eventsVO);

	EventVO saveEvent(EventVO eventVO);

	boolean postEditEvent(EventVO eventVO);

	boolean deleteEvent(EventVO eventVo);

	EventVO viewEventDetails(EventVO eventVo);

	EventRegisterVO saveEventRegister(EventRegisterVO eventRegisterVO);

	List<EventRegisterVO> searchEvents(EventRegisterVO eventRegisterVO);

	EventVO getEventObjects(EventVO eventVO);

	List<EventRegisterVO> viewEventRegistration(EventRegisterVO eventRegisterVO);

	EventRegisterVO emailValidations(EventRegisterVO eventRegisterVO);

	EventRegisterVO ViewEventRegistrationDetails(EventRegisterVO eventRegisterVO);

	List<EventVO> retrieveCurrentEvents(EventVO eventVO);

	List<EventVO> retrievePastEvents(EventVO eventVO);

	List<EventVO> viewListEvent(EventVO eventVO, HttpSession session);

	long retrieveOfEventsCount(EventVO eventVO);

	long retrieveOfEventsRegistrationCount(EventRegisterVO eventRegisterVO);

	List<EventRegisterVO> totalPastEventsCount(EventRegisterVO eventRegisterVO);

	EventRegisterVO mobileNumberValidations(EventRegisterVO eventRegisterVO);

	long retrieveOfEventsCompanyCount(EventVO eventVO);

	int searchTitleName(EventVO eventVO);

	List<EventVO> searchPageEvent(EventVO eventVO);

}
