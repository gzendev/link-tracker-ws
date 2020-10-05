package com.fierastudio.linktracker.ws.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.fierastudio.linktracker.ws.model.Link;

public interface LinkRepository extends CrudRepository<Link, Long> {
	
	Optional<Link> findByOriginalLink(String originalLink);
	
}
