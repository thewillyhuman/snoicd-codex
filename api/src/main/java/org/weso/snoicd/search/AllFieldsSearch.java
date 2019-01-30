package org.weso.snoicd.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllFieldsSearch implements Search {

	private ConceptsService service;
	private String textToFind;

	public AllFieldsSearch(ConceptsService service, String textToFind) {
		log.info("Search by all fields created.");
		this.service = service;
		this.textToFind = textToFind;
	}

	@Override
	public ResponseToQuery execute() {
		log.info("Executing search by all fields.");
		ResponseToQuery rtq = new ResponseToQuery();

		// Get results for code.
		rtq.setResult(this.service.getConceptByCode(this.textToFind));

		// Add with the results for description.
		rtq.getResult().addAll(this.service.getConceptsByDescription(this.textToFind));

		// And finally set the status to OK.
		rtq.setStatus(HttpStatus.OK);

		// TODO Auto-generated method stub
		return null;
	}

}
