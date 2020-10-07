package com.fierastudio.linktracker.ws.dto;

import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class LinkRequest {
	
	@NotNull
	private String target;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yy")
	private String expiration;
}
