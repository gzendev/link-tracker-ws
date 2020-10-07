package com.fierastudio.linktracker.ws.services;

import java.util.Date;
import com.fierastudio.linktracker.ws.dto.LinkDto;
import com.fierastudio.linktracker.ws.dto.StatisticDto;
import com.fierastudio.linktracker.ws.message.ResponseMessage;

public interface LinkService {
	
	public LinkDto save(final String original, final Date expiration, final String token);
	
	public StatisticDto getStatistic(final String shortened);
	
	public ResponseMessage invalidate(final String shortened);
	
}
