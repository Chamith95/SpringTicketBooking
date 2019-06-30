package com.middleware.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Override
	public List<EventDto> getOrgEvents(String orgId) {
		List<EventDto> returnValue =new ArrayList<>();
		
//		EventEntity userEntity =EventRepository.findByUserId(orgId);
//		
		ModelMapper modelMapper = new ModelMapper();
//		
//		
//		if(userEntity ==null) return returnValue;
		
		Iterable<EventEntity> addresses =eventRepository.findAllByOrgid(orgId);
		
		
	
		
		for(EventEntity addressEntity:addresses) {
//			System.out.println(modelMapper.map(addressEntity,EventDto.class).getAddressId());
			returnValue.add(modelMapper.map(addressEntity,EventDto.class));
		}
		
			
		return returnValue;
	}

	@Override
	public EventDto getEventByEventId(String eventId) {
		EventDto returnValue =new EventDto();
		EventEntity eventEntity =eventRepository.findByEventid(eventId);
		if(eventEntity== null) {
		throw new UsernameNotFoundException("User with id" +eventId+" not found");
	}
	
	BeanUtils.copyProperties(eventEntity, returnValue );
	return returnValue;
	}

	@Override
	public EventDto updateEvent(String eventid, EventDto event) {
		EventDto returnValue =new EventDto();
		EventEntity eventEntity =eventRepository.findByEventid(eventid);
		if(eventEntity== null) {
		return null;
	}
		
		eventEntity.setName(event.getName());
		eventEntity.setDate(event.getDate());
		eventEntity.setVenue(event.getVenue());
		eventEntity.setDresstype(event.getDresstype());
		eventEntity.setTicketcount(event.getTicketcount());
		eventEntity.setDescription(event.getDescription());
		
		
		EventEntity updatedEventDetails=eventRepository.save(eventEntity);
		BeanUtils.copyProperties(updatedEventDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteEvent(String eventId) {
		EventEntity eventEntity =eventRepository.findByEventid(eventId);
		
		if(eventEntity== null) {
		return ;
	}
		eventRepository.delete(eventEntity);
		
	}

}
