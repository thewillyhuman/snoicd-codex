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


import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.weso.snoicd.crawler.StartUp;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;
import org.weso.snoicd.crawler.types.snomed.SnomedNode;

import com.mongodb.client.MongoCollection;
import org.weso.snoicd.crawler.types.snomed.SnomedRelation;
import org.weso.snoicd.crawler.types.snomed.SnomedSimpleNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Instance of SnomedCrawler.java
 * 
 * @author Guillermo Facundo Colunga
 * @version 0.1
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
		MongoCollection<Document> coll = super.hDB.getCollection( "snomed" );

		AbstractTerminologyNode node;

		int iterations = 1;
		
		for (Document doc : coll.find()) {

			if(iterations %100 == 0) System.out.println(iterations);
			if(iterations %10000 == 0) System.out.println(iterations);
			
			// Create the node.
			node = new SnomedNode();
			node.setTerminologyName("SNOMED");
			
			// Set its ID
			node.setConceptID( doc.get( "conceptId" ).toString() );
			
			// Add the description found.
			//node.getDescriptions().add(doc.get( "DESCRIPTION" ).toString());

			List<Document> descriptions = (ArrayList)doc.get("descriptions");

			for(Document d : descriptions) {
				node.getDescriptions().add(d.get("term").toString());
			}

			List<Document> relationships = (ArrayList)doc.get("relationships");

			if(relationships!= null) {
				for(Document r : relationships) {
					String relationType = ((Document)r.get("type")).get("preferredTerm").toString();
					String endOfRelationId = r.get("sourceId").toString();

					((SnomedNode) node).getRelations().add(
							new SnomedRelation(relationType, endOfRelationId));
				}
			}

			//System.out.println(((ArrayList)doc.get("descriptions")).get(0).getClass());

			// Add the relations of the snomed node.
			//((SnomedNode) node).getRelations().add( new SnomedRelation("new snomed relation", null));
			
			StartUp._nodes.put( node.getConceptID(), node );

			iterations++;
		}
	}
}
