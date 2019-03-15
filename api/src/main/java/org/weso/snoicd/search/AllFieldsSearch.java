package org.weso.snoicd.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllFieldsSearch implements Search {

	private String textToFind;

	public AllFieldsSearch(String textToFind) {
		log.info("Search by all fields created.");
		this.textToFind = textToFind;
	}

	@Override
	public ResponseToQuery execute(ConceptsService service) {
		log.info("Executing search by all fields.");
		ResponseToQuery rtq = new ResponseToQuery();
		
		rtq.setQuery(this.textToFind);

		// Get results for code.
		rtq.setResult(service.getConceptByCode(this.textToFind));

		// Add with the results for description.
		rtq.getResult().addAll(service.getConceptsSearch(this.textToFind));

		// And finally set the status to OK.
		rtq.setStatus(HttpStatus.OK);

		log.info("Dispaching search by all fields.");
		// TODO Auto-generated method stub
		return rtq;
	}
}
