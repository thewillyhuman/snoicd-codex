package org.weso.snoicd.search.persistence;

public interface WarmUpMemory {

    /**
     * Starts the process of loading the index files produced by the indexer in the persistence layer of the search.
     *
     * @return true if the process was successful, false otherwise.
     */
    boolean init();
}
