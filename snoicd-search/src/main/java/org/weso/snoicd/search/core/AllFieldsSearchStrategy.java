package org.weso.snoicd.search.core;

import org.weso.snoicd.core.Concept;
import org.weso.snoicd.search.persistence.Persistence;

import java.util.Set;

public class AllFieldsSearchStrategy extends AbstractSearchStrategy {

    public AllFieldsSearchStrategy(Persistence persistenceLayer) {
        super(persistenceLayer);
    }

    @Override
    public void run() {
        // Instantiate the search strategies
        AbstractSearchStrategy conceptIdSearch = new ConceptIDSearchStrategy(this.persistenceLayer);
        conceptIdSearch.setQuery(this.query);
        AbstractSearchStrategy descriptionsSearch = new DescriptionSearchStrategy(this.persistenceLayer);
        descriptionsSearch.setQuery(this.query);

        // Init on each task on each thread.
        conceptIdSearch.run();
        descriptionsSearch.run();

        // Join the results.
        this.result = conceptIdSearch.getResult();
        this.result.addAll(descriptionsSearch.getResult());

        this.isResultComputed = true;
    }
}
