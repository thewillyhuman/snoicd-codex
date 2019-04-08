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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;
import java.util.SortedMap;

import org.weso.snoicd.types.Concept;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
	public static void init() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		log.info( "Memory warming started." );
		
		log.info( "Loading description index" );
		FileInputStream fis = new FileInputStream("descriptions.index");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SortedMap<String, Set<Concept>> map = (SortedMap<String, Set<Concept>>) ois.readObject();
        
        PersistenceImpl.instance.getDescriptionIndexMemoryMap().putAll( map );
        ois.close();
        fis.close();
		
        log.info( "Loading code index" );
        fis = new FileInputStream("conceptID.index");
        ois = new ObjectInputStream(fis);
        map = (SortedMap<String, Set<Concept>>) ois.readObject();
        
        PersistenceImpl.instance.getIDIndexMemoryMap().putAll( map );
        ois.close();
        fis.close();
        
		log.info( "Idexes in memory." );
		log.info( "Ready to start up." );
	}
}
