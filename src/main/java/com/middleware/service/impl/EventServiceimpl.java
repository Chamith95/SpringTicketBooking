package com.middleware.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.middleware.io.entity.EventEntity;
import com.middleware.repository.EventRepository;
import com.middleware.service.EventService;
import com.middleware.shared.Utils;
import com.middleware.shared.dto.EventDto;

@Service
public class EventServiceimpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	Utils utils;

	@Override
	public EventDto createEvent(EventDto event) {
		// TODO Auto-generated method stub
		EventEntity eventEntity=new EventEntity();
		BeanUtils.copyProperties(event, eventEntity);
		
		String eventid=utils.generateEventId(20);
		eventEntity.setEventid(eventid);
		
		EventEntity storedEvent=eventRepository.save(eventEntity);
		
		EventDto returnvalue=new EventDto();
		BeanUtils.copyProperties(storedEvent, returnvalue);
		
		return returnvalue;
	}

}