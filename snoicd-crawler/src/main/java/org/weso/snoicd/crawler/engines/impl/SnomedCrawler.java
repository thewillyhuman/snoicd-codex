/*
 * This source file is part of the snoicd-crawler open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-crawler project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.crawler.engines.impl;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;

import com.mongodb.client.MongoCollection;

/**
 * Instance of SnomedCrawler.java
 * 
 * @author
 * @version
 */
public class SnomedCrawler extends AbstractCrawler {

	List<AbstractTerminologyNode> crawledNodes;

	/*
	 * Allocates a [] object and initializes it so that it represents
	 */
	public SnomedCrawler( String uri, int port, String dbName ) {
		super( uri, port, dbName );
		this.crawledNodes = new LinkedList<AbstractTerminologyNode>();
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.crawler.engines.AbstractCrawler#specificCrawl()
	 */
	@Override
	void specificCrawl() {
		// Getting the snomed collection.
		MongoCollection<Document> coll = super.hDB.getCollection( "snomed" );

		for (Document doc : coll.find()) {
			System.out.println( doc );
		}
	}

}
