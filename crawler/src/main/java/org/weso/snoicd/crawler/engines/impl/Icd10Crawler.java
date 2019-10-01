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
import org.weso.snoicd.crawler.types.icd.ICDVersion;
import org.weso.snoicd.crawler.types.icd.IcdNode;

import com.mongodb.client.MongoCollection;

/**
 * Instance of Icd10Crawler.java
 * 
 * @author
 * @version
 */
public class Icd10Crawler extends AbstractCrawler {

	public Icd10Crawler( String uri, int port, String dbName ) {
		super( uri, port, dbName );
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.crawler.engines.AbstractCrawler#specificCrawl()
	 */
	@Override
	void specificCrawl() {
		// Getting the snomed collection.
		MongoCollection<Document> coll = super.hDB.getCollection( "icd10" );

		AbstractTerminologyNode node;

		for (Document doc : coll.find()) {

			// Create the node.
			node = new IcdNode();
			node.setTerminologyName("ICD");

			// Set its ID
			node.setConceptID( doc.get( "Full Code" ).toString() );

			// Add the description found.
			node.getDescriptions().add( doc.get( "Full Description" ).toString() );

			// Set the version.
			( (IcdNode) node ).setVersion( ICDVersion.V_10 );

			StartUp._nodes.put( node.getConceptID(), node );
		}
	}

}
