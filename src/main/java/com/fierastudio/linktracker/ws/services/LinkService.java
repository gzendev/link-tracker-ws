package com.fierastudio.linktracker.ws.services;

import java.net.MalformedURLException;
import java.util.Date;
import com.fierastudio.linktracker.ws.response.LinkResponse;

public interface LinkService {
	
	public LinkResponse save(String originalLink, Date expiredDate) throws MalformedURLException;
	
}
