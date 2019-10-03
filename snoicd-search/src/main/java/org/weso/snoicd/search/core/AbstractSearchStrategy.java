package org.weso.snoicd.search.core;

import org.weso.snoicd.core.Concept;
import org.weso.snoicd.search.persistence.Persistence;

import java.util.Set;

public abstract class AbstractSearchStrategy extends Thread implements SearchStrategy {

    protected Persistence persistenceLayer;
    protected String query;
    protected Set<Concept> result;
    protected boolean isResultComputed = false, isResultReaded = false;

    public AbstractSearchStrategy(Persistence persistenceLayer) {
        this.persistenceLayer = persistenceLayer;
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public Set<Concept> getResult() throws IllegalStateException {
        if(!isResultComputed)
            throw new IllegalStateException("The result has not been computed");

        if(!isResultReaded) {
            try {
                this.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.isResultReaded = true;
        }

        return this.result;
    }
}
