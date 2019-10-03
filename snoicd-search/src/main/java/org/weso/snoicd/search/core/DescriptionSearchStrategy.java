package org.weso.snoicd.search.core;

import org.weso.snoicd.search.persistence.Persistence;

public class DescriptionSearchStrategy extends AbstractSearchStrategy {

    public DescriptionSearchStrategy(Persistence persistenceLayer) {
        super(persistenceLayer);
    }

    @Override
    public void run() {
        String[] words = query.split(" ");
        this.result = super.persistenceLayer.findByDescription(words);
        this.isResultComputed = true;
    }
}
