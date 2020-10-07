package com.fierastudio.linktracker.ws.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fierastudio.linktracker.ws.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
	
	Optional<Link> findByOriginal(String original);
	
	Optional<Link> findById(long id);
	
	@Query(value = "SELECT l FROM Link l WHERE l.shortened = :shortened AND l.valid = 1")
	Optional<Link> findByShortened(String shortened);
	
	@Query(value = "SELECT nextval('link_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
	
}
