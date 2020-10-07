package com.fierastudio.linktracker.ws.services.impl;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fierastudio.linktracker.ws.config.Config;
import com.fierastudio.linktracker.ws.dto.LinkDto;
import com.fierastudio.linktracker.ws.model.Link;
import com.fierastudio.linktracker.ws.repository.LinkRepository;
import com.fierastudio.linktracker.ws.services.LinkService;
import com.fierastudio.linktracker.ws.util.Base62;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private Config config;
	
	private String generateShortenedLink(Long n) {
		return Base62.encode(n);
	}

	public LinkDto save(final String original, final Date expiration, final String token) {
		LinkDto response = new LinkDto();
		Optional<Link> link = linkRepository.findByOriginal(original);
		if(link.isPresent()) {
			response.setTarget(link.get().getOriginal());
			response.setLink(link.get().getShortened());
			response.setValid(true);
		} else {
			Long id = linkRepository.getNextSeriesId();
			Link model = linkRepository.save(new Link(id, original, config.getBaseUrl() + generateShortenedLink(id), expiration, token, 0));
			response = Link.fromModel(model);
		}
		return response;
	}
}
