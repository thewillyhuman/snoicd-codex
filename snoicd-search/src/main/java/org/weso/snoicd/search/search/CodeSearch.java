package org.weso.snoicd.search.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.search.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSearch implements Search {
	
	private String code;
	
	public CodeSearch(String code) {
		log.info("Search by code created.");
		this.code = code;
	}

	@Override
	public ResponseToQuery execute(ConceptsService service) {
		log.info("Executing search by code.");
		ResponseToQuery rtq = new ResponseToQuery();
		
		rtq.setQuery(this.code);
		rtq.setResult(service.getConceptByCode(this.code));
		rtq.setStatus(HttpStatus.OK);
		
		return rtq;
	}
}
