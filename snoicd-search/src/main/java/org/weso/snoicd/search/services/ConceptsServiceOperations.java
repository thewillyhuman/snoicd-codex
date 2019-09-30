package org.weso.snoicd.search.services;

import org.weso.snoicd.types.Concept;

import java.util.Set;

public interface ConceptsServiceOperations {

    /**
     * Gets the concept code.
     *
     * @param code to be look for.
     * @return the list of concepts that match the given code.
     */
    Set<Concept> getConceptByCode(String code);

    /**
     * Gets the concept code.
     *
     * @param description to be look for.
     * @return the set of concepts that match the given code.
     */
    Set<Concept> getConceptByDescription(String description);

    /**
     * Gets the concepts to search for.
     *
     * @param query to be executed.
     * @return the list of concepts.
     */
    Set<Concept> getConceptsByAllFields(String query);
}
