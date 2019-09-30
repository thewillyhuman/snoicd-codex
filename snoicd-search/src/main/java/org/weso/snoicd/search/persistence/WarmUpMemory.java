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

import java.io.*;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.weso.snoicd.types.Concept;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

/**
 * Instance of WarmUpMemory.java
 * 
 * @author 
 * @version 
 */
@Slf4j
public class WarmUpMemory {
	
	@SuppressWarnings("unchecked")
	public static void init() throws IOException, ClassNotFoundException {
	    log.info( "Memory warming started." );
		log.info( "Loading description index" );

		// The map where the diferent indexes will be loaded before adding them to the persistence layer.
        ConcurrentHashMap<String, Set<Concept>> map;

        // Mapper object to translate from the JSON files to the persistence.
        ObjectMapper mapper = new ObjectMapper();

        // Loading the descriptions index files.
        map = mapper.readValue(new File(
                "descriptionsIndex.json"), new TypeReference<ConcurrentHashMap<String, Set<Concept>>>() {
        });

        // Loading the descriptions index file in the persistence layer.
        BigTablePersistenceImpl.instance.getDescriptionIndexMemoryMap().putAll( map );
		
        log.info( "Loading code index" );

        // Loading the concept id index files.
        map = mapper.readValue(new File(
                "conceptIDIndex.json"), new TypeReference<ConcurrentHashMap<String, Set<Concept>>>() {
        });

        // Loading the concept id index file in the persistence layer.
        BigTablePersistenceImpl.instance.getIDIndexMemoryMap().putAll( map );

        // Cloaning the map
        map = null;
        
		log.info( "Idexes in memory." );
		log.info( "Ready to start up." );
	}
}
