package com.middleware.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.middleware.service.OrgService;
import com.middleware.shared.dto.OrgDto;
import com.middleware.ui.model.request.OrgDetailsRequestModel;
import com.middleware.ui.model.response.OrgRest;



@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("Organizations")
public class OrganizationController {
	
	@Autowired
	OrgService orgService;
	
	@GetMapping
	public String getOrg()
	{
		return "get organization was called";
	}
	
	@PutMapping
	public String updateOrg()
	{
		return "Upadte organization was called";
	}
	
	@PostMapping
	public OrgRest createUser(@RequestBody OrgDetailsRequestModel orgDetails)
	{
		OrgRest returnValue =new OrgRest();
		System.out.println(orgDetails.getName());
		OrgDto orgDto =new OrgDto();
		BeanUtils.copyProperties(orgDetails,orgDto);
		
		OrgDto createdOrg =orgService.createOrganization(orgDto);
		BeanUtils.copyProperties(createdOrg, returnValue);
//		System.out.println(createdUser.getFirstName());
		
		return returnValue;
	}
	
	@DeleteMapping
	public String deleteOrg()
	{
		return "Delete organization was called";
	}

}
