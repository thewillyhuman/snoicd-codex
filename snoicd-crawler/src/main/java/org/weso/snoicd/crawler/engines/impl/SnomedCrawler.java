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


import org.bson.Document;
import org.weso.snoicd.crawler.StartUp;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;
import org.weso.snoicd.crawler.types.snomed.SnomedNode;
import org.weso.snoicd.crawler.types.snomed.SnomedRelation;

import com.mongodb.client.MongoCollection;

/**
 * Instance of SnomedCrawler.java
 * 
 * @author
 * @version
 */
public class SnomedCrawler extends AbstractCrawler {

	/*
	 * Allocates a [] object and initializes it so that it represents
	 */
	public SnomedCrawler( String uri, int port, String dbName ) {
		super( uri, port, dbName );
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.crawler.engines.AbstractCrawler#specificCrawl()
	 */
	@Override
	void specificCrawl() {
		// Getting the snomed collection.
		MongoCollection<Document> coll = super.hDB.getCollection( "icd9" );

		AbstractTerminologyNode node;
		
		for (Document doc : coll.find()) {
			
			// Create the node.
			node = new SnomedNode();
			
			// Set its ID
			node.setConceptID( doc.get( "ICD9_CODE" ).toString() );
			
			// Add the description found.
			node.getDescriptions().add(doc.get( "DESCRIPTION" ).toString());
			
			((SnomedNode) node).getRelations().add( new SnomedRelation("new snomed relation", null));
			
			StartUp._nodes.add( node );
		}
	}
}
