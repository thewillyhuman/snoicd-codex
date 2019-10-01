package org.weso.snoicd.search;

import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.filter.Filter;

public interface SnoicdSearch {

    /**
     * Executes a query over the given filter.
     *
     * @param filter
     * @param query
     * @return
     * @throws IllegalStateException
     */
    ResponseToQuery executeQuery(Filter filter, String query) throws IllegalStateException;

    /**
     * Loads the concepts in memory, mandatory to execute before any search operation can be executed.
     *
     * @return true if the operation has been done successfully, false otherwise.
     */
    boolean loadConceptsInMemory() throws IllegalStateException;
}
