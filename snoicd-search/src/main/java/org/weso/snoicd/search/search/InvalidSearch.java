package org.weso.snoicd.search.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.search.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidSearch implements Search {

	@Override
	public ResponseToQuery execute(ConceptsService service) {
		log.info("Filter was: invalid.");
		log.info("SEARCH invalidated.");
		
		ResponseToQuery rtq = new ResponseToQuery();
		rtq.setQuery("invalid-search");
		rtq.setResult(null);
		rtq.setStatus(HttpStatus.BAD_REQUEST);
		
		return rtq;
	}

}
