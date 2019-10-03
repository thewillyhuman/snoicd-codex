package org.weso.snoicd.search.core;

import org.weso.snoicd.search.persistence.Persistence;

public class ConceptIDSearchStrategy extends AbstractSearchStrategy {

    public ConceptIDSearchStrategy(Persistence persistenceLayer) {
        super(persistenceLayer);
    }

    @Override
    public void run() {
        this.result = super.persistenceLayer.findByCode(this.query);
        this.isResultComputed = true;
    }
}
