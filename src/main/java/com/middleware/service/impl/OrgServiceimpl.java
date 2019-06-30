package com.middleware.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public OrgDto createOrganization(OrgDto org) {
		// TODO Auto-generated method stub
		
		if(orgRepository.findByEmail(org.getEmail())!=null)throw new RuntimeException("Record Already exists");
		
		OrgEntity orgEntity=new OrgEntity();
		BeanUtils.copyProperties(org, orgEntity);
		
		String OrgId=utils.generateOrgId(30);
		orgEntity.setOrgId(OrgId);
		orgEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(org.getPassword()));
		
		OrgEntity storedOrgEntity=orgRepository.save(orgEntity);
		
		OrgDto returnValue=new OrgDto();
		BeanUtils.copyProperties(storedOrgEntity, returnValue);
		
		return returnValue;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		OrgEntity orgEntity=orgRepository.findByEmail(email);


		if(orgEntity ==null)throw new UsernameNotFoundException(email);
		
		return new User(orgEntity.getEmail(),orgEntity.getEncryptedPassword(),new ArrayList<>());
	
	}


	@Override
	public OrgDto getUser(String email) {
		OrgEntity orgEntity=orgRepository.findByEmail(email);
		
		if(orgEntity ==null)return null;
		
		OrgDto returnValue=new OrgDto();
		BeanUtils.copyProperties(orgEntity, returnValue);
		
		return returnValue;
	}

}
