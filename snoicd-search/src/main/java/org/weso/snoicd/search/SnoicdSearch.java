package org.weso.snoicd.search;

import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.core.AbstractSearchStrategy;
import org.weso.snoicd.search.core.SearchStrategy;

public interface SnoicdSearch {

    /**
     * Executes a query over the given filter.
     *
     * @param searchStrategy to apply before the execution of the query.
     * @return the response to the executed query.
     * @throws IllegalStateException if a the concepts are not loaded in memory before.
     */
    ResponseToQuery executeQuery(AbstractSearchStrategy searchStrategy, String query) throws IllegalStateException;

    /**
     * Loads the concepts in memory.
     *
     * @return true if no problen during loading the concepts.
     * @throws IllegalStateException if the conepts are already in memory.
     */
    boolean loadConceptsInMemory() throws IllegalStateException;
}
