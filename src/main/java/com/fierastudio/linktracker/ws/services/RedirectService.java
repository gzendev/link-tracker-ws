package com.fierastudio.linktracker.ws.services;

public interface RedirectService {
	
	public String getUrl(final String shortened, final String token) throws Exception;

}
