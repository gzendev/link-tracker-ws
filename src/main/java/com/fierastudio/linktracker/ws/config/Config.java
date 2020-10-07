package com.fierastudio.linktracker.ws.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="server")
public class Config {
	
	private String domain;
	private String subdomain;
	
	public String getBaseUrl() {
		return domain + subdomain;
	}

}
