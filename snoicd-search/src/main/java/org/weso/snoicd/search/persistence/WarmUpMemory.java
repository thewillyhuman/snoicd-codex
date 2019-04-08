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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

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
	
	public static void init() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		log.info( "Memory warming started." );
		//List<Concept> concepts = new ArrayList<Concept>();
		//ObjectMapper mapper = new ObjectMapper();
		//concepts = mapper.readValue( new File("concepts.json"), mapper.getTypeFactory().constructCollectionType(List.class, Concept.class) );
		
		FileInputStream fis = new FileInputStream("../data/descriptions.index");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SortedMap<String, Set<Concept>> map = (SortedMap<String, Set<Concept>>) ois.readObject();
		
		log.info( "Concepts in memory." );
		log.info( "Loading concepts in big table." );
		
		/*for(Concept c : concepts) {
			if( c.getCode() != null && c.getDescriptions() != null)
				PersistenceImpl.instance.saveConcept( c );
		}*/
		
        PersistenceImpl.instance.getDescriptionIndexMemoryMap().putAll( map );
        ois.close();
        fis.close();
		
		
		log.info( "Concepts in big table." );
		log.info( "Cleaning memory." );
		//concepts = null;
		log.info( "Memory clean." );
	}
}
