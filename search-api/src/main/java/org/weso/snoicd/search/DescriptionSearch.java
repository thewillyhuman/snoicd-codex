package org.weso.snoicd.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DescriptionSearch implements Search {
	
	private String description;
	
	public DescriptionSearch(String description) {
		log.info("Search by description created.");
		this.description = description;
	}

	@Override
	public ResponseToQuery execute(ConceptsService service) {
		log.info("Executing search by description.");
		ResponseToQuery rtq = new ResponseToQuery();
		
		rtq.setQuery(this.description);
		rtq.setResult(service.getConceptByDescription(this.description));
		rtq.setStatus(HttpStatus.OK);
		
		return rtq;
	}
}
