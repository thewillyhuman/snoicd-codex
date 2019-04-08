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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.weso.snoicd.search.types.Concept;
import org.weso.snoicd.search.utils.StringNormalizator;

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
		// Initialization of the concept index.
		this._condeptIDIndex = new BigTableProducer<String, Concept>()
				.withIndexEngine( new IndexEngine() {
					
					@Override
					public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
						table.getMemoryMap().put( key, new HashSet<V>() );
						table.getMemoryMap().get( key ).add( value );
						return true;
					}
				}).build();
		
		// Initialization of the description index.
		this._conceptDescriptionIndex = new BigTableProducer<String, Concept>()
				.withIndexEngine( new IndexEngine() {
					
					@SuppressWarnings("unchecked")
					@Override
					public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
						String normalizedKey = StringNormalizator.normalize(key.toString());
						String[] keys = normalizedKey.split( " " );
						
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
	public Set<Concept> findByCode( String code ) {
		Set<Concept> set = this._condeptIDIndex.find( code );
		if(set == null)
			set = new HashSet<Concept>();
		return set;
	}

	@Override
	public Set<Concept> findByDescription( String... words ) {
		Arrays.stream( words ).forEach((w) -> w = StringNormalizator.normalize(w) );
		Set<Concept> set = this._conceptDescriptionIndex.findIntersection( words );
		if(set == null)
			set = new HashSet<Concept>();
		return set;
	}

	@Override
	public Set<Concept> search( String query ) {
		Set<Concept> set = findByCode(query);
		
		if(set == null)
			set = new HashSet<Concept>();
		
		String[] words = query.split( " " );
		set.addAll( findByDescription( words ) );
		
		return set;
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
