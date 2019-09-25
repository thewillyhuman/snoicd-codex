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
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.weso.snoicd.types.Concept;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;

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
		ProgressBar pb = new ProgressBar("Loading description index", 100);
		FileInputStream fis = new FileInputStream("descriptions.index");
		pb.stepTo( 25 );
        ObjectInputStream ois = new ObjectInputStream(fis);
        pb.stepTo( 50 );
        ConcurrentMap<String, Set<Concept>> map = (ConcurrentHashMap<String, Set<Concept>>) ois.readObject();
        pb.stepTo( 100 );
        pb.close();
        
        BigTablePersistenceImpl.instance.getDescriptionIndexMemoryMap().putAll( map );
        ois.close();
        fis.close();
		
        log.info( "Loading code index" );
        pb = new ProgressBar("Loading concepts id index", 100);
        fis = new FileInputStream("conceptID.index");
        pb.stepTo( 25 );
        ois = new ObjectInputStream(fis);
        pb.stepTo( 50 );
        map = (ConcurrentHashMap<String, Set<Concept>>) ois.readObject();
        pb.stepTo( 100 );
        pb.close();
        
        BigTablePersistenceImpl.instance.getIDIndexMemoryMap().putAll( map );
        ois.close();
        fis.close();
        
		log.info( "Idexes in memory." );
		log.info( "Ready to start up." );
	}
}
