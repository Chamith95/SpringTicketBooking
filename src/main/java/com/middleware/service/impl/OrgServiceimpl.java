package com.middleware.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.middleware.io.entity.OrgEntity;
import com.middleware.repository.OrgRepository;
import com.middleware.service.OrgService;
import com.middleware.shared.Utils;
import com.middleware.shared.dto.OrgDto;

@Service
public class OrgServiceimpl implements OrgService {
	
	@Autowired
	OrgRepository orgRepository;
	
	@Autowired
	Utils utils;
	

	@Override
	public OrgDto createOrganization(OrgDto org) {
		// TODO Auto-generated method stub
		
		if(orgRepository.findByEmail(org.getEmail())!=null)throw new RuntimeException("Record Already exists");
		
		OrgEntity orgEntity=new OrgEntity();
		BeanUtils.copyProperties(org, orgEntity);
		
		String OrgId=utils.generateOrgId(30);
		orgEntity.setOrgId(OrgId);
		orgEntity.setEncryptedPassword("test");
		
		OrgEntity storedOrgEntity=orgRepository.save(orgEntity);
		
		OrgDto returnValue=new OrgDto();
		BeanUtils.copyProperties(storedOrgEntity, returnValue);
		
		return returnValue;
	}

}
