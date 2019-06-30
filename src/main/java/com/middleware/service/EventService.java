package com.middleware.service;

import java.util.List;

import com.middleware.shared.dto.EventDto;

public interface EventService {
	EventDto createEvent(EventDto event);
	List<EventDto> getOrgEvents(String orgId);
	EventDto updateEvent(String eventid,EventDto user);
	void deleteEvent(String userId);
	EventDto getEventByEventId(String eventId);
}
