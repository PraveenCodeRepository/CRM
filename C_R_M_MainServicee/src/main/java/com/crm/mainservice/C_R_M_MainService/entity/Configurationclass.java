package com.crm.mainservice.C_R_M_MainService.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurationclass {
    
	
	
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	
}
