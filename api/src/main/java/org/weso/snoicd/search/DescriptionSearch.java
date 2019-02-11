package org.weso.snoicd.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DescriptionSearch implements Search {
	
	private ConceptsService service;
	private String description;
	
	public DescriptionSearch(ConceptsService service, String description) {
		log.info("Search by description created.");
		this.service = service;
		this.description = description;
	}

	@Override
	public ResponseToQuery execute() {
		log.info("Executing search by description.");
		ResponseToQuery rtq = new ResponseToQuery();
		
		rtq.setQuery(this.description);
		rtq.setResult(this.service.getConceptsSearch(this.description));
		rtq.setStatus(HttpStatus.OK);
		
		return rtq;
	}
}
