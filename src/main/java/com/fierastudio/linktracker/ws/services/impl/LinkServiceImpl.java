package com.fierastudio.linktracker.ws.services.impl;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.Optional;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import com.fierastudio.linktracker.ws.model.Link;
import com.fierastudio.linktracker.ws.repository.LinkRepository;
import com.fierastudio.linktracker.ws.response.LinkResponse;
import com.fierastudio.linktracker.ws.services.LinkService;
import com.fierastudio.linktracker.ws.util.Base62;

public class LinkServiceImpl implements LinkService {
	
	@Autowired
	private LinkRepository linkRepository;
	
	private String generateShortLink(Link link) {
		return Base62.encode(link.getId());
	}

	public LinkResponse save(String originalLink, Date expiredDate) throws MalformedURLException {
		String[] schemes = {"http","https"};
		UrlValidator valid = new UrlValidator(schemes);
		LinkResponse response = new LinkResponse();
		
		if (!valid.isValid(originalLink)) 
			throw new MalformedURLException();
		
		Optional<Link> link = linkRepository.findByOriginalLink(originalLink);
		if(link.isPresent()) {
			response.setTarget(link.get().getOriginalLink());
			response.setLink(link.get().getShortenedLink());
			response.setValid(true);
		} else {
			Link model = linkRepository.save(new Link(originalLink, null, expiredDate));
		}
		return response;
	}

}
