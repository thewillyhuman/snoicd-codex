/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2019 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.weso.snoicd.types.Concept;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Instance of WarmUpMemory.java
 * 
 * @author 
 * @version 
 */
@Slf4j
public class WarmUpMemory {
	
	public static void init() throws JsonParseException, JsonMappingException, IOException {
		log.info( "Memory warming started." );
		List<Concept> concepts = new ArrayList<Concept>();
		ObjectMapper mapper = new ObjectMapper();
		concepts = mapper.readValue( new File("concepts.json"), mapper.getTypeFactory().constructCollectionType(List.class, Concept.class) );
		
		log.info( "Concepts in memory." );
		log.info( "Loading concepts in big table." );
		
		for(Concept c : concepts) {
			if( c.getCode() != null && c.getDescriptions() != null)
				PersistenceImpl.instance.saveConcept( c );
		}
		
		log.info( "Concepts in big table." );
		log.info( "Cleaning memory." );
		concepts = null;
		log.info( "Memory clean." );
	}
}
