package com.fierastudio.linktracker.ws.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fierastudio.linktracker.ws.config.Config;
import com.fierastudio.linktracker.ws.dto.LinkDto;
import com.fierastudio.linktracker.ws.dto.StatisticDto;
import com.fierastudio.linktracker.ws.message.ResponseMessage;
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
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		if(link.isPresent() && link.get().getExpiration().compareTo(calendar.getTime()) > 0) {
			response.setTarget(link.get().getOriginal());
			response.setLink(link.get().getShortened());
			response.setValid(true);
		} else {
			Long id = linkRepository.getNextSeriesId();
			Link model = linkRepository.save(new Link(id, original, config.getBaseUrl() + generateShortenedLink(id), expiration, token, 0, 1));
			response = Link.fromModel(model);
		}
		return response;
	}

	@Override
	public StatisticDto getStatistic(String shortened) {
		StatisticDto stat = new StatisticDto();
		final UrlValidator valid = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
		
		if (!valid.isValid(shortened))
			return stat;
		
		Optional<Link> link = linkRepository.findByShortened(shortened);
		if(!link.isPresent()) return stat;
		
		stat.setShortened(link.get().getShortened());
		stat.setRedirect(link.get().getRedirects());
		return stat;
	}

	@Override
	public ResponseMessage invalidate(String shortened) {
		final UrlValidator valid = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
		Optional<Link> link = linkRepository.findByShortened(shortened);
		
		if (!valid.isValid(shortened))
			return new ResponseMessage("URL Mal Formada");
		
		if(!link.isPresent()) return new ResponseMessage("URL inexistente");
		
		link.get().setValid(0);
		linkRepository.save(link.get());
		
		return new ResponseMessage("URL inválidada");
	}
}
