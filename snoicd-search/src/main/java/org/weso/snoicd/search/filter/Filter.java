package org.weso.snoicd.search.filter;

import org.weso.snoicd.core.Concept;
import org.weso.snoicd.search.persistence.BigTablePersistenceImpl;
import java.util.Set;

public interface Filter {

    Filter DEFAULT_CONCEPT_ID_FILTER = new ConceptIDFilter(BigTablePersistenceImpl.instance);
    Filter DEFAULT_DESCRIPTIONS_FILTER = new DescriptionFilter(BigTablePersistenceImpl.instance);
    Filter DEFAULT_ALL_FIELDS_FILTER = new AllFieldsFilter(BigTablePersistenceImpl.instance);

    /**
     * Executes the query over the filter operation.
     *
     * @return the result of executing the query over the filter.
     */
    Set<Concept> executeQuery(String query);
}
