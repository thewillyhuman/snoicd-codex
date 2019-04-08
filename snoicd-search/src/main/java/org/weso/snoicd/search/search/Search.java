package org.weso.snoicd.search.search;

import org.weso.snoicd.search.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

@FunctionalInterface
public interface Search {
	
	/**
	 * Executes the search and returns the response to the given query.
	 * 
	 * @return the corresponding ResponseEntity after the execution of the query.
	 */
	public ResponseToQuery execute(ConceptsService service);

}
