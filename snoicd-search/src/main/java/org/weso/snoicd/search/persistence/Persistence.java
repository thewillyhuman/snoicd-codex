/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2019 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 *
 */
package org.weso.snoicd.search.persistence;

import org.weso.snoicd.core.Concept;

import java.util.Set;

/**
 * Instance of Persistence.java
 *
 * @author
 */
public interface Persistence {

    /**
     * Finds the set of concepts that has as a code the given one. Usually the set
     * will contain one or none elements as the code must be unique.
     *
     * @param code to look for.
     * @return the set of concepts that are identified with the given code.
     */
    Set<Concept> findByCode(String code);

    /**
     * Finds the set of concepts that have on its descriptions the given words.
     *
     * @param words to look for in the descriptions.
     * @return the set of concepts that have on its descriptions the given words.
     */
    Set<Concept> findByDescription(String[] words);

    /**
     * Main entry-point for an uncontrolled search where we want to look by
     * description and code.
     *
     * @param query is the set of strings to look for in the code or the
     *              descriptions.
     * @return
     */
    Set<Concept> findByAllFields(String query);

    /**
     * Saves a concept in the persistence layer.
     *
     * @param concept to save
     */
    void saveConcept(Concept concept);

    /**
     * Removes all concepts. It is implement for test proposes.
     */
    void deleteAll();
}
