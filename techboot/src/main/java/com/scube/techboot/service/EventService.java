package com.scube.techboot.service;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.bo.CourseDetailsBO;
import com.scube.techboot.bo.EventRegisterBO;
import com.scube.techboot.bo.EventsBO;

public interface EventService {

	long retriveOfEvent(EventsBO eventsBo);

	EventsBO saveEvent(EventsBO eventsBo);

	boolean postEditEvent(EventsBO eventBO);

	boolean deleteEvent(EventsBO eventBo);

	List<EventsBO> viewListEvent(EventsBO eventBO,HttpSession session) throws FileNotFoundException, ParseException;

	EventRegisterBO saveEventRegister(EventRegisterBO eventRegisterBO);

	List<EventRegisterBO> viewEventRegistration(EventRegisterBO eventRegisterBo);

	EventRegisterBO emailValidations(EventRegisterBO eventRegisterBo);

	EventRegisterBO ViewEventRegistrationDetails(EventRegisterBO eventRegisterBo);

	List<EventsBO> retrieveCurrentEvents(EventsBO eventBO) throws ParseException;

	List<EventsBO> retrievePastEvents(EventsBO eventBO) throws ParseException;

	List<EventRegisterBO> searchEvents(EventRegisterBO eventRegisterBO);

	EventsBO getEventObjects(EventsBO eventBO) throws ParseException;

	long retrieveOfEventsCount(EventsBO eventBO);

	long retrieveOfEventsRegistrationCount(EventRegisterBO eventRegisterBo);

	List<EventRegisterBO> totalEventsCount(EventRegisterBO eventRegisterBO);

	EventRegisterBO mobileNumberValidations(EventRegisterBO eventRegisterBo);

	long retrieveOfEventsCompanyCount(EventsBO eventBO);

	List<EventsBO> viewEventsDetails(EventsBO eventsBO);

	int searchEventName(EventsBO eventsBO);

	List<EventsBO> searchPageEvent(EventsBO eventsBO);

}
