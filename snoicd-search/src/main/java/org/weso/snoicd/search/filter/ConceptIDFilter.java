package org.weso.snoicd.search.filter;

import org.weso.snoicd.core.Concept;
import org.weso.snoicd.search.persistence.Persistence;

import java.util.Set;

public class ConceptIDFilter extends AbstractFilter {

    public ConceptIDFilter(Persistence persistenceLayer) {
        super(persistenceLayer);
    }

    @Override
    public Set<Concept> executeQuery(String query) {
        return super.persistenceLayer.findByCode(query);
    }
}
