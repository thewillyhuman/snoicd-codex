package org.weso.snoicd.search;

import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchExecutor implements Search {

	private Search searchStrategy;

	public SearchExecutor(String query, String filter) {
		
		if (filter != null && filter != "" && filter != " ") {
			log.info("Filter found.");

			// If we filter by code.
			if (filter.equals("code")) {
				log.info("Filter was: code.");
				searchStrategy = new CodeSearch(query);

				// If we filter only by description.
			} else if (filter.equals("description")) {
				log.info("Filter was: description.");
				searchStrategy = new DescriptionSearch(query);

				// In any other case the query is not accepted.
			} else {
				log.warn("Invalid search found.");
				searchStrategy = new InvalidSearch();
			}
			
			// If there is no filter present then...
		} else {
			log.info("No filter found.");
			searchStrategy = new AllFieldsSearch(query);
		}
	}

	@Override
	public ResponseToQuery execute(ConceptsService service) {
		return this.searchStrategy.execute(service);
	}

}
