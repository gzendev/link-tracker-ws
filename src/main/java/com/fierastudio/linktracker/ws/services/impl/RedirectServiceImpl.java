package com.fierastudio.linktracker.ws.services.impl;

import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fierastudio.linktracker.ws.config.Config;
import com.fierastudio.linktracker.ws.model.Link;
import com.fierastudio.linktracker.ws.repository.LinkRepository;
import com.fierastudio.linktracker.ws.services.RedirectService;

@Service
public class RedirectServiceImpl implements RedirectService {

	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private Config config;
	
	@Override
	public String getUrl(final String subdomain, final String shortened, final String token) throws Exception {
		if(!config.getSubdomain().equals(subdomain))
			throw new MalformedURLException("URL mal formada");
		
		Optional<Link> original = linkRepository.findByShortened(config.getBaseUrl() + shortened);
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		if(!original.isPresent() || !original.get().getToken().equals(token) 
				|| original.get().getExpiration().compareTo(calendar.getTime()) <= 0 || original.get().getValid() == 0)
			throw new Exception("URL inexistente");
		
		original.get().register();
		linkRepository.save(original.get());

		return original.get().getOriginal();
	}
}
