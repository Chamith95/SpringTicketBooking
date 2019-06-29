package com.middleware.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.middleware.service.OrgService;
import com.middleware.service.UserService;







@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final OrgService orgDetailsService;
	private final UserService userDetailsServices;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService,OrgService orgDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsServices=userDetailsService;
		this.orgDetailsService=orgDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception {
		
		
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,"/Organizations").permitAll()
		.antMatchers(HttpMethod.POST,"/users").permitAll().
			anyRequest().authenticated().and().addFilter(new AuthenticationFilter(authenticationManager()))
			.addFilter(new AuthorizationFilter(authenticationManager()));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
	auth.userDetailsService(userDetailsServices).passwordEncoder(bCryptPasswordEncoder);
		auth.userDetailsService(orgDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList("*"));
		// or any domain that you want to restrict to
		configuration.addAllowedHeader("Authorization");
		configuration.addAllowedHeader("Access-Control-Expose-Headers");
		configuration.addAllowedHeader("Content-Type");
		configuration.addAllowedHeader("Origin");
		configuration.addAllowedHeader("Accept");
	    configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("UserId");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.addExposedHeader("Authorization");
		configuration.addExposedHeader("UserId");
		configuration.addExposedHeader("OrgId");
		configuration.addExposedHeader("Content-type");
		// Add the method support as you like
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
}
