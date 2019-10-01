package org.weso.snoicd.search.filter;

import org.weso.snoicd.search.persistence.Persistence;

public abstract class AbstractFilter implements Filter {

    Persistence persistenceLayer;

    public AbstractFilter(Persistence persistenceLayer) {
        this.persistenceLayer = persistenceLayer;
    }
}
