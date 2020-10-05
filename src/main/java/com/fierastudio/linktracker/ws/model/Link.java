package com.fierastudio.linktracker.ws.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="Link", schema = "linktracker")
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "original_link", updatable = false, nullable = false)
	private String originalLink;
	
	@NotNull
	@Column(name = "shortened_link", updatable = true, nullable = true)
	private String shortenedLink;
	
	@NotNull
	@Column(name = "expirate_date", updatable = false, nullable = false)
	private Date expiredDate;
	
	public Link(String originalLink, String shortenedLink, Date expiredDate) {
		this.originalLink = originalLink;
		this.shortenedLink = shortenedLink;
		this.expiredDate = expiredDate;
	}
}
