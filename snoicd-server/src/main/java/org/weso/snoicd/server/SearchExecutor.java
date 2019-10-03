package org.weso.snoicd.server;

//import lombok.extern.slf4j.Slf4j;
//import org.weso.snoicd.search.services.ConceptsServiceOperations;
//import org.weso.snoicd.types.ResponseToQueryAdapter;

import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.core.AbstractSearchStrategy;
import org.weso.snoicd.search.core.AllFieldsSearchStrategy;
import org.weso.snoicd.search.core.ConceptIDSearchStrategy;
import org.weso.snoicd.search.core.SearchStrategy;
import org.weso.snoicd.search.persistence.BigTablePersistenceImpl;

//@Slf4j
public class SearchExecutor /*implements Search*/ {

    private AbstractSearchStrategy searchStrategy;

    public SearchExecutor(String filter) {

        if (filter != null && filter != "" && filter != " ") {

            // If we filter by code.
            if (filter.equals("code")) {
                searchStrategy = new ConceptIDSearchStrategy(BigTablePersistenceImpl.instance);

                // If we filter only by description.
            } else if (filter.equals("description")) {
                searchStrategy = new ConceptIDSearchStrategy(BigTablePersistenceImpl.instance);

                // In any other case the query is not accepted.
            } else {
                searchStrategy = new AllFieldsSearchStrategy(BigTablePersistenceImpl.instance);
            }

            // If there is no filter present then...
        } else {
            searchStrategy = new AllFieldsSearchStrategy(BigTablePersistenceImpl.instance);
        }
    }

    public ResponseToQuery execute(String query) {
        System.err.println("Executing search...");
        this.searchStrategy.setQuery(query);
        this.searchStrategy.run();
        return new ResponseToQuery(query, this.searchStrategy.getResult());
    }
}
