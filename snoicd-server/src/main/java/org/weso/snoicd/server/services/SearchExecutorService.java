package org.weso.snoicd.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.core.AbstractSearchStrategy;
import org.weso.snoicd.search.core.AllFieldsSearchStrategy;
import org.weso.snoicd.search.core.ConceptIDSearchStrategy;
import org.weso.snoicd.search.persistence.BigTablePersistenceImpl;

public class SearchExecutorService /*implements Search*/ {

    private AbstractSearchStrategy searchStrategy;

    private final Logger log = LoggerFactory.getLogger(SearchExecutorService.class);

    public SearchExecutorService(String filter) {

        if (filter != null && filter != "" && filter != " ") {

            // If we filter by code.
            if (filter.equals("code")) {
                log.info("Searching by CODE");
                searchStrategy = new ConceptIDSearchStrategy(BigTablePersistenceImpl.instance);

                // If we filter only by description.
            } else if (filter.equals("description")) {
                log.info("Searching by DESCRIPTION");
                searchStrategy = new ConceptIDSearchStrategy(BigTablePersistenceImpl.instance);

                // In any other case the query is not accepted.
            } else {
                log.info("Searching by ALL FIELDS [invalid filter]");
                searchStrategy = new AllFieldsSearchStrategy(BigTablePersistenceImpl.instance);
            }

            // If there is no filter present then...
        } else {
            log.info("Searching by ALL FIELDS [no filter found]");
            searchStrategy = new AllFieldsSearchStrategy(BigTablePersistenceImpl.instance);
        }
    }

    public ResponseToQuery execute(String query) {
        log.info("Executing query -> " + query);
        this.searchStrategy.setQuery(query);
        this.searchStrategy.run();
        return new ResponseToQuery(query, this.searchStrategy.getResult());
    }
}
