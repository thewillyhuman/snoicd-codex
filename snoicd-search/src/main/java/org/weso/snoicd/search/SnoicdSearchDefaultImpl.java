package org.weso.snoicd.search;

import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.core.AbstractSearchStrategy;
import org.weso.snoicd.search.core.SearchStrategy;
import org.weso.snoicd.search.persistence.BigTableWarmUpMemoryImpl;
import org.weso.snoicd.search.persistence.WarmUpMemory;

import java.util.HashSet;

public class SnoicdSearchDefaultImpl implements SnoicdSearch {

    private String pathToConceptIdIndex, pathToDescriptionsIndex;

    private boolean areConceptsLoaded = false;

    public SnoicdSearchDefaultImpl(String pathToConceptIdIndex, String pathToDescriptionsIndex) {
        this.pathToConceptIdIndex = pathToConceptIdIndex;
        this.pathToDescriptionsIndex = pathToDescriptionsIndex;
    }

    @Override
    public ResponseToQuery executeQuery(AbstractSearchStrategy searchStrategy, String query) throws IllegalStateException {
        ResponseToQuery rtq = new ResponseToQuery("", new HashSet<>());

        rtq.setQuery(query);
        searchStrategy.setQuery(query);
        searchStrategy.run();
        rtq.setResult(searchStrategy.getResult());

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
