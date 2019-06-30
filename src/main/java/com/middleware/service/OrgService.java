package com.middleware.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.middleware.shared.dto.OrgDto;




public interface OrgService extends UserDetailsService {
	OrgDto createOrganization(OrgDto org);
	OrgDto getUser(String email);
}
