package com.fierastudio.linktracker.ws.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fierastudio.linktracker.ws.dto.LinkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Link", schema = "linktracker")
public class Link {

	@Id
    @SequenceGenerator(name = "id_seq", sequenceName = "link_id_seq", allocationSize = 1)
    @Column(name = "link_id", updatable = false)
	private Long link_id;
	
	@NotNull
	@Column(name = "original", updatable = false, nullable = false)
	private String original;
	
	@NotNull
	@Column(name = "shortened", updatable = false, nullable = false)
	private String shortened;
	
	@NotNull
	@Column(name = "expiration", updatable = false, nullable = false)
	private Date expiration;
	
	@NotNull
	@Column(name = "token", updatable = false, nullable = false)
	private String token;
	
	@NotNull
	@Column(name = "redirect", updatable = false, nullable = false)
	private Integer redirects;
	
	public static LinkDto fromModel(final Link link) {
		return new LinkDto(link.getOriginal(), link.getShortened(), true);		
	}
	
	public void register() {
		this.redirects++;
	}
}
