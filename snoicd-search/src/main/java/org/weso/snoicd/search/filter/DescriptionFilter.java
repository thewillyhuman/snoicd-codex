package org.weso.snoicd.search.filter;

import org.weso.snoicd.core.Concept;
import org.weso.snoicd.search.persistence.Persistence;

import java.util.Set;

public class DescriptionFilter extends AbstractFilter {


    public DescriptionFilter(Persistence persistenceLayer) {
        super(persistenceLayer);
    }

    @Override
    public Set<Concept> executeQuery(String query) {
        String[] words = query.split(" ");
        return super.persistenceLayer.findByDescription(words);
    }
}
