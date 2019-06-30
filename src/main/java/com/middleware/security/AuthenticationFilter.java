package com.middleware.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.middleware.SpringApplicationContext;
import com.middleware.service.OrgService;
import com.middleware.service.UserService;
import com.middleware.shared.dto.OrgDto;
import com.middleware.shared.dto.UserDto;
import com.middleware.ui.model.request.UserLogingRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager=authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res)throws AuthenticationException{
		System.out.println("tried");
		try {
			UserLogingRequestModel creds=new ObjectMapper()
					.readValue(req.getInputStream(),UserLogingRequestModel.class);
//		 	System.out.println(creds.getEmail());
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(),
							creds.getPassword(),
							new ArrayList<>())
							);
			
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,
												FilterChain chain,Authentication auth)throws IOException,ServletException{
	
		String userName=((User) auth.getPrincipal()).getUsername();
		System.out.println(userName);
		System.out.println(SecurityConstants.TOKEN_SECRET);
		
		String token =Jwts.builder()
					.setSubject(userName)
					.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512,SecurityConstants.TOKEN_SECRET)
					.compact();
		
		UserService userService=(UserService) SpringApplicationContext.getBean("userServiceimpl");
		OrgService orgService=(OrgService) SpringApplicationContext.getBean("orgServiceimpl");
		UserDto userDto=userService.getUser(userName);
		OrgDto orgDto=orgService.getUser(userName);
					
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		
		if(userDto!=null) {
			res.addHeader("UserId", userDto.getUserId());
		}
		else if(orgDto!=null){
			res.addHeader("OrgId", orgDto.getOrgId());
		}
		
//		String JsonString = new Gson().toJson( SecurityConstants.TOKEN_PREFIX + token);
//		PrintWriter out = res.getWriter();
//		res.setContentType("application/json");
//		res.setCharacterEncoding("UTF-8");
//		out.print(JsonString);
//		out.flush();
	}
	
	}

