/*
 * This source file is part of the snoicd-indexer open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-indexer project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.indexer.engine;

import java.util.HashSet;

import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.index.IndexEngine;

/**
 * Instance of CodeIndex.java
 * 
 * @author 
 * @version 
 */
public class CodeIndex implements IndexEngine {

	/* (non-Javadoc)
	 * @see io.thewilly.bigtable.index.IndexEngine#index(io.thewilly.bigtable.BigTable, java.lang.Object, java.lang.Object)
	 */
	@Override
	public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
		table.getMemoryMap().put( key, new HashSet<V>() );
		table.getMemoryMap().get( key ).add( value );
		return true;
	}

}
