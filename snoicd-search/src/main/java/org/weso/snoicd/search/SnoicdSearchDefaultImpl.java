package org.weso.snoicd.search;

import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.persistence.BigTableWarmUpMemoryImpl;
import org.weso.snoicd.search.persistence.WarmUpMemory;
import org.weso.snoicd.search.filter.Filter;

public class SnoicdSearchDefaultImpl implements SnoicdSearch {

    private Filter filter;
    private String query, pathToConceptIdIndex, pathToDescriptionsIndex;

    private boolean areConceptsLoaded = false;

    public SnoicdSearchDefaultImpl(Filter filter, String query,
                                   String pathToConceptIdIndex, String pathToDescriptionsIndex) {
        this.filter = filter;
        this.query = query;
        this.pathToConceptIdIndex = pathToConceptIdIndex;
        this.pathToDescriptionsIndex = pathToDescriptionsIndex;
    }

    @Override
    public ResponseToQuery executeQuery(Filter filter, String query) throws IllegalStateException {
        ResponseToQuery rtq = new ResponseToQuery("", null);

        rtq.setQuery(query);
        rtq.setResult(filter.executeQuery(query));

        return rtq;
    }

    /**
     * Loads the concepts in memory, mandatory to execute before any search operation can be executed.
     *
     * @return true if the operation has been done successfully, false otherwise.
     */
    @Override
    public boolean loadConceptsInMemory() throws IllegalStateException {
        if(areConceptsLoaded) throw new IllegalStateException("The concepts are already loaded in memory");

        WarmUpMemory warmUpMemoryObject = new BigTableWarmUpMemoryImpl(
                this.pathToConceptIdIndex,
                this.pathToDescriptionsIndex
        );

        warmUpMemoryObject.init();

        areConceptsLoaded = true;
        return true;
    }
}
