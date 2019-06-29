package com.middleware.security;

import com.middleware.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME=86400000;
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String TOKEN_SECRET="jf9i4jgu83nfl10";
	public static final String HEADER_STRING="Authorization";
	public static final String USER_SIGN_UP_URL="/users";
	public static final String ORG_SIGN_UP_URL="/Organizations";
	
	public static String getTokenSecret()
	{
		AppProperties appProperties =(AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
