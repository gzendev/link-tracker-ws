package com.fierastudio.linktracker.ws.services.impl;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.Optional;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fierastudio.linktracker.ws.model.Link;
import com.fierastudio.linktracker.ws.repository.LinkRepository;
import com.fierastudio.linktracker.ws.services.RedirectService;

@Service
public class RedirectServiceImpl implements RedirectService {

	@Autowired
	private LinkRepository linkRepository;
	
	@Override
	public String getUrl(final String shortened, final String token) throws Exception {
		final String[] schemes = {"http","https"};
		final UrlValidator valid = new UrlValidator(schemes);
		
		if (!valid.isValid(shortened))
			throw new MalformedURLException("URL mal formada");
		
		Optional<Link> original = linkRepository.findByShortened(shortened);
		
		if(!original.isPresent() || !original.get().getToken().equals(token) || original.get().getExpiration().compareTo(new Date()) > 0)
			throw new Exception("URL inexistente");
		
		Statistic stat = original.get().getStatistic();
		if(stat != null) {
			stat.register();
			statisticRepository.save(stat);
		} else {
			statisticRepository.save(new Statistic(null, 1, original.get()));
		}
		
		return original.get().getOriginal();
	}
	
}
