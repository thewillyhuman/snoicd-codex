/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2019 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.weso.snoicd.types.Concept;

import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.BigTableProducer;
import io.thewilly.bigtable.index.IndexEngine;

/**
 * Instance of PersistenceImpl.java
 * 
 * @author 
 * @version 
 */
public class PersistenceImpl implements Persistence {
	
	public static PersistenceImpl instance = new PersistenceImpl();
	
	private BigTable<String, Concept> _condeptIDIndex;
	private BigTable<String, Concept> _conceptDescriptionIndex;
	
	private PersistenceImpl() { 
		this._condeptIDIndex = new BigTableProducer<String, Concept>()
				.withIndexEngine( new IndexEngine() {
					
					@Override
					public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
						table.getMemoryMap().put( key, new HashSet<V>() );
						table.getMemoryMap().get( key ).add( value );
						return true;
					}
				}).build();
		
		
		
		this._conceptDescriptionIndex = new BigTableProducer<String, Concept>()
				.withIndexEngine( new IndexEngine() {
					
					@SuppressWarnings("unchecked")
					@Override
					public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
						String[] keys = key.toString().split( " " );
						
						for(String ikey : keys) {
							if (ikey.length() >= 3) {
								ikey = ikey.toLowerCase();
								if (table.getMemoryMap().containsKey(ikey)) {
									table.getMemoryMap().get(ikey).add(value);
								} else {
									table.getMemoryMap().put((K) ikey, new HashSet<V>());
									table.getMemoryMap().get(ikey).add(value);
								}
							}
						}
						
						return true;
					}
				} ).build();
		
	} // Singleton.

	@Override
	public List<Concept> findByCode( String code ) {
		List<Concept> ret = new ArrayList<Concept>();
		System.out.println( "Search by code give as a result: " + this._condeptIDIndex.find( code ) );
		Set<Concept> set = this._condeptIDIndex.find( code );
		if(set != null)
			ret.addAll( set );
		return ret;
	}

	@Override
	public List<Concept> findByDescription( String... words ) {
		List<Concept> ret = new ArrayList<Concept>();
		Arrays.stream( words ).forEach( String::toLowerCase );
		System.out.println(  "Search by description give as a result: " + this._conceptDescriptionIndex.findIntersection( words ) );
		Set<Concept> set = this._conceptDescriptionIndex.findIntersection( words );
		ret.addAll( set );
		return ret;
	}

	@Override
	public List<Concept> search( String query ) {
		Set<Concept> set = new HashSet<Concept>();
		
		set.addAll( findByCode(query) );
		String[] words = query.split( " " );
		set.addAll( findByDescription( words ) );
		
		List<Concept> ret = new ArrayList<Concept>();
		ret.addAll( set );
		return ret;
	}

	@Override
	public void saveConcept(Concept concept) {
		this._condeptIDIndex.insert( concept.getCode(), concept );
		
		for(String description : concept.getDescriptions()) {
			this._conceptDescriptionIndex.insert( description, concept );
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
