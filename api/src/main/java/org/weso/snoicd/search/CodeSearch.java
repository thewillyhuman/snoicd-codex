package org.weso.snoicd.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSearch implements Search {
	
	private ConceptsService service;
	private String code;
	
	public CodeSearch(ConceptsService service, String code) {
		log.info("Search by code created.");
		this.service = service;
		this.code = code;
	}

	@Override
	public ResponseToQuery execute() {
		log.info("Executing search by code.");
		ResponseToQuery rtq = new ResponseToQuery();
		
		rtq.setQuery(code);
		rtq.setResult(this.service.getConceptByCode(this.code));
		rtq.setStatus(HttpStatus.OK);
		
		return rtq;
	}
}
