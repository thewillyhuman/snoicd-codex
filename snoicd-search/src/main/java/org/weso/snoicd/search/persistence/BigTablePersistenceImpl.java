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

import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.BigTableProducer;
import io.thewilly.bigtable.index.IndexEngine;
import org.weso.snoicd.search.utils.StringNormalizator;
import org.weso.snoicd.types.Concept;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Instance of PersistenceImpl.java
 *
 * @author
 */
public class BigTablePersistenceImpl implements Persistence {

    public static BigTablePersistenceImpl instance = new BigTablePersistenceImpl();

    private BigTable<String, Concept> _condeptIDIndex;
    private BigTable<String, Concept> _conceptDescriptionIndex;

    public Map<String, Set<Concept>> getIDIndexMemoryMap() {
        return this._condeptIDIndex.getMemoryMap();
    }

    public Map<String, Set<Concept>> getDescriptionIndexMemoryMap() {
        return this._conceptDescriptionIndex.getMemoryMap();
    }

    private BigTablePersistenceImpl() {

        // Initialization of the concept index.
        this._condeptIDIndex = new BigTableProducer<String, Concept>().withIndexEngine(IndexEngine.DEFAULT_ENGINE).build();

        // Initialization of the description index.
        this._conceptDescriptionIndex = new BigTableProducer<String, Concept>().withIndexEngine(IndexEngine.DEFAULT_ENGINE).build();
    }

    @Override
    public Set<Concept> findByCode(String code) {
        Set<Concept> set = this._condeptIDIndex.find(code);
        if (set == null)
            set = new HashSet<Concept>();
        return set;
    }

    @Override
    public Set<Concept> findByDescription(String... words) {
        for (int i = 0; i < words.length; i++) words[i] = StringNormalizator.normalize(words[i]);
        Set<Concept> set = this._conceptDescriptionIndex.findIntersection(words);
        if (set == null)
            set = new HashSet<Concept>();
        return set;
    }

    @Override
    public Set<Concept> search(String query) {
        Set<Concept> set = findByCode(query);

        if (set == null)
            set = new HashSet<Concept>();

        String[] words = query.split(" ");
        set.addAll(findByDescription(words));

        return set;
    }

    @Override
    public void saveConcept(Concept concept) {
        this._condeptIDIndex.insert(concept.getCode(), concept);

        for (String description : concept.getDescriptions()) {
            this._conceptDescriptionIndex.insert(description, concept);
        }
    }

    /* (non-Javadoc)
     * @see org.weso.snoicd.repositories.Persistence#removelAll()
     */
    @Override
    public void deleteAll() {
        this._conceptDescriptionIndex.clear();
        this._condeptIDIndex.clear();
    }
}
