package com.middleware.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
}
