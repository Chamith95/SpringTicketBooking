package com.middleware.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.middleware.service.EventService;
import com.middleware.shared.dto.EventDto;
import com.middleware.ui.model.request.EventRequestModel;
import com.middleware.ui.model.response.EventRest;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("events")
public class EventController {
	
	@Autowired
	EventService eventService;

	@PostMapping
	public EventRest createUser(@RequestBody EventRequestModel eventDetails)
	{
		EventRest returnValue =new EventRest();
		
		EventDto eventDto =new EventDto();
		BeanUtils.copyProperties(eventDetails,eventDto);
		
		EventDto createdEvent =eventService.createEvent(eventDto);
		BeanUtils.copyProperties(createdEvent, returnValue);
//		System.out.println(createdUser.getFirstName());
		
		return returnValue;
	}
	
	@GetMapping(path="/{id}")
	public List<EventRest> getOrgEvent(@PathVariable String id)
	{
		List<EventRest> returnValue=new ArrayList<>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<EventDto> eventDTO =eventService.getOrgEvents(id);
		
		if(eventDTO !=null && !eventDTO.isEmpty()) {
		java.lang.reflect.Type listType = new TypeToken<List<EventRest>>() {}.getType();
		returnValue= modelMapper.map(eventDTO, listType);
		}
		
	
	

		
		return returnValue;
	}
	
	@GetMapping(path="/all")
	public List<EventRest> getAllEvents()
	{
		List<EventRest> returnValue=new ArrayList<>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<EventDto> eventDTO =eventService.getAllEvents();
		
		if(eventDTO !=null && !eventDTO.isEmpty()) {
		java.lang.reflect.Type listType = new TypeToken<List<EventRest>>() {}.getType();
		returnValue= modelMapper.map(eventDTO, listType);
		}
		
	
	

		
		return returnValue;
	}
	
	@GetMapping(path="event/{id}")
	public EventRest getEvent(@PathVariable String id)
	{
		EventRest returnValue=new EventRest();
		
		ModelMapper modelMapper = new ModelMapper();
		
		EventDto eventDto =eventService.getEventByEventId(id);
		
		returnValue=modelMapper.map(eventDto,EventRest.class);

		
		return returnValue;
	}
	
	@PutMapping(path="/{id}")
	public EventRest updateUser(@PathVariable String id,@RequestBody EventRequestModel eventDetails)
	{
		EventRest returnValue=new EventRest();
		

		
		EventDto eventDto =new EventDto();
		BeanUtils.copyProperties(eventDetails, eventDto);
		
		EventDto updateEvent=eventService.updateEvent(id,eventDto);
		BeanUtils.copyProperties(updateEvent, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}")
	public EventRest DeleteUser(@PathVariable String id)
	{
		EventRest returnvalue=new EventRest();
		ModelMapper modelMapper = new ModelMapper();
		
		EventDto eventDto =eventService.getEventByEventId(id);
		
		
	
		
		eventService.deleteEvent(id);
		
		returnvalue=modelMapper.map(eventDto,EventRest.class);
		return returnvalue;
	}
}
