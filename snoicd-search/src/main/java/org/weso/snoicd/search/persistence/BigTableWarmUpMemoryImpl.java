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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.weso.snoicd.types.Concept;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Instance of WarmUpMemory.java
 *
 * @author
 */
@Slf4j
public class BigTableWarmUpMemoryImpl implements WarmUpMemory {

    private String descriptionIndexPath, conceptIdIndexPath;

    public BigTableWarmUpMemoryImpl(String descriptionIndexPath, String conceptIdIndexPath) {
        this.descriptionIndexPath = descriptionIndexPath;
        this.conceptIdIndexPath = conceptIdIndexPath;
    }

    @Override
    public boolean init() {
        log.info("Memory warming started.");
        log.info("Loading description index");

        // The map where the diferent indexes will be loaded before adding them to the persistence layer.
        ConcurrentHashMap<String, Set<Concept>> map;

        // Mapper object to translate from the JSON files to the persistence.
        ObjectMapper mapper = new ObjectMapper();

        // Loading the descriptions index files.
        try {
            map = mapper.readValue(new File(
                            this.descriptionIndexPath),
                    new TypeReference<ConcurrentHashMap<String, Set<Concept>>>() {
                    });

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Loading the descriptions index file in the persistence layer.
        BigTablePersistenceImpl.instance.getDescriptionIndexMemoryMap().putAll(map);

        log.info("Loading code index");

        // Loading the concept id index files.
        try {
            map = mapper.readValue(new File(
                            this.conceptIdIndexPath),
                    new TypeReference<ConcurrentHashMap<String, Set<Concept>>>() {
                    });

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Loading the concept id index file in the persistence layer.
        BigTablePersistenceImpl.instance.getIDIndexMemoryMap().putAll(map);

        // Cloaning the map
        map = null;

        log.info("Idexes in memory.");
        log.info("Ready to start up.");
        return true;
    }
}
