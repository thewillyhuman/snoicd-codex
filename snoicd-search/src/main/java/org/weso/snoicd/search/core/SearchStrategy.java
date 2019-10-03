package org.weso.snoicd.search.core;

import org.weso.snoicd.core.Concept;

import java.util.Set;

public interface SearchStrategy {

    /**
     * Gets the result after the executio of th filter.
     *
     * @return the result after the execution of the filter.
     * @throws IllegalStateException If the results are get before executing the filter.
     */
    Set<Concept> getResult() throws IllegalStateException;

    void setQuery( String query );
}
